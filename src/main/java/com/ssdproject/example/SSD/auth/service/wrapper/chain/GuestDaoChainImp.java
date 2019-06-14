package com.ssdproject.example.SSD.auth.service.wrapper.chain;

import com.ssdproject.example.SSD.auth.dao.GuestDao;
import com.ssdproject.example.SSD.auth.model.entity.UserEntity;
import com.ssdproject.example.SSD.auth.model.entity.users.GuestEntity;

import java.util.Optional;

public class GuestDaoChainImp extends UserDaoChain {

    private GuestDao guestDao;

    public GuestDaoChainImp(UserDaoChain nextUserDao, GuestDao guestDao) {
        super(nextUserDao);
        this.guestDao = guestDao;
    }

    @Override
    public UserEntity findByEmail(String email) {
        GuestEntity guest = guestDao.findByEmail(email);
        if (guest != null) {
            return guest;
        } else {
            if (super.nextUserDao == null) {
                return null;
            }
            return super.nextUserDao.findByEmail(email);
        }
    }

    @Override
    public UserEntity findById(Long id) {
        Optional<GuestEntity> guest = guestDao.findById(id);
        if (guest.isPresent()) {
            return guest.get();
        } else {
            if (super.nextUserDao == null) {
                return null;
            }
            return super.nextUserDao.findById(id);
        }
    }

    @Override
    public UserEntity saveOrUpdate(UserEntity user) {
        if (user instanceof GuestEntity) {
            return guestDao.save((GuestEntity) user);
        } else {
            if (super.nextUserDao == null) {
                return null;
            }
            return super.nextUserDao.saveOrUpdate(user);
        }
    }
}
