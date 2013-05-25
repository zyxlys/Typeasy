package me.imomo.typeasy.service;

import java.util.List;

import me.imomo.typeasy.dao.UsersDao;
import me.imomo.typeasy.vo.Users;

public class UsersService {
	private UsersDao ud = new UsersDao();

	/**
	 * 修改个人信息
	 * @param user
	 */
	public void modifyProfile(Users user) {
		ud.edit(user);
	}
	
	/**
	 * 后台添加用户
	 * @param user
	 */
	public void add(Users user) {
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
	public List<Users> list() {
		List<Users> users = ud.list();
		return users;
	}
	
	
	/**
	 * 
	 * 按uid查找用户
	 * @param u
	 * @return
	 */
	public Users find(int uid) {
		Users user = ud.find(uid);
		return user;
	}

}
