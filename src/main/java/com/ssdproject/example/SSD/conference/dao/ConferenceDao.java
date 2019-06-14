package com.ssdproject.example.SSD.conference.dao;

import com.ssdproject.example.SSD.conference.model.entity.ConferenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConferenceDao extends JpaRepository<ConferenceEntity, Long> {
}
