package com.ssdproject.example.SSD.auth.model.entity.users;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ssdproject.example.SSD.auth.model.entity.RoleEntity;
import com.ssdproject.example.SSD.auth.model.entity.UserEntity;
import com.ssdproject.example.SSD.conference.model.entity.ConferenceEntity;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "organisers")
public class OrganiserEntity extends UserEntity {

    @JsonIgnore
    @ManyToMany
    private List<ConferenceEntity> conferences;

    @Builder
    public OrganiserEntity(@NotBlank @Size(max = 50) String name, @NotBlank @Size(max = 100) String surname, String url,
                           String aboutMe, @NotBlank @Size(max = 50) @Email String email, @NotBlank @Size(min = 6, max = 100) String password,
                           RoleEntity role, List<ConferenceEntity> conferences) {
        super(name, surname, url, aboutMe, email, password, role);
        this.conferences = conferences;
    }
}
