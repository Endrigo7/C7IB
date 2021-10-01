package school.cesar.c7ib.respositories

import school.cesar.c7ib.entities.Agencia

object AgenciaRepository {

    private val agencias = mutableListOf<Agencia>()

    fun add(agencia: Agencia){
        agencias.add(agencia)
    }

    fun buscar(numero: Int) =
        agencias.filter { it.numero == numero }

    fun all() =
        agencias

}
