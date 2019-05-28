package com.ssdproject.example.SSD.auth.model.entity.users;

import com.ssdproject.example.SSD.auth.model.entity.UserEntity;
import com.ssdproject.example.SSD.auth.model.enums.AcademicTitle;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "reviewers")
public class ReviewerEntity extends UserEntity {

    @Enumerated(EnumType.STRING)
    @Column
    private AcademicTitle academicTitle;
}
