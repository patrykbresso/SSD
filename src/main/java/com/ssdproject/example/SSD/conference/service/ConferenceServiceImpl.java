package com.ssdproject.example.SSD.conference.service;

import com.ssdproject.example.SSD.conference.dao.ConferenceDao;
import com.ssdproject.example.SSD.conference.model.entity.ConferenceEntity;
import com.ssdproject.example.SSD.conference.model.to.ConferenceTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ConferenceServiceImpl {

    @Autowired
    private ConferenceDao conferenceDao;

    private ModelMapper modelMapper = new ModelMapper();

    public ConferenceTO getOne(Long id) {
        Optional<ConferenceEntity> conferenceEntityOptional = conferenceDao.findById(id);
        if (conferenceEntityOptional.isPresent()) {
            ConferenceEntity conferenceEntity = conferenceEntityOptional.get();
            return mapToTO(conferenceEntity);
        }
        return null;
    }

    public List<ConferenceTO> getAll() {
        List<ConferenceEntity> conferenceEntities = conferenceDao.findAll();
        return conferenceEntities.stream().map(this::mapToTO).collect(Collectors.toList());
    }

    private ConferenceTO mapToTO(ConferenceEntity entity) {
        ConferenceTO conference = modelMapper.map(entity, ConferenceTO.class);
        int numberSeatsLeft = entity.getGuests() != null ?
                entity.getConferenceInformation().getMaxNumberOfSeats() - entity.getGuests().size() :
                entity.getConferenceInformation().getMaxNumberOfSeats();
        int numberPresentationsLeft = entity.getAuthors() != null ?
                entity.getConferenceInformation().getMaxNumberOfPresentations() - entity.getAuthors().size() :
                entity.getConferenceInformation().getMaxNumberOfPresentations();

        conference.getConferenceInformation().setAvailableNumberOfPresentations(numberPresentationsLeft);
        conference.getConferenceInformation().setAvailableSeats(numberSeatsLeft);

        return conference;
    }
}
