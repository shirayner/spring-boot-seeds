package com.ray.study.springboot12elasticsearch.controller;

import com.ray.study.springboot12elasticsearch.entity.BlogES;
import com.ray.study.springboot12elasticsearch.repository.BlogESRepository;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;
import java.util.ArrayList;
import java.util.List;

/**
 * UserController
 *
 * @author ray
 * @date 2019/10/16
 */
@RestController
@RequestMapping("/blog")
public class BlogController {

    @Autowired
    private BlogESRepository blogESRepository;

    @GetMapping("")
    public List list() {
        Iterable<BlogES> blogESList = blogESRepository.findAll();
        List blogList= new ArrayList();
        blogESList.forEach(blogES -> {
            blogList.add(blogES);
        });
        return blogList;
    }

    @GetMapping("/search")
    public Page search(@RequestParam("keyword") String keyword) {
        // 构建查询条件
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        // 添加基本分词查询
        //queryBuilder.withQuery(QueryBuilders.matchQuery("author", keyword));
        queryBuilder.withQuery(QueryBuilders.matchPhraseQuery("author", keyword));
        // 搜索，获取结果
        Page<BlogES> blogESPage = blogESRepository.search(queryBuilder.build());
        return blogESPage;
    }

    @PostMapping("/add")
    public BlogES create(@RequestBody BlogES blogES) {
        return blogESRepository.save(blogES);
    }

}
