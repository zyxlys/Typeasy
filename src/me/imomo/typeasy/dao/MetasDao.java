package me.imomo.typeasy.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import me.imomo.typeasy.commons.DBConnection;
import me.imomo.typeasy.vo.Metas;

public class MetasDao {
	private Connection conn = null;
	
	/**
	 * 按项目编号查找信息
	 * @param mid
	 * @return
	 */
	public Metas findByMetasMid(int mid){
		conn=DBConnection.getConnection();
		Metas met=new Metas();
		String sql="SELECT * FROM metas WHERE mid=? ";
		try{
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setInt(1, mid);
			ResultSet rs=ps.executeQuery();
			
		    while(rs.next()){
				met.setMid(rs.getInt(1));
				met.setName(rs.getString(2));
				met.setSlug(rs.getString(3));
				met.setType(rs.getString(4));
				met.setDescription(rs.getString(5));
				met.setCount(rs.getInt(6));
				met.setOrder(rs.getInt(7));
		   }
		rs.close();
		ps.close();
		
		}catch(Exception e){
			   e.printStackTrace();
		   
		}finally{
			try{
				conn.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return met;
	}
	
	
	/**
	 * 按项目名称查找信息
	 * @param name
	 * @return
	 */
	public Metas findByMetasName(String name){
		conn=DBConnection.getConnection();
		Metas met=new Metas();
		String sql="SELECT * FROM metas WHERE name=?";
		try{
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				met.setMid(rs.getInt(1));
				met.setName(rs.getString(2));
				met.setSlug(rs.getString(3));
				met.setType(rs.getString(4));
				met.setDescription(rs.getString(5));
				met.setCount(rs.getInt(6));
				met.setOrder(rs.getInt(7));
			}
			rs.close();
			ps.close();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				conn.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return met;
		
	}
	
	
	/**
	 * 插入项目信息
	 * @param met
	 */
	public void insert(Metas met){
		conn=DBConnection.getConnection();
		String sql="INSERT INTO Metas VALUES(?,?,?,?,?,?,?)";
		try{
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setInt(1, met.getMid());
			ps.setString(2,met.getName());
			ps.setString(3, met.getSlug());
			ps.setString(4, met.getType());
			ps.setString(5, met.getDescription());
			ps.setInt(6, met.getCount());
			ps.setInt(7, met.getOrder());
			//ps.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				conn.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	
	
	
	/**
	 * 按项目编号删除项目信息
	 * @param mid
	 */
	public void delete(int mid){
		conn=DBConnection.getConnection();
		Metas met=new Metas();
		String sql="DELETE FROM Metas WHERE mid=?";
		try{
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setInt(1,mid);
			ps.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				conn.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	
	
	/**
	 * 修改项目信息
	 * @param met
	 */
	public void modify(Metas met){
		conn=DBConnection.getConnection();
		String sql="UPDATE Mates SET name=?,slug=?,type=?,description=?,count=?,order=? WHERE mid=?";
		try{
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setInt(7, met.getMid());
			ps.setString(2, met.getName());
			ps.setString(3, met.getSlug());
			ps.setString(4, met.getType());
			ps.setInt(5, met.getCount());
			ps.setInt(6, met.getOrder());
			//ps.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				conn.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	
	
	/**
	 * 查找所有的项目信息
	 * @return
	 */
	public List<Metas> findAll(){
		List<Metas> list=new ArrayList<Metas>();
		conn=DBConnection.getConnection();
		Metas meta=new Metas();
		String sql="SELECT * FROM Metas";
		try{
			PreparedStatement ps=conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				meta.setMid(rs.getInt("mid"));
				list.add(meta);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				conn.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return list;
	}
	
}
