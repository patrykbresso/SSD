package com.ssdproject.example.SSD.conference.model.entity;

import com.ssdproject.example.SSD.shared.model.entity.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
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
}
