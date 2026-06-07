package com.platformcommons.assignment.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddressDTO {

    private String addressType;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String state;
    private String country;
    private String pinCode;
}