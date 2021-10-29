package school.cesar.c7ib.validators

import org.junit.jupiter.api.*
import school.cesar.c7ib.controllers.OperationsController
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.PrintStream

class OperationsControllerClienteTest {

    private val capturedOut = ByteArrayOutputStream()
    private val standardOut = System.out

    @BeforeEach
    fun beforeEach(fileName: TestInfo) {
        initializeCaptureStdout()
        initializeStdinWithFile(fileName.tags.first())
    }

    private fun initializeCaptureStdout() {
        System.setOut(PrintStream(capturedOut))
    }

    private fun initializeStdinWithFile(fileName: String) {
        val uri = javaClass.classLoader.getResource(fileName)
        val file = File(uri.path).inputStream()
        System.setIn(file)
    }

    @AfterEach
    fun afterEach() {
        System.setOut(PrintStream(standardOut))
    }

    @Test
    @Tag("just-clientes.txt")
    fun `deve salvar cliente quando cliente for valido`() {
        OperationsController.init()

        val expectedResult = "[Cliente(cpf=32464074012, nome=Joao, dataNascimento=1980-10-09)]"
        val obtainedResult = capturedOut.toString().trim()

        Assertions.assertEquals(expectedResult, obtainedResult)
    }


}