package com.ssdproject.example.SSD.schedule.model.entity;

import com.ssdproject.example.SSD.shared.model.entity.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "schedule_items")
public class ScheduleItemEntity extends AbstractEntity {

    @Column(nullable = false)
    private String topic;

    @Column(nullable = false)
    private String description;

    private String remarks;

    @Column(nullable = false)
    private String localizationDetail;

    @Column(nullable = false)
    private LocalDateTime startDate;

    @Column(nullable = false)
    private LocalDateTime endDate;

    @OneToMany(fetch = FetchType.EAGER)
    private List<PosterEntity> posters;

    @OneToOne
    private PresentationEntity presentation;
}
