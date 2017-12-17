package com.Dao;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

public class javamail 
{   
	public javamail(String email) {
		// TODO Auto-generated constructor stub	
		try {
			mail mail = new mail("15813359204@qq.com", email);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

}
