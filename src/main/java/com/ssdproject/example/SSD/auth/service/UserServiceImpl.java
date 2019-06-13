package com.ssdproject.example.SSD.auth.service;

import com.ssdproject.example.SSD.auth.model.entity.UserEntity;
import com.ssdproject.example.SSD.auth.model.entity.users.AuthorEntity;
import com.ssdproject.example.SSD.auth.model.entity.users.ReviewerEntity;
import com.ssdproject.example.SSD.auth.model.to.UserDetailsTO;
import com.ssdproject.example.SSD.auth.service.wrapper.UserDaoWrapperImpl;
import com.ssdproject.example.SSD.shared.model.to.ResponseTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl {

    @Autowired
    private UserDaoWrapperImpl userDaoWrapper;

    private ModelMapper modelMapper = new ModelMapper();

    public ResponseEntity<?> getUserDetails(String email) {
        UserEntity userEntity = userDaoWrapper.findByEmail(email);
        if (userEntity == null) {
            return new ResponseEntity<>(new ResponseTO("User with provided id not found in the system."), HttpStatus.BAD_REQUEST);
        }
        UserDetailsTO userDetailsTO = new UserDetailsTO(userEntity.getName(), userEntity.getSurname(), userEntity.getAboutMe(), userEntity.getUrl());

        if (userEntity instanceof AuthorEntity) {
            userDetailsTO.setTitle(((AuthorEntity) userEntity).getAcademicTitle());
        } else if (userEntity instanceof ReviewerEntity) {
            userDetailsTO.setTitle(((ReviewerEntity) userEntity).getAcademicTitle());
        }

        return ResponseEntity.ok(userDetailsTO);
    }

    public ResponseEntity<?> updateUser(UserDetailsTO userDetails) {
        UserEntity userEntity = userDaoWrapper.findByEmail(userDetails.getEmail());
        if (userEntity == null) {
            return new ResponseEntity<>(new ResponseTO("User with provided id not found in the system."), HttpStatus.BAD_REQUEST);
        }

        userEntity.setName(userDetails.getName());
        userEntity.setSurname(userDetails.getSurname());
        userEntity.setUrl(userDetails.getUrl());
        userEntity.setAboutMe(userDetails.getAboutMe());
        if (userEntity instanceof AuthorEntity) {
            ((AuthorEntity) userEntity).setAcademicTitle(userDetails.getTitle());
        } else if (userEntity instanceof ReviewerEntity) {
            ((ReviewerEntity) userEntity).setAcademicTitle(userDetails.getTitle());
        }

        userDaoWrapper.saveOrUpdate(userEntity);
        return ResponseEntity.ok(new ResponseTO("User data correctly updated."));
    }
}
