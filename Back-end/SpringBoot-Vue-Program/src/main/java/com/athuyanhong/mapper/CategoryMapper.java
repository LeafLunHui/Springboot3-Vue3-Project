package com.athuyanhong.mapper;

import com.athuyanhong.pojo.Category;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @Author Wei_Rong
 * @Description
 * @Date 2023-11-08-0008 17:42
 * @Package_Name com.athuyanhong.mapper
 * @Project_Name SpringBoot-Vue-Program
 */
@Mapper
public interface CategoryMapper {

    @Select("SELECT * FROM category")
    List<Category> findCategoryAll();

    @Insert("INSERT INTO category" +
            " (category_name,category_alias,create_user,create_time,update_time) " +
            "values" +
            "(#{category.categoryName},#{category.categoryAlias},#{category.createUser},now(),now())")
    Integer addNewCategory(@Param("category") Category category);


    @Select("SELECT * FROM category where category_name = #{category_name}")
    Category findCategoryByName(@Param("category_name") String category_name);


    @Select("SELECT * FROM category where category_alias = #{category_alias}")
    Category findCategoryByAlias(@Param("category_alias") String category_alias);


    @Update("UPDATE category SET " +
            "category_name=#{category_name},category_alias=#{category_alias},update_time=now()" +
            "where id = #{categoryId}")
    Integer updateCategory(@Param("category_name") String category_name,
                           @Param("category_alias") String category_alias,@Param("categoryId") Integer categoryId);

    @Select("SELECT * FROM category where id = #{categoryId}")
    Category findCategoryById(@Param("categoryId") Integer categoryId);


    @Delete("DELETE FROM category WHERE id = #{categoryId}")
    Integer deleteCategoryById(@Param("categoryId") Integer categoryId);
}
