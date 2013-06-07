package me.imomo.typeasy.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import me.imomo.typeasy.commons.DBConnection;
import me.imomo.typeasy.vo.OptionsVO;

/**
 * Options Data Access Object
 * 
 * @version 1.0 2013/05/12
 * @author YL
 * 
 */

public class OptionsDAO {

	/**
	 * 根据配置名称查找
	 * 
	 * @param name
	 * @return options
	 */
	public OptionsVO findByName(String name) {
		OptionsVO option = new OptionsVO();
		Connection conn = DBConnection.getConnection();
		String sql = "SELECT * FROM `options` WHERE `name`=?";

		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();
			while (rs != null && rs.next()) {
				option.setName(rs.getString("name"));
				// option.setUser(rs.getInt("user"));
				option.setValue(rs.getString("value"));
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

		return option;
	}

	/**
	 * 修改操作
	 * 
	 * @param options
	 * @return options
	 */
	public void edit(OptionsVO options) {
		Connection conn = DBConnection.getConnection();
		String sql = "UPDATE `options` SET `value`=? WHERE `name`=?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, options.getValue());
			ps.setString(2, options.getName());
			ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 查询所有的选项
	 * 
	 * @return
	 */
	public List<OptionsVO> list() {
		List<OptionsVO> options = new ArrayList<OptionsVO>();
		OptionsVO option = null;
		Connection conn = DBConnection.getConnection();
		String sql = "SELECT * FROM `options`";

		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				option = new OptionsVO();
				option.setName(rs.getString("name"));
				option.setValue(rs.getString("value"));
				options.add(option);
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

		return options;
	}

}
