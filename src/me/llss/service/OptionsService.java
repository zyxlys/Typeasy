package me.llss.service;

import java.util.List;

import me.llss.vo.OptionsVO;

/**
 * Options Service接口
 * 
 * @author Acris
 * @version 2.0 2013/09/17 12:11
 * 
 */
public interface OptionsService {

	/**
	 * 编辑选项
	 * 
	 * @param option
	 */
	public abstract void edit(OptionsVO option);

	/**
	 * 根据选项名称查找
	 * 
	 * @param name
	 * @return
	 */
	public abstract OptionsVO findByName(String name);

	/**
	 * 查找所有选项
	 * 
	 * @return
	 */
	public abstract List<OptionsVO> list();
}
