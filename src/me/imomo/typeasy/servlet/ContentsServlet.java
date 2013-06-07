package me.imomo.typeasy.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;

import me.imomo.typeasy.commons.ContentsComparator;
import me.imomo.typeasy.commons.MetasComparator;
import me.imomo.typeasy.dao.ContentsDAO;
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

/**
 * 内容表Servlet
 * 
 * @author Administrator
 * 
 */
public class ContentsServlet extends HttpServlet {

	private static final long serialVersionUID = 7035043301348731807L;

	private ContentsService cs = new ContentsService();
	private CommentsService cos = new CommentsService();
	private MetasService ms = new MetasService();
	private OptionsService os = new OptionsService();
	private UsersService us = new UsersService();
	private RelationshipsService rs = new RelationshipsService();

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
		if (action.equals("multiDel")) {
			this.multiDel(request, response);
		}
		if (action.equals("search")) {
			this.search(request, response);
		}
		if (action.equals("listByTag")) {
			this.listByTag(request, response);
		}
		if (action.equals("listByCategory")) {
			this.listByCategory(request, response);
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
		HttpSession session = request.getSession();
		ContentsVO content = new ContentsVO();

		String nowtime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
				.format(Calendar.getInstance().getTime());
		String type = request.getParameter("type");
		int authorId = Integer.valueOf(request.getParameter("authorId"));
		int uid = ((UsersVO) session.getAttribute("user")).getUid();
		content.setCreated(nowtime);
		content.setTitle(request.getParameter("title"));
		content.setText(request.getParameter("text"));
		content.setType(type);
		content.setAuthorId(authorId);
		cs.add(content);

		if (type.equals("post")) {
			List<ContentsVO> contentsList = cs.list();
			Collections.sort(contentsList, new ContentsComparator());
			int cid = -1;
			for (int i = (contentsList.size() - 1); i < contentsList.size(); i++) {
				cid = (contentsList.get(i)).getCid();
			}

			int cMid = -1;
			String category = request.getParameter("category");
			if (category != null)
				cMid = Integer.valueOf(category);

			RelationshipsVO re1 = new RelationshipsVO();
			re1.setCid(cid);
			re1.setMid(cMid);
			rs.add(re1);
			ms.editCount(cMid, "+");

			String nameArray[] = request.getParameter("name").split(",");
			RelationshipsVO re2 = new RelationshipsVO();
			MetasVO tMeta = null;

			int num = nameArray.length;
			int tMidArray[] = new int[num];
			int tMid = -1;
			for (int i = 0; i < num; i++) {
				if ((ms.findByName(nameArray[i])).getName() != null) {
					tMidArray[i] = (ms.findByName(nameArray[i])).getMid();
					if (tMidArray[i] != 0) {
						re2.setCid(cid);
						re2.setMid(tMidArray[i]);
						rs.add(re2);
						ms.editCount(tMidArray[i], "+");
					}
				} else {
					tMeta = new MetasVO();
					if (!("".equals(nameArray[i]))) {
						tMeta.setName(nameArray[i]);
						tMeta.setType("tag");
						ms.add(tMeta);
					}
					List<MetasVO> metasList = ms.listAll();
					Collections.sort(metasList, new MetasComparator());

					for (int j = (metasList.size() - 1); j > 0; j--) {
						if ("tag".equals(metasList.get(j).getType())) {
							tMid = (metasList.get(j)).getMid();
							break;
						}
					}
					re2.setCid(cid);
					re2.setMid(tMid);
					rs.add(re2);
					ms.editCount(tMid, "+");
				}
			}
		}
		/* 获取相关session */

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

		if (authorId == uid) {
			request.setAttribute("message", "发表成功!");
			request.setAttribute("returnURL", request.getContextPath()
					+ "/admin/manage-posts-visitor.jsp");
			request.getRequestDispatcher("../admin/message.jsp").forward(
					request, response);
		} else {
			if (type.equals("post")) {
				request.setAttribute("message", "发表成功!");
				request.setAttribute("returnURL", request.getContextPath()
						+ "/admin/manage-posts.jsp");
				request.getRequestDispatcher("../admin/message.jsp").forward(
						request, response);
			}
			if (type.equals("page")) {
				request.setAttribute("message", "发表成功!");
				request.setAttribute("returnURL", request.getContextPath()
						+ "/admin/manage-pages.jsp");
				request.getRequestDispatcher("../admin/message.jsp").forward(
						request, response);
			}
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
	 * 搜索文章
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void search(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String keywords = request.getParameter("keywords");
		List<ContentsVO> sContents = cs.search(keywords);
		Collections.reverse(sContents);
		HttpSession session = request.getSession();
		session.setAttribute("sContents", sContents);
		response.sendRedirect("../blog/archive.jsp?action=search");
	}

	/**
	 * 按标签查看
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void listByTag(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		int mid = Integer.valueOf(request.getParameter("mid"));
		List<RelationshipsVO> rls = rs.findByMid(mid);
		List<ContentsVO> tmContents = new ArrayList<ContentsVO>();
		for (RelationshipsVO rl : rls) {
			int cid = rl.getCid();
			tmContents.add(cs.find(cid));
		}
		Collections.reverse(tmContents);
		HttpSession session = request.getSession();
		session.setAttribute("tmContents", tmContents);
		response.sendRedirect("../blog/archive.jsp?action=tag");
	}

	/**
	 * 按目录查看
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void listByCategory(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		int mid = Integer.valueOf(request.getParameter("mid"));
		List<RelationshipsVO> rls = rs.findByMid(mid);
		List<ContentsVO> cmContents = new ArrayList<ContentsVO>();
		for (RelationshipsVO rl : rls) {
			int cid = rl.getCid();
			cmContents.add(cs.find(cid));
		}

		Collections.reverse(cmContents);
		HttpSession session = request.getSession();
		session.setAttribute("cmContents", cmContents);
		response.sendRedirect("../blog/archive.jsp?action=category");
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

		HttpSession session = request.getSession();
		int authorId = Integer.valueOf(request.getParameter("authorId"));
		int uid = ((UsersVO) session.getAttribute("user")).getUid();
		int cid = Integer.valueOf(request.getParameter("cid"));
		List<RelationshipsVO> res = rs.findByCid(cid);
		for (RelationshipsVO re : res) {
			ms.editCount(re.getMid(), "-");
		}
		String type = request.getParameter("type");
		cs.del(cid);
		cos.delByCid(cid);
		rs.del(cid);

		/* 获取相关session */

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

		if (authorId == uid) {
			request.setAttribute("message", "删除成功!");
			request.setAttribute("returnURL", request.getContextPath()
					+ "/admin/manage-posts-visitor.jsp");
			request.getRequestDispatcher("../admin/message.jsp").forward(
					request, response);
		} else {

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

		HttpSession session = request.getSession();
		int cid = Integer.parseInt(request.getParameter("cid"));
		String type = request.getParameter("type");
		int oldCMid = -1;
		if (request.getParameter("oldCMid") != null)
			oldCMid = Integer.valueOf(request.getParameter("oldCMid"));
		ContentsVO c = new ContentsVO();
		String nowtime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
				.format(Calendar.getInstance().getTime());
		c.setModified(nowtime);
		c.setCid(cid);
		c.setTitle(request.getParameter("title"));
		c.setText(request.getParameter("text"));
		c.setType(type);
		cs.edit(c);
		String author = request.getParameter("authorId");
		if (author == null)
			author = "-1";
		int authorId = Integer.valueOf(author);
		int uid = ((UsersVO) session.getAttribute("user")).getUid();

		if (type.equals("post")) {

			int cMid = -1;
			String category = request.getParameter("category");
			if (category != null)
				cMid = Integer.valueOf(category);
			RelationshipsVO re1 = new RelationshipsVO();
			re1.setCid(cid);
			re1.setMid(cMid);
			rs.edit(re1, oldCMid);

			/********* 目录处理完毕 ********/

			String nameArray[] = request.getParameter("name").split(",");
			RelationshipsVO re2 = new RelationshipsVO();
			MetasVO tMeta = null;

			int num = nameArray.length;
			int tMidArray[] = new int[num];
			int tMid = -1;
			for (int i = 0; i < num; i++) {

				if ((ms.findByName(nameArray[i])).getName() != null) {
					tMidArray[i] = (ms.findByName(nameArray[i])).getMid();
					if (tMidArray[i] != 0) {
						re2.setCid(cid);
						re2.setMid(tMidArray[i]);
						rs.add(re2);
						ms.editCount(tMid, "+");
					}
				} else {
					tMeta = new MetasVO();
					if (!("".equals(nameArray[i]))) {
						tMeta.setName(nameArray[i]);
						tMeta.setType("tag");
						ms.add(tMeta);

						List<MetasVO> metasList = ms.listAll();
						Collections.sort(metasList, new MetasComparator());

						for (int j = (metasList.size() - 1); j > 0; j--) {
							if ("tag".equals(metasList.get(j).getType())) {
								tMid = (metasList.get(j)).getMid();
								break;
							}
						}
						re2.setCid(cid);
						re2.setMid(tMid);
						rs.add(re2);
						ms.editCount(tMid, "+");
					}
				}
			}

			List<ContentsVO> contents = cs.list();
			Collections.reverse(contents);
			session.setAttribute("contents", contents);
			if (authorId == uid) {
				request.setAttribute("message", "修改成功!");
				request.setAttribute("returnURL", request.getContextPath()
						+ "/admin/manage-posts-visitor.jsp");
				request.getRequestDispatcher("../admin/message.jsp").forward(
						request, response);
			} else {
				request.setAttribute("message", "修改成功!");
				request.setAttribute("returnURL", request.getContextPath()
						+ "/admin/manage-posts.jsp");
				request.getRequestDispatcher("../admin/message.jsp").forward(
						request, response);
			}
		} else {
			List<ContentsVO> contents = cs.list();
			Collections.reverse(contents);
			session.setAttribute("contents", contents);
			if (authorId == uid) {
				request.setAttribute("message", "修改成功!");
				request.setAttribute("returnURL", request.getContextPath()
						+ "/admin/manage-posts-visitor.jsp");
				request.getRequestDispatcher("../admin/message.jsp").forward(
						request, response);
			} else {
				request.setAttribute("message", "修改成功!");
				request.setAttribute("returnURL", request.getContextPath()
						+ "/admin/manage-pages.jsp");
				request.getRequestDispatcher("../admin/message.jsp").forward(
						request, response);
			}
		}

		/* 获取相关session */

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
		HttpSession session = request.getSession();
		String cid[] = request.getParameterValues("cid");
		String type = request.getParameter("type");
		String op = request.getParameter("multiOption");
		String opreator = request.getParameter("opreatorId");
		if (opreator == null || "".equals(opreator))
			opreator = "-1";
		int opreatorId = Integer.valueOf(opreator);
		int uid = ((UsersVO) session.getAttribute("user")).getUid();

		if (op.equals("multiDel")) {
			for (int i = 0; i < cid.length; i++) {
				int contentId = Integer.valueOf(cid[i]);
				List<RelationshipsVO> res = rs.findByCid(contentId);
				for (RelationshipsVO re : res) {
					ms.editCount(re.getMid(), "-");
				}
				cos.delByCid(contentId);
				cs.del(contentId);
				rs.del(contentId);

			}

			/* 获取相关session */

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

			if (opreatorId == uid) {
				request.setAttribute("message", "删除成功!");
				request.setAttribute("returnURL", request.getContextPath()
						+ "/admin/manage-posts-visitor.jsp");
				request.getRequestDispatcher("../admin/message.jsp").forward(
						request, response);
			} else {

				if (type.equals("post")) {
					request.setAttribute("message", "删除成功!");
					request.setAttribute("returnURL", request.getContextPath()
							+ "/admin/manage-posts.jsp");
					request.getRequestDispatcher("../admin/message.jsp")
							.forward(request, response);
				} else {
					request.setAttribute("message", "删除成功!");
					request.setAttribute("returnURL", request.getContextPath()
							+ "/admin/manage-pages.jsp");
					request.getRequestDispatcher("../admin/message.jsp")
							.forward(request, response);
				}
			}
		} else {

			if (opreatorId == uid) {
				response.sendRedirect("../admin/manage-posts-visitor.jsp");
			} else {
				if (type.equals("post")) {
					response.sendRedirect("../admin/manage-posts.jsp");
				} else {
					response.sendRedirect("../admin/manage-pages.jsp");
				}
			}
		}

	}

}
