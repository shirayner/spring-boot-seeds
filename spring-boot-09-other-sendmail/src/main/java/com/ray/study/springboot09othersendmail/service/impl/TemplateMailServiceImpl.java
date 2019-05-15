package com.ray.study.springboot09othersendmail.service.impl;

import com.ray.study.springboot09othersendmail.entity.Mail;
import com.ray.study.springboot09othersendmail.service.TemplateMailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.thymeleaf.TemplateEngine;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.Iterator;
import java.util.Map;

/**
 * description
 *
 * @author shira 2019/05/15 21:49
 */
@Service
@Slf4j
public class TemplateMailServiceImpl  implements TemplateMailService {

	@Autowired
	private JavaMailSender mailSender;

	@Value("${spring.mail.username}")
	private String from;

	@Autowired
	private TemplateEngine templateEngine;

	@Override
	public void send(Mail mail) {
		if(mail.getMailTemplate()!=null
				&& !StringUtils.isEmpty(mail.getMailTemplate().getFilePath())){
			sendByTemplate( mail);
		}else{
			sendByText(mail);
		}
	}


	/**
	 * 根据邮件文本内容发送邮件
	 * @param mail
	 */
	private void sendByText(Mail mail){
		try {

			MimeMessage mimeMessage = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
			helper.setFrom(from);
			helper.setTo(mail.getTo());
			helper.setSubject(mail.getSubject());
			// 启用html
			helper.setText(mail.getText(), true);

			// 添加邮件中内嵌的静态资源
			Iterator<Map.Entry<String, String>> inlineEntries = mail.getInlineMap().entrySet().iterator();
			while (inlineEntries.hasNext()){
				Map.Entry<String, String> entry = inlineEntries.next();
				String contentId = entry.getKey();
				String filePathName = entry.getValue();
				FileSystemResource file = new FileSystemResource(new File(filePathName));
				helper.addInline(contentId, file);
			}

			// 添加邮件末尾的附件
			Iterator<Map.Entry<String, String>> attachmentEntries = mail.getAttachmentMap().entrySet().iterator();
			while (attachmentEntries.hasNext()){
				Map.Entry<String, String> entry = attachmentEntries.next();
				String contentId = entry.getKey();
				String filePathName = entry.getValue();
				FileSystemResource file = new FileSystemResource(new File(filePathName));
				helper.addAttachment(contentId, file);
			}


			mailSender.send(mimeMessage);

		} catch (MessagingException e) {
			e.printStackTrace();
		}


		log.debug("邮件发送成功：{} -> {}", from, mail.getTo());
	}


	/**
	 * 根据模板发送邮件
	 * @param mail 邮件
	 */
	private void sendByTemplate(Mail mail){
		String text = templateEngine.process(mail.getMailTemplate().getFilePath(), mail.getMailTemplate().getContext());
		mail.setText(text);
		sendByText(mail);
	}




}
