package com.ssdproject.example.SSD.payment.model.entity;

import com.ssdproject.example.SSD.payment.model.enums.Currency;
import com.ssdproject.example.SSD.payment.model.enums.ValueType;
import com.ssdproject.example.SSD.shared.model.entity.AbstractEntity;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Builder
@Entity
@Table(name = "currency_value")
public class CurrencyValueEntity extends AbstractEntity {

    @Column(nullable = false)
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Currency currency;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ValueType type;

    public CurrencyValueEntity(BigDecimal amount, Currency currency, ValueType type) {
        this.amount = amount;
        this.currency = currency;
        this.type = type;
    }

    public CurrencyValueEntity() {
    }
}
