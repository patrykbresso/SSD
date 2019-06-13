package com.ssdproject.example.SSD.auth.service.wrapper.chain;

import com.ssdproject.example.SSD.auth.dao.AuthorDao;
import com.ssdproject.example.SSD.auth.model.entity.UserEntity;
import com.ssdproject.example.SSD.auth.model.entity.users.AuthorEntity;

import java.util.Optional;

public class AuthorDaoChainImpl extends UserDaoChain {

    private AuthorDao authorDao;

    public AuthorDaoChainImpl(UserDaoChain nextUserDao, AuthorDao authorDao) {
        super(nextUserDao);
        this.authorDao = authorDao;
    }

    @Override
    public UserEntity findByEmail(String email) {
        AuthorEntity author = authorDao.findByEmail(email);
        if (author != null) {
            return author;
        } else {
            return super.nextUserDao.findByEmail(email);
        }
    }

    @Override
    public UserEntity findById(Long id) {
        Optional<AuthorEntity> author = authorDao.findById(id);
        if (author.isPresent()) {
            return author.get();
        } else {
            return super.nextUserDao.findById(id);
        }
    }

    @Override
    public UserEntity saveOrUpdate(UserEntity user) {
        if (user instanceof AuthorEntity) {
            return authorDao.save((AuthorEntity) user);
        } else {
            return super.nextUserDao.saveOrUpdate(user);
        }
    }
}
