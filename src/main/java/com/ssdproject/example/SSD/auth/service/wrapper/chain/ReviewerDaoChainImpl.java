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
        if(super.nextUserDao == null){
            return null;
        }

        ReviewerEntity reviewer = reviewerDao.findByEmail(email);
        if (reviewer != null) {
            return reviewer;
        } else {
            return super.nextUserDao.findByEmail(email);
        }
    }

    @Override
    public UserEntity findById(Long id) {
        if(super.nextUserDao == null){
            return null;
        }

        Optional<ReviewerEntity> reviewer = reviewerDao.findById(id);
        if (reviewer.isPresent()) {
            return reviewer.get();
        } else {
            return super.nextUserDao.findById(id);
        }
    }

    @Override
    public UserEntity saveOrUpdate(UserEntity user) {
        if(super.nextUserDao == null){
            return null;
        }

        if (user instanceof ReviewerEntity) {
            return reviewerDao.save((ReviewerEntity) user);
        } else {
            return super.nextUserDao.saveOrUpdate(user);
        }
    }
}
