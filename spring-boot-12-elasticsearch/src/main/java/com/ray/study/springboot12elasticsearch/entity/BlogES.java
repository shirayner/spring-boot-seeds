package com.ray.study.springboot12elasticsearch.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;

/**
 * UserES
 *
 * @author ray
 * @date 2019/10/15
 */
@Data
@Document(indexName = "blog", type = "blog")
public class BlogES implements Serializable {

    @Id
    private Long id;

    //@Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String author;

    private String title;

    private String summary;

    private String content;


}
