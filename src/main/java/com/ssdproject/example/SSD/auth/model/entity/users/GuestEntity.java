package com.ssdproject.example.SSD.auth.model.entity.users;

import com.ssdproject.example.SSD.auth.model.entity.UserEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "guests")
public class GuestEntity extends UserEntity {
}
