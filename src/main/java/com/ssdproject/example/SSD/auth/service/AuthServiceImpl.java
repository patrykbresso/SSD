package com.ssdproject.example.SSD.auth.service;

import com.ssdproject.example.SSD.auth.dao.RoleDao;
import com.ssdproject.example.SSD.auth.model.entity.RoleEntity;
import com.ssdproject.example.SSD.auth.model.entity.UserEntity;
import com.ssdproject.example.SSD.auth.model.entity.users.AuthorEntity;
import com.ssdproject.example.SSD.auth.model.entity.users.GuestEntity;
import com.ssdproject.example.SSD.auth.model.enums.LoginType;
import com.ssdproject.example.SSD.auth.model.enums.UserRoleName;
import com.ssdproject.example.SSD.auth.model.to.LoginTo;
import com.ssdproject.example.SSD.auth.model.to.RegisterTO;
import com.ssdproject.example.SSD.config.security.jwt.JwtProvider;
import com.ssdproject.example.SSD.shared.model.to.JwtResponseTO;
import com.ssdproject.example.SSD.shared.model.to.ResponseTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl {

    @Autowired
    PasswordEncoder encoder;
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private UserDaoWrapperImpl userDaoWrapper;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtProvider jwtProvider;

    public ResponseEntity<?> createAccount(RegisterTO form) {

        switch (form.getRegistrationType()) {
            case AUTHOR:
                AuthorEntity newAuthor = new AuthorEntity();
                persistNewUser(newAuthor, form);
                break;
            case GUEST:
                GuestEntity guestEntity = new GuestEntity();
                persistNewUser(guestEntity, form);
                break;
        }
        return new ResponseEntity<>(new ResponseTO("User registered successfully!"), HttpStatus.OK);
    }

    private void persistNewUser(UserEntity user, RegisterTO form) {
        user.setPassword(encoder.encode(form.getPassword()));
        user.setEmail(form.getEmail());
        user.setName(form.getName());
        user.setSurname(form.getSurname());
        RoleEntity role = null;
        if (user instanceof AuthorEntity) {
            role = roleDao.findByName(UserRoleName.ROLE_AUTHOR);
        } else if (user instanceof GuestEntity) {
            role = roleDao.findByName(UserRoleName.ROLE_GUEST);
        }
        if (role != null) {
            user.setRole(role);
        }
        userDaoWrapper.saveOrUpdate(user);
    }

    public ResponseEntity<?> login(LoginTo loginRequest) {
        UserEntity userEntity = userDaoWrapper.findByEmail(loginRequest.getEmail());
        if (userEntity == null) {
            return new ResponseEntity<>(new ResponseTO("User with provided email not found in the system."), HttpStatus.BAD_REQUEST);
        }
        if (userEntity.getRole().getName() != mapToRole(loginRequest.getLoginType())) {
            String msg = String.format("User with provided email cannot log in as %s.", loginRequest.getLoginType().name().toLowerCase());
            return new ResponseEntity<>(new ResponseTO(msg), HttpStatus.BAD_REQUEST);
        }

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtProvider.generateJwtToken(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        return ResponseEntity.ok(new JwtResponseTO(jwt, userDetails.getUsername(), userDetails.getAuthorities()));
    }

    private UserRoleName mapToRole(LoginType loginType) {
        switch (loginType) {
            case AUTHOR:
                return UserRoleName.ROLE_AUTHOR;
            case GUEST:
                return UserRoleName.ROLE_GUEST;
            case ORGANISER:
                return UserRoleName.ROLE_ORGANISER;
            case REVIEWER:
                return UserRoleName.ROLE_REVIEWER;
        }
        return null;
    }
}
