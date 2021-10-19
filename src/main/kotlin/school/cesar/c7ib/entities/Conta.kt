package school.cesar.c7ib.entities

import java.math.BigDecimal

data class Conta(
    val agencia: Agencia,
    val numero: String,
    val cliente: Cliente,
    val tipoConta: TipoConta,
    val senha: String,
    val saldo: BigDecimal
) {
    fun creditar(valor: BigDecimal) {
        saldo.add(valor)
    }

    fun debitar(valor: BigDecimal) {
        saldo.subtract(valor)
    }

    fun transferir(valor: BigDecimal, destino: Conta) {
        debitar(valor)
        destino.creditar(valor)
    }
}
