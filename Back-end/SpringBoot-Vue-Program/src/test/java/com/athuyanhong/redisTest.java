package com.athuyanhong;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.concurrent.TimeUnit;

/**
 * @Author Wei_Rong
 * @Description
 * @Date 2023-11-09-0009 15:45
 * @Package_Name com.athuyanhong
 * @Project_Name SpringBoot-Vue-Program
 */

@SpringBootTest
public class redisTest {

    @Autowired
    StringRedisTemplate redisTemplate;

    @Test
    void testRedisSet(){
        redisTemplate.opsForValue().set("username","张三",15, TimeUnit.SECONDS);
    }

    @Test
    void testRedisGet(){
        System.out.println(redisTemplate.opsForValue().get("username"));

    }
}
