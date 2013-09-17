package me.llss.service.impl;

import java.util.List;

import me.llss.dao.impl.RelationshipsDAOImpl;
import me.llss.service.RelationshipsService;
import me.llss.vo.RelationshipsVO;

/**
 * RelationshipsService实现
 * 
 * @author Acris
 * @version 2.0 2013/09/17 12:15
 * 
 */
public class RelationshipsServiceImpl implements RelationshipsService {

	private RelationshipsDAOImpl relationshipsDAO = new RelationshipsDAOImpl();

	/**
	 * 添加关系
	 * 
	 * @param relationship
	 */
	public void add(RelationshipsVO relationship) {
		relationshipsDAO.add(relationship);
	}

	/**
	 * 删除关系
	 * 
	 * @param cid
	 */
	public void del(int cid) {
		relationshipsDAO.del(cid);
	}

	/**
	 * 修改关系
	 * 
	 * @param relationship
	 * @param oldMid
	 */
	public void edit(RelationshipsVO relationship, int oldMid) {
		relationshipsDAO.edit(relationship, oldMid);
	}

	/**
	 * 查询关系
	 * 
	 * @return
	 */
	public List<RelationshipsVO> list() {
		return relationshipsDAO.list();
	}

	/**
	 * 根据mid查找关系
	 * 
	 * @param mid
	 * @return
	 */
	public List<RelationshipsVO> findByMid(int mid) {
		return relationshipsDAO.findByMid(mid);
	}

	/**
	 * 根据cid查找关系
	 * 
	 * @param mid
	 * @return
	 */
	public List<RelationshipsVO> findByCid(int cid) {
		return relationshipsDAO.findByCid(cid);
	}

	/**
	 * 查询mid对应的cid数
	 * 
	 * @param mid
	 * @return
	 */
	public int countContents(int mid) {
		return relationshipsDAO.countContents(mid);
	}
}
