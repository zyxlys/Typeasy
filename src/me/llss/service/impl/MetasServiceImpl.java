package me.llss.service.impl;

import java.util.List;

import me.llss.dao.impl.MetasDAOImpl;
import me.llss.service.MetasService;
import me.llss.vo.MetasVO;

/**
 * MetasService实现
 * 
 * @author Administrator
 * @version 2.0 2013/09/17 12:11
 */
public class MetasServiceImpl implements MetasService {

	MetasDAOImpl metasDAO = new MetasDAOImpl();

	/**
	 * 添加项目
	 * 
	 * @param meta
	 */
	public void add(MetasVO meta) {
		metasDAO.add(meta);
	}

	/**
	 * 删除项目
	 * 
	 * @param mid
	 */
	public void del(int mid) {
		metasDAO.del(mid);
	}

	/*
	 * 编辑项目
	 */
	public void edit(MetasVO meta) {
		metasDAO.edit(meta);
	}

	/**
	 * 查询所有的项目
	 * 
	 * @return
	 */
	public List<MetasVO> listAll() {
		return metasDAO.find();
	}

	/**
	 * 根据mid查找项目
	 * 
	 * @param mid
	 * @return
	 */
	public MetasVO findByMid(int mid) {
		return metasDAO.findByMid(mid);
	}

	/**
	 * 根据名称查找项目
	 * 
	 * @param name
	 * @return
	 */
	public MetasVO findByName(String name) {
		return metasDAO.findByName(name);
	}

	/**
	 * 修改文章计数
	 * 
	 * @param mid
	 * @param op
	 */
	public void editCount(int mid, String op) {
		metasDAO.editCount(mid, op);
	}

}
