package com.arabsoft.pfe.projet.exception;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BusinessException extends Exception {

    public BusinessException(String code, String message) {
        super(message);
        this.code = code;
    }

    private String code;
}
