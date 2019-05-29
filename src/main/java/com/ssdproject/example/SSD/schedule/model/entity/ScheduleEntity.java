package com.ssdproject.example.SSD.schedule.model.entity;

import com.ssdproject.example.SSD.conference.model.entity.ConferenceEntity;
import com.ssdproject.example.SSD.schedule.model.enums.ScheduleStatus;
import com.ssdproject.example.SSD.shared.model.entity.AbstractEntity;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "schedules")
public class ScheduleEntity extends AbstractEntity {

    @OneToOne
    private ConferenceEntity conference;

    @Enumerated(EnumType.STRING)
    @Column
    private ScheduleStatus status = ScheduleStatus.IN_PROGRESS;

    @OneToMany
    private List<ScheduleItemEntity> presentationsAndPosters;
}
