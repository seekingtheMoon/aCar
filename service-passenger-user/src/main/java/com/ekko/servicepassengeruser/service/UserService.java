package com.ekko.servicepassengeruser.service;

import com.baomidou.mybatisplus.core.injector.methods.SelectByMap;
import com.ekko.internalcommon.dto.ResponseResult;
import com.ekko.servicepassengeruser.dto.PassengerUser;
import com.ekko.servicepassengeruser.mapper.PassengerUserMapper;
import com.mysql.cj.protocol.PacketSentTimeHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
        System.out.println(passengerUsers.isEmpty() ? "无记录" : passengerUsers.get(0).getPassengerName());

        // 判断用户信息是否存在
        if (passengerUsers.isEmpty()) {
            // 如果不存在，新建用户
            // 设置用户名,性别，手机号，状态
            PassengerUser passengerUser = new PassengerUser();
            passengerUser.setPassengerName(generateDefalutName());
            passengerUser.setPassengerGender((byte) 0);
            passengerUser.setPassengerPhone(passengerPhone);
            passengerUser.setState((byte) 0);

            LocalDateTime now = LocalDateTime.now();
            passengerUser.setGmtCreate(now);
            passengerUser.setGmtModified(now);

            passengerUserMapper.insert(passengerUser);
        }

        return ResponseResult.success();
    }

    private String generateDefalutName() {
        String curMill = LocalDateTime.now().toString().substring(21,26);
        return "#" + curMill;
    }


}
