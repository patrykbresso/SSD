package com.ssdproject.example.SSD.schedule.model.entity;

import com.ssdproject.example.SSD.schedule.model.enums.ScheduleStatus;
import com.ssdproject.example.SSD.shared.model.entity.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "schedules")
public class ScheduleEntity extends AbstractEntity {

    @Enumerated(EnumType.STRING)
    @Column
    private ScheduleStatus status = ScheduleStatus.IN_PROGRESS;

    @OneToMany(fetch = FetchType.EAGER)
    private List<ScheduleItemEntity> presentationsAndPosters;
}
