package com.ssdproject.example.SSD.auth.model.entity.users;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ssdproject.example.SSD.auth.model.entity.UserEntity;
import com.ssdproject.example.SSD.auth.model.enums.AcademicTitle;
import com.ssdproject.example.SSD.conference.model.entity.ConferenceEntity;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "authors")
public class AuthorEntity extends UserEntity {

    @Enumerated(EnumType.STRING)
    @Column
    private AcademicTitle academicTitle;

    @Column
    private boolean banned = false;

    @JsonIgnore
    @ManyToMany
    private List<ConferenceEntity> conferences;
}
