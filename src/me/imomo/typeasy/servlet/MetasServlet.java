package me.imomo.typeasy.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import me.imomo.typeasy.service.MetasService;
import me.imomo.typeasy.vo.Metas;

@SuppressWarnings("serial")
public class MetasServlet extends HttpServlet {

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
		if (action.equals("edit")) {
			this.edit(request, response);
		}
		if (action.equals("list")) {
			this.list(request, response);
		}

	}

	public void add(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		MetasService metasService = new MetasService();
		boolean flag = false;

		int mid = Integer.valueOf(request.getParameter("mid"));
		String name = request.getParameter("name");
		String slug = request.getParameter("slug");
		String type = request.getParameter("type");
		String description = request.getParameter("description");
		int count = Integer.valueOf(request.getParameter("count"));
		int order = Integer.valueOf(request.getParameter("order"));

		Metas metas = new Metas();
		flag = metasService.insert(metas);

		if (flag) {
			request.getRequestDispatcher("../admin/index.jsp").forward(request,
					response);
		} else {
			response.sendRedirect("../admin/manage-categories.jsp");
		}
	}

	public void del(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		MetasService metasService = new MetasService();
		int mid = Integer.valueOf(request.getParameter("mid"));
		metasService.delete(mid);
		request.setAttribute("message", "删除成功!");
		request.setAttribute("returnURL", request.getContextPath()
				+ "/servlet/MetasServlet?action=list");
		request.getRequestDispatcher("../admin/index.jsp").forward(request,
				response);
		// response.sendRedirect("list");
	}

	public void edit(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		MetasService metasService = new MetasService();
		boolean flag = false;
		int mid = Integer.valueOf(request.getParameter("mid"));
		String name = request.getParameter("name");
		String slug = request.getParameter("slug");
		String type = request.getParameter("type");
		String description = request.getParameter("description");
		int count = Integer.valueOf(request.getParameter("count"));
		int order = Integer.valueOf(request.getParameter("order"));

		Metas metas = new Metas();
		flag = metasService.modify(metas);

		if (flag) {
			request.getRequestDispatcher("../admin/index.jsp").forward(request,
					response);
		} else {
			response.sendRedirect("");
		}
	}

	public void list(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		MetasService metasService = new MetasService();
		List<Metas> list = metasService.listAll();
		request.setAttribute("list", list);
		request.getRequestDispatcher("../admin/manage-categories.jsp").forward(
				request, response);
	}
}
