package com.factoria.moments.controllers;

import com.factoria.moments.dtos.ErrorDto;
import com.factoria.moments.exceptions.BadRequestException;
import com.factoria.moments.exceptions.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<ErrorDto> runtimeExceptionHandler(RuntimeException ex) {
        var error = ErrorDto.builder()
                .code("E-001")
                .message(ex.getMessage())
                .build();

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<ErrorDto> notFoundExceptionHandler(NotFoundException ex) {
        var error = ErrorDto.builder()
                .code(ex.getCode())
                .message(ex.getMessage())
                .build();

        return new ResponseEntity<>(error, ex.getHttpStatus());
    }

    @ExceptionHandler(value = BadRequestException.class)
    public ResponseEntity<ErrorDto> badRequestExceptionHandler(BadRequestException ex) {
        var error = ErrorDto.builder()
                .code(ex.getCode())
                .message(ex.getMessage())
                .build();

        return new ResponseEntity<>(error, ex.getHttpStatus());
    }
}
