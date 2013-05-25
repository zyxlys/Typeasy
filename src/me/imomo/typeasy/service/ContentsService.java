package me.imomo.typeasy.service;

import java.util.List;

import me.imomo.typeasy.dao.ContentsDao;
import me.imomo.typeasy.vo.Contents;

public class ContentsService {

	private ContentsDao cd = new ContentsDao();
	/**
	 * 发表文章
	 * @param c
	 * @return
	 */
	public boolean add(Contents c){
		boolean cs = cd.addArticle(c);
		return cs;
	}
	/**
	 * 显示文章列表
	 * @return
	 */
	public List<Contents> findAll(){
		List<Contents> cs = cd.findAll();
		return cs;
	}
	/**
	 * 根据id删除文章
	 * @param id
	 * @return
	 */
	public boolean delete(int id){
		boolean cs = cd.deleteArticle(id);
		return cs;
	}
	/**
	 * 根据id修改文章
	 * @param article
	 * @return
	 */
	public boolean modify(Contents article){
		Contents c = cd.selectById(article.getCid());
		if(c!=null){
			cd.modify(article);
			return true;
		}
		return false;
	}
	public Contents selectById(int id){
		Contents c = cd.selectById(id);
		
		return c;
		
	}
}
