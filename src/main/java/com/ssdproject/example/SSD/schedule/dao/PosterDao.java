package com.ssdproject.example.SSD.schedule.dao;

import com.ssdproject.example.SSD.schedule.model.entity.PosterEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PosterDao extends JpaRepository<PosterEntity, Long> {
}
