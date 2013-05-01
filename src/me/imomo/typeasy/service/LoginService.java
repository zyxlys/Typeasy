package me.imomo.typeasy.service;

import me.imomo.typeasy.dao.LoginDao;
import me.imomo.typeasy.vo.User;

/**
 * Login Service
 * @version 1.0	2013/05/01
 * @author Mo
 *
 */
public class LoginService {
	private LoginDao ld = new LoginDao();
	
	public User login(User u) {
		User user = ld.login(u);
		return user;
	}
	
	public User getUser() {
		User user = ld.getUser();
		return user;
	}
}
