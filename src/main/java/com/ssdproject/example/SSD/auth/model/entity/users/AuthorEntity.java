package com.ssdproject.example.SSD.auth.model.entity.users;

import com.ssdproject.example.SSD.auth.model.entity.UserEntity;
import com.ssdproject.example.SSD.auth.model.enums.AcademicTitle;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "authors")
public class AuthorEntity extends UserEntity {

    @Enumerated(EnumType.STRING)
    @Column
    private AcademicTitle academicTitle;

    @Column
    private boolean banned = false;
}
