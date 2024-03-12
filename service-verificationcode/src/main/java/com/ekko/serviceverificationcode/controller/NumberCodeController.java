package com.ekko.serviceverificationcode.controller;

import com.ekko.internalcommon.dto.ResponseResult;
import com.ekko.internalcommon.response.NumberCodeResponse;
import net.sf.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NumberCodeController {

    @GetMapping("/numberCode/{size}")
    public ResponseResult numberCode(@PathVariable("size") int size) {

        // 生成验证码
        double mathRandom = (Math.random() * 9 + 1) * (int)(Math.pow(10, size - 1));
        int resultInt = (int) mathRandom;

        // 响应对象
        NumberCodeResponse response = new NumberCodeResponse();
        response.setNumberCode(resultInt);

        // 返回
        return ResponseResult.success(response);

    }
}
