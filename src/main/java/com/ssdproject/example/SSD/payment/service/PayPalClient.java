package com.ssdproject.example.SSD.payment.service;

import com.paypal.api.payments.*;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import com.ssdproject.example.SSD.payment.model.entity.CurrencyValueEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PayPalClient {

    @Value("${paypal.url.cancel}")
    private String calncelURL;

    @Value("${paypal.url.return}")
    private String returnURL;

    String clientId = "AbMJhzqnrSC-o3CVKbsBIN5fHupQ1Eq-w5ijk_axB0WkTvcNsP-R2JkoY_1S4Ei4ZF_j4dJx67JJsPuE";
    String clientSecret = "EI-sJFJjfBHUvxczbjqDuQVvsDUPyKzRBjj8Zo7tVZwpFvBp5vHtFQzkTAcUYD0Ww1FPpJIonN1-Sh-X";

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
        redirectUrls.setCancelUrl(calncelURL);
        redirectUrls.setReturnUrl(returnURL);
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

    public Map<String, Object> completePayment(HttpServletRequest req){
        Map<String, Object> response = new HashMap();
        Payment payment = new Payment();
        payment.setId(req.getParameter("paymentId"));

        PaymentExecution paymentExecution = new PaymentExecution();
        paymentExecution.setPayerId(req.getParameter("PayerID"));
        try {
            APIContext context = new APIContext(clientId, clientSecret, "sandbox");
            Payment createdPayment = payment.execute(context, paymentExecution);
            if(createdPayment!=null){
                response.put("status", "success");
                response.put("payment", createdPayment);
            }
        } catch (PayPalRESTException e) {
            System.err.println(e.getDetails());
        }
        return response;
    }


}
