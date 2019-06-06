package com.ssdproject.example.SSD.auth.model.entity.users;

import com.ssdproject.example.SSD.auth.model.entity.RoleEntity;
import com.ssdproject.example.SSD.auth.model.entity.UserEntity;
import com.ssdproject.example.SSD.auth.model.enums.AcademicTitle;
import com.ssdproject.example.SSD.conference.model.entity.ConferenceEntity;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "reviewers")
public class ReviewerEntity extends UserEntity {

    @Enumerated(EnumType.STRING)
    @Column
    private AcademicTitle academicTitle;

    @ManyToMany
    private List<ConferenceEntity> conferences;

    @Builder
    public ReviewerEntity(@NotBlank @Size(max = 50) String name, @NotBlank @Size(max = 100) String surname, String url, String aboutMe, @NotBlank @Size(max = 50) @Email String email, @NotBlank @Size(min = 6, max = 100) String password, RoleEntity role, AcademicTitle academicTitle, List<ConferenceEntity> conferences) {
        super(name, surname, url, aboutMe, email, password, role);
        this.academicTitle = academicTitle;
        this.conferences = conferences;
    }
}
