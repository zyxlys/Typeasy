package me.imomo.typeasy.service;

import java.util.List;

import me.imomo.typeasy.dao.CommentsDao;
import me.imomo.typeasy.vo.Comments;

public class CommentsService {
	private CommentsDao cd = new CommentsDao();

	public boolean add(Comments c) {
		boolean flag = cd.add(c);
		return flag;
	}

	public boolean del(int coid) {
		boolean flag = cd.del(coid);
		return flag;
	}

	public List<Comments> list() {
		List<Comments> list = cd.list();
		return list;
	}

	public boolean edit(Comments c) {
		 boolean flag = cd.edit(c);
		 return flag;
	}
	public Comments find(int coid){
		Comments c = cd.find(coid);
		return c;
	}

}
