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
        //0.1 确定连接位置
 		try {
        Properties props = new Properties();
        //获取163邮箱smtp服务器的地址，
        props.setProperty("mail.host", "smtp.163.com");
        //是否进行权限验证。
        props.setProperty("mail.smtp.auth", "true");
        
        
        //0.2确定权限（账号和密码）
           Authenticator authenticator = new Authenticator() {
            @Override
            public PasswordAuthentication getPasswordAuthentication() {
                //填写自己的163邮箱的登录帐号和授权密码，授权密码的获取，在后面会进行讲解。
               return new PasswordAuthentication("15813359204","199821130365lyk");
           }
        };

        //1 获得连接
        
        Session session = Session.getDefaultInstance(props, authenticator);
        session.setDebug(true);
        
        //2 创建消息
        Message message = new MimeMessage(session);
        // 2.1 发件人        xxx@163.com 我们自己的邮箱地址，就是名称
        message.setFrom(new InternetAddress("15813359204@163.com"));
        message.setRecipient(RecipientType.TO, new InternetAddress(to));
        // 2.3 主题（标题）
        message.setSubject("注册成功");
        // 2.4 正文
        String str = "注册成功";
        //设置编码，防止发送的内容中文乱码。
       message.setContent(str, "text/html;charset=UTF-8");
       
       
       //3发送消息
       Transport.send(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
