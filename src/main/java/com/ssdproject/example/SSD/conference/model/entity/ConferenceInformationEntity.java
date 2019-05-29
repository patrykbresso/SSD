package com.ssdproject.example.SSD.conference.model.entity;

import com.ssdproject.example.SSD.shared.model.entity.AbstractEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "information")
public class ConferenceInformationEntity extends AbstractEntity {

    @Column(nullable = false)
    private String topic;

    @Column
    private String description;

    @Column(nullable = false)
    private int maxNumberOfPresentations;

    @Column(nullable = false)
    private int maxNumberOfSeats;
}
