package me.llss.dao;

import java.util.List;

import me.llss.vo.OptionsVO;

/**
 * OptionsDAO接口
 * 
 * @author Acris
 * @version 2.0 2013/09/17 11:28
 */

public interface OptionsDAO {
	/**
	 * 根据配置名称查找
	 * 
	 * @param name
	 * @return options
	 */
	public abstract OptionsVO findByName(String name);

	/**
	 * 修改操作
	 * 
	 * @param options
	 * @return options
	 */
	public abstract void edit(OptionsVO options);

	/**
	 * 查询所有的选项
	 * 
	 * @return
	 */
	public abstract List<OptionsVO> list();
}
