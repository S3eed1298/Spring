package com.luv2code.springboot.cruddemo.exception;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EmployeeErrorResponse {
    private int status;
    private String message;
    private long timeStamp;
}
