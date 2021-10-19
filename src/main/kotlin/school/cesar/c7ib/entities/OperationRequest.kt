package school.cesar.c7ib.entities

import school.cesar.c7ib.dtos.ContaDTO

data class OperationRequest(
    val cliente: Cliente? = null,
    val agencia: Agencia? = null,
    val contaDTO: ContaDTO? = null
)


