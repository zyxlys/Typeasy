package me.imomo.typeasy.service;

import java.util.List;

import me.imomo.typeasy.dao.CommentsDAO;
import me.imomo.typeasy.vo.CommentsVO;

public class CommentsService {
	private CommentsDAO cd = new CommentsDAO();

	public void add(CommentsVO comment) {
		 cd.add(comment);
	}

	public void del(int coid) {
		cd.del(coid);
		
	}

	public List<CommentsVO> list() {
		List<CommentsVO> comments = cd.list();
		return comments;
	}

	public void edit(CommentsVO c) {
		 cd.edit(c);
	}
	
	public CommentsVO find(int coid){
		CommentsVO comment = cd.find(coid);
		return comment;
	}

}
