package com.ssdproject.example.SSD.payment.model.entity;

import com.ssdproject.example.SSD.auth.model.entity.users.AuthorEntity;
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
@Table(name = "author_payments")
public class AuthorPaymentEntity extends PaymentEntity {

    @ManyToOne
    private AuthorEntity author;

    @ManyToOne
    private ConferenceEntity conference;

    @Builder
    public AuthorPaymentEntity(PaymentStatus status, LocalDateTime dueDate, LocalDateTime paymentDate,
                               LocalDateTime returnDate, CurrencyValueEntity currencyValue, AuthorEntity author, ConferenceEntity conference) {
        super(status, dueDate, paymentDate, returnDate, currencyValue);
        this.author = author;
        this.conference = conference;
    }
}
