package com.serivce;
/**
 * 用户的业务逻辑类
 */
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Dao.sqlhelper;
import com.model.user;

public class userserivce{
	private user user = null;
	private sqlhelper sqlhelper = null;
	String sql = null;
      public userserivce() {
	}
      public String checkuser(String username,String password)
      {
    	  sql = "select * from password where id = ?";
    	  String[] paStrings = {username};
    	  sqlhelper = new sqlhelper("select count(*) from users","select count(*) from mission");
    	  com.model.password password2 = sqlhelper.getusername(username, sql,paStrings);
    	  if(password2 == null)
    	  {
    		  return "用户名不存在";
    	  }
    	  else if(password2.getPassword().equals(password) == false)
    	  {
    		  return "密码不正确";
    	  }
    	  else if(password2.getPassword().equals(password) == true)
    	  {
    		  return "密码正确";
    	  }
    	  else {
			return null;
		}
      }
      //得到当前使用者信息
      public user getuser(String username)
      {
    	  sqlhelper = new sqlhelper("select count(*) from users","select count(*) from mission");
    	  sql = "select * from users where id = ?";
    	  String[] paras = {username};
    	  user = sqlhelper.getusermessage(sql, paras).get(0);
    	  return user;
      }
      //得到所有用户信息
      public ArrayList<user> getalluser(String username)
      {
    	  sqlhelper = new sqlhelper("select count(*) from users","select count(*) from mission");
    	  sql = "select * from users";
    	  String[] paras = {};
    	  return sqlhelper.getusermessage(sql, paras);
      }
      
      public int get_user_pagecount()
      {   
    	  sqlhelper = new sqlhelper("select count(*) from users","select count(*) from mission");
    	  return sqlhelper.user_pageCount;
      }
      public boolean add_new_user(String id,String name,String password,String college,String major,String grade,String email,String direction)
      {   
    	  boolean a = true;
    	  sql = "insert into users values (?,?,?,?,?,?,?)";
    	  String[] paras = {id,name,college,major,grade,email,direction};
    	  sqlhelper = new sqlhelper("select count(*) from users","select count(*) from mission");
    	  a = sqlhelper.update_database(sql, paras);
    	  sql = "insert into password values(?,?)";
    	  String[] paras2 = {id,password};
    	  a = sqlhelper.update_database(sql, paras2);
    	  return a;
      }
      
      public boolean update_user(String id,String name,String password,String college,String major,String grade,String email,String direction)
      {
    	  boolean a = true;
    	  sql = "update users set name = ?,college = ?,major = ?,class = ?,email = ?,direction = ? where id = ?";
    	  String[] paras = {name,college,major,grade,email,direction,id};
    	  sqlhelper = new sqlhelper("select count(*) from users","select count(*) from mission");
    	  a = sqlhelper.update_database(sql, paras);
    	  sql = "update password set password = ? where id = ?";
    	  String[] paras2 = {password,id};
    	  a = sqlhelper.update_database(sql, paras2);
    	  return a;
      }
      
      public ArrayList<user> getpageuser(int pagenow)
      {
    	  sqlhelper = new sqlhelper("select count(*) from users","select count(*) from mission");
    	  sql = "select next.*,rownum num from (select new.*,rownum num from (select * from users) new where rownum <= ?) next where num >= ?";
    	  String[] paras = {(pagenow*sqlhelper.user_pageSize)+"",(sqlhelper.user_pageSize*(pagenow-1)+1)+""};
    	  return sqlhelper.getusermessage(sql, paras);
      }
      
      public ArrayList<user> getselectusers(int pagenow,String keyword)
      {
      	String user = "select count(*) from users where id like " + "'%" + keyword + "%'" + " or " + "name like " + "'%" + keyword +"%'";
      	String mission = "select count(*) from mission where id like " + "'%" + keyword + "%'" + " or " + "content like " + "'%" + keyword + "%'";
      	sqlhelper sqlhelper = new sqlhelper(user,mission);
      	sql = "select next.*,rownum num from (select new.*,rownum num from(select * from users where id like " + "'%" + keyword + "%'" + " or " + "name like " + "'%" + keyword + "%'" + ") new where rownum <= ?) next where num >= ?";
      	String[] paras = {(pagenow * sqlhelper.user_pageSize)+"",(sqlhelper.user_pageSize*(pagenow-1)+1)+""};
      	return sqlhelper.getusermessage(sql, paras);
      }
      
      public int getselectusercount(int pagenow,String keyword)
      {    
      	String user = "select count(*) from users where id like " + "'%" + keyword + "%'" + " or " + "name like " + "'%" + keyword +"%'";
      	String mission = "select count(*) from mission where id like " + "'%" + keyword + "%'" + " or " + "content like " + "'%" + keyword + "%'";
      	sqlhelper sqlhelper = new sqlhelper(user,mission);
      	 return sqlhelper.user_pageCount;
      }
}
