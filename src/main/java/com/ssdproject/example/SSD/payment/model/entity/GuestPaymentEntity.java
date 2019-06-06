package com.ssdproject.example.SSD.payment.model.entity;

import com.ssdproject.example.SSD.auth.model.entity.users.GuestEntity;
import com.ssdproject.example.SSD.conference.model.entity.ConferenceEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "guest_payments")
public class GuestPaymentEntity extends PaymentEntity {

    @ManyToOne
    private GuestEntity guest;

    @ManyToOne
    private ConferenceEntity conference;


    public GuestPaymentEntity(GuestEntity guest, ConferenceEntity conference) {
        this.guest = guest;
        this.conference = conference;
    }

    public GuestPaymentEntity() {
    }
}
