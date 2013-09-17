package me.llss.service;

import java.util.List;

import me.llss.vo.CommentsVO;

/**
 * CommentsService接口
 * 
 * @author Acris
 * @version 2.0 2013/09/17 11:44
 */

public interface CommentsService {

	/**
	 * 添加评论
	 * 
	 * @param comment
	 */
	public abstract void add(CommentsVO comment);

	/**
	 * 根据cid删除评论
	 * 
	 * @param cid
	 */
	public abstract void delByCid(int cid);

	/**
	 * 根据coid删除评论
	 * 
	 * @param coid
	 */
	public abstract void del(int coid);

	/**
	 * 列出所有评论
	 * 
	 * @return
	 */
	public abstract List<CommentsVO> list();

	/**
	 * 修改评论
	 * 
	 * @param c
	 */
	public abstract void edit(CommentsVO c);

	/**
	 * 根据coid查找评论
	 * 
	 * @param coid
	 * @return
	 */
	public abstract CommentsVO find(int coid);

}
