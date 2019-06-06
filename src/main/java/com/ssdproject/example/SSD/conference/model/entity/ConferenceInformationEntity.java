package com.ssdproject.example.SSD.conference.model.entity;

import com.ssdproject.example.SSD.shared.model.entity.AbstractEntity;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Builder
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


    public ConferenceInformationEntity(String topic, String description, int maxNumberOfPresentations, int maxNumberOfSeats) {
        this.topic = topic;
        this.description = description;
        this.maxNumberOfPresentations = maxNumberOfPresentations;
        this.maxNumberOfSeats = maxNumberOfSeats;
    }

    public ConferenceInformationEntity() {
    }
}
