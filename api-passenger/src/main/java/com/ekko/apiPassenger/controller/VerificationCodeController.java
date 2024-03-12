package com.ekko.apiPassenger.controller;

import com.ekko.apiPassenger.request.VerificationCodeDTO;
import com.ekko.apiPassenger.service.VerificationCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VerificationCodeController {

    @Autowired
    private VerificationCodeService verificationCodeService;

    @GetMapping("/verification-code")
    public String verificationCode(@RequestBody VerificationCodeDTO verificationCodeDTO) {


        String passengerPhone = verificationCodeDTO.getPassengerPhone();

        System.out.println("phone number: " + passengerPhone);

        return verificationCodeService.generatorCode(passengerPhone);
    }
}
