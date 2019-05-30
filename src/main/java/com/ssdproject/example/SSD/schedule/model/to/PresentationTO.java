package com.ssdproject.example.SSD.schedule.model.to;

import com.ssdproject.example.SSD.auth.model.to.AuthorTO;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class PresentationTO {

    private Long id;
    private String title;
    private String description;
    private String url;
    private LocalDateTime fileAttachingDate;
    private List<AuthorTO> authors;
}
