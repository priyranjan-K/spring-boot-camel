package com.example.camel.controller;

import com.example.camel.model.*;
import com.example.camel.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/sample")
public class EmployeeController {

    private final EmployeeService service;
    private final JmsTemplate jmsTemplate;

    @Autowired
    EmployeeController(EmployeeService service, JmsTemplate jmsTemplate) {
        this.service = service;
        this.jmsTemplate = jmsTemplate;
    }

    @PostMapping(value = "saveEmployeeDetails", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> saveEmployeeDetails(@RequestBody @Valid EmployeeDetails employeeDetails) {
        return service.saveEmployeeRecord(employeeDetails);
    }

    @GetMapping(value = "fetchAllEmpoyeeDetails", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<EmployeeDetails>> fetchAllEmpoyeeDetails() {
        return service.fetchAllReorcd();
    }


    @Scheduled(fixedDelay = 5000)
    public void trigerActiveMQ() {
        EmployeeDetails employeeDetails = EmployeeDetails.builder()
                .name("John").
                address(Address.builder().state(State.BIHAR).country(Country.INDIA).houseNumber(123).
                        nearestArea("kanak").houseName("Jannat").pincode(Pincode.ELC).build()).build();
        jmsTemplate.convertAndSend("activemq:sendPostRequest", employeeDetails);
    }
}
