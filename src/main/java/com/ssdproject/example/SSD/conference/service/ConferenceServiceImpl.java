package com.ssdproject.example.SSD.conference.service;

import com.ssdproject.example.SSD.auth.model.entity.users.GuestEntity;
import com.ssdproject.example.SSD.auth.service.UserDaoImpl;
import com.ssdproject.example.SSD.conference.dao.ConferenceDao;
import com.ssdproject.example.SSD.conference.model.entity.ConferenceEntity;
import com.ssdproject.example.SSD.conference.model.to.ConferenceTO;
import com.ssdproject.example.SSD.conference.model.to.SimpleConferenceTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ConferenceServiceImpl {

    @Autowired
    private ConferenceDao conferenceDao;

    @Autowired
    private UserDaoImpl userDaoWrapper;

    private ModelMapper modelMapper = new ModelMapper();

    public ConferenceTO getOne(Long id) {
        Optional<ConferenceEntity> conferenceEntityOptional = conferenceDao.findById(id);
        if (conferenceEntityOptional.isPresent()) {
            ConferenceEntity conferenceEntity = conferenceEntityOptional.get();
            return mapToTO(conferenceEntity);
        }
        return null;
    }

    public List<SimpleConferenceTO> getAll() {
        List<ConferenceEntity> conferenceEntities = conferenceDao.findAll();
        return conferenceEntities.stream().map(entity -> {
            ConferenceTO conference = mapToTO(entity);
            return modelMapper.map(conference, SimpleConferenceTO.class);
        }).collect(Collectors.toList());
    }

    public ConferenceEntity addGuest(Long conferenceId) {
        Optional<GuestEntity> guestEntity = userDaoWrapper.findById((long) 1); // TODO get user from token
        Optional<ConferenceEntity> conference = conferenceDao.findById(conferenceId);
        if (conference.isPresent() && guestEntity.isPresent()) {
            List<GuestEntity> guestEntities = new ArrayList<>();
            if (conference.get().getGuests() != null) {
                guestEntities.addAll(conference.get().getGuests());
            }
            guestEntities.add(guestEntity.get());
            conference.get().setGuests(guestEntities);
            conferenceDao.save(conference.get());
            return conference.get();
        }
        return null;
    }

    private ConferenceTO mapToTO(ConferenceEntity entity) {
        ConferenceTO conference = modelMapper.map(entity, ConferenceTO.class);
        int numberSeatsLeft = entity.getGuests() != null ?
                entity.getConferenceInformation().getMaxNumberOfSeats() - entity.getGuests().size() :
                entity.getConferenceInformation().getMaxNumberOfSeats();
        // dopisac metode to tego
        int numberPresentationsLeft = entity.getConferenceInformation().getMaxNumberOfPresentations();

        conference.getConferenceInformation().setAvailableNumberOfPresentations(numberPresentationsLeft);
        conference.getConferenceInformation().setAvailableSeats(numberSeatsLeft);

        return conference;
    }

    public List<GuestEntity> getAllGuests(Long conferenceId) {
        return conferenceDao.getOne(conferenceId).getGuests();
    }
}
