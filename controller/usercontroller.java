package com.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Dao.javamail;
import com.Dao.mail;
import com.model.user;
import com.serivce.userserivce;

/**
 * ���ڴ����Աע����תҳ�棬����Ա��Ա����ͨ��
 * 
 * @author SDQ888
 * 
 */
public class usercontroller extends HttpServlet {
	public usercontroller() {
		super();
	}

	public void destroy() {
		super.destroy();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		//��ת��ע�����
		if (request.getParameter("type") != null && request.getParameter("type").equals("register") == true) {
			request.getRequestDispatcher("/WEB-INF/view/register.jsp").forward(
					request, response);
			return;
		//��ת���޸ĸ�����Ϣ����
		} else if (request.getParameter("type") != null &&request.getParameter("type").equals("change") == true) {
			request.getRequestDispatcher("/WEB-INF/view/change.jsp").forward(
					request, response);
			return;
		}
		userserivce userserivce = new userserivce();
		String username = request.getParameter("username");
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String college = request.getParameter("college");
		String major = request.getParameter("major");
		String grade = request.getParameter("grade");
		String classes = request.getParameter("classes");
		String email = request.getParameter("email");
		String direction = request.getParameter("direction");
		//�޸ĸ�����Ϣ����
		  if(request.getParameter("change") != null&&request.getParameter("change").equals("change_next"))
		  {
			  if(userserivce.update_user(username, name, password, college, major, grade, email, direction) == true)
			  {
				  request.getRequestDispatcher("/WEB-INF/view/ok.jsp").forward(
							request, response);
					return;
			  }
			  else {
				  request.getRequestDispatcher("/WEB-INF/view/lost.jsp").forward(
							request, response);
					return;
			}
		  }
              //ע�����
			if (userserivce.add_new_user(username, name, password, college,
					major, grade+classes, email, direction) == true) {
				javamail javamail = new javamail(email);
				request.getRequestDispatcher("/WEB-INF/view/ok.jsp").forward(
						request, response);
				return;
			}
			else {
				request.getRequestDispatcher("/WEB-INF/view/lost.jsp").forward(
						request, response);
				return;
			}
			
	}

	public void init() throws ServletException {

	}

}
