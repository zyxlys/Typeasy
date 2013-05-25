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
import javax.swing.JOptionPane;

import me.imomo.typeasy.dao.ContentsDao;
import me.imomo.typeasy.service.ContentsService;
import me.imomo.typeasy.vo.Contents;

@SuppressWarnings("serial")
public class ContentsServlet extends HttpServlet {
	private ContentsService cs = new ContentsService();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getParameter("action");
		if(action.equals("add")){
			this.add(request, response);
		}
		if(action.equals("list")){
			this.list(request, response);
		}
		if(action.equals("del")){
			this.del(request, response);
		}
		if(action.equals("edit")){
			this.edit(request, response);
		}
		if(action.equals("find")){
			this.find(request, response);
		}
	}
	/**
	 * 发表文章
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void add(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		boolean flag = false;
		Contents content = new Contents();
		SimpleDateFormat fomater=new SimpleDateFormat("yyyy'-'MM'-'dd HH:mm:ss");	
		String nowtime=fomater.format(new Date());
		content.setCreated(nowtime);
		content.setTitle(request.getParameter("title"));
		content.setText(request.getParameter("text"));
		content.setType(request.getParameter("type"));
		flag = cs.add(content);
		java.util.List<Contents> articlelist = cs.findAll();
		HttpSession session = request.getSession();
		session.setAttribute("contents", articlelist);
		if(flag){
			request.setAttribute("message","发表成功!");
			request.setAttribute("returnURL",request.getContextPath()+"/admin/manage-posts.jsp");
			request.getRequestDispatcher("../admin/message.jsp").forward(request, response);
		}
	}
	/**
	 * 查询所有文章
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void list(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Contents> contents = cs.findAll();
		HttpSession session = request.getSession();
		session.setAttribute("contents",contents);
	}
	/**
	 * 删除文章
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void del(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		int id =Integer.valueOf(request.getParameter("cid"));
		cs.delete(id);
		java.util.List<Contents> contents = cs.findAll();
		HttpSession session = request.getSession();
		session.setAttribute("contents", contents);
		request.setAttribute("message","删除成功!");
		request.setAttribute("returnURL",request.getContextPath()+"/admin/manage-posts.jsp");
		request.getRequestDispatcher("../admin/message.jsp").forward(request, response);
	}
	/**
	 * 修改文章
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void edit(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		boolean contents = false;
		int id=Integer.parseInt(request.getParameter("cid"));
		Contents c = new Contents();
		SimpleDateFormat fomater=new SimpleDateFormat("yyyy'-'MM'-'dd'-' HH:mm:ss");	
		String nowtime=fomater.format(new Date());
		c.setModified(nowtime);
		c.setTitle(request.getParameter("title"));
		c.setText(request.getParameter("text"));
		c.setType(request.getParameter("type"));
		c.setCid(id);
		if(cs.modify(c)){
			java.util.List<Contents> articlelist = cs.findAll();
			HttpSession session = request.getSession();
			session.setAttribute("contents", articlelist);
			request.setAttribute("message","修改成功!");
			request.setAttribute("returnURL",request.getContextPath()+"/admin/manage-posts.jsp");
			request.getRequestDispatcher("../admin/message.jsp").forward(request, response);
		}
	}
	public void find(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id=Integer.parseInt(request.getParameter("cid"));
		Contents content = new Contents();
		content = cs.selectById(id);
		if(content!=null){
			request.setAttribute("content",content);
			request.getRequestDispatcher("../admin/edit-post.jsp").forward(request,response);
		}
	}
	
}
