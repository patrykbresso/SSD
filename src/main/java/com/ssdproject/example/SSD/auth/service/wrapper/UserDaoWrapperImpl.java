package com.ssdproject.example.SSD.auth.service.wrapper;

import com.ssdproject.example.SSD.auth.dao.AuthorDao;
import com.ssdproject.example.SSD.auth.dao.GuestDao;
import com.ssdproject.example.SSD.auth.dao.OrganiserDao;
import com.ssdproject.example.SSD.auth.dao.ReviewerDao;
import com.ssdproject.example.SSD.auth.model.entity.UserEntity;
import com.ssdproject.example.SSD.auth.service.wrapper.chain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserDaoWrapperImpl {

    private AuthorDao authorDao;
    private GuestDao guestDao;
    private OrganiserDao organiserDao;
    private ReviewerDao reviewerDao;
    private UserDaoChain userDaoChain;

    @Autowired
    public UserDaoWrapperImpl(AuthorDao authorDao, GuestDao guestDao, OrganiserDao organiserDao, ReviewerDao reviewerDao) {
        this.authorDao = authorDao;
        this.guestDao = guestDao;
        this.organiserDao = organiserDao;
        this.reviewerDao = reviewerDao;

        this.userDaoChain = getUserDaoChain();
    }

    public UserEntity findByEmail(String email) {
        return userDaoChain.findByEmail(email);
    }

    /*
    Do not use!!!!
    Method cause problem with duplicated user ids in different tables - wrong user could be picked.
     */
    public UserEntity findById(Long id) {
        return userDaoChain.findById(id);
    }

    public UserEntity saveOrUpdate(UserEntity user) {
        return userDaoChain.saveOrUpdate(user);
    }

    public UserDaoChain getUserDaoChain() {
        UserDaoChain guestDaoChain = new GuestDaoChainImp(null, guestDao);
        UserDaoChain authorDaoChain = new AuthorDaoChainImpl(guestDaoChain, authorDao);
        UserDaoChain reviewerDaoChain = new ReviewerDaoChainImpl(authorDaoChain, reviewerDao);
        return new OrganiserDaoChainImpl(reviewerDaoChain, organiserDao);
    }
}
