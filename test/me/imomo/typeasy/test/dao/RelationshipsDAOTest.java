package me.imomo.typeasy.test.dao;

import java.util.List;

import me.imomo.typeasy.dao.MetasDAO;
import me.imomo.typeasy.dao.RelationshipsDAO;
import me.imomo.typeasy.vo.RelationshipsVO;

import org.junit.Test;

public class RelationshipsDAOTest {

	private  RelationshipsDAO relationshipsDao=null;
	

	@Test
	public void add(){
		relationshipsDao=new RelationshipsDAO();
		RelationshipsVO relationship=new RelationshipsVO();
		relationship.setCid(1);
		relationship.setMid(2);
		relationshipsDao.add(relationship);
	}
	
	@Test
	public void findMid(){
		
		relationshipsDao=new RelationshipsDAO();
		System.out.println(relationshipsDao.findMid(2).getMid());
	}
	
	
	@Test
	public void del(){
		relationshipsDao=new RelationshipsDAO();
		relationshipsDao.del(2);
	}
	
	@Test
	public void edit(){	
		relationshipsDao=new RelationshipsDAO();
		RelationshipsVO relationship=new RelationshipsVO();
		relationship.setCid(1);
		relationship.setMid(1);
		relationshipsDao.edit(relationship);
	}
	
	
	@Test
	public void find(){
		relationshipsDao=new RelationshipsDAO();
		List<RelationshipsVO> relationships=relationshipsDao.find();
		for(RelationshipsVO met:relationships){
			System.out.println(met.getMid());
		}
		System.out.println(relationships.size());
	}
}
