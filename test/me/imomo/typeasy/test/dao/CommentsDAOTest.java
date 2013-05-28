package me.imomo.typeasy.test.dao;

import java.util.List;

import org.junit.Test;

import me.imomo.typeasy.dao.CommentsDAO;
import me.imomo.typeasy.vo.CommentsVO;

public class CommentsDAOTest {
	private CommentsVO c = new CommentsVO();
	private CommentsDAO cd = new CommentsDAO();

	@Test
	/**
	 * 测试增加评论
	 */
	public void add() {
		c.setAuthor("chenyan");
		c.setCid(29);
		c.setMail("mail");
		c.setAuthorId(29);
		c.setText("陈艳你好");
		c.setUrl("chenyanyan");
		c.setOwnerId(29);
		cd.add(c);
		System.out.println(c.getAuthor());
	}
	

	@Test
	/**
	 * 测试修改评论
	 */
	public void edit() {
		c.setCoid(6);
		c.setAuthor("Y");
		c.setMail("Y");
		c.setUrl("Y");
		c.setText("滴答滴啊滴答滴答");
		cd.edit(c);
	}

	@Test
	/**
	 * 测试删除评论
	 */
	public void del() {
		cd.del(5);
		System.out.println();
	}

	@Test
	/**
	 * 测试查询所以评论
	 */
	public void list() {
		List<CommentsVO> comments = (List) cd.list();
		for (CommentsVO c : comments) {
			System.out.println(c.getCoid());
		}

	}
	@Test
	/**
	 * 测试通过coid查找
	 */
	public void find(){
		cd.find(6);
		System.out.print((cd.find(6)).getAuthor());
		
	}
}
