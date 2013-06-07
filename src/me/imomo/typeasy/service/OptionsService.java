package me.imomo.typeasy.service;

import java.util.List;

import me.imomo.typeasy.dao.OptionsDAO;
import me.imomo.typeasy.vo.OptionsVO;

/**
 * Options Service
 * 
 * @version 1.0 2013/05/18
 * @author YL
 * 
 */
public class OptionsService {

	private OptionsDAO od = new OptionsDAO();

	/**
	 * 编辑选项
	 * 
	 * @param option
	 */
	public void edit(OptionsVO option) {
		od.edit(option);
	}

	/**
	 * 根据选项名称查找
	 * 
	 * @param name
	 * @return
	 */
	public OptionsVO findByName(String name) {
		return od.findByName(name);
	}

	/**
	 * 查找所有选项
	 * 
	 * @return
	 */
	public List<OptionsVO> list() {
		return od.list();
	}
}
