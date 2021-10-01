package school.cesar.c7ib.dtos

import java.math.BigDecimal

data class ContaDTO(
    val cpf: String,
    val valorInicial: BigDecimal,
    val senha: String,
    val cofirmacaoSenha: String
)
