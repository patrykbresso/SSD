package com.ssdproject.example.SSD.conference.endpoint;

import com.ssdproject.example.SSD.auth.service.UserDaoImpl;
import com.ssdproject.example.SSD.conference.model.to.ConferenceTO;
import com.ssdproject.example.SSD.conference.model.to.SimpleConferenceTO;
import com.ssdproject.example.SSD.conference.service.ConferenceServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/conference")
public class ConferenceRest {

    @Autowired
    private ConferenceServiceImpl conferenceService;

    @Autowired
    private UserDaoImpl userDaoWrapper;

    @GetMapping("/all")
    public ResponseEntity<?> getAll() {
        List<SimpleConferenceTO> conferences = conferenceService.getAll();
        return new ResponseEntity<>(conferences, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable Long id) {
        ConferenceTO conference = conferenceService.getOne(id);
        return new ResponseEntity<>(conference, HttpStatus.OK);
    }

    @PostMapping("/{conferenceId}/user")
    public ResponseEntity<?> addUserToConference(@PathVariable Long conferenceId) {
        return new ResponseEntity<>(conferenceService.addGuest(conferenceId), HttpStatus.OK);
    }

    @GetMapping("/{conferenceId}/users")
    public ResponseEntity<?> getUsersFromConference(@PathVariable Long conferenceId){
        return new ResponseEntity<>(conferenceService.getAllGuests(conferenceId), HttpStatus.OK);
    }

}
