package me.imomo.typeasy.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import me.imomo.typeasy.service.MetasService;
import me.imomo.typeasy.service.UsersService;
import me.imomo.typeasy.vo.ContentsVO;
import me.imomo.typeasy.vo.MetasVO;
import me.imomo.typeasy.vo.UsersVO;

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
		if (action.equals("find")) {
			this.find(request, response);
		}
	}

	public void add(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		MetasService metasService = new MetasService();

		String slug = request.getParameter("slug");
		String name = request.getParameter("name");
		String type = request.getParameter("type");
		String description = request.getParameter("description");
        

		MetasVO meta = new MetasVO();
		meta.setName(name);
		meta.setSlug(slug);	
		meta.setType(type);
		meta.setDescription(description);
		metasService.add(meta);
		List<MetasVO> metas = metasService.listAll();
		HttpSession session = request.getSession();
		session.setAttribute("metas", metas);
		if(type.equals("category")){
			request.setAttribute("message","添加成功!");
			request.setAttribute("returnURL",request.getContextPath()+"/admin/manage-categories.jsp");
			request.getRequestDispatcher("../admin/message.jsp").forward(request, response);
		}
		else{
			request.setAttribute("message","添加成功!");
			request.setAttribute("returnURL",request.getContextPath()+"/admin/manage-tags.jsp");
			request.getRequestDispatcher("../admin/message.jsp").forward(request, response);
		}
	}

	
	

	
	
	public void del(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		MetasService metasService = new MetasService();
		int mid = Integer.valueOf(request.getParameter("mid"));
		MetasVO meta = metasService.findMid(mid);
		String type = meta.getType();
		metasService.del(mid);
		java.util.List<MetasVO> metas = metasService.listAll();
		HttpSession session = request.getSession();
		session.setAttribute("metas", metas);
		if(type.equals("category")){
			request.setAttribute("message","删除成功!");
			request.setAttribute("returnURL",request.getContextPath()+"/admin/manage-categories.jsp");
			request.getRequestDispatcher("../admin/message.jsp").forward(request, response);
		}
		else{
			request.setAttribute("message","删除成功!");
			request.setAttribute("returnURL",request.getContextPath()+"/admin/manage-tags.jsp");
			request.getRequestDispatcher("../admin/message.jsp").forward(request, response);
		}
		
	}
		

	public void edit(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		MetasService metasService = new MetasService();
		int mid = Integer.valueOf(request.getParameter("mid"));
		String name = request.getParameter("name");
		String slug = request.getParameter("slug");
		String type = request.getParameter("type");
		String description = request.getParameter("description");

		MetasVO meta = new MetasVO();
		meta.setMid(mid);
		meta.setName(name);
		meta.setSlug(slug);
		meta.setType(type);
		meta.setDescription(description);
		metasService.edit(meta);
		List<MetasVO> metas = metasService.listAll();
		HttpSession session=request.getSession();
		session.setAttribute("metas", metas);
		if(type.equals("category")){
			request.setAttribute("message","更新成功!");
			request.setAttribute("returnURL",request.getContextPath()+"/admin/manage-categories.jsp");
			request.getRequestDispatcher("../admin/message.jsp").forward(request, response);
		}
		else{
			request.setAttribute("message","更新成功!");
			request.setAttribute("returnURL",request.getContextPath()+"/admin/manage-tags.jsp");
			request.getRequestDispatcher("../admin/message.jsp").forward(request, response);
		}
		
	}

	public void list(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		MetasService metasService = new MetasService();
		List<MetasVO> metas = metasService.listAll();
		HttpSession session=request.getSession();
		session.setAttribute("metas", metas);
		
	}	
	
	public void find(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		MetasService metasService = new MetasService();
		int mid = Integer.valueOf(request.getParameter("mid"));
		String type = request.getParameter("type");
		MetasVO meta = metasService.findMid(mid);
		request.setAttribute("meta", meta);
		if(type.equals("category")){
			request.getRequestDispatcher("../admin/edit-category.jsp").forward(request, response);
		}
		else{
			request.getRequestDispatcher("../admin/edit-tag.jsp").forward(request, response);
		}
		
	}
	
}
