package com.ray.study.springboot09othersendmail.service;

import com.ray.study.springboot09othersendmail.entity.Mail;
import com.ray.study.springboot09othersendmail.entity.MailTemplate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.ResourceUtils;
import org.thymeleaf.context.Context;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

/**
 * description
 *
 * @author shira 2019/05/15 21:50
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TemplateMailServiceTest {

	@Autowired
	private TemplateMailService templateMailService;

	@Test
	public void send() throws FileNotFoundException {
		// to、subject
		String to = "shiray1994@163.com";
		String subject = "[JavaMailTest]您的京东订单【85993160742】电子发票已开具";

		// inlineMap
		String jdMailHeaderImgPath = ResourceUtils
				.getFile(ResourceUtils.CLASSPATH_URL_PREFIX + "static/img/jd-mail-header.png").getAbsolutePath();
		String invoiceQrCodeImgPath = ResourceUtils
				.getFile(ResourceUtils.CLASSPATH_URL_PREFIX + "static/img/invoice-qrcode.png").getAbsolutePath();
		Map<String, String> inlineMap = new HashMap<>();
		inlineMap.put("jdMailHeader", jdMailHeaderImgPath);
		inlineMap.put("invoiceQrCode", invoiceQrCodeImgPath);

		// attachmentMap
		String invoicePdfPath = ResourceUtils
				.getFile(ResourceUtils.CLASSPATH_URL_PREFIX + "static/pdf/invoice1.pdf").getAbsolutePath();
		Map<String,String> attachmentMap = new HashMap<>();
		attachmentMap.put("invoice-85993160742.pdf", invoicePdfPath);

		// mailTemplate
		Context ctx = new Context();
		ctx.setVariable("to", to);
		String templateFilePath = "mail/mail-template.html";
		MailTemplate mailTemplate = new MailTemplate(templateFilePath, ctx);


		// send mail
		Mail mail = new Mail();
		mail.setTo(to);
		mail.setSubject(subject);
		mail.setInlineMap(inlineMap);
		mail.setAttachmentMap(attachmentMap);
		mail.setMailTemplate(mailTemplate);

		templateMailService.send(mail);
	}
}