package me.imomo.typeasy.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import me.imomo.typeasy.commons.MD5;
import me.imomo.typeasy.service.LoginService;
import me.imomo.typeasy.service.UsersService;
import me.imomo.typeasy.vo.ContentsVO;
import me.imomo.typeasy.vo.UsersVO;

@SuppressWarnings("serial")
public class UsersServlet extends HttpServlet {
	private UsersService us = new UsersService();
	private MD5 md5 = new MD5();

	public UsersServlet() {
		super();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	/**
	 * doPost
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		if (action == null) {
			action = "";
		}
		if (action.equals("add")) {
			this.add(request, response);
		}
		if (action.equals("list")) {
			this.list(request, response);
		}
		if (action.equals("edit")) {
			this.edit(request, response);
		}
		if (action.equals("del")) {
			this.del(request, response);
		}
		if (action.equals("find")) {
			this.find(request, response);
		}

	}

	/**
	 * 后台添加用户
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void add(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UsersVO user = new UsersVO();
		SimpleDateFormat fomater = new SimpleDateFormat(
				"yyyy'-'MM'-'dd HH:mm:ss");
		String nowtime = fomater.format(new Date());
		user.setCreated(nowtime);
		user.setName(request.getParameter("name"));
		user.setPassword(md5.getMD5ofStr(request.getParameter("password")));
		user.setScreenName(request.getParameter("screenName"));
		user.setMail(request.getParameter("mail"));
		user.setUrl(request.getParameter("url"));
		user.setGroup(request.getParameter("group"));
		us.add(user);
		List<UsersVO> users = us.list();
		HttpSession session = request.getSession();
		session.setAttribute("users", users);
		request.setAttribute("message", "添加成功!");
		request.setAttribute("returnURL", request.getContextPath()
				+ "/admin/manage-users.jsp");
		request.getRequestDispatcher("../admin/message.jsp").forward(request,
				response);
	}

	/**
	 * 查询所有用户并返回
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void list(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<UsersVO> users = us.list();
		HttpSession session = request.getSession();
		session.setAttribute("users", users);
	}

	/**
	 * 修改用户信息
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void edit(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UsersVO user = new UsersVO();
		user.setName(request.getParameter("name"));
		user.setScreenName(request.getParameter("screenName"));
		user.setMail(request.getParameter("mail"));
		user.setUrl(request.getParameter("url"));
		us.modifyProfile(user);
		List<UsersVO> users = us.list();
		HttpSession session = request.getSession();
		session.setAttribute("users", users);
		request.setAttribute("message", "修改成功!");
		request.setAttribute("returnURL", request.getContextPath()
				+ "/admin/manage-users.jsp");
		request.getRequestDispatcher("../admin/message.jsp").forward(request,
				response);

	}

	/**
	 * 删除用户
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void del(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int uid = Integer.valueOf(request.getParameter("uid"));
		us.del(uid);
		List<UsersVO> users = us.list();
		HttpSession session = request.getSession();
		session.setAttribute("users", users);
		request.setAttribute("message", "删除成功!");
		request.setAttribute("returnURL", request.getContextPath()
				+ "/admin/manage-users.jsp");
		request.getRequestDispatcher("../admin/message.jsp").forward(request,
				response);
	}

	public void find(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("uid"));
		UsersVO user = null;
		user = us.find(id);
		if (user != null) {
			request.setAttribute("user", user);
		}
		request.getRequestDispatcher("../admin/edit-user.jsp").forward(request,
				response);
	}

}
