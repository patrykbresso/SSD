package com.ssdproject.example.SSD.auth.dao;

import com.ssdproject.example.SSD.auth.model.entity.users.GuestEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuestDao extends JpaRepository<GuestEntity, Long> {

    GuestEntity findByEmail(String email);
}
