package com.mailson.pereira.caju.web.exceptionhandler

import com.fasterxml.jackson.databind.ObjectMapper
import com.mailson.pereira.caju.input.exception.AccountNotFoundException
import com.mailson.pereira.caju.input.exception.CustomerNotFoundException
import com.mailson.pereira.caju.input.exception.InvalidMovementBalanceTypeException
import com.mailson.pereira.caju.input.exception.InvalidMovementValueException
import com.mailson.pereira.caju.web.exceptionhandler.dto.GenericException
import com.mailson.pereira.caju.web.exceptionhandler.dto.GenericExceptionFieldError
import org.springframework.context.MessageSource
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.MissingServletRequestParameterException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.time.LocalDateTime

@ControllerAdvice
class ExceptionHandler(
    private val messageSource: MessageSource,
    private val objectMapper: ObjectMapper
): ResponseEntityExceptionHandler() {

    // hadndle invalid movement value
    @ExceptionHandler(value = [InvalidMovementValueException::class])
    protected fun handleInvalidMovementValueException(
        exception: InvalidMovementValueException,
        request: WebRequest
    ): ResponseEntity<GenericException>{
        val genericException = getGenericException(
            HttpStatus.NOT_FOUND.value(),
            "No active active accounts",
            exception.message!!
        )

        return ResponseEntity(genericException, HttpStatus.NOT_FOUND)
    }

    // handle invalid type exception
    @ExceptionHandler(value = [InvalidMovementBalanceTypeException::class])
    protected fun handleInvalidMovementBalanceTypeException(
        exception: InvalidMovementBalanceTypeException,
        request: WebRequest
    ): ResponseEntity<GenericException>{
        val genericException = getGenericException(
            HttpStatus.NOT_FOUND.value(),
            "Invalid movement type",
            exception.message!!
        )

        return ResponseEntity(genericException, HttpStatus.NOT_FOUND)
    }

    // hadndle customer not found
    @ExceptionHandler(value = [AccountNotFoundException::class])
    protected fun handleAccountNotFoundException(
        exception: AccountNotFoundException,
        request: WebRequest
    ): ResponseEntity<GenericException>{
        val genericException = getGenericException(
            HttpStatus.NOT_FOUND.value(),
            "No active active accounts",
            exception.message!!
        )

        return ResponseEntity(genericException, HttpStatus.NOT_FOUND)
    }

    // hadndle customer not found
    @ExceptionHandler(value = [CustomerNotFoundException::class])
    protected fun handleCustomerNotFoundException(
        exception: CustomerNotFoundException,
        request: WebRequest
    ): ResponseEntity<GenericException>{
        val genericException = getGenericException(
            HttpStatus.NOT_FOUND.value(),
            "Customer not found",
            exception.message!!
        )

        return ResponseEntity(genericException, HttpStatus.NOT_FOUND)
    }

    // request with no bbody
    override fun handleHttpMessageNotReadable(
        ex: HttpMessageNotReadableException,
        headers: HttpHeaders,
        status: HttpStatusCode,
        request: WebRequest): ResponseEntity<Any>? {

        val genericException = getGenericException(
            HttpStatus.BAD_REQUEST.value(),
            "Message not readable",
            "Check the body of the request to be a valid request"
        )

        return handleExceptionInternal(
            ex,
            objectMapper.writeValueAsString(genericException),
            HttpHeaders(),
            HttpStatus.BAD_REQUEST,
            request
        )
    }

    override fun handleMethodArgumentNotValid(
        ex: MethodArgumentNotValidException,
        headers: HttpHeaders,
        status: HttpStatusCode,
        request: WebRequest
    ): ResponseEntity<Any>? {
        val errors = ex.allErrors.map {
            val error = it as FieldError

            GenericExceptionFieldError(
                field = error.field,
                message = error.defaultMessage ?: "error"
            )
        }

        val genericException = getGenericException(
            HttpStatus.BAD_REQUEST.value(),
            "Request is not valid",
            "Check the parameters for a valid request",
            errors
        )

        return handleExceptionInternal(
            ex,
            objectMapper.writeValueAsString(genericException),
            HttpHeaders(),
            HttpStatus.BAD_REQUEST,
            request
        )
    }

    override fun handleMissingServletRequestParameter(
        ex: MissingServletRequestParameterException,
        headers: HttpHeaders,
        status: HttpStatusCode,
        request: WebRequest
    ): ResponseEntity<Any>? {
        val genericException = getGenericException(
            HttpStatus.BAD_REQUEST.value(),
            "Request is not valid",
            ex.message
        )

        return handleExceptionInternal(
            ex,
            objectMapper.writeValueAsString(genericException),
            HttpHeaders(),
            HttpStatus.BAD_REQUEST,
            request
        )
    }

    fun getGenericException(
        statusCode: Int,
        messageTitle: String,
        messageDetail: String,
        fieldErrors: List<GenericExceptionFieldError>? = null
    ): GenericException {
        return GenericException(
            timestamp = LocalDateTime.now().toString(),
            statusCode = statusCode,
            messageTitle = messageTitle,
            messageDetail = messageDetail,
            fieldErrors = fieldErrors
        )
    }
}