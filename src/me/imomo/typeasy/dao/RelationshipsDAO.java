package me.imomo.typeasy.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import me.imomo.typeasy.commons.DBConnection;
import me.imomo.typeasy.vo.RelationshipsVO;

/**
 * 关系表DAO类
 * 
 * @author Administrator
 * 
 */
public class RelationshipsDAO {

	/**
	 * 增加关系
	 * 
	 * @param relationship
	 */
	public void add(RelationshipsVO relationship) {
		Connection conn = DBConnection.getConnection();
		String sql = "INSERT INTO `relationships`(`cid`,`mid`) VALUES (?,?)";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, relationship.getCid());
			ps.setInt(2, relationship.getMid());
			ps.executeUpdate();
		} catch (Exception e) {
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
	 * 删除关系表的cid
	 * 
	 * @param cid
	 */
	public void del(int cid) {
		Connection conn = DBConnection.getConnection();
		String sql = "DELETE FROM `relationships` WHERE `cid`=?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, cid);
			ps.execute();
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
	 * 修改关系表的mid
	 * 
	 * @param relationship
	 */
	public void edit(RelationshipsVO relationship, int oldMid) {
		Connection conn = DBConnection.getConnection();
		String sql = "UPDATE `relationships` SET `mid`=? WHERE `cid`=? AND `mid`=?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, relationship.getMid());
			ps.setInt(2, relationship.getCid());
			ps.setInt(3, oldMid);
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
	 * 查找所有的关系
	 * 
	 * @return
	 */
	public List<RelationshipsVO> list() {
		List<RelationshipsVO> relationships = new ArrayList<RelationshipsVO>();
		Connection conn = DBConnection.getConnection();
		RelationshipsVO relationship = null;
		String sql = "SELECT * FROM `relationships`";
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				relationship = new RelationshipsVO();
				relationship.setCid(rs.getInt("cid"));
				relationship.setMid(rs.getInt("mid"));
				relationships.add(relationship);
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
		return relationships;

	}

	/**
	 * 通过mid查找cid的个数，及文章数
	 * 
	 * @param mid
	 * @return
	 */
	public List<RelationshipsVO> findByMid(int mid) {
		List<RelationshipsVO> relationships = new ArrayList<RelationshipsVO>();
		Connection conn = DBConnection.getConnection();
		RelationshipsVO relationshipVO = null;
		String sql = "SELECT * FROM `relationships` WHERE `mid`=? ";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, mid);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				relationshipVO = new RelationshipsVO();
				relationshipVO.setCid(rs.getInt("cid"));
				relationshipVO.setMid(rs.getInt("mid"));
				relationships.add(relationshipVO);
			}
		} catch (SQLException e) {
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return relationships;

	}

	/**
	 * 根据cid查找关系对象
	 * 
	 * @param cid
	 * @return
	 */
	public List<RelationshipsVO> findByCid(int cid) {
		List<RelationshipsVO> relationships = new ArrayList<RelationshipsVO>();
		Connection conn = DBConnection.getConnection();
		RelationshipsVO relationshipVO = null;
		String sql = "SELECT * FROM `relationships` WHERE `cid`=? ";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, cid);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				relationshipVO = new RelationshipsVO();
				relationshipVO.setCid(rs.getInt("cid"));
				relationshipVO.setMid(rs.getInt("mid"));
				relationships.add(relationshipVO);
			}
		} catch (SQLException e) {
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return relationships;

	}

	/**
	 * 计算mid对应的cid数
	 * 
	 * @param mid
	 * @return
	 */
	public int countContents(int mid) {
		int count = -1;
		Connection conn = DBConnection.getConnection();
		String sql = "SELECT count(cid) FROM relationships WHERE mid=?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, mid);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				count = rs.getInt(1);
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
		return count;

	}
}
