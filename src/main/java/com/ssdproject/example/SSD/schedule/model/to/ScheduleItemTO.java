package com.ssdproject.example.SSD.schedule.model.to;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class ScheduleItemTO {

    private Long id;
    private String topic;
    private String description;
    private String remarks;
    private String localizationDetail;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private List<PosterTO> posters;
    private PresentationTO presentation;
}
