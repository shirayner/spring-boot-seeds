package com.ray.study.springboot10configreadconfigproperty.config;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * 配置属性映射类
 * 注意：@PropertySource 只能加载 properties 配置文件，而无法加载 yml 配置文件
 *
 * @author shira 2019/05/16 21:18
 */
@Data
@NoArgsConstructor
@Component
//@PropertySource("classpath:user.properties")
@PropertySource(value = "classpath:user-default.yml", factory = YamlPropertyLoaderFactory.class)
@ConfigurationProperties(prefix = "csdn.user")
public class UserProperties {

	private String nikeName;

	private Long age;

	private String email;

}
