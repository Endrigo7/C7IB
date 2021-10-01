package school.cesar.c7ib.entities

import java.time.LocalDateTime

data class Cliente(
    val cpf: String,
    val nome: String,
    val dataNascimento: LocalDateTime
)
