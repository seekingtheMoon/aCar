package com.ekko.apiPassenger.service;

import com.ekko.apiPassenger.remote.ServiceVerificationcodeClient;
import com.ekko.internalcommon.dto.ResponseResult;
import com.ekko.internalcommon.response.NumberCodeResponse;
import com.ekko.internalcommon.response.TokenResponse;
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


    /**
     * 生成验证码
     * @param passengerPhone 用户手机号
     * @return
     */
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

    /**
     * 校验验证码
     * @param passengerPhone 用户手机号
     * @param verificationCode 用户填写的验证码
     * @return
     */
    public ResponseResult checkCode(String passengerPhone, String verificationCode) {

        // 根据手机号，去redis读取验证码
        System.out.println("访问redis，获取验证码");

        // 校验验证码
        System.out.println("校验用户发送的验证码与redis中验证码是否一致");

        // 判断原来是否有用户，并进行对应的处理
        System.out.println("判断用户类型，对应处理");

        // 颁发令牌
        System.out.println("颁发令牌");

        TokenResponse tokenResponse = new TokenResponse();
        tokenResponse.setToken("token value");


        return ResponseResult.success(tokenResponse);
    }
}
