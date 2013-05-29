package me.imomo.typeasy.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import me.imomo.typeasy.commons.CookieUtil;
import me.imomo.typeasy.commons.MD5;
import me.imomo.typeasy.dao.LoginDAO;
import me.imomo.typeasy.service.CommentsService;
import me.imomo.typeasy.service.ContentsService;
import me.imomo.typeasy.service.LoginService;
import me.imomo.typeasy.service.MetasService;
import me.imomo.typeasy.service.OptionsService;
import me.imomo.typeasy.service.UsersService;
import me.imomo.typeasy.vo.CommentsVO;
import me.imomo.typeasy.vo.ContentsVO;
import me.imomo.typeasy.vo.MetasVO;
import me.imomo.typeasy.vo.OptionsVO;
import me.imomo.typeasy.vo.RelationshipsVO;
import me.imomo.typeasy.vo.UsersVO;

/**
 * 用户登录相关的Servlet
 * 
 * @version 1.0 2013/05/02
 * @author Mo
 * 
 */
@SuppressWarnings("serial")
public class LoginServlet extends HttpServlet {
	private RelationshipsVO rs = new RelationshipsVO();
	private LoginService ls = new LoginService();
	private ContentsService cs = new ContentsService();
	private CommentsService cos = new CommentsService();
	private MetasService ms = new MetasService();
	private OptionsService os = new OptionsService();
	private UsersService us = new UsersService();
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
	 * doPost方法用于判断回去的action的值并跳转到相应的方法
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/* 获取相关session */
		HttpSession session = request.getSession();

		List<ContentsVO> contents = cs.list();
		Collections.reverse(contents);
		session.setAttribute("contents", contents);

		List<CommentsVO> comments = cos.list();
		Collections.reverse(comments);
		session.setAttribute("comments", comments);

		List<UsersVO> users = us.list();
		Collections.reverse(users);
		session.setAttribute("users", users);

		List<MetasVO> metas = ms.listAll();
		Collections.reverse(metas);
		session.setAttribute("metas", metas);

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
		if (action.equals("register")) {
			this.register(request, response);
		}

	}

	/**
	 * 验证登录表单的输入是否正确
	 * 
	 * @param request
	 * @param response
	 * @return boolean
	 */
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

	/**
	 * 用户登录
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void login(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		boolean flag = validateLogin(request, response);
		RequestDispatcher rd = null;
		if (flag) {
			UsersVO user = new UsersVO();
			String name = request.getParameter("user_login");
			String password = md5.getMD5ofStr(request.getParameter("user_pwd"));
			user.setName(name);
			user.setPassword(password);
			String rememberMe = request.getParameter("rememberMe");
			UsersVO u = ls.login(user);
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

	/**
	 * 安全退出
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void logout(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 删除cookie中的值以及session中的值
		CookieUtil.removeCookie("UserCookie", request, response);
		request.getSession().removeAttribute("user");
		request.setAttribute("loginMessage", "您已经安全退出");
		RequestDispatcher rd = null;
		rd = request.getRequestDispatcher("../login.jsp");
		rd.forward(request, response);
	}

	/**
	 * 用户注册
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void register(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("user_login");
		String password = md5.getMD5ofStr(request.getParameter("user_pwd"));
		String mail = request.getParameter("user_email");
		String screenName = request.getParameter("user_nickname");
		String nowtime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
				.format(Calendar.getInstance().getTime());
		UsersVO user = new UsersVO();
		user.setName(name);
		user.setPassword(password);
		user.setMail(mail);
		user.setScreenName(screenName);
		user.setCreated(nowtime);
		boolean flag = ls.register(user);
		
		/*获取相关session*/
		HttpSession session=request.getSession();

		
		List<ContentsVO> contents = cs.list();
		Collections.reverse(contents);
		session.setAttribute("contents", contents);
		
		List<CommentsVO> comments = cos.list();
		Collections.reverse(comments);
		session.setAttribute("comments", comments);
		
		List<UsersVO> users = us.list();
		Collections.reverse(users);
		session.setAttribute("users", users);
		
		
		List<MetasVO> metas = ms.listAll();
		Collections.reverse(metas);
		session.setAttribute("metas", metas);
		
		List<OptionsVO> options = os.list();
		Collections.reverse(options);
		session.setAttribute("options", options);
		
		if (flag) {
			UsersVO u = ls.login(user);
			session.setAttribute("user", u);
			response.sendRedirect("../login.jsp");
		} else {
			request.setAttribute("registerMessage", "用户名或邮箱已被注册,请重新输入.");
			RequestDispatcher rd = null;
			rd = request.getRequestDispatcher("/login.jsp");
			rd.forward(request, response);
		}
	}
}
