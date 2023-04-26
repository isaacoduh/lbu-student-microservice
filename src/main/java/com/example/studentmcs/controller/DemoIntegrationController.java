package com.example.studentmcs.controller;

import com.example.studentmcs.model.Account;
import com.example.studentmcs.model.Invoice;
import com.example.studentmcs.service.IntegrationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Date;

@RestController
@RequestMapping("/api/un/webhook/")
public class DemoIntegrationController {

    private final IntegrationService integrationService;

    public DemoIntegrationController(IntegrationService integrationService)
    {
        this.integrationService = integrationService;
    }


    @PostMapping("/createAccount")
    public ResponseEntity<?> testPostFinance() {
        Account account = new Account();

        account.setId(1L);
        account.setStudentId("c1345833");
        account.setHasOutstandingBalance(false);
        System.out.print(account);
        integrationService.postAccountData(account);

        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @PostMapping("/createInvoice")
    public ResponseEntity<?> testPostInvoice(){
        Account account = new Account();

        account.setId(1L);
        account.setStudentId("c1345833");
        account.setHasOutstandingBalance(false);

        Invoice invoice = new Invoice();

        invoice.setId(1L);
        invoice.setReference("34HH3478");
        invoice.setAmount(300.00);
        invoice.setDueDate(LocalDate.of(2023, 04, 30));
        invoice.setStatus(Invoice.Status.OUTSTANDING);
        invoice.setType(Invoice.Type.LIBRARY_FINE);
        invoice.setAccount(account);

        System.out.print(invoice);

        integrationService.postInvoiceData(invoice);
        return new ResponseEntity<>(null, HttpStatus.OK);

    }
}
