package me.imomo.typeasy.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import me.imomo.typeasy.commons.DBConnection;
import me.imomo.typeasy.vo.CommentsVO;

public class CommentsDAO {

	/**
	 * 增加评论
	 * 
	 * @param c
	 * @return
	 */
	public void add(CommentsVO c) {
		Connection conn = DBConnection.getConnection();
		String sql = "INSERT INTO `comments`(`cid`,`created`,`author`,`authorId`,`ownerId`,`mail`,`url`,`text`) VALUES(?,?,?,?,?,?,?,?)";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, c.getCid());
			pstmt.setString(2, c.getCreated());
			pstmt.setString(3, c.getAuthor());
			pstmt.setInt(4, c.getAuthorId());
			pstmt.setInt(5, c.getOwnerId());
			pstmt.setString(6, c.getMail());
			pstmt.setString(7, c.getUrl());
			pstmt.setString(8, c.getText());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

	}

	/**
	 * 删除评论
	 * 
	 * @param coid
	 * @return
	 */
	public void del(int coid) {
		Connection conn = DBConnection.getConnection();
		String sql = "DELETE FROM `comments` WHERE `coid`=?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, coid);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

	}

	public void delByCid(int cid) {
		Connection conn = DBConnection.getConnection();
		String sql = "DELETE FROM `comments` WHERE `cid`=?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, cid);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

	}

	/**
	 * 修改评论
	 * 
	 * @param c
	 * @return
	 */
	public void edit(CommentsVO c) {
		Connection conn = DBConnection.getConnection();
		String sql = "UPDATE `comments` SET `author`=?,`mail`=?,`url`=?,`text`=? WHERE `coid`=?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, c.getAuthor());
			pstmt.setString(2, c.getMail());
			pstmt.setString(3, c.getUrl());
			pstmt.setString(4, c.getText());
			pstmt.setInt(5, c.getCoid());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 查询所有的评论
	 * 
	 * @return
	 */
	public List<CommentsVO> list() {
		List<CommentsVO> comments = new ArrayList<CommentsVO>();
		CommentsVO comment = null;
		Connection conn = DBConnection.getConnection();
		String sql = "SELECT * FROM `comments` ";
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				comment = new CommentsVO();
				comment.setCoid(rs.getInt("coid"));
				comment.setCid(rs.getInt("cid"));
				comment.setCreated(rs.getString("created"));
				comment.setAuthor(rs.getString("author"));
				comment.setAuthorId(rs.getInt("authorId"));
				comment.setOwnerId(rs.getInt("ownerId"));
				comment.setMail(rs.getString("mail"));
				comment.setUrl(rs.getString("url"));
				comment.setIp(rs.getString("ip"));
				comment.setAgent(rs.getString("agent"));
				comment.setText(rs.getString("text"));
				comment.setType(rs.getString("type"));
				comment.setStatus(rs.getString("status"));
				comment.setParent(rs.getInt("parent"));
				comments.add(comment);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return comments;
	}

	/**
	 * 通过coid查找评论
	 * 
	 * @param coid
	 * @return
	 */
	public CommentsVO find(int coid) {
		Connection conn = DBConnection.getConnection();
		CommentsVO comment = new CommentsVO();
		String sql = "SELECT * FROM `comments`  WHERE `coid` =?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, coid);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				comment.setCoid(rs.getInt("coid"));
				comment.setAuthor(rs.getString("author"));
				comment.setCid(rs.getInt("cid"));
				comment.setCreated(rs.getString("created"));
				comment.setText(rs.getString("text"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		}
		return comment;
	}

}
