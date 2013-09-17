package me.llss.service;

import me.llss.vo.UsersVO;

/**
 * Login Service
 * 
 * @author Acris
 * @version 2.0 2013/09/17 11:50
 * 
 */
public interface LoginService {

	/**
	 * User Log In
	 * 
	 * @param u
	 * @return
	 */
	public abstract UsersVO login(UsersVO u);

	/**
	 * User Register
	 * 
	 * @param u
	 * @return boolean
	 */
	public abstract boolean register(UsersVO u);
}
