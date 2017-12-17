package com.model;
//ÑéÖ¤Âë
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import oracle.net.aso.g;

public class images extends HttpServlet {
	public images() {
		super();
	}

	public void destroy() {
		super.destroy(); 
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {   
 		response.setContentType("text/html;charset=utf-8");
 		response.setCharacterEncoding("utf-8");
 		response.setHeader("Cache-Control", "no-cache");
 		response.setHeader("Pragma", "no-cache");
 		response.setDateHeader("expires", -1);
 		response.setHeader("Content-Type", "image/jpeg");
 		BufferedImage bufferedImage = new BufferedImage(60, 30, BufferedImage.TYPE_INT_RGB);
 	    Graphics graphics = bufferedImage.getGraphics();
 	    graphics.setColor(Color.GRAY);
 	    graphics.drawRect(0, 0, 60, 30);
 	    graphics.setColor(Color.WHITE);
 	    graphics.setFont(new Font(null,Font.BOLD,20));
 	   String numString = makestring();
 	    request.getSession().setAttribute("checkcode", numString);
 	    graphics.drawString(numString, 0, 20);
 	    ImageIO.write(bufferedImage, "jpg", response.getOutputStream());
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
         doGet(request, response);
        
	}
	public String makestring()
	{
	   Random random = new Random();
	   String num = random.nextInt(9999)+"";
	   StringBuffer stringBuffer = new StringBuffer();
	   for(int i = 0;i < 4-num.length();i++)
	   {    
		   int number = random.nextInt(25);
		   stringBuffer.append((char)(97+number));
	   }
	   num = num + stringBuffer.toString();
	   return num;
	}

	public void init() throws ServletException {
		// Put your code here
	}

}
