package com.ssdproject.example.SSD.conference.model.entity;

import com.ssdproject.example.SSD.auth.model.entity.users.AuthorEntity;
import com.ssdproject.example.SSD.auth.model.entity.users.GuestEntity;
import com.ssdproject.example.SSD.auth.model.entity.users.OrganiserEntity;
import com.ssdproject.example.SSD.schedule.model.entity.ScheduleEntity;
import com.ssdproject.example.SSD.shared.model.entity.AbstractEntity;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "conferences")
public class ConferenceEntity extends AbstractEntity {

    @Column(nullable = false)
    private LocalDateTime startDate;

    @Column(nullable = false)
    private LocalDateTime endDate;

    @Column(nullable = false)
    private LocalDateTime registrationDate;

    @Column
    private boolean cancelled = false;

    @OneToOne
    @JoinColumn(name = "information_id")
    private ConferenceInformationEntity conferenceInformation;

    @ManyToMany(mappedBy = "conferences")
    private List<OrganiserEntity> organisers;

    @ManyToMany(mappedBy = "conferences")
    private List<AuthorEntity> authors;

    @ManyToMany(mappedBy = "conferences")
    private List<GuestEntity> guests;

    @OneToOne
    private ScheduleEntity schedule;

    @OneToOne
    private AddressEntity address;
}
