package com.athuyanhong.service;

import com.athuyanhong.pojo.Result;
import com.athuyanhong.pojo.User;

/**
 * @Author Wei_Rong
 * @Description
 * @Date 2023-11-08-0008 14:24
 * @Package_Name com.athuyanhong.service
 * @Project_Name SpringBoot-Vue-Program
 */
public interface UserService {
    User findByUserName(String username);

    Result register(String username, String password);

    Result updateUserInfo(User user);

    Result updateAvatar(String avatarUrl);

    Result updatePwd(String old_password,String new_password,String re_password,User loginUser);



}
