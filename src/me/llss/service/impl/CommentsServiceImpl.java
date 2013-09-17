package me.llss.service.impl;

import java.util.List;

import me.llss.dao.impl.CommentsDAOImpl;
import me.llss.service.CommentsService;
import me.llss.vo.CommentsVO;

/**
 * CommentsService 实现
 * 
 * @author Acris
 * @version 2.0 2013/09/17 11:46
 */
public class CommentsServiceImpl implements CommentsService {
	private CommentsDAOImpl cd = new CommentsDAOImpl();

	/**
	 * 添加评论
	 * 
	 * @param comment
	 */
	public void add(CommentsVO comment) {
		cd.add(comment);
	}

	/**
	 * 根据cid删除评论
	 * 
	 * @param cid
	 */
	public void delByCid(int cid) {
		cd.delByCid(cid);

	}

	/**
	 * 根据coid删除评论
	 * 
	 * @param coid
	 */
	public void del(int coid) {
		cd.del(coid);

	}

	/**
	 * 列出所有评论
	 * 
	 * @return
	 */
	public List<CommentsVO> list() {
		List<CommentsVO> comments = cd.list();
		return comments;
	}

	/**
	 * 修改评论
	 * 
	 * @param c
	 */
	public void edit(CommentsVO c) {
		cd.edit(c);
	}

	/**
	 * 根据coid查找评论
	 * 
	 * @param coid
	 * @return
	 */
	public CommentsVO find(int coid) {
		CommentsVO comment = cd.find(coid);
		return comment;
	}

}
