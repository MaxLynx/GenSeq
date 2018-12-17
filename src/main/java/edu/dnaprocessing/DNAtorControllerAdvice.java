package edu.dnaprocessing;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice("annotations = ExceptionHandling.class")
public class DNAtorControllerAdvice {

	@ExceptionHandler
    public Exception handle(Exception ex) {
        return ex;
    }
}
