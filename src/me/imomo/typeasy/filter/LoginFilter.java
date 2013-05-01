package me.imomo.typeasy.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import me.imomo.typeasy.commons.CookieUtil;
import me.imomo.typeasy.vo.User;

public class LoginFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) arg0;
		HttpServletResponse response = (HttpServletResponse) arg1;
		Cookie cookie = CookieUtil.isCookieExist("UserCookie", request);

		if (request.getSession().getAttribute("user") == null) {
			if (cookie != null) {
				User user = CookieUtil.validateCookieLoginIdAndPassword(cookie);
				if (user != null) {
					request.getSession().setAttribute("user", user);
					arg2.doFilter(arg0, arg1);
				}
			} else {
				response.sendRedirect(request.getContextPath() + "/login.jsp");
			}
		} else {
			arg2.doFilter(arg0, arg1);
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
