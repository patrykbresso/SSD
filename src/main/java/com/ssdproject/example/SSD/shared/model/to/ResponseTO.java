package com.ssdproject.example.SSD.shared.model.to;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseTO {

    private String message;

    public ResponseTO(String message) {
        this.message = message;
    }
}
