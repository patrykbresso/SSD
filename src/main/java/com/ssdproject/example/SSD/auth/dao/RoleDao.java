package com.ssdproject.example.SSD.auth.dao;

import com.ssdproject.example.SSD.auth.model.entity.RoleEntity;
import com.ssdproject.example.SSD.auth.model.enums.UserRoleName;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleDao extends JpaRepository<RoleEntity, Long> {

    RoleEntity findByName(UserRoleName role);
}
