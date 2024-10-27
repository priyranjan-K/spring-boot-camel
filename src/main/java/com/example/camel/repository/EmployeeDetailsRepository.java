package com.example.camel.repository;

import com.example.camel.model.EmployeeDetails;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EmployeeDetailsRepository extends CrudRepository<EmployeeDetails, UUID> {

}
