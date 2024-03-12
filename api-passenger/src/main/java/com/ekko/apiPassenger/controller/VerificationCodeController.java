package com.ekko.apiPassenger.controller;

import com.ekko.apiPassenger.request.VerificationCodeDTO;
import com.ekko.apiPassenger.service.VerificationCodeService;
import com.ekko.internalcommon.dto.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VerificationCodeController {

    @Autowired
    private VerificationCodeService verificationCodeService;

    @GetMapping("/verification-code")
    public ResponseResult verificationCode(@RequestBody VerificationCodeDTO verificationCodeDTO) {


        String passengerPhone = verificationCodeDTO.getPassengerPhone();


        return verificationCodeService.generatorCode(passengerPhone);

    }
}
