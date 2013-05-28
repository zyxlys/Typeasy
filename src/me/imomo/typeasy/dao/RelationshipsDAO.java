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

public class RelationshipsDAO {


	public void add(RelationshipsVO relationship) {
		Connection conn = DBConnection.getConnection();
		String sql = "INSERT INTO relationships VALUES (?,?)";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, relationship.getCid());
			ps.setInt(2, relationship.getMid());
			ps.executeUpdate();
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

	public void del(int cid) {
		Connection conn = DBConnection.getConnection();
		String sql = "DELETE FROM relationships WHERE cid=?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, cid);
			ps.executeUpdate();
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

	public void edit(RelationshipsVO relationship) {
		Connection conn = DBConnection.getConnection();
		String sql = "UPDATE relationships SET mid=? WHERE cid=?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(2, relationship.getCid());
			ps.setInt(1, relationship.getMid());
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

	public List<RelationshipsVO> find() {
		List<RelationshipsVO> relationships = new ArrayList<RelationshipsVO>();
		Connection conn = DBConnection.getConnection();
		RelationshipsVO relationship = null;
		String sql = "SELECT * FROM relationships";
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

	public RelationshipsVO findMid(int mid) {
		Connection conn = DBConnection.getConnection();
		RelationshipsVO relationship = new RelationshipsVO();
		String sql = "SELECT * FROM relationships WHERE mid=?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, mid);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				relationship.setCid(rs.getInt("cid"));
				relationship.setMid(rs.getInt("mid"));
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
		return relationship;

	}
}
