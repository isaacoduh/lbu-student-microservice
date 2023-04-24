package com.example.studentmcs.service;

import com.example.studentmcs.model.Account;
import com.example.studentmcs.model.Invoice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.http.HttpHeaders;


@Service
public class IntegrationService {
    @Autowired
    private final WebClient webClient;

    public IntegrationService(WebClient webClient) {
        this.webClient = webClient;
    }

    public void postInvoiceData(Invoice invoice)
    {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Invoice> requestEntity = new HttpEntity<>(invoice, headers);

        ResponseEntity<Void> responseEntity = webClient.post()
                .uri("http://localhost:3500/api/v1/invoices")
                .headers(httpHeaders -> httpHeaders.addAll(headers))
                .bodyValue(invoice)
                .retrieve()
                .toBodilessEntity()
                .block();
        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            // Request succeeded
            System.out.println("Invoice data posted successfully");
            System.out.print(responseEntity.getBody());
        } else {
            // Request failed
            System.err.println("Failed to post invoice data. Status code: " + responseEntity.getStatusCode());
        }
    }

    public void postAccountData(Account account)
    {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Account> requestEntity = new HttpEntity<>(account, headers);

        ResponseEntity<Void> responseEntity = webClient.post()
                .uri("http://localhost:3500/api/v1/accounts")
                .headers(httpHeaders -> httpHeaders.addAll(headers))
                .bodyValue(account)
                .retrieve()
                .toBodilessEntity()
                .block();

        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            // Request succeeded
            System.out.println("Account data posted successfully");
            System.out.print(responseEntity.getBody());
        } else {
            // Request failed
            System.err.println("Failed to post account data. Status code: " + responseEntity.getStatusCode());
        }

    }

}
