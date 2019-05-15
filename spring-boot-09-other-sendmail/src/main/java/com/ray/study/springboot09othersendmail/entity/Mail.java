package com.ray.study.springboot09othersendmail.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Collections;
import java.util.Map;

/**
 * description
 *
 * @author shira 2019/05/15 22:59
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Mail  implements Serializable {

	// 发件人
	private String from;

	// 收件人
	private String to;

    //邮件主题
	private String subject;

	// 邮件内容
	private String text;

	// 内嵌静态资源map
	private Map<String,String> inlineMap = Collections.emptyMap();

	// 附件map
	private Map<String,String> attachmentMap = Collections.emptyMap();

	// 模板
	private MailTemplate MailTemplate;

}
