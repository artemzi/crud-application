package com.artemzi.controllers.advices;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;

@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
public class RestExceptionsControllerAdvice {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * Дефолтный обработчик ошибок
     */
    @ExceptionHandler(Exception.class)
    public @ResponseBody
    ResponseEntity exceptionHandler(Exception e, HttpServletResponse response) {
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        logException(e);
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    private void logException(Exception e) {
        String errorMsg;
        if (e.getCause() != null)
            errorMsg = String.format("%s: %s [cause: %s: %s]", e.getClass().getSimpleName(), e.getMessage(),
                    e.getCause().getClass().getSimpleName(), e.getCause().getMessage());
        else errorMsg = String.format("%s: %s", e.getClass().getSimpleName(), e.getMessage());
        logger.warn(errorMsg);
    }
}
