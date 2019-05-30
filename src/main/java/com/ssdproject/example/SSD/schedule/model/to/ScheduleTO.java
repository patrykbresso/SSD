package com.ssdproject.example.SSD.schedule.model.to;

import com.ssdproject.example.SSD.schedule.model.enums.ScheduleStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ScheduleTO {

    private Long id;
    private ScheduleStatus status = ScheduleStatus.IN_PROGRESS;
    private List<ScheduleItemTO> presentationsAndPosters;
}
