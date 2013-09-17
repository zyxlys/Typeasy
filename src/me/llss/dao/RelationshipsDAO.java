package me.llss.dao;

import java.util.List;

import me.llss.vo.RelationshipsVO;

/**
 * RelationshipsDAO接口
 * 
 * @author Acris
 * @version 2.0 2013/09/17 11:31
 */

public interface RelationshipsDAO {

	/**
	 * 增加关系
	 * 
	 * @param relationship
	 */
	public abstract void add(RelationshipsVO relationship);

	/**
	 * 删除关系表的cid
	 * 
	 * @param cid
	 */
	public abstract void del(int cid);

	/**
	 * 修改关系表的mid
	 * 
	 * @param relationship
	 */
	public abstract void edit(RelationshipsVO relationship, int oldMid);

	/**
	 * 查找所有的关系
	 * 
	 * @return
	 */
	public abstract List<RelationshipsVO> list();

	/**
	 * 通过mid查找cid的个数，及文章数
	 * 
	 * @param mid
	 * @return
	 */
	public abstract List<RelationshipsVO> findByMid(int mid);

	/**
	 * 根据cid查找关系对象
	 * 
	 * @param cid
	 * @return
	 */
	public abstract List<RelationshipsVO> findByCid(int cid);

	/**
	 * 计算mid对应的cid数
	 * 
	 * @param mid
	 * @return
	 */
	public abstract int countContents(int mid);

}
