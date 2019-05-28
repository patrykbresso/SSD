package com.ssdproject.example.SSD.auth.endpoint;

import com.ssdproject.example.SSD.auth.model.to.LoginTo;
import com.ssdproject.example.SSD.auth.model.to.RegisterTO;
import com.ssdproject.example.SSD.auth.service.AuthServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthRest {

    @Autowired
    private AuthServiceImpl authService;

    @PostMapping("/login")
    public ResponseEntity<?> authenticate(@Valid @RequestBody LoginTo loginRequest) {
        ResponseEntity<?> response = authService.login(loginRequest);
        return response;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterTO registerRequest) {
        return authService.createAccount(registerRequest);
    }
}
