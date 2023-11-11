package com.athuyanhong.controller;

import com.athuyanhong.pojo.Category;
import com.athuyanhong.pojo.Category.Add;
import com.athuyanhong.pojo.Category.Update;
import com.athuyanhong.pojo.Result;
import com.athuyanhong.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author Wei_Rong
 * @Description
 * @Date 2023-11-08-0008 17:40
 * @Package_Name com.athuyanhong.controller
 * @Project_Name SpringBoot-Vue-Program
 */

@RestController
public class CategoryController {

    @Autowired
    private CategoryService categoryService;


    @GetMapping("/category")
    public Result<List<Category>> list(){
        return Result.success(categoryService.findAllCategory());
    }

    @PostMapping("/category")
    public Result addNewCategory(@RequestBody @Validated(Add.class) Category category){
        return categoryService.addNewCategory(category.getCategoryName(),category.getCategoryAlias());
    }

    @PutMapping("/category")
    public Result updateCategory(@RequestBody @Validated(Update.class) Category category){
        return categoryService.updateCategory(category);
    }

    @GetMapping("/category/detail")
    public Result<Category> categoryDetail(@RequestParam("id") Integer id){
        return categoryService.categoryDetail(id);
    }

    @DeleteMapping("/category")
    public Result deleteCategory(@RequestParam("id") Integer id){
        return categoryService.deleteCategoryById(id);
    }
}
