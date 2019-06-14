package com.ssdproject.example.SSD.payment.controllers;


import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/payments")
public class PaymentsController {


//    @PostMapping("/pay")
//    public String pay(HttpServletRequest request){
//        String cancelUrl = "";
//        String successUrl = "";
//        try {
//            Payment payment = paypalService.createPayment(
//                    4.00,
//                    "USD",
//                    PaypalPaymentMethod.paypal,
//                    PaypalPaymentIntent.sale,
//                    "payment description",
//                    cancelUrl,
//                    successUrl);
//            for(Links links : payment.getLinks()){
//                if(links.getRel().equals("approval_url")){
//                    return "redirect:" + links.getHref();
//                }
//            }
//        } catch (PayPalRESTException e) {
////            log.error(e.getMessage());
//        }
//        return "redirect:/";
//    }
}
