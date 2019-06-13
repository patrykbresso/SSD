package com.ssdproject.example.SSD.schedule.dao;

import com.ssdproject.example.SSD.schedule.model.entity.ScheduleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleDao extends JpaRepository<ScheduleEntity, Long> {
}
