package com.ray.study.springboot10configreadconfigproperty.config;

import org.springframework.boot.env.YamlPropertySourceLoader;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.support.DefaultPropertySourceFactory;
import org.springframework.core.io.support.EncodedResource;
import java.io.IOException;

/**
 * 实现yaml配置文件加载工厂，以使用@PropertySource注解加载指定yaml文件的配置
 *
 * @author shira 2019/05/16 23:25
 */
public class YamlPropertyLoaderFactory extends DefaultPropertySourceFactory {
	@Override
	public PropertySource<?> createPropertySource(String name, EncodedResource resource) throws IOException {

		if (null == resource) {
			super.createPropertySource(name, resource);
		}
		return new YamlPropertySourceLoader().load(resource.getResource().getFilename(), resource.getResource()).get(0);
	}
}
