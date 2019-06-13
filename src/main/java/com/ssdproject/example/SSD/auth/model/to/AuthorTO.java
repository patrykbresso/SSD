package com.ssdproject.example.SSD.auth.model.to;

import com.ssdproject.example.SSD.auth.model.enums.AcademicTitle;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthorTO {

    private String name;
    private String surname;
    private String url;
    private String aboutMe;
    private String email;
    private AcademicTitle academicTitle;
}
