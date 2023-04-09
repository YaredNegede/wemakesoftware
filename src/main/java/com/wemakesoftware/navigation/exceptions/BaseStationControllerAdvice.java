package com.wemakesoftware.navigation.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class BaseStationControllerAdvice {

    /**
     * this needs to be implemented base on specific event that happened
     * @param exception
     * @return
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Error> handleRuntimeException(RuntimeException exception) {
        return ResponseEntity.badRequest().body(new Error(NavigationException.NOT_FOUND,NavigationException.NOT_FOUND_CODE));
    }

}
