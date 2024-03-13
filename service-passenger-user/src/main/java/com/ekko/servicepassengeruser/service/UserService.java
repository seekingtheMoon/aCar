package com.ekko.servicepassengeruser.service;

import com.ekko.internalcommon.dto.ResponseResult;
import org.springframework.stereotype.Service;

@Service
public class UserService {


    public ResponseResult loginOrRegister(String passengerPhone) {

        // 根据手机号查询用户信息

        // 判断用户信息是否存在

        // 如果不存在，插入用户信息

        System.out.println("userService Here !");
        System.out.println("手机号： " + passengerPhone);

        return ResponseResult.success();
    }
}
