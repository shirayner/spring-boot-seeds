package com.ray.study.springboot09othersendmail.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.thymeleaf.context.Context;

/**
 * description
 *
 * @author shira 2019/05/15 23:27
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MailTemplate {

	// 模板路径
	private String filePath;

	// 模板模型map
	private Context context;
}
