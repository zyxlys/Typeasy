package me.imomo.typeasy.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import me.imomo.typeasy.commons.DBConnection;
import me.imomo.typeasy.vo.Users;

/**
 * User Log In Data Access Object
 * 
 * @version 1.0 2013/05/01
 * @author Mo
 * 
 */
public class LoginDao {
	private Connection conn = null;

	/**
	 * 用户登录 若登录成功,返回数据库中对应的User对象
	 * 
	 * @param u
	 * @return User
	 */
	public Users login(Users u) {
		conn = DBConnection.getConnection();
		Users user = new Users();
		if (u != null) {
			String sql = "SELECT * FROM users WHERE name=? AND password=?";
			ResultSet rs;
			PreparedStatement pstmt;
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, u.getName());
				pstmt.setString(2, u.getPassword());
				rs = pstmt.executeQuery();
				if (rs != null && rs.next()) {
					user.setUid(rs.getInt("uid"));
					user.setName(rs.getString("name"));
					user.setPassword(rs.getString("password"));
					user.setMail(rs.getString("mail"));
					user.setUrl(rs.getString("url"));
					user.setScreenName(rs.getString("screenName"));
					user.setAvatar(rs.getString("avatar"));
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

	/**
	 * 用户注册
	 * 
	 * @param u
	 */
	public void register(Users u) {
		Connection conn = DBConnection.getConnection();
		String sql = "INSERT INTO users(name,password,mail,screenName) VALUES(?,?,?,?)";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, u.getName());
			pstmt.setString(2, u.getPassword());
			pstmt.setString(3, u.getMail());
			pstmt.setString(4, u.getScreenName());
			pstmt.execute();
		} catch (Exception e) {
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

	/**
	 * 根据用户名查找用户
	 * 
	 * @param name
	 * @return User
	 */
	public Users getUserByName(String name) {
		Users user = new Users();
		conn = DBConnection.getConnection();
		String sql = "SELECT * FROM users WHERE name='" + name + "'";
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			if (rs != null && rs.next()) {
				user.setUid(rs.getInt("uid"));
				user.setName(rs.getString("name"));
				user.setPassword(rs.getString("password"));
				user.setMail(rs.getString("mail"));
				user.setUrl(rs.getString("url"));
				user.setScreenName(rs.getString("screenName"));
				user.setAvatar(rs.getString("avatar"));
				user.setCreated(rs.getString("created"));
				user.setGroup(rs.getString("group"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
	 * 根据邮箱查找用户
	 * 
	 * @param name
	 * @return User
	 */
	public Users getUserByMail(String mail) {
		Users user = new Users();
		conn = DBConnection.getConnection();
		String sql = "SELECT * FROM users WHERE mail='" + mail + "'";
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			if (rs != null && rs.next()) {
				user.setUid(rs.getInt("uid"));
				user.setName(rs.getString("name"));
				user.setPassword(rs.getString("password"));
				user.setMail(rs.getString("mail"));
				user.setUrl(rs.getString("url"));
				user.setScreenName(rs.getString("screenName"));
				user.setAvatar(rs.getString("avatar"));
				user.setCreated(rs.getString("created"));
				user.setGroup(rs.getString("group"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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

}
