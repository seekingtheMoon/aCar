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

        System.out.println("size: " + size);

        // 生成验证码
        double mathRandom = (Math.random() * 9 + 1) * (int)(Math.pow(10, size - 1));
        int resultInt = (int) mathRandom;

        NumberCodeResponse response = new NumberCodeResponse();
        response.setNumberCode(resultInt);

        System.out.println(response.getNumberCode());

        // 返回
        return ResponseResult.success(response);
    }
}
