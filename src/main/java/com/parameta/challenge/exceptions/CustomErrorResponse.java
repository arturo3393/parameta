package com.parameta.challenge.exceptions;

import lombok.Data;

/**
 * Entity class representing the API costume exceptions responses
 */
@Data
public class CustomErrorResponse {
    private String timestamp;
    private int status;
    private String error;
    private String message;
    private String path;
}
