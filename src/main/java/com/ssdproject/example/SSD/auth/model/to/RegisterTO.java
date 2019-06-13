package com.ssdproject.example.SSD.auth.model.to;

import com.ssdproject.example.SSD.auth.model.enums.RegisterType;
import lombok.Getter;

@Getter
public class RegisterTO {

    private String name;
    private String surname;
    private String email;
    private String password;
    private RegisterType registrationType;
}
