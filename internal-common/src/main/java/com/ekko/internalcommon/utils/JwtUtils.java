package com.ekko.internalcommon.utils;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

import javax.lang.model.type.TypeKind;
import java.util.*;

public class JwtUtils {

    // 盐
    private static final String SIGN = "EKKO#%^^3556";


    // 生成token
    public static String generatorToken(String passengerPhone) {
        Map<String, String> map = new HashMap<>();
        map.put("passengerPhone", passengerPhone);

        // 签发过期时间
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 1);
        Date date = calendar.getTime();
        JWTCreator.Builder builder = JWT.create();


        // 整合map
        map.forEach(builder::withClaim);


        // 整合过期时间
        builder.withExpiresAt(date);

        // 生成token 并返回
        return builder.sign(Algorithm.HMAC256(SIGN));
    }

    // 解析token
//    public static DecodedJWT paserToken(String token) {
//        return JWT.require(Algorithm.HMAC256(SIGN)).build().verify(token);
//    }
    public static String parseToken(String token) {
        DecodedJWT verify = JWT.require(Algorithm.HMAC256(SIGN)).build().verify(token);
        return verify.getClaim("passengerPhone").toString();

    }


    public static void main(String[] args) {
        String token = generatorToken("15999145585");
        System.out.println(token);
        System.out.println(parseToken(token));
    }
}
