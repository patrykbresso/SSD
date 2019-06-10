package com.ssdproject.example.SSD.auth.dao;

import com.ssdproject.example.SSD.auth.model.entity.users.ReviewerEntity;
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
class ReviewerDaoTest {

    @Autowired
    private ReviewerDao reviewerDao;

    @Autowired
    private DataPopulatorServiceImpl dataPopulatorService;

    @BeforeEach
    private void init() {
        dataPopulatorService.saveRolesAndCurrencies();
    }

    @Test
    void shouldFindReviewerByEmail() {
        // given
        String email = "andrzej@gmail.com";
        String name = "Andrzej";
        String surname = "Strzelba";
        dataPopulatorService.saveReviewer(name, email, surname, AcademicTitle.PROFESSOR, new ArrayList<>());
        // when
        ReviewerEntity savedReviewer = reviewerDao.findByEmail(email);
        // then
        assertEquals(email, savedReviewer.getEmail());
        assertEquals(name, savedReviewer.getName());
        assertEquals(surname, savedReviewer.getSurname());
    }

    @Test
    void shouldReturnNullWhenLookingForNotSavedReviewer() {
        // given
        String email = "andrzej@gmail.com";
        // when
        ReviewerEntity savedReviewer = reviewerDao.findByEmail(email);
        // then
        assertNull(savedReviewer);
    }
}