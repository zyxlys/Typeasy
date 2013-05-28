package me.imomo.typeasy.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import me.imomo.typeasy.commons.DBConnection;
import me.imomo.typeasy.vo.UsersVO;

/**
 * User Data Access Object
 * 
 * @version 1.0 2013/05/02
 * @author Mo
 * 
 */
public class UsersDAO {

	/**
	 * 添加用户
	 * 
	 * @param u
	 */
	public void add(UsersVO user) {
		Connection conn = DBConnection.getConnection();
		String sql = "INSERT INTO users(name,password,mail,url,screenName,created) VALUES(?,?,?,?,?)";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getName());
			pstmt.setString(2, user.getPassword());
			pstmt.setString(3, user.getMail());
			pstmt.setString(4, user.getUrl());
			pstmt.setString(5, user.getScreenName());
			pstmt.setString(6, user.getCreated());
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
	 * List all user
	 * 
	 * @return List<User>
	 */
	public List<UsersVO> list() {
		List<UsersVO> users = new ArrayList<UsersVO>();
		UsersVO user = null;
		Connection conn = DBConnection.getConnection();
		String sql = "SELECT * FROM users";
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				user = new UsersVO();
				user.setUid(rs.getInt("uid"));
				user.setName(rs.getString("name"));
				user.setPassword("password");
				user.setMail(rs.getString("mail"));
				user.setUrl(rs.getString("url"));
				user.setScreenName(rs.getString("screenName"));
				user.setAvatar(rs.getString("avatar"));
				user.setCreated(rs.getString("created"));
				user.setGroup(rs.getString("group"));
				users.add(user);
			}
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
		return users;
	}

	/**
	 * update user
	 * 
	 * @param user
	 */
	public void edit(UsersVO user) {
		Connection conn = DBConnection.getConnection();
		String sql = "UPDATE users SET name=?,password=?,mail=?,url=?,screenName=?,avatar=?,group=? WHERE uid=?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getName());
			pstmt.setString(2, user.getPassword());
			pstmt.setString(3, user.getMail());
			pstmt.setString(4, user.getUrl());
			pstmt.setString(5, user.getScreenName());
			pstmt.setString(6, user.getAvatar());
			pstmt.setString(7, user.getGroup());
			pstmt.setInt(8, user.getUid());
			pstmt.executeUpdate();
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
	public UsersVO find(int uid) {
		UsersVO user = null;
		Connection conn = DBConnection.getConnection();
		String sql = "SELECT * FROM users WHERE uid=?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, uid);
			ResultSet rs = pstmt.executeQuery();
			if (rs != null && rs.next()) {
				user = new UsersVO();
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
	 * delete user by user name
	 * 
	 * @param id
	 */
	public void del(int uid) {
		Connection conn = DBConnection.getConnection();
		String sql = "DELETE FROM users WHERE uid=?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, uid);
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

}
