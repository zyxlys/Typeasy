package me.imomo.typeasy.service;

import me.imomo.typeasy.dao.LoginDao;
import me.imomo.typeasy.dao.UsersDao;
import me.imomo.typeasy.vo.Users;

/**
 * Login Service
 * 
 * @version 1.0 2013/05/01
 * @author Mo
 * 
 */
public class LoginService {
	private LoginDao ld = new LoginDao();

	/**
	 * User Log In
	 * @param u
	 * @return
	 */
	public Users login(Users u) {
		Users user = ld.login(u);
		return user;
	}

	/**
	 * User Register
	 * @param u
	 * @return boolean
	 */
	public boolean register(Users u) {
		Users user1 = ld.getUserByName(u.getName());
		Users user2 = ld.getUserByMail(u.getMail());
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
