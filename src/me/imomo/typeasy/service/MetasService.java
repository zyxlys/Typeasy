package me.imomo.typeasy.service;

import java.util.List;

import me.imomo.typeasy.dao.MetasDAO;
import me.imomo.typeasy.dao.RelationshipsDAO;
import me.imomo.typeasy.vo.MetasVO;

/**
 * Metas Service类
 * 
 * @author Administrator
 * 
 */
public class MetasService {

	MetasDAO metasDAO = new MetasDAO();

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
