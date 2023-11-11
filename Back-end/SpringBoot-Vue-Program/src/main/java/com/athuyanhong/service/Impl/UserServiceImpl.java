package com.athuyanhong.service.Impl;

import com.athuyanhong.mapper.UserMapper;
import com.athuyanhong.pojo.Result;
import com.athuyanhong.pojo.User;
import com.athuyanhong.service.UserService;
import com.athuyanhong.utils.Md5Util;
import com.athuyanhong.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * @Author Wei_Rong
 * @Description
 * @Date 2023-11-08-0008 14:24
 * @Package_Name com.athuyanhong.service.Impl
 * @Project_Name SpringBoot-Vue-Program
 */

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    UserMapper userMapper;

    @Override
    public User findByUserName(String username) {
        return userMapper.findByUserName(username);
    }

    @Override
    public Result register(String username, String password) {
        final var passwordMd5String = Md5Util.getMD5String(password);
        final var code = userMapper.RegisterUser(username, passwordMd5String);
        if (code > 0) {
            return Result.success();
        } else {
            return Result.error("数据库插入失败！");
        }
    }

    @Override
    public Result updateUserInfo(User user) {

        user.setUpdateTime(LocalDateTime.now());
        final var code = userMapper.updateUserInfo(user);

        if (code > 0) {
            return Result.success();
        } else {
            return Result.error("更新用户信息失败！");
        }
    }

    @Override
    public Result updateAvatar(String avatarUrl) {
        Map<String, Object> map = ThreadLocalUtil.get();
        final var userId = map.get("id");
        final var code = userMapper.updateAvatar(avatarUrl, (Integer) userId);
        if (code > 0) {
            return Result.success();
        } else {
            return Result.error("上传用户头像失败！");
        }
    }

    @Override
    public Result updatePwd(String old_password, String new_password, String re_password,User loginUser) {
        if(!StringUtils.hasLength(old_password) ||
                !StringUtils.hasLength(new_password) ||
                !StringUtils.hasLength(re_password)){
            return Result.error("缺少必要的参数!");
        }

        if(new_password.equals(re_password)==false){
            return Result.error("重复新密码不一致");
        }
        final var userId = loginUser.getId();
        if (Md5Util.checkPassword(old_password,loginUser.getPassword())==false) {
            return Result.error("旧密码错误！");
        }

        final var code = userMapper.updatePwd(Md5Util.getMD5String(new_password),userId);

        if (code > 0) {
            return Result.success();
        } else {
            return Result.error("更新密码失败！");
        }
    }


}
