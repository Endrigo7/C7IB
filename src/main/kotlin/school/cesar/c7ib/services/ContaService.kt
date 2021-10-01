package school.cesar.c7ib.services

import school.cesar.c7ib.dtos.ContaDTO
import school.cesar.c7ib.entities.Agencia
import school.cesar.c7ib.entities.Conta
import school.cesar.c7ib.respositories.ContasRepository
import school.cesar.c7ib.util.DataUtil
import school.cesar.c7ib.validators.ContaValidator
import kotlin.random.Random

class ContaService {

    private val contaValidator = ContaValidator()

    private fun gerarNumero(): String {
        val sequencial: String = montaSequencial()
        val data = formatoDataNumeroConta()
        val digito = Random.nextInt(99)
        return "$sequencial$data-$digito"
    }

    private fun montaSequencial() =
        all()
            .lastOrNull()
            ?.let {
                (it.numero.substring(0, 4).toInt() + 1)
                    .toString()
                    .padStart(4, '0')
            } ?: "0001"

    private fun formatoDataNumeroConta(): String {
        val now = DataUtil.dataHoraAtual()
        return "${now.year}${now.month}${now.dayOfMonth}${now.hour}${now.minute}${now.second}${now.nano}"
    }

    private fun add(conta: Conta) {
        ContasRepository.add(conta)
    }

    fun criar(conta: ContaDTO) {
        contaValidator.validaAbertura(conta)
        

    }

    fun buscar(agencia: Agencia, numero: String) =
        ContasRepository.buscar(agencia, numero)

    fun all() =
        ContasRepository.all()

}