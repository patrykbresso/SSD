package com.ssdproject.example.SSD.conference.endpoint;

import com.ssdproject.example.SSD.auth.service.wrapper.UserDaoWrapperImpl;
import com.ssdproject.example.SSD.conference.model.to.ConferenceTO;
import com.ssdproject.example.SSD.conference.model.to.SimpleConferenceTO;
import com.ssdproject.example.SSD.conference.model.to.UserToConferenceAddTo;
import com.ssdproject.example.SSD.conference.service.ConferenceServiceImpl;
import com.ssdproject.example.SSD.shared.model.to.ResponseTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/conference")
public class ConferenceRest {

    @Autowired
    private ConferenceServiceImpl conferenceService;

    @Autowired
    private UserDaoWrapperImpl userDaoWrapper;

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

    @PreAuthorize("hasRole('ROLE_GUEST') or hasRole('ROLE_AUTHOR')")
    @PostMapping("/add-user")
    public ResponseEntity<?> addUserToConference(@RequestBody UserToConferenceAddTo form, Principal principal) {
        if (form.getConferenceId() == null || form.getUserEmail() == null) {
            return new ResponseEntity<>(new ResponseTO("Provided form is wrong, please send a correct request."), HttpStatus.BAD_REQUEST);
        }
        if (!principal.getName().equals(form.getUserEmail())) {
            return new ResponseEntity<>(new ResponseTO("Provided email do not belong to current logged in user."), HttpStatus.BAD_REQUEST);
        }

        return conferenceService.addUserToConference(form.getConferenceId(), form.getUserEmail());
    }
}
