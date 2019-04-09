package io.github.mavaze.crxmarkets.exception;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.*;

@Slf4j
@ControllerAdvice
public class ExceptionHandlerAdvice {

    @ResponseStatus(NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public void notFoundExceptionHandler(@NonNull final NotFoundException e){
        log.error(e.getLocalizedMessage());
    }

    @ResponseStatus(INTERNAL_SERVER_ERROR)
    @ExceptionHandler(RuntimeException.class)
    public void handleRuntimeException(RuntimeException e) {
        log.error("Internal server error occurred: [{}]", e.getMessage(), e);
    }

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public void handleJsonConversionException(HttpMessageNotReadableException e) {
        log.trace("Invalid json payload received: [{}]", e.getMessage(), e);
    }
}
