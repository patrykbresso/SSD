package com.ssdproject.example.SSD.auth.endpoint;

import com.ssdproject.example.SSD.auth.model.to.UserDetailsTO;
import com.ssdproject.example.SSD.auth.service.UserServiceImpl;
import com.ssdproject.example.SSD.shared.model.to.ResponseTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/user")
public class UserRest {

    @Autowired
    private UserServiceImpl userService;

    @GetMapping("/detail/{email}")
    public ResponseEntity<?> getUserDetails(@PathVariable String email, Principal principal) {
        if (!principal.getName().equals(email)) {
            return new ResponseEntity<>(new ResponseTO("Provided email do not belong to current logged in user."), HttpStatus.BAD_REQUEST);
        }

        return userService.getUserDetails(email);
    }

    @PatchMapping("/update")
    public ResponseEntity<?> updateUserDetails(@RequestBody UserDetailsTO userDetails, Principal principal) {
        if (!principal.getName().equals(userDetails.getEmail())) {
            return new ResponseEntity<>(new ResponseTO("Provided email do not belong to current logged in user."), HttpStatus.BAD_REQUEST);
        }

        return userService.updateUser(userDetails);
    }
}
