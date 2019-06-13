package com.ssdproject.example.SSD.conference.dao;

import com.ssdproject.example.SSD.conference.model.entity.ConferenceInformationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConferenceInformationDao extends JpaRepository<ConferenceInformationEntity, Long> {
}
