package com.ssdproject.example.SSD.schedule.model.entity;

import com.ssdproject.example.SSD.auth.model.entity.users.AuthorEntity;
import com.ssdproject.example.SSD.shared.model.entity.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.ManyToMany;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public abstract class ExhibitionItemEntity extends AbstractEntity {

    @Column(nullable = false)
    private String title;

    @Column
    private String description;

    @Column(nullable = false)
    private String url;

    @Column(nullable = false)
    private LocalDateTime fileAttachingDate;

    @ManyToMany
    private List<AuthorEntity> authors;
}
