package com.ssdproject.example.SSD.conference.model.to;

import com.ssdproject.example.SSD.conference.model.enums.Country;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@Setter
public class AddressTO {

    private Long id;
    private String city;
    private String postalCode;
    private String street;
    private String streetNumber;
    private String description;
    private Country country;
}
