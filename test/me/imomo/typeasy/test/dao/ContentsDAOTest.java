package me.imomo.typeasy.test.dao;

import java.util.List;

import org.junit.Test;

import me.imomo.typeasy.dao.ContentsDAO;
import me.imomo.typeasy.vo.CommentsVO;
import me.imomo.typeasy.vo.ContentsVO;

/**
 *	内容测试
 * @author 夏、末	
 */
public class ContentsDAOTest {
	private ContentsDAO cd = new ContentsDAO();
	private ContentsVO c = new ContentsVO();
	
	@Test
	/**
	 * 测试添加文章
	 */
	public void add(){
		c.setTitle("下一站幸福");
		c.setText("这是测试代码拉拉");
		c.setSlug("哈哈");
		c.setAuthorId(4);
		c.setType("post");
		cd.add(c);
	}
	
	@Test
	/**
	 * 测试删除文章
	 */
	public void del() {
		cd.del(3);
	}
	
	@Test
	/**
	 * 测试修改文章
	 */
	public void edit(){
		c.setTitle("我该拉拉");
		c.setText("hello world!");
		c.setType("public");
		c.setCid(19);
	    cd.edit(c);
		
	}
	
	@Test
	/**
	 * 测试通过cid查找文章
	 */
	public void find(){
		cd.find(22);
		System.out.println(cd.find(22).getTitle());
		
	}
	
	@Test
	/**
	 * 测试查找所有文章
	 */
	public void list(){
		List<ContentsVO> contents = (List) cd.list();
		for (ContentsVO c : contents) {
			System.out.println(c.getTitle());
		}	
		
	}
	@Test
	/**
	 * 测试修改评论数
	 */
	public void editCommentsNum(){
		c.setCid(22);
		c.setCommentsNum(10);
		cd.editCommentsNum(22,"+");
		
	}

}
