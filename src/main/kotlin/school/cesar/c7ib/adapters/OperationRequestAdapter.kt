package school.cesar.c7ib.adapters

import school.cesar.c7ib.dtos.OperationDTO
import school.cesar.c7ib.util.ObjectMapperBuilder

object OperationRequestAdapter {

    fun toOperationRequest(operationJson: String): OperationDTO =
        ObjectMapperBuilder.build().readValue(operationJson, OperationDTO::class.java)
}