package com.example.gestiondestock.handlers;

import com.example.gestiondestock.exception.ErrorCodes;
import lombok.Builder;

import java.util.ArrayList;
import java.util.List;

@Builder
public class ErrorDto {

    private Integer httpCode;
    private ErrorCodes errorCodes;
    private String message;
    private List<String> errors = new ArrayList<>();
}
