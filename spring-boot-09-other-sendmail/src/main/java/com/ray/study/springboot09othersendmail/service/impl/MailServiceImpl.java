package com.ray.study.springboot09othersendmail.service.impl;

import com.ray.study.springboot09othersendmail.service.MailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;

/**
 * description
 *
 * @author shira 2019/05/15 18:28
 */
@Service
@Slf4j
public class MailServiceImpl implements MailService {

	@Autowired
	private JavaMailSender mailSender;

	@Value("${spring.mail.username}")
	private String from;

	@Override
	public void sendSimpleMail(String to, String subject, String text) {
		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		// 设置发件人、收件人、主题、内容
		simpleMailMessage.setFrom(from);
		simpleMailMessage.setTo(to);
		simpleMailMessage.setSubject(subject);
		simpleMailMessage.setText(text);
		// 发送邮件
		mailSender.send(simpleMailMessage);

		log.debug("邮件发送成功：{} -> {}", from, to);
	}

	@Override
	public void sendMimeMail(String to, String subject, String text, Map<String, String> inlineMap, Map<String, String> attachmentMap) {
		try {

			MimeMessage mimeMessage = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
			helper.setFrom(from);
			helper.setTo(to);
			helper.setSubject(subject);
			// 启用html
			helper.setText(text, true);

			// 添加邮件中内嵌的静态资源
			Iterator<Map.Entry<String, String>> inlineEntries = inlineMap.entrySet().iterator();
			while (inlineEntries.hasNext()){
				Map.Entry<String, String> entry = inlineEntries.next();
				String contentId = entry.getKey();
				String filePathName = entry.getValue();
				FileSystemResource file = new FileSystemResource(new File(filePathName));
				helper.addInline(contentId, file);
			}

			// 添加邮件末尾的附件
			Iterator<Map.Entry<String, String>> attachmentEntries = attachmentMap.entrySet().iterator();
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


		log.debug("邮件发送成功：{} -> {}", from, to);

	}

	@Override
	public void sendInlineMail(String to, String subject, String text, Map<String, String> inlineMap) {
		this.sendMimeMail(to, subject, text, inlineMap, Collections.emptyMap());
	}


	@Override
	public void sendAttachmentMail(String to, String subject, String text, Map<String, String> attachmentMap) {
		this.sendMimeMail(to, subject, text, Collections.emptyMap(), attachmentMap);
	}
}
