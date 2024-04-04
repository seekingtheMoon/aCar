package com.ekko.internalcommon.utils;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.ekko.internalcommon.constant.IdentityConstant;
import com.ekko.internalcommon.dto.TokenResult;

import javax.lang.model.type.TypeKind;
import java.util.*;

public class JwtUtils {

    // 盐
    private static final String SIGN = "EKKO#%^^3556";

    private static final String JWT_KEY_PHONE = "phone";

    // 乘客是  1 ， 司机是 2
    private static final String JWT_KEY_IDENTITY = "identity";


    // 生成token, 手机号，身份信息
    public static String generatorToken(String phone, String identity) {
        Map<String, String> map = new HashMap<>();
        map.put(JWT_KEY_PHONE, phone);
        map.put(JWT_KEY_IDENTITY, identity);

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
    public static TokenResult parseToken(String token) {
        DecodedJWT verify = JWT.require(Algorithm.HMAC256(SIGN)).build().verify(token);
        String phone = verify.getClaim(JWT_KEY_PHONE).toString();
        String identity = verify.getClaim(JWT_KEY_IDENTITY).toString();

        TokenResult tokenResult = new TokenResult();
        tokenResult.setPhone(phone);
        tokenResult.setIdentity(identity);

        return tokenResult;
    }


    public static void main(String[] args) {
        String token = generatorToken("15999145585", IdentityConstant.PASSENGER.getIdentity());
        System.out.println(token);
        System.out.println(parseToken(token));
    }
}
