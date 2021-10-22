package school.cesar.c7ib.controllers

import school.cesar.c7ib.adapters.OperationRequestAdapter.toOperationRequest
import school.cesar.c7ib.dtos.OperationDTO
import school.cesar.c7ib.services.ClienteService
import school.cesar.c7ib.services.ContaService

object OperationsController {

    private val clienteService = ClienteService()
    private val contaService = ContaService()

    fun init() {
        val operationsJson = readOperations()
        println("lista de string: $operationsJson")

        val operations = buildOperationsRequestList(operationsJson)
        println("lista de operationRequest: $operations")

        handleOperations(operations)
    }

    private fun readOperations() =
        generateSequence(::readLine).toList()

    private fun buildOperationsRequestList(operationsJson: List<String>) =
        operationsJson.map { toOperationRequest(it) }

    private fun handleOperations(listOperationsRequest: List<OperationDTO>) {
        listOperationsRequest.forEach { operationRequest ->

            val operationRequest =
                operationRequest.cliente?.apply {
                    clienteService.add(this)
                } ?: operationRequest.agencia?.apply {

                } ?: operationRequest.contaDTO?.apply {
                    contaService.criar(this)
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