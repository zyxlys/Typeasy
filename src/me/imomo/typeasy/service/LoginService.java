package me.imomo.typeasy.service;

import me.imomo.typeasy.dao.LoginDAO;
import me.imomo.typeasy.dao.UsersDAO;
import me.imomo.typeasy.vo.UsersVO;

/**
 * Login Service
 * 
 * @version 1.0 2013/05/01
 * @author Mo
 * 
 */
public class LoginService {
	private LoginDAO ld = new LoginDAO();

	/**
	 * User Log In
	 * @param u
	 * @return
	 */
	public UsersVO login(UsersVO u) {
		UsersVO user = ld.login(u);
		return user;
	}

	/**
	 * User Register
	 * @param u
	 * @return boolean
	 */
	public boolean register(UsersVO u) {
		UsersVO user1 = ld.getUserByName(u.getName());
		UsersVO user2 = ld.getUserByMail(u.getMail());
		boolean flag = false;
		if (user1.getUid() == null && user2.getUid() == null) {
			ld.register(u);
			flag = true;
		} else {
			flag = false;
		}
		return flag;
	}
}
