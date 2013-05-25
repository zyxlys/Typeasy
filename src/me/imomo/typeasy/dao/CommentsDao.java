package me.imomo.typeasy.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import me.imomo.typeasy.commons.DBConnection;
import me.imomo.typeasy.vo.Comments;

public class CommentsDao {
	private Connection conn = null;

	/**
	 * 增加评论
	 * 
	 * @param c
	 * @return
	 */
	public boolean add(Comments c) {
		conn = DBConnection.getConnection();
		String sql = "INSERT INTO comments(coid,cid,created,author,authorId,ownerId,mail,url,ip,agent,text,type,status,parent)VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, c.getCoid());
			pstmt.setInt(2, c.getCid());
			pstmt.setString(3, c.getCreated());
			pstmt.setString(4, c.getAuthor());
			pstmt.setInt(5, c.getAuthorId());
			pstmt.setInt(6, c.getOwnerId());
			pstmt.setString(7, c.getMail());
			pstmt.setString(8, c.getUrl());
			pstmt.setString(9, c.getIp());
			pstmt.setString(10, c.getAgent());
			pstmt.setString(11, c.getText());
			pstmt.setString(12, c.getType());
			pstmt.setString(13, c.getStatus());
			pstmt.setInt(14, c.getParent());
			pstmt.executeUpdate();
			return true;
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

		return false;

	}

	/**
	 * 删除评论
	 * 
	 * @param coid
	 * @return
	 */
	public boolean del(int coid) {
		Connection conn = DBConnection.getConnection();
		String sql = "DELETE FROM comments WHERE coid=?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, coid);
			pstmt.executeUpdate();
			return true;
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
		return false;

	}

	/**
	 * 修改评论
	 * 
	 * @param c
	 * @return
	 */
	public boolean edit(Comments c) {
		Connection conn = DBConnection.getConnection();
		String sql = "UPDATAE comments SET text=?,staus=?WHERE coid=?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(11, c.getText());
			pstmt.setString(13, c.getStatus());
			pstmt.executeUpdate();
			return true;
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
		return false;
	}

	/**
	 * 查询所有的评论
	 * 
	 * @return
	 */
	public List<Comments> list() {
		List<Comments> list = new ArrayList<Comments>();
		Comments c = null;
		Connection conn = DBConnection.getConnection();
		String sql = "SELECT * FROM comments ";
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				c = new Comments();
				c.setCoid(rs.getInt("coid"));
				c.setCid(rs.getInt("cid"));
				c.setCreated(rs.getString("created"));
				c.setAuthor(rs.getString("author"));
				c.setAuthorId(rs.getInt("authorId"));
				c.setOwnerId(rs.getInt("ownerId"));
				c.setMail(rs.getString("mail"));
				c.setUrl(rs.getString("url"));
				c.setIp(rs.getString("ip"));
				c.setAgent(rs.getString("agent"));
				c.setText(rs.getString("text"));
				c.setType(rs.getString("type"));
				c.setStatus(rs.getString("status"));
				c.setParent(rs.getInt("parent"));
				list.add(c);
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
		return list;
	}

	public Comments find(int coid) {
		Connection conn = DBConnection.getConnection();
		Comments c = new Comments();
		String sql = "SELECT * FROM comments  WHERE coid =?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, coid);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				c.setCoid(rs.getInt("coid"));
				c.setAuthor(rs.getString("author"));
				c.setCid(rs.getInt("cid"));
				c.setCreated(rs.getString("created"));
				c.setText(rs.getString("text"));
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
		return c;

	}

}
