package school.cesar.c7ib.adapters

import school.cesar.c7ib.entities.OperationRequest
import school.cesar.c7ib.util.ObjectMapperBuilder

object OperationRequestAdapter {

    fun toOperationRequest(operationJson: String): OperationRequest =
        ObjectMapperBuilder.build().readValue(operationJson, OperationRequest::class.java)
}