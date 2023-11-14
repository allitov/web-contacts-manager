package com.allitov.webcontactsmanager.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.security.InvalidParameterException;

@ControllerAdvice
@Slf4j
public class ExceptionControllerAdvice {

    @ExceptionHandler(NoSuchRecordException.class)
    public String exceptionNoSuchRecordHandler(NoSuchRecordException exception) {
        log.info(exception.getMessage());

        return "redirect:/";
    }

    @ExceptionHandler(InvalidParameterException.class)
    public String exceptionInvalidParameterHandler(InvalidParameterException exception) {
        log.info(exception.getMessage());

        return "redirect:/";
    }
}
