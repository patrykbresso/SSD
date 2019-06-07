package com.ssdproject.example.SSD.payment.service;

import com.paypal.api.payments.*;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import com.ssdproject.example.SSD.auth.dao.GuestDao;
import com.ssdproject.example.SSD.conference.dao.ConferenceDao;
import com.ssdproject.example.SSD.payment.dao.CurrencyValueDao;
import com.ssdproject.example.SSD.payment.dao.GuestPaymentDao;
import com.ssdproject.example.SSD.payment.model.entity.CurrencyValueEntity;
import com.ssdproject.example.SSD.payment.model.entity.GuestPaymentEntity;
import com.ssdproject.example.SSD.payment.model.enums.PaymentStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class PayPalClient {

    @Value("${paypal.url.cancel}")
    private String cancelURL;

    @Value("${paypal.url.return}")
    private String returnURL;

    String clientId = "AbMJhzqnrSC-o3CVKbsBIN5fHupQ1Eq-w5ijk_axB0WkTvcNsP-R2JkoY_1S4Ei4ZF_j4dJx67JJsPuE";
    String clientSecret = "EI-sJFJjfBHUvxczbjqDuQVvsDUPyKzRBjj8Zo7tVZwpFvBp5vHtFQzkTAcUYD0Ww1FPpJIonN1-Sh-X";

    @Autowired
    private GuestPaymentDao guestPaymentDao;
    @Autowired
    private ConferenceDao conferenceDao;

    @Autowired
    private CurrencyValueDao currencyValueDao;

    @Autowired
    private GuestDao guestDao;

    public Map<String, Object> createPayment(Long conferenceId, CurrencyValueEntity currencyValueEntity) {
        Map<String, Object> response = new HashMap<String, Object>();
        Amount amount = new Amount();

        amount.setCurrency(currencyValueEntity.getCurrency().toString());
        amount.setTotal(currencyValueEntity.getAmount().toString());
        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        List<Transaction> transactions = new ArrayList<Transaction>();
        transactions.add(transaction);

        Payer payer = new Payer();
        payer.setPaymentMethod("paypal");

        Payment payment = new Payment();
        payment.setIntent("sale");
        payment.setPayer(payer);
        payment.setTransactions(transactions);

        RedirectUrls redirectUrls = new RedirectUrls();
        redirectUrls.setReturnUrl(returnURL.replace("{:conferenceId}", conferenceId.toString()));
        redirectUrls.setCancelUrl(cancelURL);
        payment.setRedirectUrls(redirectUrls);
        Payment createdPayment;
        try {
            String redirectUrl = "";
            APIContext context = new APIContext(clientId, clientSecret, "sandbox");
            createdPayment = payment.create(context);
            if (createdPayment != null) {
                List<Links> links = createdPayment.getLinks();
                for (Links link : links) {
                    if (link.getRel().equals("approval_url")) {
                        redirectUrl = link.getHref();
                        break;
                    }
                }
                response.put("status", "success");
                response.put("redirect_url", redirectUrl);
            }
        } catch (PayPalRESTException e) {
            System.out.println("Error happened during payment creation!");
        }
        return response;
    }

    public GuestPaymentEntity completePayment(Long conferenceId){
        CurrencyValueEntity currencyValueEntity = currencyValueDao.getOne((long)1);
        GuestPaymentEntity guestPaymentEntity = GuestPaymentEntity.builder()
                .conference(conferenceDao.getOne(conferenceId))
                .currencyValue(currencyValueEntity)
                .guest(guestDao.getOne((long)1))
                .paymentDate(LocalDateTime.now())
                .dueDate(LocalDateTime.now())
                .status(PaymentStatus.ACCEPTED)
                .build();
        return guestPaymentDao.save(guestPaymentEntity);
    }


}
