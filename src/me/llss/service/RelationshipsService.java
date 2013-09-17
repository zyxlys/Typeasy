package me.llss.service;

import java.util.List;

import me.llss.vo.RelationshipsVO;

/**
 * RelationshipsService接口
 * 
 * @author Acris
 * @version 2.0 2013/09/17 12:13
 */
public interface RelationshipsService {

	/**
	 * 添加关系
	 * 
	 * @param relationship
	 */
	public abstract void add(RelationshipsVO relationship);

	/**
	 * 删除关系
	 * 
	 * @param cid
	 */
	public abstract void del(int cid);

	/**
	 * 修改关系
	 * 
	 * @param relationship
	 * @param oldMid
	 */
	public abstract void edit(RelationshipsVO relationship, int oldMid);

	/**
	 * 查询关系
	 * 
	 * @return
	 */
	public abstract List<RelationshipsVO> list();

	/**
	 * 根据mid查找关系
	 * 
	 * @param mid
	 * @return
	 */
	public abstract List<RelationshipsVO> findByMid(int mid);

	/**
	 * 根据cid查找关系
	 * 
	 * @param mid
	 * @return
	 */
	public abstract List<RelationshipsVO> findByCid(int cid);

	/**
	 * 查询mid对应的cid数
	 * 
	 * @param mid
	 * @return
	 */
	public abstract int countContents(int mid);
}
