package school.cesar.c7ib.dtos

import school.cesar.c7ib.entities.Agencia
import java.math.BigDecimal

data class ContaDTO(
    val agencia: Agencia,
    val numero: String,
    val tipoConta: Int,
    val cpf: String,
    val valorInicial: BigDecimal,
    val senha: String,
    val confirmacaoSenha: String
)
