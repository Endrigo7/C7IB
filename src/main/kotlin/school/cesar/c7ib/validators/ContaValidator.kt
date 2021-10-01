package school.cesar.c7ib.validators

import school.cesar.c7ib.dtos.ContaDTO

class ContaValidator {

    fun validaAbertura(conta: ContaDTO) {
        validaCamposObrigatorios(conta)
        validaTamanhoCampos(conta)
        validaFormatoCampos(conta)
        validateCliente(conta)
        validaSaldoMinimo(conta)
    }
}
