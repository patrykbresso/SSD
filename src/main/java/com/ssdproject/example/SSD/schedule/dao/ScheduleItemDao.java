package com.ssdproject.example.SSD.schedule.dao;

import com.ssdproject.example.SSD.schedule.model.entity.ScheduleItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleItemDao extends JpaRepository<ScheduleItemEntity, Long> {
}
