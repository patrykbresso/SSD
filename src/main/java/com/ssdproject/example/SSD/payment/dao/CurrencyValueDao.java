package com.ssdproject.example.SSD.payment.dao;

import com.ssdproject.example.SSD.payment.model.entity.CurrencyValueEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyValueDao extends JpaRepository<CurrencyValueEntity, Long> {
}
