package com.example.demo.mapper;

import com.example.demo.model.ArticleInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ArticleInfoMapper {

    public List<ArticleInfo> getAll();

    public int delById(int id);

    public ArticleInfo detail(int id);

    // 添加文章
    public int add(ArticleInfo articleInfo);
}
