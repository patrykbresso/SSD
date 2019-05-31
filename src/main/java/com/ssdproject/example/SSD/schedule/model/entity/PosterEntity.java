package com.ssdproject.example.SSD.schedule.model.entity;

import com.ssdproject.example.SSD.auth.model.entity.users.AuthorEntity;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "posters")
public class PosterEntity extends ExhibitionItemEntity {

    @Builder
    public PosterEntity(String title, String description, String url, LocalDateTime fileAttachingDate, List<AuthorEntity> authors) {
        super(title, description, url, fileAttachingDate, authors);
    }
}
