package credit.application.system.exceptions

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.FieldError
import org.springframework.validation.ObjectError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.time.LocalDateTime

@RestControllerAdvice
class RestExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun validExceptionHandler(exception: MethodArgumentNotValidException): ResponseEntity<ExceptionDetails> {
        val errors: MutableMap<String, String?> = HashMap()
        exception.bindingResult.allErrors.stream().forEach {
            error: ObjectError ->
            val fieldName: String = (error as FieldError).field
            val message: String? = error.defaultMessage
            errors[fieldName] = message
        }

        return ResponseEntity(
            ExceptionDetails(
                title = "Bad Request",
                timestamp = LocalDateTime.now(),
                status = HttpStatus.BAD_REQUEST.value(),
                exception = exception.objectName,
                details = errors
            ), HttpStatus.BAD_REQUEST
        )
    }

}