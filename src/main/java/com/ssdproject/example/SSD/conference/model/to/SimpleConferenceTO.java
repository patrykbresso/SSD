package com.ssdproject.example.SSD.conference.model.to;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class SimpleConferenceTO {

    private Long id;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private LocalDateTime registrationDate;
    private boolean cancelled = false;
    private ConferenceInformationTO conferenceInformation;
    private AddressTO address;
}
