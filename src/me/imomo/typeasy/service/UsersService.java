package me.imomo.typeasy.service;

import java.util.List;

import me.imomo.typeasy.dao.UsersDAO;
import me.imomo.typeasy.vo.UsersVO;

public class UsersService {
	private UsersDAO ud = new UsersDAO();

	/**
	 * 修改个人信息
	 * @param user
	 */
	public void modifyProfile(UsersVO user) {
		ud.edit(user);
	}
	
	/**
	 * 后台添加用户
	 * @param user
	 */
	public void add(UsersVO user) {
		ud.add(user);
	}
	
	/**
	 * 珊瑚用户
	 * @param user
	 */
	public void del(int uid) {
		ud.del(uid);
	}
	
	
	/**
	 * 获取所有用户
	 * @return
	 */
	public List<UsersVO> list() {
		List<UsersVO> users = ud.list();
		return users;
	}
	
	
	/**
	 * 
	 * 按uid查找用户
	 * @param u
	 * @return
	 */
	public UsersVO find(int uid) {
		UsersVO user = ud.find(uid);
		return user;
	}

}
