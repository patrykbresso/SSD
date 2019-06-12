package com.ssdproject.example.SSD.auth.model.to;

import com.ssdproject.example.SSD.auth.model.enums.AcademicTitle;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserDetailsTO {

    private String name;
    private String surname;
    private String aboutMe;
    private String url;
    private AcademicTitle title;
    private String email;

    public UserDetailsTO(String name, String surname, String aboutMe, String url) {
        this.name = name;
        this.surname = surname;
        this.aboutMe = aboutMe;
        this.url = url;
    }
}
