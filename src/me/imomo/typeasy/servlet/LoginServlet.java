package me.imomo.typeasy.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import me.imomo.typeasy.commons.CookieUtil;
import me.imomo.typeasy.commons.MD5;
import me.imomo.typeasy.dao.LoginDao;
import me.imomo.typeasy.service.LoginService;
import me.imomo.typeasy.vo.User;

public class LoginServlet extends HttpServlet {
	private LoginService ls = new LoginService();
	private MD5 md5 = new MD5();

	/**
	 * Constructor of the object.
	 */
	public LoginServlet() {
		super();
	}

	/**
	 * 此处交给doPost方法处理
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);

	}

	/**
	 * doPost方法用于处理与登录相关的诸多事项
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getParameter("action");
		if (action == null) {
			action = "login";
		}
		if (action.equals("login")) {
			this.login(request, response);
		}
		if (action.equals("logout")) {
			this.logout(request, response);
		}
	}

	public boolean validateLogin(HttpServletRequest request,
			HttpServletResponse response) {
		boolean flag = true;
		String loginMessage = "";
		String name = request.getParameter("user_login");
		String password = request.getParameter("user_pwd");
		if (name == null || name.equals("")) {
			flag = false;
			loginMessage = "用户名不能为空";
		}
		if (password == null || password.equals("")) {
			flag = false;
			loginMessage = "密码不能为空";
		}
		if ((name == null || name.equals(""))
				&& (password == null || password.equals(""))) {
			flag = false;
			loginMessage = "用户名和密码不能为空";
		}
		request.setAttribute("loginMessage", loginMessage);
		return flag;
	}

	public void login(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		boolean flag = validateLogin(request, response);
		RequestDispatcher rd = null;
		if (flag) {
			User user = new User();
			String name = request.getParameter("user_login");
			String password = md5.getMD5ofStr(request.getParameter("user_pwd"));
			user.setName(name);
			user.setPassword(password);
			String rememberMe = request.getParameter("rememberMe");
			User u = ls.login(user);
			if (u.getUid() == null) {
				request.setAttribute("loginMessage", "用户名或密码错误");
				rd = request.getRequestDispatcher("/login.jsp");
				rd.forward(request, response);
			} else {
				HttpSession session = request.getSession();
				session.setAttribute("user", u);
				if (rememberMe != null && rememberMe.endsWith("forever")) {
					String cookieValue = name + "," + password;
					Cookie cookie = new Cookie("UserCookie", cookieValue);
					cookie.setMaxAge(864000); // 设置有效期为十天
					cookie.setPath(request.getContextPath());
					response.addCookie(cookie);
				}
				response.sendRedirect("../admin/index.jsp");
			}
		} else {
			rd = request.getRequestDispatcher("/login.jsp");
			rd.forward(request, response);
		}
	}

	public void logout(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 删除cookie中的值以及session中的值
		CookieUtil.removeCookie("UserCookie", request, response);
		request.getSession().removeAttribute("user");
		request.setAttribute("loginMessage", "您已经安全退出");
		RequestDispatcher rd = null;
		rd = request.getRequestDispatcher("/login.jsp");
		rd.forward(request, response);
	}
}
