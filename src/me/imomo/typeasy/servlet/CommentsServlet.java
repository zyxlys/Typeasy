package me.imomo.typeasy.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import me.imomo.typeasy.dao.CommentsDao;
import me.imomo.typeasy.service.CommentsService;
import me.imomo.typeasy.vo.Comments;
import me.imomo.typeasy.vo.Contents;

@SuppressWarnings("serial")
public class CommentsServlet extends HttpServlet {

	private CommentsService cs = new CommentsService();

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		if (action.equals("add")) {
			this.add(request, response);
		}
		if (action.equals("del")) {
			this.del(request, response);
		}
		if (action.equals("list")) {
			this.list(request, response);
		}
		if (action.equals("edit")) {
			this.edit(request, response);
		}
		if(action.equals("find")){
			this.find(request,response);
		}

	}

	/**
	 * 添加评论
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void add(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		boolean flag = false;
		Comments c = new Comments();
		c.setText(request.getParameter("text"));
		c.setAuthor(request.getParameter("author"));
		c.setMail(request.getParameter("mail"));
		c.setUrl(request.getParameter("url"));
		flag = cs.add(c);
		if (flag) {
			request.setAttribute("comments", "添加成功!");
			 request.setAttribute("returnURL",request.getContextPath()+"/servlet/CommentsServlet?action=add");
			request.getRequestDispatcher("../admin/comments.jsp").forward(
					request, response);
		}
	}

	/**
	 * 删除评论
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void del(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int id = Integer.valueOf(request.getParameter("coid"));
		cs.del(id);
		List<Comments> list = cs.list();
		HttpSession session = request.getSession();
		session.setAttribute("comments", list);
		request.setAttribute("message", "删除成功!");
		request.setAttribute("returnURL", request.getContextPath()
				+ "/admin/manage-comments.jsp");
		request.getRequestDispatcher("../admin/message.jsp").forward(request,
				response);
	}

	/**
	 * 查询评论
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */

	public void list(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Comments> list = cs.list();
		HttpSession session = request.getSession();
		session.setAttribute("comments", list);

	}

	/**
	 * 修改评论
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void edit(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Comments c = new Comments();
		c.setAuthor(request.getParameter("author"));
		c.setMail(request.getParameter("mail"));
		c.setUrl(request.getParameter("url"));
		c.setText(request.getParameter("text"));
		cs.edit(c);
		List<Comments> list = cs.list();
		HttpSession session = request.getSession();
		session.setAttribute("comments", list);
		request.setAttribute("message", "修改成功!");
		request.setAttribute("returnURL", request.getContextPath()
				+ "/admin/manage-comments.jsp");
		request.getRequestDispatcher("../admin/message.jsp").forward(
				request, response);

	}
	public void find(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		int id = Integer.valueOf(request.getParameter("coid"));
		Comments c = cs.find(id);
		request.setAttribute("comment", c);
		request.getRequestDispatcher("../admin/edit-comment.jsp").forward(request, response);
	}

}
