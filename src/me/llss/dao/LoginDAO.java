package me.llss.dao;

import me.llss.vo.UsersVO;

/**
 * LoginDAO接口
 * 
 * @author Acris
 * @version 2.0 2013/09/17 11:27
 */

public interface LoginDAO {
	/**
	 * 用户登录
	 * 
	 * @param u
	 * @return User
	 */
	public abstract UsersVO login(UsersVO u);

	/**
	 * 用户注册
	 * 
	 * @param u
	 */
	public abstract void register(UsersVO u);

	/**
	 * 根据用户名查找用户
	 * 
	 * @param name
	 * @return User
	 */
	public abstract UsersVO getUserByName(String name);

	/**
	 * 根据邮箱查找用户
	 * 
	 * @param name
	 * @return User
	 */
	public abstract UsersVO getUserByMail(String mail);
}
