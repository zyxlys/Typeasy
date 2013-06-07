package me.imomo.typeasy.service;

import java.util.List;

import me.imomo.typeasy.dao.CommentsDAO;
import me.imomo.typeasy.vo.CommentsVO;

/**
 * 评论Service
 * 
 * @author Administrator
 * 
 */
public class CommentsService {
	private CommentsDAO cd = new CommentsDAO();

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
