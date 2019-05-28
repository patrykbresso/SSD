package com.ssdproject.example.SSD.auth.model.entity;

import com.ssdproject.example.SSD.shared.model.entity.AbstractEntity;
import lombok.Data;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@MappedSuperclass
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "email"
        })
})
public abstract class UserEntity extends AbstractEntity {

    @NotBlank
    @Size(max = 50)
    @Column(length = 50, nullable = false)
    private String name;

    @NotBlank
    @Size(max = 100)
    @Column(length = 100, nullable = false)
    private String surname;

    @Column
    private String url;

    @Column
    private String aboutMe;

    @NaturalId
    @NotBlank
    @Size(max = 50)
    @Email
    @Column(unique = true)
    private String email;

    @NotBlank
    @Size(min = 6, max = 100)
    private String password;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private RoleEntity role;
}
