package com.example.studentmcs.service;

import com.example.studentmcs.model.Account;
import com.example.studentmcs.model.Invoice;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.http.HttpHeaders;
import reactor.core.publisher.Mono;


@Service
public class IntegrationService {
    @Autowired
    private final WebClient webClient;

    public IntegrationService(WebClient webClient) {
        this.webClient = webClient;
    }

    public Boolean getGraduationStatus(String studentId){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> requestEntity = new HttpEntity<>(null, headers);
        return webClient.get()
                .uri("http://localhost:3500/api/v1/check/"+studentId+"/status")
                .headers(httpHeaders -> httpHeaders.addAll(headers))
                .retrieve()
//                .bodyToMono();
//                .toBodilessEntity()
//                .block();
                .bodyToMono(Boolean.class).block();
    }
    public Mono getGraudationStatusForStudentId(String studentId){

        System.out.print("String from integration service \n: " + studentId);
        return webClient.get()
                .uri("http://localhost:3500/api/v1/check/" + studentId + "/status")
                .retrieve()
                .bodyToMono(Boolean.class);
    }

    private static class StudentStatus {
        private boolean hasOutstandingBalance;

        public boolean isHasOutstandingBalance() {
            return hasOutstandingBalance;
        }

        public void setHasOutstandingBalance(boolean hasOutstandingBalance) {
            this.hasOutstandingBalance = hasOutstandingBalance;
        }
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

    public void postLibraryData(Account account) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Account> requestEntity = new HttpEntity<>(account, headers);
        ResponseEntity<Void> responseEntity = webClient.post()
                .uri("http://localhost:8001/api/v1/register")
                .bodyValue(account)
                .retrieve()
                .toBodilessEntity()
                .block();
        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            // Request succeeded
            System.out.println("Library  data posted successfully");
            System.out.print(responseEntity.getBody());
        } else {
            // Request failed
            System.err.println("Failed to post account data. Status code: " + responseEntity.getStatusCode());
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
