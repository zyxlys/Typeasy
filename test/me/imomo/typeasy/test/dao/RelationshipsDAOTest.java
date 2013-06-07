package me.imomo.typeasy.test.dao;

import java.util.List;

import me.imomo.typeasy.dao.MetasDAO;
import me.imomo.typeasy.dao.RelationshipsDAO;
import me.imomo.typeasy.vo.RelationshipsVO;

import org.junit.Test;

/**
 *	关系表测试
 * @author 夏、末
 *
 */
public class RelationshipsDAOTest {

	private  RelationshipsDAO relationshipsDao=null;
	

	@Test
	/**
	 * 测试添加
	 */
	public void add(){
		relationshipsDao=new RelationshipsDAO();
		RelationshipsVO relationship=new RelationshipsVO();
		relationship.setCid(1);
		relationship.setMid(2);
		relationshipsDao.add(relationship);
	}
	
	
	
	@Test
	/**
	 * 测试删除
	 */
	public void del(){
		relationshipsDao=new RelationshipsDAO();
		relationshipsDao.del(2);
	}
	
	
	
	@Test
	/**
	 * 测试查找所有
	 */
	public void find(){
		relationshipsDao=new RelationshipsDAO();
		List<RelationshipsVO> relationships=relationshipsDao.list();
		for(RelationshipsVO met:relationships){
			System.out.println(met.getMid());
		}
		System.out.println(relationships.size());
	}
}
