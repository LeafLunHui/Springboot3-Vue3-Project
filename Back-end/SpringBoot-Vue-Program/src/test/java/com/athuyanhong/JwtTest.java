package com.athuyanhong;

import com.athuyanhong.utils.Md5Util;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author Wei_Rong
 * @Description
 * @Date 2023-11-08-0008 15:33
 * @Package_Name com.athuyanhong
 * @Project_Name SpringBoot-Vue-Program
 */


public class JwtTest {


    @Test
    public void testGen(){


        Map<String, Object> claims = new HashMap<>();

        claims.put("id",1);
        claims.put("username","张三");



        String token = JWT.create().withClaim("user",claims).withExpiresAt(new Date(System.currentTimeMillis()+1000*60*60*12))
                .sign(Algorithm.HMAC256("athuyanhong2366907908"));

        System.out.println(token);
    }


    @Test
    void getMD5String(){
        System.out.println(Md5Util.getMD5String("123456"));
    }

    @Test
    void testPass(){
        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9" +
                ".eyJ1c2VyIjp7ImlkIjoxLCJ1c2VybmFtZSI6IuW8oOS4iSJ9LCJleHAiOjE2OTk0NzIxNzB9" +
                ".4dn6ZenRC7JIkkk06GM6_2XOT989XB89KqAy89LFV_s";
        final var athuyanhong2366907908 = JWT.require(Algorithm.HMAC256("athuyanhong2366907908")).build();

        DecodedJWT decodedJWT = athuyanhong2366907908.verify(token);

        Map<String, Claim> stringObjectMap = decodedJWT.getClaims();

        System.out.println(stringObjectMap.get("user"));
    }
}
