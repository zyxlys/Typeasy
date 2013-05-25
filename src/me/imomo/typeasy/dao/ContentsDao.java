package me.imomo.typeasy.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.resource.cci.ResultSet;

import me.imomo.typeasy.commons.DBConnection;
import me.imomo.typeasy.vo.Contents;
import me.imomo.typeasy.vo.Users;

public class ContentsDao {
	
	//发表文章
	public boolean addArticle(Contents article){
		Connection conn = DBConnection.getConnection();
		try {
			PreparedStatement ps = conn.prepareStatement("INSERT INTO contents(title,created,text,type) VALUES(?,?,?,?)");
			ps.setString(1,article.getTitle());
			ps.setString(2,article.getCreated());
			ps.setString(3,article.getText());
			ps.setString(4,article.getType());
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	//根据cid删除文章
	public boolean deleteArticle(int id){
		Connection conn = DBConnection.getConnection();
		try {
			PreparedStatement ps = conn.prepareStatement("DELETE FROM contents WHERE cid = ?");
			ps.setInt(1,id);
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	//根据cid修改文章
	public boolean modify(Contents article){
		Connection conn = DBConnection.getConnection();
		try {
			PreparedStatement ps = conn.prepareStatement("UPDATE contents SET title =?,text =?,modified = ?,type =? WHERE cid =?");
			ps.setString(1,article.getTitle());
			ps.setString(2,article.getText());
			ps.setString(3,article.getModified());
			ps.setString(4,article.getType());
			ps.setInt(5,article.getCid());
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	//根据cid修改评论次数
	public boolean updateArticle(int commentsNum,int cid){
		Connection conn = DBConnection.getConnection();
		try {
			PreparedStatement ps = conn.prepareStatement("UPDATE contents SET commentsNum =? where cid =?");
			ps.setInt(1,commentsNum);
			ps.setInt(2,cid);
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
		
	}
	//根据cid查询文章
	public Contents selectById(int cid){
		Contents ret = null;
		Connection conn = DBConnection.getConnection();
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM contents WHERE cid = ?");
			ps.setInt(1,cid);
			java.sql.ResultSet rs = ps.executeQuery();
			while(rs.next()){
				ret = new Contents();
				ret.setCid(rs.getInt(1));
				ret.setTitle(rs.getString(2));
				ret.setSlug(rs.getString(3));
				ret.setCreated(rs.getString(4));
				ret.setModified(rs.getString(5));
				ret.setText(rs.getString(6));
				ret.setOrder(rs.getInt(7));
				ret.setAuthorId(rs.getInt(8));
				ret.setTemplate(rs.getString(9));
				ret.setType(rs.getString(10));
				ret.setStatus(rs.getString(11));
				ret.setPassword(rs.getString(12));
				ret.setCommentsNum(rs.getInt(13));
				ret.setAllowComment(rs.getString(14));
				ret.setAllowPing(rs.getString(15));
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return ret;
	}
	
	//查询所有文章 
	public List<Contents> findAll(){
		List<Contents> list = new ArrayList<Contents>();
		Contents ret = null;
		Connection conn = DBConnection.getConnection();
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM contents");
			java.sql.ResultSet rs = ps.executeQuery();
			while(rs.next()){
				ret = new Contents();
				ret.setCid(rs.getInt(1));
				ret.setTitle(rs.getString(2));
				ret.setSlug(rs.getString(3));
				ret.setCreated(rs.getString(4));
				ret.setModified(rs.getString(5));
				ret.setText(rs.getString(6));
				ret.setOrder(rs.getInt(7));
				ret.setAuthorId(rs.getInt(8));
				ret.setTemplate(rs.getString(9));
				ret.setType(rs.getString(10));
				ret.setStatus(rs.getString(11));
				ret.setPassword(rs.getString(12));
				ret.setCommentsNum(rs.getInt(13));
				ret.setAllowComment(rs.getString(14));
				ret.setAllowPing(rs.getString(15));
				list.add(ret);
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
}
