package com.ekko.apiPassenger.request;


import lombok.Data;

@Data
public class VerificationCodeDTO {

    /**
     * 用户手机号码
     */
    private String passengerPhone;

    /**
     * 验证码
     */
    private String verificationCode;


}
