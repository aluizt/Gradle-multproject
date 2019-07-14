package br.com.estagio.testesgradle.pagination.exceptions;

import br.com.estagio.testesgradle.pagination.exceptions.InvalidPageException;
import br.com.estagio.testesgradle.user.exception.StandardError;
import br.com.estagio.testesgradle.user.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class PaginationExceptionHadler {
    @ExceptionHandler(InvalidPageException.class)
    public ResponseEntity<StandardError> paginErro(InvalidPageException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError err = new StandardError(System.currentTimeMillis(),
                status.value(),
                "pagina invalida",
                e.getMessage(),
                request.getRequestURI());

        return ResponseEntity.status(status).body(err);
    }
}
