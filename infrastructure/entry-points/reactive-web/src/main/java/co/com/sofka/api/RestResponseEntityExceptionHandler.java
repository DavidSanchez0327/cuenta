package co.com.sofka.api;

import co.com.sofka.api.dto.ErrorResponseDto;
import co.com.sofka.model.exception.TechnicalException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(TechnicalException.class)
    protected ResponseEntity<Object> handleConflict(
            TechnicalException ex, WebRequest request) {
        ErrorResponseDto responseDto = ErrorResponseDto.builder()
                .code(HttpStatus.CONFLICT.value())
                .message(ex.getMessage())
                .build();
        return handleExceptionInternal(ex, responseDto,
                new HttpHeaders(), HttpStatus.CONFLICT, request);
    }


}
