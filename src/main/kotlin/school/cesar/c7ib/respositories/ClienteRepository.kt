package school.cesar.c7ib.respositories

import school.cesar.c7ib.entities.Cliente

object ClienteRepository {

    private val clientes = mutableListOf<Cliente>()

    fun add(cliente: Cliente){
        clientes.add(cliente)
    }

    fun buscar(cpf: String) =
        clientes.filter { it.cpf == cpf }

    fun all() =
        clientes
}
