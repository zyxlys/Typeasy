package me.imomo.typeasy.test.dao;

import me.imomo.typeasy.dao.LoginDAO;
import me.imomo.typeasy.vo.UsersVO;

import org.junit.Test;

/**
 * 登录和注册测试
 * @author 夏、末
 *
 */
public class LoginDAOTest {
	private LoginDAO ld = new LoginDAO();
	private UsersVO user = new UsersVO();
	
	@Test
	/**
	 * 测试登录是否成功
	 */
	public void login(){
		user.setName("admin");
		user.setPassword("E10ADC3949BA59ABBE56E057F20F883E");
		ld.login(user);
		System.out.println(ld.login(user).getScreenName());
	}
	
	@Test
	/**
	 * 测试注册
	 */
	public void register(){
		user.setName("chenyan");
		user.setMail("yanyan@com");
		user.setUrl("yanyan");
		user.setScreenName("yanyan");
		user.setGroup("group");
		ld.register(user);
		
	}
	
	@Test
	/**
	 * 测试通过用户名查找用户
	 */
	public void getUserByName(){
		ld.getUserByName("chenyan");
		System.out.println(ld.getUserByName("chenyan").getUid());
	}
	
	@Test
	/**
	 * 根据邮箱查找用户
	 */
	public void getUserByMail(){
		ld.getUserByMail("imomome@imomo.me");
		System.out.println(ld.getUserByMail("imomome@imomo.me").getName());
	}
}
