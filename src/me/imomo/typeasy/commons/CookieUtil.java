package me.imomo.typeasy.commons;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import me.imomo.typeasy.service.LoginService;
import me.imomo.typeasy.vo.UsersVO;

/**
 * Cookie Utility
 * 
 * @version 1.0 2013/05/01
 * @author Mo
 * 
 */
public class CookieUtil {

	/**
	 * 判断cookie值是否存在，如果不存在返回null
	 * 
	 * @param name
	 * @param request
	 * @return Cookie
	 */
	public static Cookie isCookieExist(String name, HttpServletRequest request) {
		Cookie[] cookieList = request.getCookies();

		if (cookieList != null) {
			for (Cookie cookie : cookieList) {
				if (cookie.getName().equals(name)) {
					return cookie;
				}
			}
		}
		return null;
	}

	/**
	 * 移除Cookie
	 * 
	 * @param name
	 * @param request
	 * @param response
	 */
	public static void removeCookie(String name, HttpServletRequest request,
			HttpServletResponse response) {
		Cookie[] cookieList = request.getCookies();

		if (cookieList != null) {
			for (Cookie cookie : cookieList) {
				if (cookie.getName().equals(name)) {
					cookie.setMaxAge(0);
					cookie.setPath(request.getContextPath());
					response.addCookie(cookie);
				}
			}
		}
	}
	
	/**
	 * 验证cookie中的用户名和密码，如果验证成功返回user对象、否则返回null
	 */
	public static UsersVO validateCookieLoginIdAndPassword(Cookie cookie){
		UsersVO user = new UsersVO();
		LoginService ls = new LoginService();
		String cookieStr = cookie.getValue();
		if(cookieStr != null){
			String[] values = cookieStr.split(",");
			if(values.length == 2){
				user.setName(values[0]);
				user.setPassword(values[1]);
				UsersVO u = ls.login(user);
				if(u != null){
					return u;
				}
			}
		}
		
		return null;
	}
}
