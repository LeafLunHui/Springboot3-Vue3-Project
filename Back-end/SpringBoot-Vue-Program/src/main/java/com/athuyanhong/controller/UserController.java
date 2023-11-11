package com.athuyanhong.controller;

import com.athuyanhong.pojo.Result;
import com.athuyanhong.pojo.User;
import com.athuyanhong.service.UserService;
import com.athuyanhong.utils.JwtUtil;
import com.athuyanhong.utils.Md5Util;
import com.athuyanhong.utils.ThreadLocalUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @Author Wei_Rong
 * @Description
 * @Date 2023-11-08-0008 14:24
 * @Package_Name com.athuyanhong.controller
 * @Project_Name SpringBoot-Vue-Program
 */


@RestController
@RequestMapping("/user")
@Validated
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    @PostMapping("/register")
    public Result register(@Pattern(regexp = "^\\S{5,16}$") String username,
                           @Pattern(regexp = "^\\S{5,16}$") String password) {

//        if (username==null || username.length()<5 || username.length()>16) {
//            return Result.error("用户名格式错误！");
//        }
//
//        if (password==null || password.length()<5 || password.length()>16) {
//            return Result.error("密码格式错误！");
//        }

        User user = userService.findByUserName(username);
        if (user != null) {
            return Result.error("用户名已经被占用了！！");
        } else {
            return userService.register(username, password);
        }
    }


    @PostMapping(value = "/login", produces = "application/json")
    public Result<String> login(@Pattern(regexp = "^\\S{5,16}$") String username,
                                @Pattern(regexp = "^\\S{5,16}$") String password) {
        //根据用户名去查询用户
        User loginUser = userService.findByUserName(username);
        if (loginUser == null) {
            return Result.error("用户名错误!!");
        }

        if (Md5Util.checkPassword(password, loginUser.getPassword())) {
            Map<String,Object> loginUserClaims= new HashMap<>();
            loginUserClaims.put("id",loginUser.getId());
            loginUserClaims.put("username",loginUser.getUsername());
            String token = JwtUtil.genToken(loginUserClaims);

            stringRedisTemplate.opsForValue().set(username,token,1, TimeUnit.HOURS);

            return Result.success(token);
        }
        return Result.error("Password is wrong!");
    }

    @GetMapping("/userInfo")
    public Result<User> UserInfo(HttpServletRequest request){
//        String token = request.getHeader("Authorization");
        Map<String,Object> map = ThreadLocalUtil.get();
        final var username = map.get("username");
        User user = userService.findByUserName((String) username);
        return Result.success(user);
    }

    @PutMapping("/update")
    public Result UpdateUserInfo(@RequestBody @Validated User user){
        return userService.updateUserInfo(user);
    }

    @PatchMapping("/updateAvatar")
    public Result updateAvatar(@RequestParam("avatarUrl") @URL String avatarUrl){
        return userService.updateAvatar(avatarUrl);
    }

    @PatchMapping(value = "/updatePwd",produces = "application/json")
    public Result<String> updatePwd(@RequestBody Map<String,String> params,@RequestHeader("Authorization") String token){
        Map<String,Object> map = ThreadLocalUtil.get();
        final var username = map.get("username");
        User user = userService.findByUserName((String) username);

        Result<String> result = userService.updatePwd(params.get("old_pwd"),
                params.get("new_pwd"),
                params.get("re_pwd"),user);

        stringRedisTemplate.opsForValue().getOperations().delete((String) username);

        return result;
    }

}
