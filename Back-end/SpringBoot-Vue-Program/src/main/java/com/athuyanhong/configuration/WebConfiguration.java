package com.athuyanhong.configuration;

import com.athuyanhong.interceptors.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author Wei_Rong
 * @Description
 * @Date 2023-11-08-0008 15:58
 * @Package_Name com.athuyanhong.config
 * @Project_Name SpringBoot-Vue-Program
 */

@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    @Autowired
    private LoginInterceptor interceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(interceptor)
                .excludePathPatterns("/user/login","/user/register","/swagger-ui/**","/api-docs/**","/swagger-ui.html");
    }
}
