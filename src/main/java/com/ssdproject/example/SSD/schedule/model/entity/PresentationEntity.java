package com.ssdproject.example.SSD.schedule.model.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "presentations")
public class PresentationEntity extends ExhibitionItemEntity {
}
