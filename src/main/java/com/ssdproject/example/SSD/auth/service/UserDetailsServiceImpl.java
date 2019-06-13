package com.ssdproject.example.SSD.auth.service;

import com.ssdproject.example.SSD.auth.model.UserPrinciple;
import com.ssdproject.example.SSD.auth.model.entity.UserEntity;
import com.ssdproject.example.SSD.auth.service.wrapper.UserDaoWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserDaoWrapperImpl userDao;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserEntity userEntity = userDao.findByEmail(s);

        if (userEntity == null) {
            throw new UsernameNotFoundException("User with provided email not found");
        }

        return UserPrinciple.build(userEntity);
    }
}
