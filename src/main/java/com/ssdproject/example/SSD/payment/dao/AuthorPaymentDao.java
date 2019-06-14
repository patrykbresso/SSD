package com.ssdproject.example.SSD.payment.dao;

import com.ssdproject.example.SSD.payment.model.entity.AuthorPaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorPaymentDao extends JpaRepository<AuthorPaymentEntity, Long> {
}
