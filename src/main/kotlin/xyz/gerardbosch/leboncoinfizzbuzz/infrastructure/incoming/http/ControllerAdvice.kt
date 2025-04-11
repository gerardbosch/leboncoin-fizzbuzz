package xyz.gerardbosch.leboncoinfizzbuzz.infrastructure.incoming.http

import org.springframework.http.HttpStatus.BAD_REQUEST
import org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import xyz.gerardbosch.leboncoinfizzbuzz.infrastructure.incoming.http.api.ErrorResp

@RestControllerAdvice
class ControllerAdvice {

  @ExceptionHandler(Throwable::class)
  fun handle(th: Throwable): ResponseEntity<ErrorResp> {
    return ResponseEntity
      .status(INTERNAL_SERVER_ERROR)
      .body(errorResp(th))
  }

  @ExceptionHandler(IllegalArgumentException::class)
   fun handle(ex: IllegalArgumentException): ResponseEntity<ErrorResp> {
     return ResponseEntity
       .status(BAD_REQUEST)
       .body(errorResp(ex))
   }

  private fun errorResp(th: Throwable) = ErrorResp(th.message ?: msgUnexpectedErr)
}

private const val msgUnexpectedErr = "Unexpected error"
