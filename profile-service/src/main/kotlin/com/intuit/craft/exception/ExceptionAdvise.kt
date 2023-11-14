package com.intuit.craft.exception

import com.intuit.craft.model.ErrorMessage
import jakarta.servlet.http.HttpServletRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.MessageSource
import org.springframework.context.i18n.LocaleContextHolder
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class ExceptionAdvise @Autowired constructor(
    private val messageSource: MessageSource
) {

    @ExceptionHandler(value = [ProfileExistsException::class])
    fun badRequestHandler(
        exception: ProfileExistsException,
        request: HttpServletRequest
    ): ResponseEntity<ErrorMessage> {
        return ResponseEntity.status(exception.msg.code).body(
            ErrorMessage(
                code = exception.msg.code.value(),
                type = ErrorTypes.BAD_REQUEST.value,
                uri = request.requestURI,
                message = messageSource.getMessage(exception.msg.message, null, LocaleContextHolder.getLocale())
            )
        )
    }

    @ExceptionHandler(value = [ProfileNotFoundException::class])
    fun notFoundHandler(exception: ProfileExistsException, request: HttpServletRequest): ResponseEntity<ErrorMessage> {
        return ResponseEntity.status(exception.msg.code).body(
            ErrorMessage(
                code = exception.msg.code.value(),
                type = ErrorTypes.NOT_FOUND.value,
                uri = request.requestURI,
                message = messageSource.getMessage(exception.msg.message, null, LocaleContextHolder.getLocale())
            )
        )
    }
}
