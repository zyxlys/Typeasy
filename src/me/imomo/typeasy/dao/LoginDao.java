package me.imomo.typeasy.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import me.imomo.typeasy.commons.DBConnection;
import me.imomo.typeasy.vo.User;

/**
 * User Log In
 * 
 * @version 1.0 2013/05/01
 * @author Mo
 * 
 */
public class LoginDao {
	private Connection conn = null;
	

	/**
	 * 
	 * @return
	 */
	public User getUser(){
		User user=null;
		conn = DBConnection.getConnection();
		String sql="SELECT * FROM users";
		try{
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			if(rs!=null&&rs.next()){
				user=new User();	

			}
		}catch(Exception e){
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return user;
	}
	
	/**
	 * 用户登录	若登录成功,返回数据库中对应的User对象
	 * 
	 * @param u
	 * @return User
	 */
	public User login(User u) {
		conn = DBConnection.getConnection();
		User user = new User();
		if (u != null) {
			String sql = "SELECT * FROM users WHERE name='" + u.getName()
					+ "' AND password='" + u.getPassword() + "'";
			ResultSet rs;
			Statement stmt;
			try {
				stmt = conn.createStatement();
				rs = stmt.executeQuery(sql);
				if (rs != null && rs.next()) {
					user.setUid(rs.getInt("uid"));
					user.setName(rs.getString("name"));
					user.setPassword(rs.getString("password"));
					user.setMail(rs.getString("mail"));
					user.setUrl(rs.getString("url"));
					user.setScreenName(rs.getString("screenName"));
					user.setCreated(rs.getString("created"));
					user.setGroup(rs.getString("group"));
				}

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				if (conn != null) {
					try {
						conn.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		return user;
	}

}
