package com.wemakesoftware.navigation;

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
    public ResponseEntity.HeadersBuilder<?> handleRuntimeException(RuntimeException exception) {
        return ResponseEntity.noContent();
    }

}
