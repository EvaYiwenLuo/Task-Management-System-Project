package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.mission;
import com.model.user;
import com.serivce.missionserivce;
import com.serivce.userserivce;

public class page extends HttpServlet {
    
	public page() {
		super();
	}

	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
       doPost(request, response);
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
       
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		int pagenow = 1;
		userserivce userserivce = new userserivce();
		missionserivce missionserivce = new missionserivce();
		String username = (String)request.getParameter("username");
		System.out.println("username = "+username);
		user user = userserivce.getuser(username);
		if(request.getParameter("pagenow") != null)
		{  
			System.out.println("pagenow = "+pagenow);
			pagenow = Integer.parseInt(request.getParameter("pagenow"));
		}
		request.setAttribute("user", user);
		ArrayList<mission> allmission = missionserivce.getallmission(pagenow);//所有任务资料
		request.setAttribute("allmission", allmission);
		request.setAttribute("pagecount", missionserivce.get_mission_pagecount("select count(*) from users","select count(*) from mission"));
		//跳转主页面
		request.getRequestDispatcher("/WEB-INF/view/user-MainFrame.jsp").forward(request, response);
	}

	public void init() throws ServletException {
		// Put your code here
	}

}
