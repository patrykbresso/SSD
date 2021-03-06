package com.ssdproject.example.SSD.conference.service;

import com.ssdproject.example.SSD.auth.model.entity.UserEntity;
import com.ssdproject.example.SSD.auth.model.entity.users.AuthorEntity;
import com.ssdproject.example.SSD.auth.model.entity.users.GuestEntity;
import com.ssdproject.example.SSD.auth.service.wrapper.UserDaoWrapperImpl;
import com.ssdproject.example.SSD.conference.dao.ConferenceDao;
import com.ssdproject.example.SSD.conference.model.entity.ConferenceEntity;
import com.ssdproject.example.SSD.conference.model.to.ConferenceTO;
import com.ssdproject.example.SSD.conference.model.to.SimpleConferenceTO;
import com.ssdproject.example.SSD.shared.model.to.ResponseTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ConferenceServiceImpl {

    @Autowired
    private ConferenceDao conferenceDao;

    @Autowired
    private UserDaoWrapperImpl userDaoWrapper;

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

    @Transactional
    public ResponseEntity<?> addUserToConference(Long conferenceId, String userEmail) {
        UserEntity userEntity = userDaoWrapper.findByEmail(userEmail);
        Optional<ConferenceEntity> conferenceEntity = conferenceDao.findById(conferenceId);

        if (!conferenceEntity.isPresent() || userEntity == null) {
            return new ResponseEntity<>(new ResponseTO("Conference or user not found."), HttpStatus.BAD_REQUEST);
        }

        ConferenceTO conferenceTO = mapToTO(conferenceEntity.get());

        if (userEntity instanceof GuestEntity) {
            if (conferenceTO.getConferenceInformation().getAvailableSeats() <= 0) {
                return new ResponseEntity<>(new ResponseTO("There is no seats available in this conference."), HttpStatus.BAD_REQUEST);
            }

            // TODO check if guest already assigned to conference

            List<GuestEntity> guests = conferenceEntity.get().getGuests();
            guests.add((GuestEntity) userEntity);
            conferenceEntity.get().setGuests(guests);
            conferenceDao.save(conferenceEntity.get());
        } else if (userEntity instanceof AuthorEntity) {
            if (conferenceTO.getConferenceInformation().getAvailableNumberOfPresentations() <= 0) {
                return new ResponseEntity<>(new ResponseTO("There is no presentations available in this conference."), HttpStatus.BAD_REQUEST);
            }
            // TODO check if author already assigned to conference

            AuthorEntity authorEntity = (AuthorEntity) userEntity;
            List<ConferenceEntity> conferences = authorEntity.getConferences();
            conferences.add(conferenceEntity.get());
            authorEntity.setConferences(conferences);
            userDaoWrapper.saveOrUpdate(authorEntity);
        }
        return ResponseEntity.ok(new ResponseTO("User successfully added to conference."));
    }

    public ResponseEntity<?> removeUserFromConference(Long conferenceId, String userEmail) {
        UserEntity userEntity = userDaoWrapper.findByEmail(userEmail);
        Optional<ConferenceEntity> conferenceEntity = conferenceDao.findById(conferenceId);

        if (!conferenceEntity.isPresent() || userEntity == null) {
            return new ResponseEntity<>(new ResponseTO("Conference or user not found."), HttpStatus.BAD_REQUEST);
        }

        ConferenceEntity conference = conferenceEntity.get();
        if (userEntity instanceof GuestEntity) {
            conference.getGuests().remove(userEntity);
        } else if (userEntity instanceof AuthorEntity) {
            //remove author from authors list
            
        }
        return null;
    }

    private ConferenceTO mapToTO(ConferenceEntity entity) {
        ConferenceTO conference = modelMapper.map(entity, ConferenceTO.class);

        int numberSeatsLeft = entity.getGuests() != null ?
                entity.getConferenceInformation().getMaxNumberOfSeats() - entity.getConferenceInformation().getMaxNumberOfPresentations() - entity.getGuests().size() :
                entity.getConferenceInformation().getMaxNumberOfSeats() - entity.getConferenceInformation().getMaxNumberOfPresentations();

        int addedPresentationsNumber = (int) entity.getSchedule().getPresentationsAndPosters().stream().filter(p -> p.getPresentation() != null).count();
        int numberPresentationsLeft = entity.getConferenceInformation().getMaxNumberOfPresentations() - addedPresentationsNumber;

        conference.getConferenceInformation().setAvailableNumberOfPresentations(numberPresentationsLeft);
        conference.getConferenceInformation().setAvailableSeats(numberSeatsLeft);

        return conference;
    }
}
