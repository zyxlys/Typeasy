package me.llss.dao;

import java.util.List;

import me.llss.vo.UsersVO;

/**
 * UsersDAO接口
 * 
 * @author Acris
 * @version 2.0 2013/09/17 11:38
 */

public interface UsersDAO {
	/**
	 * 添加用户
	 * 
	 * @param u
	 */
	public abstract void add(UsersVO user);

	/**
	 * List all user
	 * 
	 * @return List<User>
	 */
	public abstract List<UsersVO> list();

	/**
	 * update user
	 * 
	 * @param user
	 */
	public abstract void edit(UsersVO user);

	/**
	 * 根据用户名查找用户
	 * 
	 * @param name
	 * @return User
	 */
	public abstract UsersVO find(int uid);

	/**
	 * delete user by user name
	 * 
	 * @param id
	 */
	public abstract void del(int uid);

	/**
	 * 修改用户密码
	 * 
	 * @param user
	 */
	public abstract void security(UsersVO user);
}
