package school.cesar.c7ib.validators

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import school.cesar.c7ib.entities.Cliente
import school.cesar.c7ib.exceptions.ClienteInvalidoException
import java.time.LocalDate

class ClienteValidatorTest {

    private val clienteValidator = ClienteValidator()
    private val cliente = Cliente("32464074012", "Jair", LocalDate.of(1981, 8, 12))

    @Test
    fun `deve lancar excecao quando nome for vazio`() {
        assertThrows<ClienteInvalidoException> {
            clienteValidator.valida(cliente.copy(nome = ""))
        }.also {
            assertEquals("O nome deve ser preenchido", it.message)
        }
    }

}