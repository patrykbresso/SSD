package com.ssdproject.example.SSD.payment.dao;

import com.ssdproject.example.SSD.payment.model.entity.GuestPaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuestPaymentDao extends JpaRepository<GuestPaymentEntity, Long> {
}
