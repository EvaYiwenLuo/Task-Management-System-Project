package com.controller;

import java.awt.Window;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.mission;
import com.model.password;
import com.model.user;
import com.serivce.missionserivce;
import com.serivce.passwordservice;
import com.serivce.userserivce;

//管理员跳转，用于管理员的使用
public class admincontroller extends HttpServlet {
	public admincontroller() {
		super();
	}

	public void destroy() {
		super.destroy();
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
		ArrayList<user> arrayList_users = null;
		ArrayList<mission> arrayList_mission = null;
		userserivce userserivce = null;
		missionserivce missionserivce = null;
		String type = request.getParameter("type");
		int pagenow = 1;
		if (request.getParameter("type") == null) {
			request.getRequestDispatcher("/WEB-INF/view/lost.jsp").forward(
					request, response);
			return;
		} else {
			type = request.getParameter("type");
		}
		if (type.equals("usermanage"))// 管理员要查看用户数据
		{
			// 准备用户数据
			if (request.getParameter("pagenow") != null) {
				pagenow = Integer.parseInt(request.getParameter("pagenow"));
			}
			userserivce = new userserivce();
			arrayList_users = userserivce.getpageuser(pagenow);
			request.setAttribute("users", arrayList_users);
			request.setAttribute("pagecount", userserivce.get_user_pagecount());
			request.getRequestDispatcher("/WEB-INF/view/showusers.jsp")
					.forward(request, response);
			return;
		} else if (type.equals("missionmanage")) {// 查看任务数据
			// 准备任务数据
			if (request.getParameter("pagenow") != null) {
				pagenow = Integer.parseInt(request.getParameter("pagenow"));
			}
			missionserivce = new missionserivce();
			arrayList_mission = missionserivce.getallmission(pagenow);
			request.setAttribute("missions", arrayList_mission);
			request.setAttribute("pagecount", missionserivce
					.get_mission_pagecount("select count(*) from users",
							"select count(*) from mission"));
			request.getRequestDispatcher("/WEB-INF/view/showmission.jsp")
					.forward(request, response);
			return;
		} else if (type.equals("addmission"))// 添加任务
		{
			String content = request.getParameter("content");
			missionserivce = new missionserivce();
			if (missionserivce.addmission(content)) {
				arrayList_mission = missionserivce.getallmission(pagenow);
				request.setAttribute("missions", arrayList_mission);
				request.setAttribute("pagecount", missionserivce
						.get_mission_pagecount("select count(*) from users",
								"select count(*) from mission"));
				request.getRequestDispatcher("/WEB-INF/view/showmission.jsp")
						.forward(request, response);
				return;
			} else {
				request.getRequestDispatcher("/WEB-INF/view/lost.jsp").forward(
						request, response);
				return;
			}

		} else if (type.equals("delete") == true)// 删除任务
		{
			String mission_id = request.getParameter("id");
			missionserivce = new missionserivce();
			if (missionserivce.deletemission(mission_id)) {
				arrayList_mission = missionserivce.getallmission(pagenow);
				request.setAttribute("missions", arrayList_mission);
				request.setAttribute("pagecount", missionserivce
						.get_mission_pagecount("select count(*) from users",
								"select count(*) from mission"));
				request.getRequestDispatcher("/WEB-INF/view/showmission.jsp")
						.forward(request, response);
				return;
			} else {
				request.getRequestDispatcher("/WEB-INF/view/lost.jsp").forward(
						request, response);
				return;
			}
		} else if (type.equals("change") == true)// 跳转修改页面
		{
			missionserivce = new missionserivce();
			String id = request.getParameter("id");
			request.setAttribute("id", id);
			mission mission = missionserivce.get_mission_id(id).get(0);
			request.setAttribute("mission", mission);
			request.getRequestDispatcher("/WEB-INF/view/missioncontent.jsp")
					.forward(request, response);
			return;
		} else if (type.equals("changemission") == true)// 确认修改
		{
			missionserivce = new missionserivce();
			String id = (String) request.getSession().getAttribute("id");
			System.out.println("id+" + id);
			String content = request.getParameter("content");
			if (missionserivce.changemission(id, content) == true) {
				request.getRequestDispatcher(
						"/admincontroller?type=missionmanage&pagenow=1")
						.forward(request, response);
			} else {
				request.getRequestDispatcher("/WEB-INF/view/lost.jsp").forward(
						request, response);
				return;
			}
		} else if (type.equals("login") == true)// 调回管理员登录界面
		{
			request.getRequestDispatcher("/WEB-INF/view/admin-MainFrame.jsp")
					.forward(request, response);
			return;
		} else if (type.equals("changeadmin") == true)// 跳转修改管理员密码界面
		{ // 准备好管理员密码账号信息
			passwordservice passwordservice = new passwordservice();
			password password = passwordservice.getallpassword("000000000000");
			request.setAttribute("password", password);
			request.getRequestDispatcher(
					"/WEB-INF/view/changeadmin_password.jsp").forward(request,
					response);
			return;
		} else if (type.equals("changeadmin_") == true)// 开始修改管理员密码
		{
			String newpassword = request.getParameter("new_password");
			passwordservice passwordservice = new passwordservice();
			if (passwordservice.updatepassword("000000000000", newpassword)) {
				request.getRequestDispatcher(
						"/WEB-INF/view/admin-MainFrame.jsp").forward(request,
						response);
				return;
			} else {
				request.getRequestDispatcher("/WEB-INF/view/lost.jsp").forward(
						request, response);
				return;
			}
		} else if (type.equals("select") == true) {

			if (request.getParameter("keyword") == null
					|| request.getParameter("keyword") == "") {
				request.getRequestDispatcher(
						"/admincontroller?type=missionmanage&pagenow=1")
						.forward(request, response);
				return;
			}
			String keyword = request.getParameter("keyword");
			keyword = new String(keyword.getBytes("iso-8859-1"), "utf-8");

			missionserivce = new missionserivce();
			if (request.getParameter("pagenow") != null) {
				pagenow = Integer.parseInt(request.getParameter("pagenow"));
			} else {
				pagenow = 1;
			}
			arrayList_mission = missionserivce.getselectmission(pagenow,
					keyword);
			System.out.println(arrayList_mission.size());
			request.setAttribute("missions", arrayList_mission);
			request.setAttribute("pagecount",
					missionserivce.getselectmissioncount(pagenow, keyword));
			request.getRequestDispatcher(
					"/WEB-INF/view/showmission.jsp?type=select&keyword="
							+ keyword).forward(request, response);
			return;
		} else if (type.equals("selectuser") == true) {

			if (request.getParameter("keyword") == null
					|| request.getParameter("keyword") == "") {
				request.getRequestDispatcher(
						"/admincontroller?type=usermanage&pagenow=1")
						.forward(request, response);
				return;
			}
			String keyword = request.getParameter("keyword");
			keyword = new String(keyword.getBytes("iso-8859-1"), "utf-8");

			userserivce = new userserivce();
			if (request.getParameter("pagenow") != null) {
				pagenow = Integer.parseInt(request.getParameter("pagenow"));
			} else {
				pagenow = 1;
			}
			arrayList_users = userserivce.getselectusers(pagenow, keyword);
			request.setAttribute("users", arrayList_users);
			request.setAttribute("pagecount",
					userserivce.getselectusercount(pagenow, keyword));
			request.getRequestDispatcher(
					"/WEB-INF/view/showusers.jsp?type=selectuser&keyword="
							+ keyword).forward(request, response);
			return;
		}
	}

	public void init() throws ServletException {

	}

}
