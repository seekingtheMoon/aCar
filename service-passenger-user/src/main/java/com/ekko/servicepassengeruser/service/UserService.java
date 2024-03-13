package com.ekko.servicepassengeruser.service;

import com.baomidou.mybatisplus.core.injector.methods.SelectByMap;
import com.ekko.internalcommon.dto.ResponseResult;
import com.ekko.servicepassengeruser.dto.PassengerUser;
import com.ekko.servicepassengeruser.mapper.PassengerUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {

    @Autowired
    private PassengerUserMapper passengerUserMapper;


    public ResponseResult loginOrRegister(String passengerPhone) {

        // 根据手机号查询用户信息
        Map<String, Object> map = new HashMap<>();
        map.put("passenger_phone", passengerPhone);
        List<PassengerUser> passengerUsers = passengerUserMapper.selectByMap(map);
        System.out.println(passengerUsers.isEmpty() ? "无记录" : passengerUsers.get(0).getPassengerPhone());

        // 判断用户信息是否存在

        // 如果不存在，插入用户信息

        System.out.println("userService Here !");
        System.out.println("手机号： " + passengerPhone);

        return ResponseResult.success();
    }
}
