package com.ekko.apiPassenger.service;

import com.ekko.apiPassenger.remote.ServiceVerificationcodeClient;
import com.ekko.internalcommon.dto.ResponseResult;
import com.ekko.internalcommon.response.NumberCodeResponse;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class VerificationCodeService {


    @Autowired
    private ServiceVerificationcodeClient serviceVerificationcodeClient;


    private String verificationCodePrefix = "passenger-verification-code-";


    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    public ResponseResult generatorCode(String passengerPhone) {

        //调用验证码服务，获取验证码
        System.out.println("调用服务 验证码");

        ResponseResult<NumberCodeResponse> numberCodeResponse = serviceVerificationcodeClient.getNumberCode(6);
        int numberCode = numberCodeResponse.getData().getNumberCode();

        System.out.println("remote number code:" + numberCode);

        // 存入 redis
        System.out.println("存入 redis");

        // key, value, ttl
        String key = verificationCodePrefix + passengerPhone;

        // 存入 redis
        stringRedisTemplate.opsForValue()
                .set(key, numberCode+"", 2, TimeUnit.MINUTES);


        return ResponseResult.success();
    }
}
