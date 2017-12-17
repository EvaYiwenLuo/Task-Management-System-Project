package com.serivce;

import java.util.ArrayList;

import com.Dao.sqlhelper;
import com.model.mission;

public class missionserivce {
    private mission mission = null;
    private sqlhelper sqlhelper = null;
    private String sql = null;
    private ArrayList<mission> allmission = null;
    
    //拿到所有任务
    public ArrayList<mission> getallmission(int pagenow)
    {
    	sqlhelper sqlhelper = new sqlhelper("select count(*) from users","select count(*) from mission");
    	sql = "select next.*,rownum num from (select new.*,rownum num from(select * from mission) new where rownum <= ?) next where num >= ?";
    	String[] paras = {(pagenow*sqlhelper.mission_pageSize)+"",(sqlhelper.mission_pageSize*(pagenow-1)+1)+""};
    	return sqlhelper.getmission(sql, paras);
    }
    public int get_mission_pagecount(String user_sql,String mission_sql)
    {
  	  sqlhelper = new sqlhelper(user_sql,mission_sql);
  	  return sqlhelper.mission_pageCount;
    }
    public ArrayList<mission> getcheckmission(int pagenow)
    {
    	sqlhelper sqlhelper = new sqlhelper("select count(*) from users","select count(*) from mission  where status = 0");
    	sql = "select next.*,rownum num from (select new.*,rownum num from(select * from mission where status = 0) new where rownum <= ?) next where num >= ?";
    	String[] paras = {(pagenow*sqlhelper.mission_pageSize)+"",(sqlhelper.mission_pageSize*(pagenow-1)+1)+""};
    	return sqlhelper.getmission(sql, paras);
    }
    
    public ArrayList<mission> getcheckedmission(int pagenow,String username)
    {
    	sqlhelper sqlhelper = new sqlhelper("select count(*) from users","select count(*) from mission  where executor = " + username);
    	sql = "select next.*,rownum num from (select new.*,rownum num from(select * from mission where executor = ?) new where rownum <= ?) next where num >= ?";
    	String[] paras = {username,(pagenow*sqlhelper.mission_pageSize)+"",(sqlhelper.mission_pageSize*(pagenow-1)+1)+""};
    	return sqlhelper.getmission(sql, paras);
    }
    public boolean getmission(String id,String username)
    {
    	sqlhelper sqlhelper = new sqlhelper("select count(*) from users", "select count(*) from mission");
    	sql = "update mission set status = ?,executor = ? where id = ?";
    	String[] paras = {"1",username,id};
    	return sqlhelper.update_database(sql, paras);
    }
    public boolean addmission(String content)
    {
    	sql = "insert  into mission (id,content,status) values(mission_seq.nextval,?,'0')";
    	String[] paras = {content};
    	sqlhelper = new sqlhelper("select count(*) from users", "select count(*) from mission");
    	return sqlhelper.update_database(sql, paras);
    }
    public boolean deletemission(String id)
    {
    	sql = "delete from mission where id = ?";
    	String[] paras = {id};
    	sqlhelper = new sqlhelper("select count(*) from users", "select count(*) from mission");
    	return sqlhelper.update_database(sql, paras);
    }
    public boolean changemission(String id,String content)
    {
    	sql = "update mission set content = ? where id = ?";
    	String[] paras = {content,id};
    	sqlhelper = new sqlhelper("select count(*) from users", "select count(*) from mission");
    	return sqlhelper.update_database(sql, paras);
    }
    public ArrayList<mission> get_mission_id(String id)
    {
    	sql = "select * from mission where id = ?";
    	String[] paras = {id};
    	sqlhelper = new sqlhelper("select count(*) from users", "select count(*) from mission");
    	return sqlhelper.getmission(sql, paras);
    }
    
    public ArrayList<mission> getselectmission(int pagenow,String keyword)
    {    
    	String user = "select count(*) from users where id like " + "'%" + keyword + "%'" + " or " + "name like " + "'%" + keyword +"%'";
    	String mission = "select count(*) from mission where id like " + "'%" + keyword + "%'" + " or " + "content like " + "'%" + keyword + "%'";
    	sqlhelper sqlhelper = new sqlhelper(user,mission);
    	sql = "select next.*,rownum num from (select new.*,rownum num from(select * from mission where id like " + "'%" + keyword + "%'" + " or " + "content like " + "'%" + keyword + "%'" + ") new where rownum <= ?) next where num >= ?";
    	String[] paras = {(pagenow*sqlhelper.mission_pageSize)+"",(sqlhelper.mission_pageSize*(pagenow-1)+1)+""};
    	return sqlhelper.getmission(sql, paras);
    }
    
    public int getselectmissioncount(int pagenow,String keyword)
    {    
    	String user = "select count(*) from users where id like " + "'%" + keyword + "%'" + " or " + "name like " + "'%" + keyword +"%'";
    	String mission = "select count(*) from mission where id like " + "'%" + keyword + "%'" + " or " + "content like " + "'%" + keyword + "%'";
    	sqlhelper sqlhelper = new sqlhelper(user,mission);
    	 return sqlhelper.mission_pageCount;
    }
}
