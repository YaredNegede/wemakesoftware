package com.wemakesoftware.navigation.exceptions;

public class NavigationException extends RuntimeException{

    public static final String NOT_FOUND = "Base station not found";

    public NavigationException(String message) {
        super(message);
    }
}
