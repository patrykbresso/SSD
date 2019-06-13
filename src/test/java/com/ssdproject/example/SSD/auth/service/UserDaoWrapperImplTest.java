package com.ssdproject.example.SSD.auth.service;

import com.ssdproject.example.SSD.auth.model.entity.UserEntity;
import com.ssdproject.example.SSD.auth.model.entity.users.AuthorEntity;
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
import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
class UserDaoWrapperImplTest {

    @Autowired
    private UserDaoWrapperImpl userDaoWrapper;

    @Autowired
    private DataPopulatorServiceImpl dataPopulatorService;

    @BeforeEach
    private void init() {
        dataPopulatorService.saveRolesAndCurrencies();
    }

    @Test
    public void shouldReturnAuthorEntityWhenSearchingByEmail() {
        // given
        String email = "author@gmail.com";
        String name = "Andrzej";
        String surname = "Strzelba";
        dataPopulatorService.saveAuthor(name, email, surname, AcademicTitle.PROFESSOR, new ArrayList<>());
        // when
        UserEntity user = userDaoWrapper.findByEmail(email);
        // then
        assertTrue(user instanceof AuthorEntity);
    }

    @Test
    public void shouldReturnAuthorEntityWithAuthorInformationWhenSearchingByEmail() {
        // given
        String email = "author@gmail.com";
        String name = "Andrzej";
        String surname = "Strzelba";
        dataPopulatorService.saveAuthor(name, email, surname, AcademicTitle.PROFESSOR, new ArrayList<>());
        // when
        UserEntity user = userDaoWrapper.findByEmail(email);
        // then
        AcademicTitle actualTitle = ((AuthorEntity) user).getAcademicTitle();
        assertEquals(AcademicTitle.PROFESSOR, actualTitle);
    }

    @Test
    public void shouldReturnReviewerEntityWhenSearchingByEmail() {
        // given
        String email = "author@gmail.com";
        String name = "Andrzej";
        String surname = "Strzelba";
        dataPopulatorService.saveReviewer(name, email, surname, AcademicTitle.PROFESSOR, new ArrayList<>());
        // when
        UserEntity user = userDaoWrapper.findByEmail(email);
        // then
        assertTrue(user instanceof ReviewerEntity);
    }

    @Test
    public void shouldReturnReviewerEntityWithReviewerInformationWhenSearchingByEmail() {
        // given
        String email = "author@gmail.com";
        String name = "Andrzej";
        String surname = "Strzelba";
        dataPopulatorService.saveReviewer(name, email, surname, AcademicTitle.PROFESSOR, new ArrayList<>());
        // when
        UserEntity user = userDaoWrapper.findByEmail(email);
        // then
        AcademicTitle actualTitle = ((ReviewerEntity) user).getAcademicTitle();
        assertEquals(AcademicTitle.PROFESSOR, actualTitle);
    }
}