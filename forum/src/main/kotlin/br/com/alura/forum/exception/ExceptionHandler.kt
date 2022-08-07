package br.com.alura.forum.exception

import br.com.alura.forum.dto.ErrorView
import org.springframework.http.HttpStatus
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import javax.servlet.http.HttpServletRequest

// Classe que vai fazer os tratamentos das Exceptions dos RestControllers. Quando ocorrer algum erro em alguma classe anotada com o RestController,
// o Spring vai procurar aqui dentro se tem algum tratamento de acordo com a Exception lançada.
@RestControllerAdvice
class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(NotFoundException::class) // Spring, quando a NotFoundException ocorrer, chame esse método.
    @ResponseStatus(HttpStatus.NOT_FOUND) // E devolva o status 404.
    fun handleNotFound(
        exception: NotFoundException,
        request: HttpServletRequest // O próprio Spring vai nos informar o Path.
    ): ErrorView {
        return ErrorView(
            status = HttpStatus.NOT_FOUND.value(),
            error = HttpStatus.NOT_FOUND.name,
            message = exception.message,
            path = request.servletPath
        )
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(Exception::class) // Quando ocorrer uma Exception Genérica (qualquer tipo), vai cair nesse cara:
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    fun handleServerError(
        exception: Exception,
        request: HttpServletRequest
    ): ErrorView {
        return ErrorView(
            status = HttpStatus.INTERNAL_SERVER_ERROR.value(),
            error = HttpStatus.INTERNAL_SERVER_ERROR.name,
            message = exception.message,
            path = request.servletPath
        )
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(MethodArgumentNotValidException::class) // Tratamento relacionados aos Bean Validations.
    @ResponseStatus(HttpStatus.BAD_REQUEST) // Status 400, pois o erro ocorreu devido à inserção incorreta dos dados pelo cliente.
    fun handleValidationError(
        exception: MethodArgumentNotValidException,
        request: HttpServletRequest
    ): ErrorView {

        val errorMessage = HashMap<String, String?>() // Coleção de dados <Chave, Valor>.
        exception.bindingResult.fieldErrors.forEach {// Devolve uma lista de erros. A percorre e a cada elemento e faz um put no HashMap.
            e -> errorMessage.put(e.field, e.defaultMessage)
        }

        return ErrorView(
            status = HttpStatus.BAD_REQUEST.value(),
            error = HttpStatus.BAD_REQUEST.name,
            message = errorMessage.toString(),
            path = request.servletPath
        )
    }

    fun outraException() {
        //TODO implements
    }
}