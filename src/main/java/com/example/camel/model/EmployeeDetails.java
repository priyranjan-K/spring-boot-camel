package com.example.camel.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Data
@Builder
@Entity
@Table(name = "employeeDetails")
public class EmployeeDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long employee_id;

    @NotBlank(message = "Employee's name can not be blank.")
    @Max(message = "Employee's name can not have more than 20 character.", value = 20)
    @Column(name = "name", length = 20)
    private String name;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

}
