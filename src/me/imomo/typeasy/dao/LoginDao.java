package me.imomo.typeasy.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import me.imomo.typeasy.commons.DBConnection;
import me.imomo.typeasy.vo.UsersVO;

/**
 * User Log In Data Access Object
 * 
 * @version 1.0 2013/05/01
 * @author Mo
 * 
 */
public class LoginDAO {

	/**
	 * 用户登录 若登录成功,返回数据库中对应的User对象
	 * 
	 * @param u
	 * @return User
	 */
	public UsersVO login(UsersVO u) {
		Connection conn = DBConnection.getConnection();
		UsersVO user = new UsersVO();
		if (u != null) {
			String sql = "SELECT * FROM `users` WHERE `name`=? AND `password`=?";
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
	public void register(UsersVO u) {
		Connection conn = DBConnection.getConnection();
		String sql = "INSERT INTO `users`(`name`,`password`,`mail`,`screenName`,`created`) VALUES(?,?,?,?,?)";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, u.getName());
			pstmt.setString(2, u.getPassword());
			pstmt.setString(3, u.getMail());
			pstmt.setString(4, u.getScreenName());
			pstmt.setString(5, u.getCreated());
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
	public UsersVO getUserByName(String name) {
		UsersVO user = new UsersVO();
		Connection conn = DBConnection.getConnection();
		String sql = "SELECT * FROM `users` WHERE `name`=?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			ResultSet rs = pstmt.executeQuery();
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
	public UsersVO getUserByMail(String mail) {
		UsersVO user = new UsersVO();
		Connection conn = DBConnection.getConnection();
		String sql = "SELECT * FROM `users` WHERE `mail`=?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mail);
			ResultSet rs = pstmt.executeQuery();
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
