package com.jianjoy.utils;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import sun.misc.BASE64Encoder;

/**
 * 邮件发送工具类
 * @author zhoujian
 *
 */
@SuppressWarnings("restriction")
public class MailSender
{
	private static final String EMAIL_SENDER = ConfigUtils.getConfig("email_user");
	private static final String PASSWD = ConfigUtils.getConfig("email_pwd");
	private static final String HOST = ConfigUtils.getConfig("email_host");
	private static final String PORT = ConfigUtils.getConfig("email_port");

	public static void main(String[] args) {
		// boolean result = MailSender.sendEmail("fb-analysis-top-targets", "详情请见附件", PropertyUtil.getPropertyValue("emailList").toString().split(","), fileURL, simpleDateFormat.format(now.getTime()) + "_TopTargets.txt");
		System.out.println(HOST);
		System.out.println(EMAIL_SENDER);
		System.out.println(PASSWD);
	}

	public static boolean sendEmail(String subject, String content, String[] to) {
		Properties props = System.getProperties();
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", HOST);
		props.put("mail.smtp.user", EMAIL_SENDER);
		props.put("mail.smtp.password", PASSWD);
		props.put("mail.smtp.port", PORT);
		props.put("mail.smtp.auth", "true");

		Session session = Session.getDefaultInstance(props, null);
		MimeMessage message = new MimeMessage(session);
		try {
			message.setFrom(new InternetAddress(EMAIL_SENDER));
			InternetAddress[] toAddress = new InternetAddress[to.length];

			for (int i = 0; i < to.length; i++) {
				toAddress[i] = new InternetAddress(to[i]);
			}

			for (int i = 0; i < toAddress.length; i++) {
				message.addRecipient(Message.RecipientType.TO, toAddress[i]);
			}
			message.setSubject(subject);
			message.setText(content);
			Transport transport = session.getTransport("smtp");
			transport.connect(HOST, EMAIL_SENDER, PASSWD);
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
		} catch (AddressException e) {
			e.printStackTrace();
			return false;
		} catch (MessagingException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public static boolean sendEmail(String subject, String content, String[] to, String affix, String affixName) {
		Properties props = System.getProperties();
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", HOST);
		props.put("mail.smtp.user", EMAIL_SENDER);
		props.put("mail.smtp.password", PASSWD);
		props.put("mail.smtp.port", PORT);
		props.put("mail.smtp.auth", "true");
		Session session = Session.getDefaultInstance(props, null);
		MimeMessage message = new MimeMessage(session);
		try {
			message.setFrom(new InternetAddress(EMAIL_SENDER));
			InternetAddress[] toAddress = new InternetAddress[to.length];

			for (int i = 0; i < to.length; i++) {
				toAddress[i] = new InternetAddress(to[i]);
			}

			for (int i = 0; i < toAddress.length; i++) {
				message.addRecipient(Message.RecipientType.TO, toAddress[i]);
			}
			message.setSubject(subject);

			Multipart multipart = new MimeMultipart();

			BodyPart contentPart = new MimeBodyPart();
			contentPart.setText(content);
			multipart.addBodyPart(contentPart);

			BodyPart bodyPart = new MimeBodyPart();
			DataSource source = new FileDataSource(affix);

			bodyPart.setDataHandler(new DataHandler(source));

			BASE64Encoder enc = new BASE64Encoder();
			bodyPart.setFileName("=?GBK?B?" + enc.encode(affixName.getBytes()) + "?=");
			multipart.addBodyPart(bodyPart);
			message.setContent(multipart);
			message.saveChanges();
			Transport transport = session.getTransport("smtp");
			transport.connect(HOST, EMAIL_SENDER, PASSWD);
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
		} catch (AddressException e) {
			e.printStackTrace();
			return false;
		} catch (MessagingException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}