package com.athuyanhong.mapper;

import com.athuyanhong.pojo.Article;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @Author Wei_Rong
 * @Description
 * @Date 2023-11-08-0008 21:46
 * @Package_Name com.athuyanhong.mapper
 * @Project_Name SpringBoot-Vue-Program
 */
@Mapper
public interface ArticleMapper {

    @Insert("insert into article" +
            "(title,content,cover_img,state,category_id,create_user,create_time,update_time)" +
            "values " +
            "(#{title},#{content},#{coverImg},#{state},#{categoryId},#{createUser},now(),now())")
    Integer addNewArticle(Article article);

    List<Article> articleList(Integer userId,Integer categoryId,String state);


    @Delete("DELETE FROM article WHERE id=#{id}")
    Integer deleteById(Integer id);


    @Select("select * from article where id = #{id}")
    Article findById(Integer id);

    @Update("UPDATE article SET " +
            "title=#{title},content=#{content},cover_img=#{coverImg},state=#{state},category_id=#{categoryId},update_time=now()" +
            " WHERE id = #{id}")
    Integer updateArticle(Article article);
}
