package com.ray.study.springboot10configreadconfigproperty.config;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 配置属性类
 *  属性映射规则： 配置文件中配置属性的"-"和"_"会被去掉并转成驼峰形式
 *   也就是说配置文件中的 homePage 可以写成： home-page/home_page/homePage
 *
 * @author shira 2019/05/16 21:18
 */
@Data
@NoArgsConstructor
@Component
@ConfigurationProperties(prefix = "csdn.blog")
public class BlogProperties {

	private String name;

	private String homePage;

	private List<Article> articles;

}
