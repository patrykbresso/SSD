package com.ssdproject.example.SSD.auth.dao;

import com.ssdproject.example.SSD.auth.model.entity.users.GuestEntity;
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
class GuestDaoTest {

    @Autowired
    private GuestDao organiserDao;

    @Autowired
    private DataPopulatorServiceImpl dataPopulatorService;

    @BeforeEach
    private void init() {
        dataPopulatorService.saveRolesAndCurrencies();
    }

    @Test
    void shouldFindGuestByEmail() {
        // given
        String email = "andrzej@gmail.com";
        String name = "Andrzej";
        String surname = "Strzelba";
        dataPopulatorService.saveGuest(name, email, surname, new ArrayList<>());
        // when
        GuestEntity savedGuest = organiserDao.findByEmail(email);
        // then
        assertEquals(email, savedGuest.getEmail());
        assertEquals(name, savedGuest.getName());
        assertEquals(surname, savedGuest.getSurname());
    }

    @Test
    void shouldReturnNullWhenLookingForNotSavedGuest() {
        // given
        String email = "andrzej@gmail.com";
        // when
        GuestEntity savedGuest = organiserDao.findByEmail(email);
        // then
        assertNull(savedGuest);
    }
}