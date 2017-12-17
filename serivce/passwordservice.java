package com.serivce;

import java.util.ArrayList;

import com.Dao.sqlhelper;
import com.model.password;

public class passwordservice {
	 private String sql = null;
      public passwordservice() {
		
	}
      public boolean changepassword_admin(String id,String password)
      {
    	  sql = "update password set password = ? where id = ?";
    	  String[] paras = {password,id};
    	  sqlhelper sqlhelper = new sqlhelper("select * from users", "select * from mission");
    	  return sqlhelper.update_database(sql, paras);
      }
       public password getallpassword(String id)
       {   
    	   sqlhelper sqlhelper = new sqlhelper("select * from users", "select * from mission");
           sql = "select * from password where id = ?";
           String[] paras = {id};
           return sqlhelper.getuser(sql, paras);
       }
       public boolean updatepassword(String id,String password)
       {
    	   sqlhelper sqlhelper = new sqlhelper("select * from users", "select * from mission");
    	   sql = "update password set password = ? where id = ?";
    	   String[] paras = {password,id};
    	   return sqlhelper.update_database(sql, paras);
       }
}
