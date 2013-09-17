package me.llss.dao;

import java.util.List;

import me.llss.vo.MetasVO;

/**
 * MetasDAO接口
 * 
 * @author Acris
 * @version 2.0 2013/09/17 11:27
 */

public interface MetasDAO {
	/**
	 * 按项目编号查找信息
	 * 
	 * @param mid
	 * @return
	 */
	public abstract MetasVO findByMid(int mid);

	/**
	 * 按项目名称查找信息
	 * 
	 * @param name
	 * @return
	 */
	public abstract MetasVO findByName(String name);

	/**
	 * 插入项目信息
	 * 
	 * @param met
	 */

	public abstract void add(MetasVO meta);

	/**
	 * 按项目编号删除项目信息
	 * 
	 * @param mid
	 */
	public abstract void del(int mid);

	/**
	 * 修改项目信息
	 * 
	 * @param met
	 */
	public abstract void edit(MetasVO meta);

	/**
	 * 查找所有的项目信息
	 * 
	 * @return
	 */
	public abstract List<MetasVO> find();

	/**
	 * 修改对应的文章数
	 * 
	 * @param mid
	 * @param op
	 */
	public abstract void editCount(int mid, String op);
}
