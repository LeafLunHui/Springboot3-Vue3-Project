package com.athuyanhong.interceptors;

import com.athuyanhong.utils.JwtUtil;
import com.athuyanhong.utils.ThreadLocalUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * @Author Wei_Rong
 * @Description
 * @Date 2023-11-08-0008 15:55
 * @Package_Name com.athuyanhong.interceptors
 * @Project_Name SpringBoot-Vue-Program
 */


@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        final var token = request.getHeader("Authorization");
        try {
            final var stringObjectMap = JwtUtil.parseToken(token);
            String username = (String) stringObjectMap.get("username");
            final var redisSavedToken = stringRedisTemplate.opsForValue().get(username);
            if(redisSavedToken==null){
                response.setStatus(401);
                return false;
            }
            if(!redisSavedToken.equals(token)){
                response.setStatus(401);
                return false;
            }
            ThreadLocalUtil.set(stringObjectMap);
            return true;
        } catch (Exception e) {
            response.setStatus(401);
            return false;
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        //清空内存中的数据防止数据泄露
        ThreadLocalUtil.remove();
    }
}
