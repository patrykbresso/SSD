package com.ssdproject.example.SSD.payment.model.entity;

import com.ssdproject.example.SSD.payment.model.enums.Currency;
import com.ssdproject.example.SSD.payment.model.enums.ValueType;
import com.ssdproject.example.SSD.shared.model.entity.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
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


}
