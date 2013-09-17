package me.llss.service.impl;

import me.llss.dao.impl.LoginDAOImpl;
import me.llss.service.LoginService;
import me.llss.vo.UsersVO;

/**
 * Login Service
 * 
 * @author Acris
 * @version 2.0 201/09/17 11:51
 * 
 */
public class LoginServiceImpl implements LoginService {
	private LoginDAOImpl ld = new LoginDAOImpl();

	/**
	 * User Log In
	 * 
	 * @param u
	 * @return
	 */
	public UsersVO login(UsersVO u) {
		UsersVO user = ld.login(u);
		return user;
	}

	/**
	 * User Register
	 * 
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
