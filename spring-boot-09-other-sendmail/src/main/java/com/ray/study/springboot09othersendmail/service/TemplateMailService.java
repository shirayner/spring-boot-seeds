package com.ray.study.springboot09othersendmail.service;

import com.ray.study.springboot09othersendmail.entity.Mail;

/**
 * 邮件发送类
 *
 * @author shira 2019/05/15 21:47
 */
public interface TemplateMailService {

	/**
	 * 发送邮件：
	 *  (1)当邮件有模板时按模板发送
	 * （2）无模板时按内容发送
	 * @param mail 邮件
	 */
	void send(Mail mail);
}
