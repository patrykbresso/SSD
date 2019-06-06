package com.ssdproject.example.SSD.payment.model.entity;

import com.ssdproject.example.SSD.payment.model.enums.PaymentStatus;
import com.ssdproject.example.SSD.shared.model.entity.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public abstract class PaymentEntity extends AbstractEntity {

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentStatus status = PaymentStatus.NEW;

    @Column(nullable = false)
    private LocalDateTime dueDate;

    @Column
    private LocalDateTime paymentDate;

    @Column
    private LocalDateTime returnDate;

    @ManyToOne
    private CurrencyValueEntity currencyValue;
}
