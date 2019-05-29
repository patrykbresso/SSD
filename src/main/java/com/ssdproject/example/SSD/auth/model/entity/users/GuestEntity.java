package com.ssdproject.example.SSD.auth.model.entity.users;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ssdproject.example.SSD.auth.model.entity.UserEntity;
import com.ssdproject.example.SSD.conference.model.entity.ConferenceEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.List;

@Data
@Entity
@Table(name = "guests")
public class GuestEntity extends UserEntity {

    @JsonIgnore
    @ManyToMany
    private List<ConferenceEntity> conferences;
}
