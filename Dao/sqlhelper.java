package com.Dao;

import java.sql.*;
import java.util.ArrayList;

import oracle.net.aso.a;

import com.model.mission;
import com.model.password;
import com.model.user;

/**
 * �����������ݿ�
 * 
 * @author SDQ888
 * 
 */
public class sqlhelper {
	private Connection connection = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	private String connectString = "oracle.jdbc.driver.OracleDriver";
	private String driverString = "jdbc:oracle:thin:@127.0.0.1:1521:ORD";
	private String usernameString = "system";
	private String passwordString = "qazwsxedc";
	private ArrayList<user> arrayList_user = null;// ���ڴ洢�û���Ϣ
	private ArrayList<mission> arrayList_mission = null;// ���ڴ洢�û���Ϣ
	private password password2 = null;// �洢�û������

	public int user_pageNow = 1;// ��ǰҳ��
	public int user_pageSize = 5;// ÿҳ��ʾ���ټ�¼
	public int user_pageCount = 1;// һ������ҳ
	public int user_rowSize = 1;// һ����������¼

	public int mission_pageNow = 1;// ��ǰҳ��
	public int mission_pageSize = 5;// ÿҳ��ʾ���ټ�¼
	public int mission_pageCount = 1;// һ������ҳ
	public int mission_rowSize = 1;// һ����������¼

	public sqlhelper(String user_sql, String mission_sql) {
		try {
			Class.forName(connectString);
			connection = DriverManager.getConnection(driverString,
					usernameString, passwordString);

			preparedStatement = connection.prepareStatement(user_sql);
			resultSet = preparedStatement.executeQuery();
			resultSet.next();
			user_rowSize = resultSet.getInt(1);
			user_pageCount = user_rowSize / user_pageSize == 0 ? user_rowSize
					/ user_pageSize : (user_rowSize / user_pageSize + 1);

			preparedStatement = connection.prepareStatement(mission_sql);
			resultSet = preparedStatement.executeQuery();
			resultSet.next();
			mission_rowSize = resultSet.getInt(1);
			mission_pageCount = mission_rowSize / mission_pageSize == 0 ? mission_rowSize
					/ mission_pageSize
					: (mission_rowSize / mission_pageSize + 1);
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public password getusername(String username, String sql, String[] paStrings) {
		try {
			Class.forName(connectString);
			connection = DriverManager.getConnection(driverString,
					usernameString, passwordString);
			preparedStatement = connection.prepareStatement(sql);
			for (int i = 1; i <= paStrings.length; i++) {
				preparedStatement.setString(i, paStrings[i - 1]);
			}
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				// ��ȡ����
				System.out.println("result:"+resultSet.getString(1)+resultSet.getString(2));
				password2 = new password(resultSet.getString(1),
						resultSet.getString(2));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.close(connection, preparedStatement, resultSet);
			return password2;
		}
	}

	public void close(Connection connection,
			PreparedStatement preparedStatement, ResultSet resultSet) {
		try {
			if (resultSet != null) {
				resultSet.close();
			}
			if (preparedStatement != null) {
				preparedStatement.close();
			}
			if (connection != null) {
				connection.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// ���������û��������û�������ʹ��
	public ArrayList<user> getusermessage(String sql, String[] paras) {
		try {
			Class.forName(connectString);
			connection = DriverManager.getConnection(driverString,
					usernameString, passwordString);
			preparedStatement = connection.prepareStatement(sql);
			for (int i = 1; i <= paras.length; i++) {
				preparedStatement.setString(i, paras[i - 1]);
			}
			resultSet = preparedStatement.executeQuery();
			arrayList_user = new ArrayList<user>();
			while (resultSet.next()) {
				user user = new user(resultSet.getString(1),
						resultSet.getString(2), resultSet.getString(3),
						resultSet.getString(4), resultSet.getString(5),
						resultSet.getString(6), resultSet.getString(7));
				arrayList_user.add(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.close(connection, preparedStatement, resultSet);
			return arrayList_user;
		}

	}

	// �õ�����
	public ArrayList<mission> getmission(String sql, String[] paras) {
		try {
			Class.forName(connectString);
			connection = DriverManager.getConnection(driverString,
					usernameString, passwordString);
			preparedStatement = connection.prepareStatement(sql);
			for (int i = 1; i <= paras.length; i++) {
				preparedStatement.setString(i, paras[i - 1]);
			}
			resultSet = preparedStatement.executeQuery();
			arrayList_mission = new ArrayList<mission>();
			while (resultSet.next()) {
				mission mission = new mission(resultSet.getString(1),
						resultSet.getString(2), resultSet.getString(3),
						resultSet.getString(4));
				arrayList_mission.add(mission);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.close(connection, preparedStatement, resultSet);
			return arrayList_mission;
		}
	}

	public boolean update_database(String sql, String[] paras)// �������ݿ�
	{
		boolean a = true;
		try {

			Class.forName(connectString);
			connection = DriverManager.getConnection(driverString,
					usernameString, passwordString);
			preparedStatement = connection.prepareStatement(sql);
			for (int i = 1; i <= paras.length; i++) {
				preparedStatement.setString(i, paras[i - 1]);
			}
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			a = false;
			e.printStackTrace();
		} finally {
            this.close(connection, preparedStatement, resultSet);
            return a;
		}
	}
	
	public password getuser(String sql,String[] paras)
	{
		try {
			Class.forName(connectString);
			connection = DriverManager.getConnection(driverString,
					usernameString, passwordString);
			preparedStatement = connection.prepareStatement(sql);
			for (int i = 1; i <= paras.length; i++) {
				preparedStatement.setString(i, paras[i - 1]);
			}
			resultSet = preparedStatement.executeQuery();
			resultSet.next();
			password2 = new password(resultSet.getString(1), resultSet.getString(2));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
            this.close(connection, preparedStatement, resultSet);
            return password2;
		}
	}
}
