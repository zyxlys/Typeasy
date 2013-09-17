package me.llss.service;

import java.util.List;

import me.llss.vo.ContentsVO;

/**
 * ContentsService
 * 
 * @author Acris
 * @version 2.0 2013/09/17 11:47
 * 
 */
public interface ContentsService {

	/**
	 * 发表文章
	 * 
	 * @param content
	 */
	public abstract void add(ContentsVO content);

	/**
	 * 显示文章列表
	 * 
	 * @return
	 */
	public abstract List<ContentsVO> list();

	/**
	 * 根据id删除文章
	 * 
	 * @param id
	 */
	public abstract void del(int id);

	/**
	 * 根据id修改文章
	 * 
	 * @param content
	 * @return
	 */
	public abstract boolean edit(ContentsVO content);

	/**
	 * 根据id查询文章
	 * 
	 * @param id
	 * @return
	 */
	public abstract ContentsVO find(int id);

	/**
	 * 更改评论数
	 * 
	 * @param cid
	 * @param o
	 */
	public abstract void editCommentsNum(int cid, String o);

	/**
	 * 搜索文章
	 * 
	 * @param keywords
	 * @return
	 */
	public abstract List<ContentsVO> search(String keywords);
}
