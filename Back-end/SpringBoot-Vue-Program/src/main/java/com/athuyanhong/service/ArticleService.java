package com.athuyanhong.service;

import com.athuyanhong.pojo.Article;
import com.athuyanhong.pojo.PageBean;
import com.athuyanhong.pojo.Result;

/**
 * @Author Wei_Rong
 * @Description
 * @Date 2023-11-08-0008 21:43
 * @Package_Name com.athuyanhong.service
 * @Project_Name SpringBoot-Vue-Program
 */
public interface ArticleService {

    Result add(Article article);

    PageBean<Article> list(Integer pageNum,Integer pageSize,Integer categoryId,String state);

    Result delete(Integer id);

    Result getDetailById(Integer id);

    Result updateArticle(Article article);
}
