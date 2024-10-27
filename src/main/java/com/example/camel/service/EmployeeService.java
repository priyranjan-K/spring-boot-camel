package com.example.camel.service;


import com.example.camel.util.Utils;
import com.example.camel.model.CustomException;
import com.example.camel.model.EmployeeDetails;
import com.example.camel.model.Response;
import com.example.camel.repository.EmployeeDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.camel.model.CustomException.*;

@Service
public class EmployeeService {

    private final EmployeeDetailsRepository repository;

    @Autowired
    EmployeeService(EmployeeDetailsRepository repository) {
        this.repository = repository;
    }

    public ResponseEntity<Response> saveEmployeeRecord(EmployeeDetails employeeDetails) {
        try {
            repository.save(employeeDetails);
            return Utils.prepareResponseEntity("Record saved Successfully.", "SAVE-202", HttpStatus.CREATED);
        } catch (Exception e) {
            throw RECORD_FAILED;
        }
    }

    public ResponseEntity<List<EmployeeDetails>> fetchAllReorcd() {
        try {
            List<EmployeeDetails> res = (List<EmployeeDetails>) repository.findAll();
            if (!res.isEmpty()) {
                return Utils.prepareResponseEntity(res, HttpStatus.OK);
            } else {
                throw new CustomException("No record found", "NO_RECORD_FOUND-200");
            }

        } catch (CustomException exception) {
            throw exception;
        } catch (Exception e) {
            throw FETCH_FAILED;
        }
    }


}
