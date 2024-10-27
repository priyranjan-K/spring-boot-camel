package com.example.camel.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Data
@Builder
@Entity
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank(message = "HouseName can not be blank.")
    private String houseName;

    @NotBlank(message = "HouseNumber can not be blank.")
    private int houseNumber;

    @NotBlank(message = "Nearest Area can not be blank.")
    private String nearestArea;

    @NotBlank(message = "Pincode can not be blank.")
    @Enumerated(EnumType.STRING)
    private Pincode pincode;

    @NotBlank(message = "State can not be blank.")
    @Enumerated(EnumType.STRING)
    private State state;

    @NotBlank(message = "Country can not be blank.")
    @Enumerated(EnumType.STRING)
    private Country country;

    @OneToOne(mappedBy = "address")
    private EmployeeDetails employeeDetails;

}