package com.wemakesoftware.navigation.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Error {

    String message;

    int code;


}
