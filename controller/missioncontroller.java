package com.controller;

//���ڽ��������ҳ��ʾ
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Dao.sqlhelper;
import com.model.mission;
import com.model.user;
import com.serivce.missionserivce;
import com.serivce.userserivce;

public class missioncontroller extends HttpServlet {

	public missioncontroller() {
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
		int pagenow = 1;
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		String username = (String) request.getParameter("username");
		System.out.println("username == " + username);
		userserivce userserivce = new userserivce();
		missionserivce missionserivce = new missionserivce();
		user user = userserivce.getuser(username);
		request.setAttribute("user", user);
		if (request.getParameter("type") != null
				&& request.getParameter("type").equals("getmission")) {
              //������д�����ݿ�
			String mission_id = request.getParameter("id");//�������ID��
			String username_new = request.getParameter("username");//����û���
			if(missionserivce.getmission(mission_id, username_new) == true)
			{
				out.println("<script language='javascript' type='text/javascript'>");
				out.println("window.alert('�������ɹ�')");
				out.println("</script>");
				request.getRequestDispatcher("/page?username=" + username + "&pagenow=1").forward(request, response);
			    return;
			}
		}
		// ׼������������
		if (request.getParameter("pagenow") != null) {
			pagenow = Integer.parseInt(request.getParameter("pagenow"));
		}
		String type = (String) request.getParameter("type");
		ArrayList<mission> allmission = null;
		if (type.equals("check") == true) {
			allmission = missionserivce.getcheckmission(pagenow);
			request.setAttribute("pagecount", missionserivce
					.get_mission_pagecount("select count(*) from users",
							"select count(*) from mission where status = 0"));
			System.out.println("��С��" + allmission.size());
		} else {
			allmission = missionserivce.getcheckedmission(pagenow, username);
			request.setAttribute("pagecount", missionserivce
					.get_mission_pagecount("select count(*) from users",
							"select count(*) from mission where executor ="
									+ username));
			System.out.println("��Сsswe��" + allmission.size());
		}
		// for(int i = 0 ;i < allmission.size();i++)
		// {
		// System.out.println(allmission.get(i).getMission_id()+allmission.get(i).getMission_content()+
		// allmission.get(i).getMission_status()+allmission.get(i).getMission_executor());
		// }
		request.setAttribute("allmission", allmission);
		// ��ת��ҳ��
		request.getRequestDispatcher("/WEB-INF/view/checkmission.jsp").forward(
				request, response);
	}

	public void init() throws ServletException {
		// Put your code here
	}

}
