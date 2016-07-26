package com.hecj.common.mail;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

public class MailUtil {

	public boolean send(Mail mail) {
		// 发送email
		HtmlEmail email = new HtmlEmail();
		System.out.println("正在发送邮件【接收者："+mail.getReceiver()+"，发送者："+mail.getUsername()+"邮件标题："+mail.getSubject()+"，邮件内容："+mail.getMessage()+"】");
		try {
			email.setHostName(mail.getHost());
			// 字符编码集的设置
			email.setCharset(Mail.ENCODEING);
			// 收件人的邮箱
			email.addTo(mail.getReceiver());
			// 发送人的邮箱
			email.setFrom(mail.getSender(), mail.getName());
			// 如果需要认证信息的话，设置认证：用户名-密码。分别为发件人在邮件服务器上的注册名称和密码
			email.setAuthentication(mail.getUsername(), mail.getPassword());
			// 要发送的邮件主题
			email.setSubject(mail.getSubject());
			// 要发送的信息，由于使用了HtmlEmail，可以在邮件内容中使用HTML标签
			email.setMsg(mail.getMessage());
			// 发送
			email.send();
			return true;
		} catch (EmailException e) {
			System.out.println("发送邮件失败："+e.getMessage());
			e.printStackTrace();
			return false;
		}
	}

}