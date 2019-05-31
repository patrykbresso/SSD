package com.ssdproject.example.SSD.review.model.entity;

import com.ssdproject.example.SSD.shared.model.entity.AbstractEntity;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Builder
@Entity
@Table(name = "remarks")
public class RemarkEntity extends AbstractEntity {

    @Column
    private String remark;
}
