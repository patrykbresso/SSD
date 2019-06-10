package com.ssdproject.example.SSD.auth.dao;

import com.ssdproject.example.SSD.auth.model.entity.users.AuthorEntity;
import com.ssdproject.example.SSD.auth.model.enums.AcademicTitle;
import com.ssdproject.example.SSD.shared.service.DataPopulatorServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
class AuthorDaoTest {

    @Autowired
    private AuthorDao authorDao;

    @Autowired
    private DataPopulatorServiceImpl dataPopulatorService;

    @BeforeEach
    private void init() {
        dataPopulatorService.saveRolesAndCurrencies();
    }

    @Test
    void shouldFindAuthorByEmail() {
        // given
        String email = "andrzej@gmail.com";
        String name = "Andrzej";
        String surname = "Strzelba";
        dataPopulatorService.saveAuthor(name, email, surname, AcademicTitle.PROFESSOR, new ArrayList<>());
        // when
        AuthorEntity savedAuthor = authorDao.findByEmail(email);
        // then
        assertEquals(email, savedAuthor.getEmail());
        assertEquals(name, savedAuthor.getName());
        assertEquals(surname, savedAuthor.getSurname());
    }

    @Test
    void shouldReturnNullWhenLookingForNotSavedAuthor() {
        // given
        String email = "andrzej@gmail.com";
        // when
        AuthorEntity savedAuthor = authorDao.findByEmail(email);
        // then
        assertNull(savedAuthor);
    }
}