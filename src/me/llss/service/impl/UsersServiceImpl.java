package me.llss.service.impl;

import java.util.List;

import me.llss.dao.impl.UsersDAOImpl;
import me.llss.service.UsersService;
import me.llss.vo.UsersVO;

/**
 * 用户Service类
 * 
 * @author Administrator
 * 
 */
public class UsersServiceImpl implements UsersService {
	private UsersDAOImpl ud = new UsersDAOImpl();

	/**
	 * 修改个人信息
	 * 
	 * @param user
	 */
	public void modifyProfile(UsersVO user) {
		ud.edit(user);
	}

	/**
	 * 后台添加用户
	 * 
	 * @param user
	 */
	public void add(UsersVO user) {
		ud.add(user);
	}

	/**
	 * 珊瑚用户
	 * 
	 * @param user
	 */
	public void del(int uid) {
		ud.del(uid);
	}

	/**
	 * 获取所有用户
	 * 
	 * @return
	 */
	public List<UsersVO> list() {
		List<UsersVO> users = ud.list();
		return users;
	}

	/**
	 * 
	 * 按uid查找用户
	 * 
	 * @param u
	 * @return
	 */
	public UsersVO find(int uid) {
		UsersVO user = ud.find(uid);
		return user;
	}

	/**
	 * 修改密码
	 * 
	 * @param user
	 */
	public void security(UsersVO user) {
		ud.security(user);
	}

}
