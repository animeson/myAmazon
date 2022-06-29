package com.amazon.svistun.dto.sales;

import lombok.Data;

@Data
public class CustomerDto {
    private String firstName;
    private String lastName;
    private String city;
    private String street;
    private String state;
    private String zipCode;
    private String email;
    private String phone;
}
