package school.cesar.c7ib.dtos

import school.cesar.c7ib.entities.Agencia
import school.cesar.c7ib.entities.Cliente

data class OperationDTO(
    val cliente: Cliente? = null,
    val agencia: Agencia? = null,
    val contaDTO: ContaDTO? = null
)


