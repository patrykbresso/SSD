package com.ssdproject.example.SSD.review.dao;

import com.ssdproject.example.SSD.review.model.entity.ReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewDao extends JpaRepository<ReviewEntity, Long> {
}
