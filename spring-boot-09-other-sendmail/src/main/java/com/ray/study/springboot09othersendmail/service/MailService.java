package com.ray.study.springboot09othersendmail.service;

import java.util.Map;

/**
 * description
 *
 * @author shira 2019/05/15 16:56
 */
public interface MailService {

	/**
	 * 1.发送包含简单文本的邮件
	 * @param to 收件人
	 * @param subject 主题
	 * @param text 内容
	 */
	void sendSimpleMail(String to, String subject, String text);

	/**
	 * 发送复杂邮件: html / 内嵌静态资源 / 带附件
	 * @param to 收件人
	 * @param subject 主题
	 * @param text 内容
	 * @param inlineMap  内嵌静态资源map    key:contentId     value:filePathName
	 * @param attachmentMap 邮件末尾附件map
	 */
	void sendMimeMail(String to, String subject, String text, Map<String,String> inlineMap,  Map<String,String> attachmentMap);



	/**
	 * 2.在邮件正文中嵌入静态资源
	 * @param to 收件人
	 * @param subject 主题
	 * @param text 内容
	 * @param  inlineMap  内嵌静态资源map    key:contentId     value:filePathName
	 */
	void sendInlineMail(String to, String subject, String text, Map<String,String> inlineMap ) ;


	/**
	 * 3.发送包含附件的邮件
	 * @param to 收件人
	 * @param subject 主题
	 * @param text 内容
	 * @param attachmentMap 邮件末尾附件map
	 */
	void sendAttachmentMail(String to, String subject, String text, Map<String,String> attachmentMap);

}
