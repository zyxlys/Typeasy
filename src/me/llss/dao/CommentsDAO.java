package me.llss.dao;

import java.util.List;
import me.llss.vo.CommentsVO;

/**
 * CommentsDAO接口
 * 
 * @author Acris
 * @version 2.0 2013/09/17 11:21
 */

public interface CommentsDAO {
	public abstract void add(CommentsVO c);

	/**
	 * 删除评论
	 * 
	 * @param coid
	 * @return
	 */
	public abstract void del(int coid);

	/**
	 * 根据cid删除文章
	 * 
	 * @param cid
	 */
	public abstract void delByCid(int cid);

	/**
	 * 修改评论
	 * 
	 * @param c
	 * @return
	 */
	public abstract void edit(CommentsVO c);

	/**
	 * 查询所有的评论
	 * 
	 * @return
	 */
	public abstract List<CommentsVO> list();

	/**
	 * 通过coid查找评论
	 * 
	 * @param coid
	 * @return
	 */
	public abstract CommentsVO find(int coid);

}
