package me.llss.dao;

import java.util.List;
import me.llss.vo.ContentsVO;

/**
 * ContentsDAO接口
 * 
 * @author Acris
 * @version 2.0 2013/09/17 11:26
 */

public interface ContentsDAO {
	/**
	 * 发表文章
	 * 
	 * @param article
	 */
	public abstract void add(ContentsVO article);

	/**
	 * 根据id删除文章
	 * 
	 * @param id
	 */
	public abstract void del(int id);

	/**
	 * 根据id修改文章
	 */
	public abstract void edit(ContentsVO article);

	/**
	 * 根据cid查询文章
	 * 
	 * @param cid
	 * @return
	 */
	public abstract ContentsVO find(int cid);

	/**
	 * 查询所有文章
	 * 
	 * @return
	 */
	public abstract List<ContentsVO> list();

	/**
	 * 更改评论数
	 * 
	 * @param cid
	 * @param o
	 */
	public abstract void editCommentsNum(int cid, String o);

	/**
	 * 按标题搜索文章
	 * 
	 * @param keywords
	 * @return
	 */
	public abstract List<ContentsVO> search(String keywords);
}
