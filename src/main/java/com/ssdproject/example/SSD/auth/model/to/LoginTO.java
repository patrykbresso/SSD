package com.ssdproject.example.SSD.auth.model.to;

import com.ssdproject.example.SSD.auth.model.enums.LoginType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginTO {

    private String email;
    private String password;
    private LoginType loginType;
}
