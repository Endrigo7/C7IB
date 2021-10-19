package school.cesar.c7ib.controllers

import school.cesar.c7ib.adapters.OperationRequestAdapter.toOperationRequest
import school.cesar.c7ib.entities.OperationRequest
import school.cesar.c7ib.services.ClienteService
import school.cesar.c7ib.services.ContaService

object OperationsController {

    private val clienteService = ClienteService()
    private val contaService = ContaService()

    fun init() {
        val operationsJson = readOperations()
        val operations = buildOperationsRequestList(operationsJson)
        println(operations)

        handleOperations(operations)
    }

    private fun readOperations() =
        generateSequence(::readLine).toList()

    private fun buildOperationsRequestList(operationsJson: List<String>) =
        operationsJson.map { toOperationRequest(it) }

    private fun handleOperations(listOperationsRequest: List<OperationRequest>) {
        listOperationsRequest.forEach { operationRequest ->

            val operationResponse =
                operationRequest.cliente?.let {
                    clienteService.add(it)
                } ?: operationRequest.agencia?.let {

                } ?: operationRequest.contaDTO?.let {
                    contaService.criar(it)
                }

            //operationResponse?.apply { writeOut(this) }
        }
        println(clienteService.all())
    }

//    private fun writeOut(operationResponse: OperationResponse) {
//        val operationResponseJson = toJson(operationResponse)
//        println(operationResponseJson)
//    }
}