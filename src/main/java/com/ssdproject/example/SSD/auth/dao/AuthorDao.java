package com.ssdproject.example.SSD.auth.dao;

import com.ssdproject.example.SSD.auth.model.entity.users.AuthorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorDao extends JpaRepository<AuthorEntity, Long> {

    AuthorEntity findByEmail(String email);
}
