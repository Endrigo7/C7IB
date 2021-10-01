package school.cesar.c7ib.services

import school.cesar.c7ib.entities.Cliente
import school.cesar.c7ib.respositories.ClienteRepository
import school.cesar.c7ib.validators.ClienteValidator

class ClienteService {

    private val clienteValidator = ClienteValidator()

    fun add(cliente: Cliente) {
        clienteValidator.validate(cliente)
        ClienteRepository.add(cliente)
    }

    fun buscar(cpf: String) =
        ClienteRepository.buscar(cpf)

    fun all() =
        ClienteRepository.all()
}
