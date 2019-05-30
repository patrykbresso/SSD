package com.ssdproject.example.SSD.conference.endpoint;

import com.ssdproject.example.SSD.conference.model.to.ConferenceTO;
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

    @GetMapping("/all")
    public ResponseEntity<?> getAll() {
        List<ConferenceTO> conferences = conferenceService.getAll();
        return new ResponseEntity<>(conferences, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable Long id) {
        ConferenceTO conference = conferenceService.getOne(id);
        return new ResponseEntity<>(conference, HttpStatus.OK);
    }
}
