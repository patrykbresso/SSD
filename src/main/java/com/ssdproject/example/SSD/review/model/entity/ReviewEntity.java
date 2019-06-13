package com.ssdproject.example.SSD.review.model.entity;

import com.ssdproject.example.SSD.auth.model.entity.users.ReviewerEntity;
import com.ssdproject.example.SSD.review.model.enums.ReviewStatus;
import com.ssdproject.example.SSD.schedule.model.entity.PosterEntity;
import com.ssdproject.example.SSD.schedule.model.entity.PresentationEntity;
import com.ssdproject.example.SSD.shared.model.entity.AbstractEntity;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@Entity
@Table(name = "reviews")
public class ReviewEntity extends AbstractEntity {

    @ManyToOne
    private ReviewerEntity reviewer;

    @ManyToOne
    private PosterEntity poster;

    @ManyToOne
    private PresentationEntity presentation;

    @Enumerated(EnumType.STRING)
    @Column
    private ReviewStatus status;

    @Column(nullable = false)
    private LocalDateTime reviewDueDate;

    @Column
    private LocalDateTime reviewFinishDate;

    @OneToMany(cascade = CascadeType.ALL)
    private List<RemarkEntity> remarks;
}
