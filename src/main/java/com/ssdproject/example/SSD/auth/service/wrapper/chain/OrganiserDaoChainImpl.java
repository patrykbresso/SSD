package com.ssdproject.example.SSD.auth.service.wrapper.chain;

import com.ssdproject.example.SSD.auth.dao.OrganiserDao;
import com.ssdproject.example.SSD.auth.model.entity.UserEntity;
import com.ssdproject.example.SSD.auth.model.entity.users.OrganiserEntity;

import java.util.Optional;

public class OrganiserDaoChainImpl extends UserDaoChain {

    private OrganiserDao organiserDao;

    public OrganiserDaoChainImpl(UserDaoChain nextUserDao, OrganiserDao organiserDao) {
        super(nextUserDao);
        this.organiserDao = organiserDao;
    }

    @Override
    public UserEntity findByEmail(String email) {
        OrganiserEntity organiser = organiserDao.findByEmail(email);
        if (organiser != null) {
            return organiser;
        } else {
            return super.nextUserDao.findByEmail(email);
        }
    }

    @Override
    public UserEntity findById(Long id) {
        Optional<OrganiserEntity> organiser = organiserDao.findById(id);
        if (organiser.isPresent()) {
            return organiser.get();
        } else {
            return super.nextUserDao.findById(id);
        }
    }

    @Override
    public UserEntity saveOrUpdate(UserEntity user) {
        if (user instanceof OrganiserEntity) {
            return organiserDao.save((OrganiserEntity) user);
        } else {
            return super.nextUserDao.saveOrUpdate(user);
        }
    }
}
