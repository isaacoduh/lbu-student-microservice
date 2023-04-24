package com.example.studentmcs.controller;

import com.example.studentmcs.model.Account;
import com.example.studentmcs.service.IntegrationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
