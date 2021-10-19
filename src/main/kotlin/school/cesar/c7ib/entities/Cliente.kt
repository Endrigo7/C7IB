package school.cesar.c7ib.entities

import java.time.LocalDate

data class Cliente(
    val cpf: String,
    val nome: String,
    val dataNascimento: LocalDate
)
