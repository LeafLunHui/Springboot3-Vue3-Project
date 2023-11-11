package com.athuyanhong.mapper;

import com.athuyanhong.pojo.User;
import org.apache.ibatis.annotations.*;

/**
 * @Author Wei_Rong
 * @Description
 * @Date 2023-11-08-0008 14:30
 * @Package_Name com.athuyanhong.mapper
 * @Project_Name SpringBoot-Vue-Program
 */

@Mapper
public interface UserMapper {


    @Select("select * from user where username=#{username}")
    User findByUserName(@Param("username") String username);


    @Insert("insert into user (username,password,create_time,update_time) values" +
            " (#{username},#{password},now(),now())")
    int RegisterUser(@Param("username") String username,@Param("password") String password);


    @Update("UPDATE user SET nickname=#{user.nickname},email=#{user.email},update_time=#{user.updateTime} where id=#{user.id}")
    int updateUserInfo(@Param("user") User user);


    @Update("UPDATE user SET user_pic=#{avatarUrl},update_Time=now() where id=#{id}")
    int updateAvatar(String avatarUrl,Integer id);

    @Update("UPDATE user set password=#{password},update_time=now() where id = #{id}")
    int updatePwd(String password,Integer id);

}
