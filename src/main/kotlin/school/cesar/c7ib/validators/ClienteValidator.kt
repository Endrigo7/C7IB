package school.cesar.c7ib.validators

import com.school.cesar.cib.util.CPFUtil
import school.cesar.c7ib.entities.Cliente
import school.cesar.c7ib.exceptions.ClienteInvalidoException

class ClienteValidator {

    fun valida(cliente: Cliente) {
        validaCamposObrigatorios(cliente)
        validaTamanhoCampos(cliente)
        validaFormatoCampos(cliente)
    }

    fun validaCamposObrigatorios(cliente: Cliente) {
        if (cliente.nome.isBlank()) {
            throw ClienteInvalidoException("O nome deve ser preenchido")
        }

        if (cliente.cpf.isBlank()) {
            throw ClienteInvalidoException("O cpf deve ser preenchido")
        }
    }

    fun validaTamanhoCampos(cliente: Cliente) {
        if (cliente.nome.length > 200) {
            throw ClienteInvalidoException("O campo nome deve ter menos de 200 caracteres")
        }

        if (cliente.cpf.length != 11) {
            throw ClienteInvalidoException("O campo cpf deve ter 11 caracteres numericos")
        }
    }

    fun validaFormatoCampos(cliente: Cliente) {
        if (!CPFUtil.isCPF(cliente.cpf)) {
            throw ClienteInvalidoException("O cpf é invalido")
        }
    }
}
