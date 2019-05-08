package com.ray.study.springboot04swagger2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger配置类
 *
 * @author shira 2019/05/07 17:10
 */
@Configuration     // 声明该类是一个配置类(等同于xml配置中的applicationContext.xml)，可以用来注册bean
@EnableSwagger2    // 启用Swagger2
public class Swagger2Config {

	@Bean
	public Docket createRestApi() {
		//设置 Swagger 扫描的包
		String basePackage = "com.ray.study.springboot04swagger2.controller";

		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(apiInfo())
				.select()
				.apis(RequestHandlerSelectors.basePackage(basePackage))
				.paths(PathSelectors.any())
				.build();
	}


	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("SpringBoot_04_整合Swagger2构建RESTfulAPI文档")
				.description("原文地址：https://github.com/shirayner/java-knowledge-hierarchy")
				.termsOfServiceUrl("https://blog.csdn.net/qq_26981333/")
				.contact("shirayner")
				.version("v1.0")
				.build();
	}
}
