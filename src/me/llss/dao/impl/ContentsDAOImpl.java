package me.llss.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import me.llss.dao.ContentsDAO;
import me.llss.utils.DBConnection;
import me.llss.vo.ContentsVO;

/**
 * 文章表数据库操作类
 * 
 * @author Acris
 * @version 2.0 2013/09/17 11:35
 * 
 */
public class ContentsDAOImpl implements ContentsDAO {

	/**
	 * 发表文章
	 * 
	 * @param article
	 */
	public void add(ContentsVO article) {
		Connection conn = DBConnection.getConnection();
		try {
			PreparedStatement ps = conn
					.prepareStatement("INSERT INTO `contents`(`title`,`created`,`text`,`type`,`authorId`,`slug`) VALUES(?,?,?,?,?,?)");
			ps.setString(1, article.getTitle());
			ps.setString(2, article.getCreated());
			ps.setString(3, article.getText());
			ps.setString(4, article.getType());
			ps.setInt(5, article.getAuthorId());
			ps.setString(6, article.getTitle());
			ps.executeUpdate();
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
	}

	/**
	 * 根据id删除文章
	 * 
	 * @param id
	 */
	public void del(int id) {
		Connection conn = DBConnection.getConnection();
		try {
			PreparedStatement ps = conn
					.prepareStatement("DELETE FROM `contents` WHERE `cid` = ?");
			ps.setInt(1, id);
			ps.executeUpdate();
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
	}

	/**
	 * 根据id修改文章
	 */
	public void edit(ContentsVO article) {
		Connection conn = DBConnection.getConnection();
		try {
			PreparedStatement ps = conn
					.prepareStatement("UPDATE `contents` SET `title` =?,`text` =?,`modified` = ?,`type` =? WHERE `cid` =?");
			ps.setString(1, article.getTitle());
			ps.setString(2, article.getText());
			ps.setString(3, article.getModified());
			ps.setString(4, article.getType());
			ps.setInt(5, article.getCid());
			ps.executeUpdate();
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
	}

	/**
	 * 根据cid查询文章
	 * 
	 * @param cid
	 * @return
	 */
	public ContentsVO find(int cid) {
		ContentsVO content = null;
		Connection conn = DBConnection.getConnection();
		try {
			PreparedStatement ps = conn
					.prepareStatement("SELECT * FROM `contents` WHERE `cid` = ?");
			ps.setInt(1, cid);
			java.sql.ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				content = new ContentsVO();
				content.setCid(rs.getInt(1));
				content.setTitle(rs.getString(2));
				content.setSlug(rs.getString(3));
				content.setCreated(rs.getString(4));
				content.setModified(rs.getString(5));
				content.setText(rs.getString(6));
				content.setOrder(rs.getInt(7));
				content.setAuthorId(rs.getInt(8));
				content.setTemplate(rs.getString(9));
				content.setType(rs.getString(10));
				content.setStatus(rs.getString(11));
				content.setPassword(rs.getString(12));
				content.setCommentsNum(rs.getInt(13));
				content.setAllowComment(rs.getString(14));
				content.setAllowPing(rs.getString(15));
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
		return content;
	}

	/**
	 * 查询所有文章
	 * 
	 * @return
	 */
	public List<ContentsVO> list() {
		List<ContentsVO> contents = new ArrayList<ContentsVO>();
		ContentsVO content = null;
		Connection conn = DBConnection.getConnection();
		try {
			PreparedStatement ps = conn
					.prepareStatement("SELECT * FROM `contents`");
			java.sql.ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				content = new ContentsVO();
				content.setCid(rs.getInt(1));
				content.setTitle(rs.getString(2));
				content.setSlug(rs.getString(3));
				content.setCreated(rs.getString(4));
				content.setModified(rs.getString(5));
				content.setText(rs.getString(6));
				content.setOrder(rs.getInt(7));
				content.setAuthorId(rs.getInt(8));
				content.setTemplate(rs.getString(9));
				content.setType(rs.getString(10));
				content.setStatus(rs.getString(11));
				content.setPassword(rs.getString(12));
				content.setCommentsNum(rs.getInt(13));
				content.setAllowComment(rs.getString(14));
				content.setAllowPing(rs.getString(15));
				contents.add(content);
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
		return contents;
	}

	/**
	 * 更改评论数
	 * 
	 * @param cid
	 * @param o
	 */
	public void editCommentsNum(int cid, String o) {
		Connection conn = DBConnection.getConnection();
		String sql = "";
		if (o == null)
			o = "";
		if (o.equals("+"))
			sql = "UPDATE `contents` SET `commentsNum`=`commentsNum`+1 WHERE `cid` = ?";
		else if (o.equals("-"))
			sql = "UPDATE `contents` SET `commentsNum`=`commentsNum`-1 WHERE `cid` = ?";
		else
			sql = "UPDATE `contents` SET `commentsNum`=`commentsNum` WHERE `cid` = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, cid);
			ps.executeUpdate();
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
	}

	/**
	 * 搜索文章
	 * 
	 * @param keywords
	 * @return
	 */
	public List<ContentsVO> search(String keywords) {
		List<ContentsVO> contents = new ArrayList<ContentsVO>();
		ContentsVO content = null;
		Connection conn = DBConnection.getConnection();
		String sql = "SELECT * FROM `contents` WHERE `title` LIKE ? AND `type`='post'";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + keywords + "%");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				content = new ContentsVO();
				content.setCid(rs.getInt(1));
				content.setTitle(rs.getString(2));
				content.setSlug(rs.getString(3));
				content.setCreated(rs.getString(4));
				content.setModified(rs.getString(5));
				content.setText(rs.getString(6));
				content.setOrder(rs.getInt(7));
				content.setAuthorId(rs.getInt(8));
				content.setTemplate(rs.getString(9));
				content.setType(rs.getString(10));
				content.setStatus(rs.getString(11));
				content.setPassword(rs.getString(12));
				content.setCommentsNum(rs.getInt(13));
				content.setAllowComment(rs.getString(14));
				content.setAllowPing(rs.getString(15));
				contents.add(content);
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
		return contents;
	}

}
