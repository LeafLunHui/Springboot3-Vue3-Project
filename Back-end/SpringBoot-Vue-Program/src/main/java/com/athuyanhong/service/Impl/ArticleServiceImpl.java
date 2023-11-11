package com.athuyanhong.service.Impl;

import com.athuyanhong.mapper.ArticleMapper;
import com.athuyanhong.pojo.Article;
import com.athuyanhong.pojo.PageBean;
import com.athuyanhong.pojo.Result;
import com.athuyanhong.service.ArticleService;
import com.athuyanhong.utils.ThreadLocalUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * @Author Wei_Rong
 * @Description
 * @Date 2023-11-08-0008 21:43
 * @Package_Name com.athuyanhong.service.Impl
 * @Project_Name SpringBoot-Vue-Program
 */

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public Result add(Article article) {
        article.setCreateTime(LocalDateTime.now());
        article.setUpdateTime(LocalDateTime.now());
        Map<String,Object> map = ThreadLocalUtil.get();
        article.setCreateUser((Integer) map.get("id"));

        final var code = articleMapper.addNewArticle(article);

        if (code > 0) {
            return Result.success();
        } else {
            return Result.error("添加失败！");
        }

    }

    @Override
    public PageBean<Article> list(Integer pageNum, Integer pageSize, Integer categoryId, String state) {
        PageBean<Article> pageBean = new PageBean<>();

        PageHelper.startPage(pageNum,pageSize);
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");

        List<Article> list = articleMapper.articleList(userId,categoryId,state);
        Page<Article> page = (Page<Article>) list;

        pageBean.setTotal(page.getTotal());
        pageBean.setItems(page.getResult());
        return pageBean;
    }

    @Override
    public Result delete(Integer id) {
        final var code = articleMapper.deleteById(id);
        if (code > 0) {
            return Result.success();
        } else {
            return Result.error("删除失败！");
        }

    }

    @Override
    public Result<Article> getDetailById(Integer id) {
        final var article = articleMapper.findById(id);
        if(article == null){
            return Result.error("请检查ID是否正确");
        }
        return Result.success(article);
    }

    @Override
    public Result updateArticle(Article article) {

        final var code = articleMapper.updateArticle(article);

        if (code > 0) {
            return Result.success();
        } else {
            return Result.error("更新失败！");
        }

    }



}
