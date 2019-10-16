package com.ray.study.springboot12elasticsearch.repository;

import com.ray.study.springboot12elasticsearch.entity.BlogES;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * UserESRepository
 *
 * @author ray
 * @date 2019/10/15
 */
@Repository
public interface BlogESRepository extends ElasticsearchRepository<BlogES, Long> {

    List<BlogES> findByTitle(String title);

}
