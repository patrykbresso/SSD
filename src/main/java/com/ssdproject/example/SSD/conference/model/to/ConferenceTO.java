package com.ssdproject.example.SSD.conference.model.to;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ConferenceTO {

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private LocalDateTime registrationDate;

    private boolean cancelled = false;

    private ConferenceInformationTO conferenceInformationEntity;
}
