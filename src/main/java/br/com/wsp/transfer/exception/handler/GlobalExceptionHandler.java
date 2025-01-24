package br.com.wsp.transfer.exception.handler;

import br.com.wsp.transfer.dto.ApiError;
import br.com.wsp.transfer.exception.IllegalScheduleDateAndValueException;
import br.com.wsp.transfer.exception.TransferBadRequestException;
import br.com.wsp.transfer.exception.TransferNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> genericException(Exception ex) {


        ApiError apiError = new ApiError(LocalDateTime.now(), HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.name(), List.of(ex.getMessage()));

        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TransferBadRequestException.class)
    public ResponseEntity<ApiError> badRequestException(Exception ex) {

        ApiError apiError = new ApiError(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.name(), List.of(ex.getMessage()));

        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TransferNotFoundException.class)
    public ResponseEntity<ApiError> notFoundException(Exception ex) {

        ApiError apiError = new ApiError(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.name(), List.of(ex.getMessage()));

        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IllegalScheduleDateAndValueException.class)
    public ResponseEntity<ApiError> illegalSheduleDateAndValueException(Exception ex) {

        ApiError apiError = new ApiError(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.name(), List.of(ex.getMessage()));

        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

}