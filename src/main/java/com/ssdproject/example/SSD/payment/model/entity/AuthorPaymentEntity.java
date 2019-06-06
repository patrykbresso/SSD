package com.ssdproject.example.SSD.payment.model.entity;

import com.ssdproject.example.SSD.auth.model.entity.users.AuthorEntity;
import com.ssdproject.example.SSD.conference.model.entity.ConferenceEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "author_payments")
public class AuthorPaymentEntity extends PaymentEntity {

    @ManyToOne
    private AuthorEntity author;

    @ManyToOne
    private ConferenceEntity conference;

    public AuthorPaymentEntity(AuthorEntity author, ConferenceEntity conference) {
        this.author = author;
        this.conference = conference;
    }

    public AuthorPaymentEntity() {
    }
}
