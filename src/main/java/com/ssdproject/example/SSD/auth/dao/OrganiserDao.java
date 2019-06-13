package com.ssdproject.example.SSD.auth.dao;

import com.ssdproject.example.SSD.auth.model.entity.users.OrganiserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrganiserDao extends JpaRepository<OrganiserEntity, Long> {

    OrganiserEntity findByEmail(String email);
}
