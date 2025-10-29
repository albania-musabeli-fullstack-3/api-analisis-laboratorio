package com.musabeli.api_laboratorio.exceptions;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ValidationErrorResponse {

    private String field;
    private String message;
    private String rejectedValue;
}
