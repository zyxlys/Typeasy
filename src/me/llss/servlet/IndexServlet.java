package me.llss.servlet;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import me.llss.service.impl.CommentsServiceImpl;
import me.llss.service.impl.ContentsServiceImpl;
import me.llss.service.impl.LoginServiceImpl;
import me.llss.service.impl.MetasServiceImpl;
import me.llss.service.impl.OptionsServiceImpl;
import me.llss.service.impl.RelationshipsServiceImpl;
import me.llss.service.impl.UsersServiceImpl;
import me.llss.vo.CommentsVO;
import me.llss.vo.ContentsVO;
import me.llss.vo.MetasVO;
import me.llss.vo.OptionsVO;
import me.llss.vo.RelationshipsVO;
import me.llss.vo.UsersVO;

/**
 * Index Serlet 获取session
 * 
 * @author Administrator
 * 
 */
@SuppressWarnings("serial")
public class IndexServlet extends HttpServlet {
	private RelationshipsServiceImpl rs = new RelationshipsServiceImpl();
	private ContentsServiceImpl cs = new ContentsServiceImpl();
	private CommentsServiceImpl cos = new CommentsServiceImpl();
	private MetasServiceImpl ms = new MetasServiceImpl();
	private OptionsServiceImpl os = new OptionsServiceImpl();
	private UsersServiceImpl us = new UsersServiceImpl();
	private LoginServiceImpl ls = new LoginServiceImpl();

	/**
	 * Constructor of the object.
	 */
	public IndexServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to
	 * post.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String cid = request.getParameter("cid");
		String type = request.getParameter("type");

		String name = null;
		String pwd = null;
		String cookieStr = null;
		UsersVO user = null;
		UsersVO u = null;

		/* 获取相关session */
		HttpSession session = request.getSession();

		if (((UsersVO) session.getAttribute("user")) == null) {

			Cookie[] cookies = request.getCookies();
			if (cookies != null) {
				for (Cookie c : cookies) {
					if (c.getName().equals("UserCookie")) {
						cookieStr = c.getValue();
					}
				}
				if (cookieStr != null) {
					String cStrArray[] = cookieStr.trim().split(",");
					name = cStrArray[0];
					pwd = cStrArray[1];
				}
				if (name != null && pwd != null) {
					user = new UsersVO();
					u = new UsersVO();
					u.setName(name);
					u.setPassword(pwd);
					user = ls.login(u);
					session.setAttribute("user", user);
				}
			}
		}

		List<RelationshipsVO> relationships = rs.list();
		Collections.reverse(relationships);
		session.setAttribute("relationships", relationships);

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

		String code = request.getParameter("error");
		if (code != null) {
			if (code.equals("404"))
				response.sendRedirect("404/");
			else if (code.equals("500"))
				response.sendRedirect("500/");
			else

			{
				if (("".equals(cid) || cid == null)
						|| ("".equals(type) || type == null))
					response.sendRedirect("index.htm");
				else if (type.equals("post"))
					response.sendRedirect("post-" + cid + ".htm");
				else
					response.sendRedirect("page-" + cid + ".htm");
			}
		} else {
			if (("".equals(cid) || cid == null)
					|| ("".equals(type) || type == null))
				response.sendRedirect("index.htm");
			else if (type.equals("post"))
				response.sendRedirect("post-" + cid + ".htm");
			else
				response.sendRedirect("page-" + cid + ".htm");
		}
	}

	/**
	 * Initialization of the servlet. <br>
	 * 
	 * @throws ServletException
	 *             if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
