package me.llss.service;

import java.util.List;

import me.llss.vo.MetasVO;

/**
 * Metas Service类
 * 
 * @author Acris
 * @version 2.0 2013/09/17 12:04
 * 
 */
public interface MetasService {

	/**
	 * 添加项目
	 * 
	 * @param meta
	 */
	public abstract void add(MetasVO meta);

	/**
	 * 删除项目
	 * 
	 * @param mid
	 */
	public abstract void del(int mid);

	/*
	 * 编辑项目
	 */
	public abstract void edit(MetasVO meta);

	/**
	 * 查询所有的项目
	 * 
	 * @return
	 */
	public abstract List<MetasVO> listAll();

	/**
	 * 根据mid查找项目
	 * 
	 * @param mid
	 * @return
	 */
	public abstract MetasVO findByMid(int mid);

	/**
	 * 根据名称查找项目
	 * 
	 * @param name
	 * @return
	 */
	public abstract MetasVO findByName(String name);

	/**
	 * 修改文章计数
	 * 
	 * @param mid
	 * @param op
	 */
	public abstract void editCount(int mid, String op);

}
