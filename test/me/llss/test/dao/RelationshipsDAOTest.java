package me.llss.test.dao;

import java.util.List;

import me.llss.dao.impl.RelationshipsDAOImpl;
import me.llss.vo.RelationshipsVO;

import org.junit.Test;

/**
 * 关系表测试
 * 
 * @author 夏、末
 * 
 */
public class RelationshipsDAOTest {

	private RelationshipsDAOImpl relationshipsDao = null;

	@Test
	/**
	 * 测试添加
	 */
	public void add() {
		relationshipsDao = new RelationshipsDAOImpl();
		RelationshipsVO relationship = new RelationshipsVO();
		relationship.setCid(1);
		relationship.setMid(2);
		relationshipsDao.add(relationship);
	}

	@Test
	/**
	 * 测试删除
	 */
	public void del() {
		relationshipsDao = new RelationshipsDAOImpl();
		relationshipsDao.del(2);
	}

	@Test
	/**
	 * 测试查找所有
	 */
	public void find() {
		relationshipsDao = new RelationshipsDAOImpl();
		List<RelationshipsVO> relationships = relationshipsDao.list();
		for (RelationshipsVO met : relationships) {
			System.out.println(met.getMid());
		}
		System.out.println(relationships.size());
	}
}
