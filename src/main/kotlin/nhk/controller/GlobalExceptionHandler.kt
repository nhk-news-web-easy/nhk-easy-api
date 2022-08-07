package nhk.controller

import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class GlobalExceptionHandler {
    private val logger = LoggerFactory.getLogger(GlobalExceptionHandler::class.java)

    @ExceptionHandler
    fun handleRuntimeException(e: Throwable): ResponseEntity<String> {
        logger.error("Controller exception", e)

        return ResponseEntity<String>("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR)
    }
}