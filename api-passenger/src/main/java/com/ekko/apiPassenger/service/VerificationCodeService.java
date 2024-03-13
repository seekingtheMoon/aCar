package com.ekko.apiPassenger.service;

import com.ekko.apiPassenger.remote.ServiceVerificationcodeClient;
import com.ekko.internalcommon.constant.CommonStatusEnum;
import com.ekko.internalcommon.dto.ResponseResult;
import com.ekko.internalcommon.response.NumberCodeResponse;
import com.ekko.internalcommon.response.TokenResponse;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
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
     *
     * @param passengerPhone 用户手机号
     * @return
     */
    public ResponseResult generatorCode(String passengerPhone) {

        // 获取生成的验证码
        ResponseResult<NumberCodeResponse> numberCodeResponse = serviceVerificationcodeClient.getNumberCode(6);
        int numberCode = numberCodeResponse.getData().getNumberCode();


        // 创建 key, value, ttl
        String key = generatorKeyByPhone(passengerPhone);
        // 存入 redis
        stringRedisTemplate.opsForValue()
                .set(key, numberCode + "", 2, TimeUnit.MINUTES);


        return ResponseResult.success();
    }

    /**
     * 根据手机号生成 key
     *
     * @param passengerPhone 用户手机号
     * @return
     */
    private String generatorKeyByPhone(String passengerPhone) {
        return verificationCodePrefix + passengerPhone;
    }

    /**
     * 校验验证码
     *
     * @param passengerPhone   用户手机号
     * @param verificationCode 用户填写的验证码
     * @return
     */
    public ResponseResult checkCode(String passengerPhone, String verificationCode) {

        // 根据手机号，去redis读取验证码
        //// 生成 key
        String key = generatorKeyByPhone(passengerPhone);

        //// 根据 key 读取 value
        String codeRedis = stringRedisTemplate.opsForValue().get(key);
        System.out.println("redis 中 val " + codeRedis);

        // 校验验证码
        //// 验证码为空
        if (StringUtils.isBlank(codeRedis)) {
            return ResponseResult.fail(
                    CommonStatusEnum.VERIFICATION_CODE_ERROR.getCode(),
                    CommonStatusEnum.VERIFICATION_CODE_ERROR.getValue()
            );
        }
        //// 用户提供验证码与redis中验证码不匹配
        if (!verificationCode.trim().equals(codeRedis.trim())) {
            return ResponseResult.fail(
                    CommonStatusEnum.VERIFICATION_CODE_ERROR.getCode(),
                    CommonStatusEnum.VERIFICATION_CODE_ERROR.getValue()
            );
        }

        // 判断原来是否有用户，并进行对应的处理
        System.out.println("判断用户类型，对应处理");

        // 颁发令牌
        System.out.println("颁发令牌");

        TokenResponse tokenResponse = new TokenResponse();
        tokenResponse.setToken("token value");


        return ResponseResult.success(tokenResponse);
    }
}
