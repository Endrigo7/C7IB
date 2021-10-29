package school.cesar.c7ib.validators

import com.school.cesar.cib.util.SenhaUtil
import school.cesar.c7ib.dtos.ContaDTO
import school.cesar.c7ib.exceptions.ContaInvalidaException
import school.cesar.c7ib.respositories.ClienteRepository
import school.cesar.c7ib.util.CPFUtil
import java.math.BigDecimal

class ContaValidator {

    fun validaAbertura(conta: ContaDTO) {
        validaCamposObrigatorios(conta)
        validaTamanhoCampos(conta)
        validaFormatoCampos(conta)
        validaCliente(conta)
        validaSaldoInicial(conta)
        validaSenha(conta)
    }

    private fun validaCamposObrigatorios(conta: ContaDTO) {
        if (conta.cpf.isBlank()) {
            throw ContaInvalidaException("O cpf deve ser preenchido")
        }

        if (conta.senha.isBlank()) {
            throw ContaInvalidaException("A senha deve ser preenchido")
        }

        if (conta.confirmacaoSenha.isBlank()) {
            throw ContaInvalidaException("A confirmação da senha deve ser preenchido")
        }
    }

    private fun validaTamanhoCampos(conta: ContaDTO) {
        if (conta.cpf.length != 11) {
            throw ContaInvalidaException("O campo cpf deve ter 11 caracteres numericos")
        }

        if (conta.senha.length !in 8..15) {
            throw ContaInvalidaException("O campo senha deve ter entre 8 e 15 caracteres")
        }

        if (conta.confirmacaoSenha.length !in 8..15) {
            throw ContaInvalidaException("O campo confirmação senha deve ter entre 8 e 15 caracteres")
        }
    }

    private fun validaFormatoCampos(conta: ContaDTO) {
        if (!CPFUtil.isCPF(conta.cpf)) {
            throw ContaInvalidaException("O cpf é invalido")
        }

        if (!SenhaUtil.isFormatoOK(conta.senha)) {
            throw ContaInvalidaException("O a senha deve conter numeros, letras maisculas e minusculas")
        }
    }

    private fun validaCliente(conta: ContaDTO) {
        takeIf { ClienteRepository.buscar(conta.cpf).isEmpty() }
            ?.apply {
                throw ContaInvalidaException("Não existe cliente cadastrado para o cpf ${conta.cpf}")
            }
    }

    private fun validaSaldoInicial(conta: ContaDTO) {
        takeIf { conta.valorInicial < BigDecimal(200) }
            ?.apply {
                throw ContaInvalidaException("Saldo inicial deve ser maior que 200")
            }
    }

    private fun validaSenha(conta: ContaDTO) {
        takeIf { conta.senha != conta.confirmacaoSenha }
            ?.apply {
                throw ContaInvalidaException("A senha deve ser igual a confirmacao")
            }
    }
}
