package com.spring.vault.customerservice.exception;

import com.spring.vault.customerservice.model.CustomerResponse;
import lombok.Data;

@Data
public class CustomerNotFoundException extends RuntimeException{
    private String errorCode;
    public CustomerNotFoundException(String message, String errorCode){
        super(message);
        this.errorCode = errorCode;
    }
}
