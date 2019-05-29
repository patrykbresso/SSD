package com.ssdproject.example.SSD.review.model.entity;

import com.ssdproject.example.SSD.review.model.enums.ReviewStatus;
import com.ssdproject.example.SSD.schedule.model.entity.PosterEntity;
import com.ssdproject.example.SSD.schedule.model.entity.PresentationEntity;
import com.ssdproject.example.SSD.shared.model.entity.AbstractEntity;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "reviews")
public class ReviewEntity extends AbstractEntity {

    @ManyToOne
    private ReviewEntity review;

    @ManyToOne
    private PosterEntity poster;

    @ManyToOne
    private PresentationEntity presentation;

    @Enumerated(EnumType.STRING)
    @Column
    private ReviewStatus status;

    @Column(nullable = false)
    private LocalDateTime reviewDueDate;

    @Column(nullable = false)
    private LocalDateTime reviewFinishDate;

    @OneToMany
    private List<RemarkEntity> remarks;
}
