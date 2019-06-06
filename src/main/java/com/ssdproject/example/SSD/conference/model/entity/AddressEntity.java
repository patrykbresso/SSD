package com.ssdproject.example.SSD.conference.model.entity;

import com.ssdproject.example.SSD.conference.model.enums.Country;
import com.ssdproject.example.SSD.shared.model.entity.AbstractEntity;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

@Data
@Builder
@Entity
@Table(name = "address")
public class AddressEntity extends AbstractEntity {

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String postalCode;

    @Column(nullable = false)
    private String street;

    @Column(nullable = false)
    private String streetNumber;

    @Column
    private String description;

    public AddressEntity(String city, String postalCode, String street, String streetNumber, String description, Country country) {
        this.city = city;
        this.postalCode = postalCode;
        this.street = street;
        this.streetNumber = streetNumber;
        this.description = description;
        this.country = country;
    }

    public AddressEntity() {
    }

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Country country;
}
