package me.imomo.typeasy.servlet;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import me.imomo.typeasy.commons.Html2Text;
import me.imomo.typeasy.service.CommentsService;
import me.imomo.typeasy.service.ContentsService;
import me.imomo.typeasy.service.MetasService;
import me.imomo.typeasy.service.OptionsService;
import me.imomo.typeasy.service.RelationshipsService;
import me.imomo.typeasy.service.UsersService;
import me.imomo.typeasy.vo.CommentsVO;
import me.imomo.typeasy.vo.ContentsVO;
import me.imomo.typeasy.vo.MetasVO;
import me.imomo.typeasy.vo.OptionsVO;
import me.imomo.typeasy.vo.RelationshipsVO;
import me.imomo.typeasy.vo.UsersVO;

@SuppressWarnings("serial")
public class MetasServlet extends HttpServlet {

	private RelationshipsService rs = new RelationshipsService();
	private ContentsService contentService = new ContentsService();
	private CommentsService cs = new CommentsService();
	private MetasService ms = new MetasService();
	private OptionsService os = new OptionsService();
	private UsersService us = new UsersService();

	/**
	 * doGet
	 */
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
		if (action.equals("add")) {
			this.add(request, response);
		}
		if (action.equals("del")) {
			this.del(request, response);
		}
		if (action.equals("edit")) {
			this.edit(request, response);
		}
		if (action.equals("list")) {
			this.list(request, response);
		}
		if (action.equals("find")) {
			this.find(request, response);
		}
		if (action.equals("multiDel")) {
			this.multiDel(request, response);
		}
	}

	/**
	 * 添加
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 */
	public void add(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		String slug = Html2Text.Html2Text(request.getParameter("slug"));
		String name = Html2Text.Html2Text(request.getParameter("name"));
		String type = Html2Text.Html2Text(request.getParameter("type"));
		String description = Html2Text.Html2Text(request.getParameter("description"));

		MetasVO meta = new MetasVO();
		meta.setName(name);
		meta.setSlug(slug);
		meta.setType(type);
		meta.setDescription(description);
		ms.add(meta);
		List<MetasVO> metas = ms.listAll();
		HttpSession session = request.getSession();
		session.setAttribute("metas", metas);
		if (type.equals("category")) {
			request.setAttribute("message", "添加成功!");
			request.setAttribute("returnURL", request.getContextPath()
					+ "/admin/manage-categories.jsp");
			request.getRequestDispatcher("../admin/message.jsp").forward(
					request, response);
		} else {
			request.setAttribute("message", "添加成功!");
			request.setAttribute("returnURL", request.getContextPath()
					+ "/admin/manage-tags.jsp");
			request.getRequestDispatcher("../admin/message.jsp").forward(
					request, response);
		}
	}

	/**
	 * 删除
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 */
	public void del(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		int mid = Integer.valueOf(request.getParameter("mid"));
		MetasVO meta = ms.findByMid(mid);
		String type = meta.getType();
		ms.del(mid);
		/* 获取相关session */
		HttpSession session = request.getSession();

		List<RelationshipsVO> relationships = rs.list();
		Collections.reverse(relationships);
		session.setAttribute("relationships", relationships);

		List<ContentsVO> contents = contentService.list();
		Collections.reverse(contents);
		session.setAttribute("contents", contents);

		List<CommentsVO> comments = cs.list();
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

		if (type.equals("category")) {
			request.setAttribute("message", "删除成功!");
			request.setAttribute("returnURL", request.getContextPath()
					+ "/admin/manage-categories.jsp");
			request.getRequestDispatcher("../admin/message.jsp").forward(
					request, response);
		} else {
			request.setAttribute("message", "删除成功!");
			request.setAttribute("returnURL", request.getContextPath()
					+ "/admin/manage-tags.jsp");
			request.getRequestDispatcher("../admin/message.jsp").forward(
					request, response);
		}

	}

	/**
	 * 修改
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 */
	public void edit(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		int mid = Integer.valueOf(request.getParameter("mid"));
		String name = Html2Text.Html2Text(request.getParameter("name"));
		String slug = Html2Text.Html2Text(request.getParameter("slug"));
		String type = Html2Text.Html2Text(request.getParameter("type"));
		String description = Html2Text.Html2Text(request.getParameter("description"));

		MetasVO meta = new MetasVO();
		meta.setMid(mid);
		meta.setName(name);
		meta.setSlug(slug);
		meta.setType(type);
		meta.setDescription(description);
		ms.edit(meta);

		/* 获取相关session */
		HttpSession session = request.getSession();
		List<RelationshipsVO> relationships = rs.list();
		Collections.reverse(relationships);
		session.setAttribute("relationships", relationships);

		List<ContentsVO> contents = contentService.list();
		Collections.reverse(contents);
		session.setAttribute("contents", contents);

		List<CommentsVO> comments = cs.list();
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

		if (type.equals("category")) {
			request.setAttribute("message", "更新成功!");
			request.setAttribute("returnURL", request.getContextPath()
					+ "/admin/manage-categories.jsp");
			request.getRequestDispatcher("../admin/message.jsp").forward(
					request, response);
		} else {
			request.setAttribute("message", "更新成功!");
			request.setAttribute("returnURL", request.getContextPath()
					+ "/admin/manage-tags.jsp");
			request.getRequestDispatcher("../admin/message.jsp").forward(
					request, response);
		}

	}

	/**
	 * 查询所有
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 */
	public void list(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		List<MetasVO> metas = ms.listAll();
		HttpSession session = request.getSession();
		session.setAttribute("metas", metas);

	}

	/**
	 * 根据mid查找
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 */
	public void find(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		int mid = Integer.valueOf(request.getParameter("mid"));
		String type = request.getParameter("type");
		MetasVO meta = ms.findByMid(mid);
		request.setAttribute("meta", meta);
		if (type.equals("category")) {
			request.getRequestDispatcher("../admin/edit-category.jsp").forward(
					request, response);
		} else {
			request.getRequestDispatcher("../admin/edit-tag.jsp").forward(
					request, response);
		}

	}

	/**
	 * 批量删除
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void multiDel(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String mid[] = request.getParameterValues("mid");
		String type = request.getParameter("type");
		String op = request.getParameter("multiOption");
		if (op.equals("multiDel")) {
			for (int i = 0; i < mid.length; i++) {
				int metaId = Integer.valueOf(mid[i]);
				ms.del(metaId);
			}

			/* 获取相关session */
			HttpSession session = request.getSession();

			List<RelationshipsVO> relationships = rs.list();
			Collections.reverse(relationships);
			session.setAttribute("relationships", relationships);

			List<ContentsVO> contents = contentService.list();
			Collections.reverse(contents);
			session.setAttribute("contents", contents);

			List<CommentsVO> comments = cs.list();
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

			request.setAttribute("message", "删除成功!");
			request.setAttribute("returnURL", request.getContextPath()
					+ "/admin/manage-categories.jsp");
			if (type.equals("category")) {
				request.setAttribute("message", "删除成功!");
				request.setAttribute("returnURL", request.getContextPath()
						+ "/admin/manage-categories.jsp");
				request.getRequestDispatcher("../admin/message.jsp").forward(
						request, response);
			} else {
				request.setAttribute("message", "删除成功!");
				request.setAttribute("returnURL", request.getContextPath()
						+ "/admin/manage-tags.jsp");
				request.getRequestDispatcher("../admin/message.jsp").forward(
						request, response);
			}
		} else {
			if (type.equals("category")) {
				response.sendRedirect("../admin/manage-categories.jsp");
			} else {
				response.sendRedirect("../admin/manage-tags.jsp");
			}
		}

	}

}
