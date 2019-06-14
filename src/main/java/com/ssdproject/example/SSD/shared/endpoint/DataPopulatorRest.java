package com.ssdproject.example.SSD.shared.endpoint;

import com.ssdproject.example.SSD.shared.service.DataPopulatorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test/populator")
public class DataPopulatorRest {

    @Autowired
    private DataPopulatorServiceImpl dataPopulatorService;

    @PostMapping("/populate")
    public void populateDB() {
        dataPopulatorService.populateDataBase();
    }
}
