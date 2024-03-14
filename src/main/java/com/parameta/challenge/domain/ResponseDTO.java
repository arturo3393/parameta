package com.parameta.challenge.domain;

import lombok.Data;

@Data
public class ResponseDTO<T> {
    private boolean answer;
    private String msg;
    private T data = null;
    private String employeeAge;
    private String workedTime;
}

