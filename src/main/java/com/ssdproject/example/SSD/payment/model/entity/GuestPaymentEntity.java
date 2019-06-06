package com.ssdproject.example.SSD.payment.model.entity;

import com.ssdproject.example.SSD.auth.model.entity.users.GuestEntity;
import com.ssdproject.example.SSD.conference.model.entity.ConferenceEntity;
import com.ssdproject.example.SSD.payment.model.enums.PaymentStatus;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
@Table(name = "guest_payments")
public class GuestPaymentEntity extends PaymentEntity {

    @ManyToOne
    private GuestEntity guest;

    @ManyToOne
    private ConferenceEntity conference;

    @Builder
    public GuestPaymentEntity(PaymentStatus status, LocalDateTime dueDate, LocalDateTime paymentDate, LocalDateTime returnDate, CurrencyValueEntity currencyValue, GuestEntity guest, ConferenceEntity conference) {
        super(status, dueDate, paymentDate, returnDate, currencyValue);
        this.guest = guest;
        this.conference = conference;
    }
}
