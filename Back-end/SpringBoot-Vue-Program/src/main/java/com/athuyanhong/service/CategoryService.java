package com.athuyanhong.service;

import com.athuyanhong.pojo.Category;
import com.athuyanhong.pojo.Result;

import java.util.List;
import java.util.Map;

/**
 * @Author Wei_Rong
 * @Description
 * @Date 2023-11-08-0008 17:42
 * @Package_Name com.athuyanhong.service
 * @Project_Name SpringBoot-Vue-Program
 */
public interface CategoryService {

    List<Category> findAllCategory();

    Result addNewCategory(String category_name, String category_alias);

    Result updateCategory(Category category);

    Result<Category> categoryDetail(Integer id);

    Result deleteCategoryById(Integer categoryId);
}
