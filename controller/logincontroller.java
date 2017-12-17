package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;

import javax.mail.Flags.Flag;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.mission;
import com.model.user;
import com.serivce.missionserivce;
import com.serivce.userserivce;

public class logincontroller extends HttpServlet {
	public logincontroller() {
		super();
	}

	public void destroy() {
		super.destroy();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset = utf-8");
		request.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		userserivce userserivce = new userserivce();
		missionserivce missionserivce = new missionserivce();
		int pagenow = 1;
		if (request.getSession().getAttribute("checkcode") != null
				&& request.getSession().getAttribute("checkcode")
						.equals(request.getParameter("text")) == false) {
			request.setAttribute("err", "验证码错误");
			request.getRequestDispatcher("/index.jsp").forward(request,
					response);
		} else if (userserivce.checkuser(username, password).equals("用户名不存在")) {
			request.setAttribute("err", "用户名不存在");
			request.getRequestDispatcher("/index.jsp").forward(request,
					response);
		} else if (userserivce.checkuser(username, password).equals("密码不正确")) {
			request.setAttribute("err", "密码不正确");
			request.getRequestDispatcher("/index.jsp").forward(request,
					response);
		}
		// 如果正确了
		else {
			if (username.equals("000000000000") == true)// 管理员登录了
			{
				request.getRequestDispatcher(
						"/WEB-INF/view/admin-MainFrame.jsp")// 跳转管理员专用界面
						.forward(request, response);
				return;
			}
			// 是否cookie记住账号密码
			if (request.getParameter("keep") != null
					&& request.getParameter("keep").equals("keep")) {
				Cookie cookie = new Cookie("username", URLEncoder.encode(
						username, "utf-8"));
				Cookie cookie2 = new Cookie("password", URLEncoder.encode(
						password, "utf-8"));
				cookie.setMaxAge(3600);
				cookie2.setMaxAge(3600);
				response.addCookie(cookie);
				response.addCookie(cookie2);
			}
			// 准备好当前用户信息
			user user = userserivce.getuser(username);
			request.setAttribute("user", user);
			request.setAttribute("pagecount", missionserivce
					.get_mission_pagecount("select count(*) from users",
							"select count(*) from mission"));
			// 准备好任务资料
			if (request.getParameter("pagenow") != null) {
				pagenow = Integer.parseInt(request.getParameter("pagenow"));
			}
			ArrayList<mission> allmission = missionserivce
					.getallmission(pagenow);// 所有任务资料
			// for(int i = 0 ;i < allmission.size();i++)
			// {
			// System.out.println(allmission.get(i).getMission_id()+allmission.get(i).getMission_content()+
			// allmission.get(i).getMission_status()+allmission.get(i).getMission_executor());
			// }
			request.setAttribute("allmission", allmission);
			// 跳转主页面
			request.getRequestDispatcher("/WEB-INF/view/user-MainFrame.jsp")
					.forward(request, response);
			return;
		}
	}

	public void init() throws ServletException {
		// Put your code here
	}

}
