package com.example.xml.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@ControllerAdvice(annotations = RestController.class)
public class AdviceController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<Object> notFoundEx(RuntimeException runtimeException, WebRequest webRequest) {
        log.info(runtimeException.fillInStackTrace().toString());
        String[] body = runtimeException.getMessage().split(":");
        return handleExceptionInternal(runtimeException, body[body.length - 1].trim(), new HttpHeaders(), HttpStatus.BAD_REQUEST, webRequest);
    }
}
