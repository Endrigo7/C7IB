package school.cesar.c7ib.respositories

import school.cesar.c7ib.entities.Agencia
import school.cesar.c7ib.entities.Conta

object ContasRepository {

    private val contas = mutableListOf<Conta>()

    fun add(conta: Conta) {
        contas.add(conta)
    }

    fun buscar(agencia: Agencia, numero: String) =
        contas.filter {
            it.agencia == agencia &&
                    it.numero == numero
        }

    fun all() =
        contas
}
