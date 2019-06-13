package com.ssdproject.example.SSD.auth.service.wrapper.chain;

import com.ssdproject.example.SSD.auth.model.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public abstract class UserDaoChain {

    protected UserDaoChain nextUserDao;

    public abstract UserEntity findByEmail(String email);

    public abstract UserEntity findById(Long id);

    public abstract UserEntity saveOrUpdate(UserEntity user);
}
