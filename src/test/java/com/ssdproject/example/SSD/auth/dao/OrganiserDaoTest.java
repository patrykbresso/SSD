package com.ssdproject.example.SSD.auth.dao;

import com.ssdproject.example.SSD.auth.model.entity.users.OrganiserEntity;
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
class OrganiserDaoTest {

    @Autowired
    private OrganiserDao organiserDao;

    @Autowired
    private DataPopulatorServiceImpl dataPopulatorService;

    @BeforeEach
    private void init() {
        dataPopulatorService.saveRolesAndCurrencies();
    }

    @Test
    void shouldFindOrganiserByEmail() {
        // given
        String email = "andrzej@gmail.com";
        String name = "Andrzej";
        String surname = "Strzelba";
        dataPopulatorService.saveOrganiser(name, email, surname, new ArrayList<>());
        // when
        OrganiserEntity savedOrganiser = organiserDao.findByEmail(email);
        // then
        assertEquals(email, savedOrganiser.getEmail());
        assertEquals(name, savedOrganiser.getName());
        assertEquals(surname, savedOrganiser.getSurname());
    }

    @Test
    void shouldReturnNullWhenLookingForNotSavedOrganiser() {
        // given
        String email = "andrzej@gmail.com";
        // when
        OrganiserEntity savedOrganiser = organiserDao.findByEmail(email);
        // then
        assertNull(savedOrganiser);
    }
}