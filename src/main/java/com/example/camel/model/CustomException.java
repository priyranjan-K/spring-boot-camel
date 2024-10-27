package com.example.camel.model;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@EqualsAndHashCode(callSuper = true)
public class CustomException extends RuntimeException {

    public final static CustomException RECORD_FAILED = new CustomException("Internal Error Occurred. Record Could not be saved.", "ERROR-400");
    public final static CustomException FETCH_FAILED = new CustomException("Unable to fetch record. Please try after sometime", "ERROR-404");

    private String errorMessage;

    private String errorCode;

    public CustomException(String exceptionMessage) {
        super(exceptionMessage);
        this.errorMessage = exceptionMessage;
    }

    public CustomException(String errorMessage, String errorCode) {
        super(errorMessage);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;

    }


}
