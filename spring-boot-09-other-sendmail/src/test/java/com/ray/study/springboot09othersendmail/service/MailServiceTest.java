package com.ray.study.springboot09othersendmail.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

/**
 * description
 *
 * @author shira 2019/05/15 16:54
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MailServiceTest {

	@Autowired
	MailService mailService;

	/**
	 * 1.发送包含简单文本的邮件
	 */
	@Test
	public void sendSimpleMail() {
		String to = "shiray1994@163.com";
		String subject = "主题：简单文本邮件";
		String text = "这里是一段简单文本。";
		mailService.sendSimpleMail(to, subject, text);
	}


	/**
	 * 2.在邮件正文中嵌入静态资源
	 */
	@Test
	public void sendInlineMail() {
		String to = "shiray1994@163.com";
		String subject = "主题：嵌入静态资源";
		String text = "<html><body><h1>这是一封内嵌图片的邮件</h1><img src=\"cid:img1\" ></body></html>";

		Map<String,String> inlineMap = new HashMap<>();
		inlineMap.put("img1", "C:\\Users\\shira\\Pictures\\img\\110.png");

		mailService.sendInlineMail(to, subject, text, inlineMap);
	}



	/**
	 * 3.发送包含附件的邮件
	 */
	@Test
	public void sendAttachmentMail()  {
		String to = "shiray1994@163.com";
		String subject = "主题：有附件";
		String text = "<html><body><h1>这是一封有附件的邮件</h1><p> Hello! There is an attachment in the end of this mail! </p></body></html>";

		Map<String,String> attachmentMap = new HashMap<>();
		attachmentMap.put("110.png", "C:\\Users\\shira\\Pictures\\img\\110.png");

		mailService.sendAttachmentMail(to, subject, text, attachmentMap);
	}

}