package me.imomo.typeasy.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import me.imomo.typeasy.commons.DBConnection;
import me.imomo.typeasy.vo.Options;

/**
 * Options Data Access Object
 * @version 1.0	2013/05/12
 * @author YL
 *
 */

public class OptionsDao {
	private Connection conn = null;
	
	/**
	 * 根据配置名称查找
	 * @param name
	 * @return options
	 */
	public Options FindByName(String name){
		Options options = new Options();
		conn = DBConnection.getConnection();
		String sql = "SELECT * FROM options WHERE name='"+name+"'";
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs!=null && rs.next())
			{
				options.setName(rs.getString("name"));
				options.setUser(rs.getInt("user"));
				options.setValue(rs.getString("value"));
			}
			rs.close();
			ps.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(conn!=null)
			{
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
	
	/**
	 * 插入操作
	 * @param options
	 */
	public boolean Insert(Options options)
	{
		conn = DBConnection.getConnection();
		String sql = "INSERT INTO Options(name,value) VALUES(?,?)";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, options.getName());
			ps.setString(2, options.getValue());
			ps.executeUpdate();
			return true;
			
		} catch (Exception e) {
			e.printStackTrace();		
		} finally {
			if(conn!=null)
			{
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return false;
	}
	
	/**
	 * 根据user删除操作
	 * @param name
	 */
	public boolean Delete(String name)
	{
		conn = DBConnection.getConnection();
		String sql = "DELETE FROM Options WHERE name=?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, name);
			ps.executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(conn!=null)
			{
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return false;
	}
	
	/**
	 * 修改操作
	 * @param options
	 * @return options
	 */
	public boolean Modify(Options options)
	{
		conn = DBConnection.getConnection();
		String sql = "UPDATE Options SET value=? WHERE name=?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, options.getName());
			ps.setString(2, options.getValue());
			ps.executeUpdate();
			return true;
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(conn!=null)
			{
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return false;
	}
	
	public List<Options> findAll()
	{
		List<Options> list = new ArrayList<Options>();
		
		conn = DBConnection.getConnection(); 
		Options op = null;
		String sql = "SELECT * FROM Options";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next())
			{
				op = new Options();
				op.setName(rs.getString(1));
				op.setUser(rs.getInt(2));
				op.setValue(rs.getString(3));
				list.add(op);
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			if(conn!=null)
			{
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		return list;
	}
}
