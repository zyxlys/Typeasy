package me.imomo.typeasy.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;

import me.imomo.typeasy.dao.ContentsDAO;
import me.imomo.typeasy.service.ContentsService;
import me.imomo.typeasy.service.MetasService;
import me.imomo.typeasy.vo.ContentsVO;
import me.imomo.typeasy.vo.MetasVO;

public class ContentsServlet extends HttpServlet {

	private ContentsService cs = new ContentsService();
	private MetasService ms = new MetasService();

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
		if (action.equals("list")) {
			this.list(request, response);
		}
		if (action.equals("del")) {
			this.del(request, response);
		}
		if (action.equals("edit")) {
			this.edit(request, response);
		}
		if (action.equals("find")) {
			this.find(request, response);
		}

	}

	/**
	 * 发表文章
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void add(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ContentsVO content = new ContentsVO();
		int mid = -1;
		SimpleDateFormat fomater=new SimpleDateFormat("yyyy'-'MM'-'dd'-' HH:mm:ss");	
		String nowtime=fomater.format(new Date());
		String type = request.getParameter("type");
		int authorId=Integer.valueOf(request.getParameter("authorId"));
		content.setCreated(nowtime);
		content.setTitle(request.getParameter("title"));
		content.setText(request.getParameter("text"));
		content.setType(type);
		content.setAuthorId(authorId);
		//From 明丽群
//		String category = request.getParameter("category");
//		if(category != null)
//			mid = Integer.valueOf(category);
//		String nameArray[] = request.getParameter("name").split(",");
//		System.out.println(nameArray.length);
//		Metas meta = new Metas();
//		meta.setMid(mid);			
//		meta.setType("tag");
//		for(String name:nameArray) {
//			meta.setName(name);
//			ms.add(meta);
//		}
		
		cs.add(content);
		List<ContentsVO> contents = cs.list();
//		List<Metas> metas = ms.listAll();
		Collections.reverse(contents);
		HttpSession session = request.getSession();
		session.setAttribute("contents", contents);
//		session.setAttribute("metas", metas);
		if(type.equals("post")){
			request.setAttribute("message","发表成功!");
			request.setAttribute("returnURL",request.getContextPath()+"/admin/manage-posts.jsp");
			request.getRequestDispatcher("../admin/message.jsp").forward(request, response);
		}
		if(type.equals("page")){
			request.setAttribute("message","发表成功!");
			request.setAttribute("returnURL",request.getContextPath()+"/admin/manage-pages.jsp");
			request.getRequestDispatcher("../admin/message.jsp").forward(request, response);
		}
		
		
	}

	/**
	 * 查询所有文章
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void list(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<ContentsVO> contents = cs.list();
		Collections.reverse(contents);
		HttpSession session = request.getSession();
		session.setAttribute("contents", contents);
	}

	/**
	 * 删除文章
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void del(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int id = Integer.valueOf(request.getParameter("cid"));
		String type = request.getParameter("type");
		cs.del(id);

		List<ContentsVO> contents = cs.list();
		Collections.reverse(contents);
		HttpSession session = request.getSession();
		session.setAttribute("contents", contents);
		if (type.equals("post")) {
			request.setAttribute("message", "删除成功!");
			request.setAttribute("returnURL", request.getContextPath()
					+ "/admin/manage-posts.jsp");
			request.getRequestDispatcher("../admin/message.jsp").forward(
					request, response);
		} else {
			request.setAttribute("message", "删除成功!");
			request.setAttribute("returnURL", request.getContextPath()
					+ "/admin/manage-pages.jsp");
			request.getRequestDispatcher("../admin/message.jsp").forward(
					request, response);
		}

	}

	/**
	 * 修改文章
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void edit(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("cid"));
		String type = request.getParameter("type");
		ContentsVO c = new ContentsVO();
		SimpleDateFormat fomater = new SimpleDateFormat(
				"yyyy'-'MM'-'dd'-' HH:mm:ss");
		String nowtime = fomater.format(new Date());
		c.setModified(nowtime);
		c.setCid(id);
		c.setTitle(request.getParameter("title"));
		c.setText(request.getParameter("text"));
		c.setType(type);
		if (cs.edit(c) && type.equals("post")) {
			List<ContentsVO> contents = cs.list();
			Collections.reverse(contents);
			HttpSession session = request.getSession();
			session.setAttribute("contents", contents);
			request.setAttribute("message", "修改成功!");
			request.setAttribute("returnURL", request.getContextPath()
					+ "/admin/manage-posts.jsp");
			request.getRequestDispatcher("../admin/message.jsp").forward(
					request, response);
		} else {
			List<ContentsVO> contents = cs.list();
			Collections.reverse(contents);
			HttpSession session = request.getSession();
			session.setAttribute("contents", contents);
			request.setAttribute("message", "修改成功!");
			request.setAttribute("returnURL", request.getContextPath()
					+ "/admin/manage-pages.jsp");
			request.getRequestDispatcher("../admin/message.jsp").forward(
					request, response);
		}
	}

	/**
	 * 通过id查询文章
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void find(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("cid"));
		String type = request.getParameter("type");
		ContentsVO content = new ContentsVO();
		content = cs.find(id);
		if (content != null) {
			request.setAttribute("content", content);
		}
		if (type.equals("post")) {
			request.getRequestDispatcher("../admin/edit-post.jsp").forward(
					request, response);
		} else {
			request.getRequestDispatcher("../admin/edit-page.jsp").forward(
					request, response);
		}
	}

}
