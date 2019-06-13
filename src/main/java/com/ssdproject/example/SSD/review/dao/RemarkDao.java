package com.ssdproject.example.SSD.review.dao;

import com.ssdproject.example.SSD.review.model.entity.RemarkEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RemarkDao extends JpaRepository<RemarkEntity, Long> {
}
