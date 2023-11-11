package com.athuyanhong.service.Impl;

import com.athuyanhong.mapper.CategoryMapper;
import com.athuyanhong.pojo.Category;
import com.athuyanhong.pojo.Result;
import com.athuyanhong.service.CategoryService;
import com.athuyanhong.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * @Author Wei_Rong
 * @Description
 * @Date 2023-11-08-0008 17:42
 * @Package_Name com.athuyanhong.service.Impl
 * @Project_Name SpringBoot-Vue-Program
 */

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;


    @Override
    public List<Category> findAllCategory() {
        return categoryMapper.findCategoryAll();
    }

    @Override
    public Result addNewCategory(String category_name, String category_alias) {
        Category category_name_check = categoryMapper.findCategoryByName(category_name);
        if (category_name_check!=null) {
            return Result.error("已经存在名为" + category_name + "的分类名称");
        }

        Category category_alias_check = categoryMapper.findCategoryByAlias(category_alias);
        if (category_alias_check!=null) {
            return Result.error("已经存在名为" + category_alias + "的分类别名");
        }

        final Map<String,Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");
        Category category = new Category((Integer) null,category_name,category_alias,userId,null,null);

        final var code = categoryMapper.addNewCategory(category);

        if (code > 0) {
            return Result.success();
        } else {
            return Result.error("添加失败！");
        }

    }

    @Override
    public Result updateCategory(Category category) {
        Integer categoryId = category.getId();
        Category categorytemp = categoryMapper.findCategoryById(categoryId);
        if(categorytemp!=null){
            String newCategoryName = category.getCategoryName();
            String newCategoryAlias = category.getCategoryAlias();
            final var code = categoryMapper.updateCategory(newCategoryName, newCategoryAlias, categoryId);

            if (code > 0) {
                return Result.success();
            } else {
                return Result.error("更新信息失败！");
            }

        }else{
            return Result.error("未找到当前Id的内容");
        }
    }

    @Override
    public Result<Category> categoryDetail(Integer id) {
        Category category = categoryMapper.findCategoryById(id);
        if(category == null){
            return Result.error("请检查ID是否正确");
        }else{
            return Result.success(category);
        }
    }

    @Override
    public Result deleteCategoryById(Integer categoryId) {
        final var code = categoryMapper.deleteCategoryById(categoryId);
        if(code > 0){
            return Result.success();
        }else{
            return Result.error("请检查ID是否存在");
        }
    }


}
