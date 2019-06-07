package com.ssdproject.example.SSD.payment.controllers;


import com.ssdproject.example.SSD.payment.model.entity.CurrencyValueEntity;
import com.ssdproject.example.SSD.payment.service.PayPalClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.PermitAll;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/payments")
public class PaymentsController {

    @Autowired
    private PayPalClient payPalClient;

    @PostMapping(value = "/make/payment")
    public Map<String, Object> makePayment(@RequestParam("conferenceId") Long id, @RequestBody CurrencyValueEntity currencyValueEntity) {
        return payPalClient.createPayment(id, currencyValueEntity);
    }

    @PostMapping(value = "/complete/payment")
    public ResponseEntity<?> completePayment(@RequestParam("conferenceId") Long conferenceId){
        return new ResponseEntity<>(payPalClient.completePayment(conferenceId), HttpStatus.OK);
    }

}
