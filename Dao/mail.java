package com.Dao;

import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

public class mail {
	private String fromString = "";
	private String to = "";
	public mail(String from,String to)  {
 		fromString = from;
 		this.to = to;
        //0.1 ȷ������λ��
 		try {
        Properties props = new Properties();
        //��ȡ163����smtp�������ĵ�ַ��
        props.setProperty("mail.host", "smtp.163.com");
        //�Ƿ����Ȩ����֤��
        props.setProperty("mail.smtp.auth", "true");
        
        
        //0.2ȷ��Ȩ�ޣ��˺ź����룩
           Authenticator authenticator = new Authenticator() {
            @Override
            public PasswordAuthentication getPasswordAuthentication() {
                //��д�Լ���163����ĵ�¼�ʺź���Ȩ���룬��Ȩ����Ļ�ȡ���ں������н��⡣
               return new PasswordAuthentication("15813359204","199821130365lyk");
           }
        };

        //1 �������
        
        Session session = Session.getDefaultInstance(props, authenticator);
        session.setDebug(true);
        
        //2 ������Ϣ
        Message message = new MimeMessage(session);
        // 2.1 ������        xxx@163.com �����Լ��������ַ����������
        message.setFrom(new InternetAddress("15813359204@163.com"));
        message.setRecipient(RecipientType.TO, new InternetAddress(to));
        // 2.3 ���⣨���⣩
        message.setSubject("ע��ɹ�");
        // 2.4 ����
        String str = "ע��ɹ�";
        //���ñ��룬��ֹ���͵������������롣
       message.setContent(str, "text/html;charset=UTF-8");
       
       
       //3������Ϣ
       Transport.send(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
