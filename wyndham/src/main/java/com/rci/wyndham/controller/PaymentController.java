package com.rci.wyndham.controller;

import com.rci.wyndham.dto.LeviesModel;
import com.rci.wyndham.entity.WMSPLeviesPayment;
import com.rci.wyndham.service.PaymentService;
import com.rci.wyndham.util.SecurityHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private SecurityHelper securityHelper;

    @GetMapping("/aud-levies")
    public ResponseEntity<LeviesModel> getAudLevies() {
        String loggedOwnerNumber = securityHelper.getLoggedUser();
        LeviesModel levies = new LeviesModel();
        levies.setOwnerNumber(loggedOwnerNumber);
        return ResponseEntity.ok(levies);
    }

    @PostMapping("/aud-levies")
    public ResponseEntity<String> processAudLevies(@Valid @RequestBody LeviesModel levies, BindingResult result) {
        if (levies.getOwnerNumber().contains("1101901047") || levies.getOwnerNumber().contains("2022201376")) {
            return ResponseEntity.badRequest().body("We cannot process payments at this time");
        }

        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body("Invalid form");
        }

        paymentService.save(levies);
        return ResponseEntity.ok("Payment processed successfully");
    }
}