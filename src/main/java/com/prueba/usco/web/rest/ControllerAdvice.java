package com.prueba.usco.web.rest;

import com.prueba.usco.web.rest.errors.BadRequestException;
import com.prueba.usco.web.rest.errors.ErrorDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({"unchecked","rawtypes"})
@RestControllerAdvice
public class ControllerAdvice {

    protected Logger logger;

    public ControllerAdvice() {
        logger = LoggerFactory.getLogger(getClass());
    }

    @ExceptionHandler(value = BadRequestException.class)
    public ResponseEntity<ErrorDTO> requestExceptionHandler(BadRequestException ex){
        return new ResponseEntity<>(ErrorDTO.builder().code(ex.getCode()).message(ex.getMessage()).build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ SQLException.class })
    public String databaseError(Exception exception) {
        logger.error("Request raised " + exception.getClass().getSimpleName());
        return "Error Data Base Connection";
    }

    @ExceptionHandler(ServletRequestBindingException.class)
    public final ResponseEntity<Object> handleHeaderException(Exception ex, WebRequest request)
    {
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        ErrorDTO error = ErrorDTO.builder().code("Bad Request").message(details.toString()).build();
        return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
    }
}
