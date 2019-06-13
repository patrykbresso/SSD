package com.ssdproject.example.SSD.conference.model.to;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ssdproject.example.SSD.auth.model.to.GuestTO;
import com.ssdproject.example.SSD.auth.model.to.OrganiserTO;
import com.ssdproject.example.SSD.schedule.model.to.ScheduleTO;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class ConferenceTO {

    private Long id;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private LocalDateTime registrationDate;
    private boolean cancelled = false;
    private ConferenceInformationTO conferenceInformation;
    private ScheduleTO schedule;
    private AddressTO address;
    private List<OrganiserTO> organisers;
    @JsonIgnore
    private List<GuestTO> guests;

    public List<GuestTO> getGuests(){
        return guests;
    }
}
