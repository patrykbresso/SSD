package com.ssdproject.example.SSD.conference.model.to;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConferenceInformationTO {

    private Long id;
    private String topic;
    private String description;
    private int maxNumberOfPresentations;
    private int availableNumberOfPresentations;
    private int maxNumberOfSeats;
    private int availableSeats;
}
