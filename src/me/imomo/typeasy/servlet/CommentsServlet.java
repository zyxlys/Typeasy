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

import me.imomo.typeasy.dao.CommentsDAO;
import me.imomo.typeasy.dao.ContentsDAO;
import me.imomo.typeasy.service.CommentsService;
import me.imomo.typeasy.service.ContentsService;
import me.imomo.typeasy.service.MetasService;
import me.imomo.typeasy.service.OptionsService;
import me.imomo.typeasy.service.UsersService;
import me.imomo.typeasy.vo.CommentsVO;
import me.imomo.typeasy.vo.ContentsVO;
import me.imomo.typeasy.vo.MetasVO;
import me.imomo.typeasy.vo.OptionsVO;
import me.imomo.typeasy.vo.RelationshipsVO;
import me.imomo.typeasy.vo.UsersVO;

public class CommentsServlet extends HttpServlet {

	private RelationshipsVO rs = new RelationshipsVO();
	private ContentsService contentService = new ContentsService();
	private CommentsService cs = new CommentsService();
	private MetasService ms = new MetasService();
	private OptionsService os = new OptionsService();
	private UsersService us = new UsersService();

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
		if (action.equals("find")) {
			this.find(request, response);
		}
		if (action.equals("multiDel")) {
			this.multiDel(request, response);
		}

	}

	/**
	 * 添加评论
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void add(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		CommentsVO comment = new CommentsVO();
		SimpleDateFormat fomater=new SimpleDateFormat("yyyy'-'MM'-'dd HH:mm:ss");	
		String nowtime=fomater.format(new Date());
		int cid = Integer.valueOf(request.getParameter("cid"));
		comment.setCid(cid);
		comment.setCreated(nowtime);
		comment.setText(request.getParameter("text"));
		comment.setAuthor(request.getParameter("author"));
		if(request.getParameter("authorId") != null)
			comment.setAuthorId(Integer.valueOf(request.getParameter("authorId")));
		else
			comment.setAuthorId(0);
		comment.setOwnerId(Integer.valueOf(request.getParameter("ownerId")));
		comment.setMail(request.getParameter("mail"));
		comment.setUrl(request.getParameter("url"));
		comment.setText(request.getParameter("text"));
		cs.add(comment);
		contentService.editCommentsNum(cid,"+");

		/*获取相关session*/
		HttpSession session=request.getSession();

		
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
		
		
		response.sendRedirect("../"+cid+".post#comment-"+cid);
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

		int coid = Integer.valueOf(request.getParameter("coid"));
		int cid = Integer.valueOf(request.getParameter("cid"));
		contentService.editCommentsNum(cid,"-");
		cs.del(coid);


		/*获取相关session*/
		HttpSession session=request.getSession();

		
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
				+ "/admin/manage-comments.jsp");
		request.getRequestDispatcher("../admin/message.jsp").forward(request,
				response);
	}

	/**
	 * 查询评论
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */

	public void list(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<CommentsVO> comments = cs.list();
		HttpSession session = request.getSession();
		session.setAttribute("comments", comments);

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
		CommentsVO comment = new CommentsVO();
		comment.setCoid(Integer.valueOf(request.getParameter("coid")));
		comment.setAuthor(request.getParameter("author"));
		comment.setMail(request.getParameter("mail"));
		comment.setUrl(request.getParameter("url"));
		comment.setText(request.getParameter("text"));
		cs.edit(comment);


		/*获取相关session*/
		HttpSession session=request.getSession();

		
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
		
		
		request.setAttribute("message", "修改成功!");
		request.setAttribute("returnURL", request.getContextPath()
				+ "/admin/manage-comments.jsp");
		request.getRequestDispatcher("../admin/message.jsp").forward(request,
				response);

	}

	/**
	 * 通过coid查找评论
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */

	public void find(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.valueOf(request.getParameter("coid"));
		CommentsVO comment = cs.find(id);
		request.setAttribute("comment", comment);
		request.getRequestDispatcher("../admin/edit-comment.jsp").forward(
				request, response);
	}

	public void multiDel(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String coid[] = request.getParameterValues("coid");
		
		String op = request.getParameter("multiOption");
		if(op.equals("multiDel")) {
			for (int i=0;i<coid.length;i++) {
				int commentId = Integer.valueOf(coid[i]);
				int cid = (cs.find(Integer.valueOf(coid[i]))).getCid();
				contentService.editCommentsNum(cid,"-");
				cs.del(commentId);
			}


			/*获取相关session*/
			HttpSession session=request.getSession();

			
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
					+ "/admin/manage-comments.jsp");
			request.getRequestDispatcher("../admin/message.jsp").forward(request,
					response);
		}
		else {
			response.sendRedirect("../admin/manage-comments.jsp");
		}

	}

}
