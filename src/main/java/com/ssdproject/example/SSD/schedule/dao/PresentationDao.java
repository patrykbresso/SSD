package com.ssdproject.example.SSD.schedule.dao;

import com.ssdproject.example.SSD.schedule.model.entity.PresentationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PresentationDao extends JpaRepository<PresentationEntity, Long> {
}
