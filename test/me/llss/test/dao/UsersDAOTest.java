package me.llss.test.dao;

import java.util.List;

import org.junit.Test;

import me.llss.dao.impl.UsersDAOImpl;
import me.llss.vo.UsersVO;

/**
 * 用户测试
 * 
 * @author 夏、末
 * 
 */
public class UsersDAOTest {
	private UsersVO user = new UsersVO();
	private UsersDAOImpl ud = new UsersDAOImpl();

	@Test
	/**
	 * 测试添加用户
	 */
	public void add() {
		user.setName("apple");
		user.setPassword("chenyan77240");
		user.setUrl("chenyan@77240");
		user.setMail("chenyan77240");
		user.setScreenName("小Y");
		ud.add(user);
	}

	@Test
	/**
	 * 测试查询所用用户
	 */
	public void list() {
		List<UsersVO> users = ud.list();
		for (UsersVO u : users) {
			System.out.println(u.getName());
		}
	}

	@Test
	/**
	 * 修改用户信息
	 */
	public void edit() {
		user.setMail("chenyan");
		user.setUrl("chenyan@qq.com");
		user.setAvatar("avatar");
		user.setScreenName("screenName");
		user.setGroup("vistor");
		user.setUid(2);
		ud.edit(user);
	}

	@Test
	/**
	 * 测试通过uid查找
	 */
	public void find() {
		ud.find(1);
		System.out.println(ud.find(1).getName());
	}

	@Test
	/**
	 * 测试删除
	 */
	public void del() {
		ud.del(2);
	}

}
