package com.wemakesoftware.navigation.exceptions;

public class NavigationException extends RuntimeException{

    public static final String NOT_FOUND = "Base station not found";

    public static final int NOT_FOUND_CODE = 1;

    public NavigationException(String message) {
        super(message);
    }
}
