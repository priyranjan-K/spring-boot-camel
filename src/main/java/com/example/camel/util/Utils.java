package com.example.camel.util;

import com.example.camel.model.EmployeeDetails;
import com.example.camel.model.Response;
import lombok.experimental.UtilityClass;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.util.List;

@UtilityClass
public class Utils {


    public static ResponseEntity<Response> prepareResponseEntity(String message, String messageCode, HttpStatus httpStatus) {
        Response response = new Response();
        response.setMessage(message);
        response.setMessageCode(messageCode);
        return ResponseEntity.status(httpStatus).header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).body(response);
    }

    public static ResponseEntity<List<EmployeeDetails>> prepareResponseEntity(List<EmployeeDetails> response, HttpStatus httpStatus) {

        return ResponseEntity.status(httpStatus).header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).body(response);
    }
}
