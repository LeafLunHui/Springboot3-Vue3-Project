package com.athuyanhong.controller;

import com.athuyanhong.pojo.Article;
import com.athuyanhong.pojo.PageBean;
import com.athuyanhong.pojo.Result;
import com.athuyanhong.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @Author Wei_Rong
 * @Description
 * @Date 2023-11-08-0008 15:23
 * @Package_Name com.athuyanhong.controller
 * @Project_Name SpringBoot-Vue-Program
 */

@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @PostMapping
    public Result add(@RequestBody @Validated(Article.Add.class) Article article){
        return articleService.add(article);
    }

    @GetMapping
    public Result<PageBean<Article>> list(@RequestParam Integer pageNum,
                                          @RequestParam Integer pageSize,
                                          @RequestParam(required = false) Integer categoryId,
                                          @RequestParam(required = false) String state
                                          ){
        return Result.success(articleService.list(pageNum,pageSize,categoryId,state));
    }

    @DeleteMapping
    public Result deleteArticleById(@RequestParam Integer id){
        return articleService.delete(id);
    }


    @GetMapping("/detail")
    public Result<Article> articleDetail(@RequestParam Integer id){
        return articleService.getDetailById(id);
    }

    @PutMapping
    public Result updateArticle(@RequestBody @Validated(Article.Update.class) Article article){
        return articleService.updateArticle(article);
    }
}
