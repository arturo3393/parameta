package com.parameta.challenge.exceptions;

import lombok.Data;

@Data
public class CustomErrorResponse {
    private String timestamp;
    private int status;
    private String error;
    private String message;
    private String path;
}
