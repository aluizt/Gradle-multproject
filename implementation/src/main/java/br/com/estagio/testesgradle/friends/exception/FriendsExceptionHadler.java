package br.com.estagio.testesgradle.friends.exception;

import br.com.estagio.testesgradle.user.exception.StandardError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class FriendsExceptionHadler {
    @ExceptionHandler(FrindsNotFoundException.class)
    public ResponseEntity<StandardError> userNotFound(FrindsNotFoundException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError err = new StandardError(System.currentTimeMillis(),
                status.value(),
                "não encontrado",
                e.getMessage(),
                request.getRequestURI());

        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(ValidadeFriendsException.class)
    public ResponseEntity<StandardError> validateUser(ValidadeFriendsException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError err = new StandardError(System.currentTimeMillis(),
                status.value(),
                "formato dos dados errados",
                e.getMessage(),
                request.getRequestURI());

        return ResponseEntity.status(status).body(err);
    }

}
