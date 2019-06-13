package com.ssdproject.example.SSD.auth.dao;

import com.ssdproject.example.SSD.auth.model.entity.users.ReviewerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewerDao extends JpaRepository<ReviewerEntity, Long> {

    ReviewerEntity findByEmail(String email);
}
