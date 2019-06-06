package com.ssdproject.example.SSD.auth.service;

import com.ssdproject.example.SSD.auth.dao.AuthorDao;
import com.ssdproject.example.SSD.auth.dao.GuestDao;
import com.ssdproject.example.SSD.auth.dao.OrganiserDao;
import com.ssdproject.example.SSD.auth.dao.ReviewerDao;
import com.ssdproject.example.SSD.auth.model.entity.UserEntity;
import com.ssdproject.example.SSD.auth.model.entity.users.AuthorEntity;
import com.ssdproject.example.SSD.auth.model.entity.users.GuestEntity;
import com.ssdproject.example.SSD.auth.model.entity.users.OrganiserEntity;
import com.ssdproject.example.SSD.auth.model.entity.users.ReviewerEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDaoWrapperImpl {

    @Autowired
    private AuthorDao authorDao;
    @Autowired
    private GuestDao guestDao;
    @Autowired
    private OrganiserDao organiserDao;
    @Autowired
    private ReviewerDao reviewerDao;

    public UserEntity findByEmail(String email) {
        AuthorEntity authorEntity = authorDao.findByEmail(email);
        if (authorEntity != null) {
            return authorEntity;
        }
        GuestEntity guestEntity = guestDao.findByEmail(email);
        if (guestEntity != null) {
            return guestEntity;
        }
        OrganiserEntity organiserEntity = organiserDao.findByEmail(email);
        if (organiserEntity != null) {
            return organiserEntity;
        }
        ReviewerEntity reviewerEntity = reviewerDao.findByEmail(email);
        return reviewerEntity;
    }

    public Optional<GuestEntity> findById(Long id){
        Optional<GuestEntity> authorEntity = guestDao.findById(id);
            return authorEntity;
    }

    public UserEntity saveOrUpdate(UserEntity user) {
        if (user instanceof AuthorEntity) {
            return authorDao.save((AuthorEntity) user);
        }
        if (user instanceof GuestEntity) {
            return guestDao.save((GuestEntity) user);
        }
        if (user instanceof ReviewerEntity) {
            return reviewerDao.save((ReviewerEntity) user);
        }
        if (user instanceof OrganiserEntity) {
            return organiserDao.save((OrganiserEntity) user);
        }
        return null;
    }
}
