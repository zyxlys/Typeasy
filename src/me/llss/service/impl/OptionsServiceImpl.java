package me.llss.service.impl;

import java.util.List;

import me.llss.dao.impl.OptionsDAOImpl;
import me.llss.service.OptionsService;
import me.llss.vo.OptionsVO;

/**
 * Options Service
 * 
 * @author Acris
 * @version 2.0 2013/09/17 12:13
 * 
 */
public class OptionsServiceImpl implements OptionsService {

	private OptionsDAOImpl od = new OptionsDAOImpl();

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
