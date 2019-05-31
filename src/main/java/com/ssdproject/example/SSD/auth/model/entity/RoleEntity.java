package com.ssdproject.example.SSD.auth.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ssdproject.example.SSD.auth.model.enums.UserRoleName;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.NaturalId;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Data
@Builder
@Entity
@Table(name = "roles")
public class RoleEntity implements GrantedAuthority {

    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @NaturalId
    @Column
    private UserRoleName name;

    @JsonIgnore
    @Override
    public String getAuthority() {
        return name.name();
    }
}
