package me.llss.service;

import java.util.List;

import me.llss.vo.UsersVO;

/**
 * UsersService接口
 * 
 * @author Acris
 * @version 2.0 2013/09/17 11:43
 */

public interface UsersService {

	/**
	 * 修改个人信息
	 * 
	 * @param user
	 */
	public abstract void modifyProfile(UsersVO user);

	/**
	 * 后台添加用户
	 * 
	 * @param user
	 */
	public abstract void add(UsersVO user);

	/**
	 * 珊瑚用户
	 * 
	 * @param user
	 */
	public abstract void del(int uid);

	/**
	 * 获取所有用户
	 * 
	 * @return
	 */
	public abstract List<UsersVO> list();

	/**
	 * 
	 * 按uid查找用户
	 * 
	 * @param u
	 * @return
	 */
	public abstract UsersVO find(int uid);

	/**
	 * 修改密码
	 * 
	 * @param user
	 */
	public abstract void security(UsersVO user);

}
