package com.ssdproject.example.SSD.auth.service.wrapper.chain;

import com.ssdproject.example.SSD.auth.dao.ReviewerDao;
import com.ssdproject.example.SSD.auth.model.entity.UserEntity;
import com.ssdproject.example.SSD.auth.model.entity.users.ReviewerEntity;

import java.util.Optional;

public class ReviewerDaoChainImpl extends UserDaoChain {

    private ReviewerDao reviewerDao;

    public ReviewerDaoChainImpl(UserDaoChain nextUserDao, ReviewerDao reviewerDao) {
        super(nextUserDao);
        this.reviewerDao = reviewerDao;
    }

    @Override
    public UserEntity findByEmail(String email) {
        ReviewerEntity reviewer = reviewerDao.findByEmail(email);
        if (reviewer != null) {
            return reviewer;
        } else {
            if (super.nextUserDao == null) {
                return null;
            }
            return super.nextUserDao.findByEmail(email);
        }
    }

    @Override
    public UserEntity findById(Long id) {
        Optional<ReviewerEntity> reviewer = reviewerDao.findById(id);
        if (reviewer.isPresent()) {
            return reviewer.get();
        } else {
            if (super.nextUserDao == null) {
                return null;
            }
            return super.nextUserDao.findById(id);
        }
    }

    @Override
    public UserEntity saveOrUpdate(UserEntity user) {
        if (user instanceof ReviewerEntity) {
            return reviewerDao.save((ReviewerEntity) user);
        } else {
            if (super.nextUserDao == null) {
                return null;
            }
            return super.nextUserDao.saveOrUpdate(user);
        }
    }
}
