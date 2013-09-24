package me.llss.actions;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import me.llss.service.impl.CommentsServiceImpl;
import me.llss.service.impl.ContentsServiceImpl;
import me.llss.service.impl.MetasServiceImpl;
import me.llss.service.impl.OptionsServiceImpl;
import me.llss.service.impl.RelationshipsServiceImpl;
import me.llss.service.impl.UsersServiceImpl;
import me.llss.utils.Html2Text;
import me.llss.vo.CommentsVO;
import me.llss.vo.ContentsVO;
import me.llss.vo.MetasVO;
import me.llss.vo.OptionsVO;
import me.llss.vo.RelationshipsVO;
import me.llss.vo.UsersVO;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

/**
 * Comments Action
 * 
 * @author Acris
 * @version 2.0 2013/09/21 11:36
 */

public class CommentsAction extends ActionSupport implements Action {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private RelationshipsServiceImpl rs = new RelationshipsServiceImpl();
	private ContentsServiceImpl contentService = new ContentsServiceImpl();
	private CommentsServiceImpl cs = new CommentsServiceImpl();
	private MetasServiceImpl ms = new MetasServiceImpl();
	private OptionsServiceImpl os = new OptionsServiceImpl();
	private UsersServiceImpl us = new UsersServiceImpl();

	private Integer cid;
	private String author;
	private Integer authorId;
	private Integer ownerId;
	private String text;
	private String mail;
	private String url;
	private String fromPage;
	private Integer coid;
	private Integer[] commentIdArray;

	/*
	 * Getters an setters
	 */
	public Integer getCid() {
		return cid;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Integer getAuthorId() {
		return authorId;
	}

	public void setAuthorId(Integer authorId) {
		this.authorId = authorId;
	}

	public Integer getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(Integer ownerId) {
		this.ownerId = ownerId;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getFromPage() {
		return fromPage;
	}

	public void setFromPage(String fromPage) {
		this.fromPage = fromPage;
	}

	public Integer getCoid() {
		return coid;
	}

	public void setCoid(Integer coid) {
		this.coid = coid;
	}

	public Integer[] getCommentIdArray() {
		return commentIdArray;
	}

	public void setCommentIdArray(Integer[] commentIdArray) {
		this.commentIdArray = commentIdArray;
	}

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return super.execute();
	}

	/**
	 * 添加评论
	 * 
	 * @return
	 */
	public String add() {
		HttpServletRequest request = ServletActionContext.getRequest();
		CommentsVO comment = new CommentsVO();
		String nowtime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
				.format(Calendar.getInstance().getTime());

		comment.setCid(cid);
		comment.setCreated(nowtime);
		comment.setAuthor(Html2Text.HtmlToText(author));
		if (authorId != null)
			comment.setAuthorId(authorId);
		else
			comment.setAuthorId(0);
		comment.setOwnerId(ownerId);
		comment.setMail(Html2Text.HtmlToText(mail));
		comment.setUrl(url);
		comment.setText(Html2Text.HtmlToText(text));
		cs.add(comment);
		contentService.editCommentsNum(cid, "+");

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

		return "addSuccess";
	}

	/**
	 * 删除评论
	 * 
	 * @return
	 */
	public String del() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		if (fromPage == null)
			fromPage = "";
		int uid = ((UsersVO) session.getAttribute("user")).getUid();
		contentService.editCommentsNum(cid, "-");
		cs.del(coid);

		/* 获取相关session */

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

		if ("postPage".equals(fromPage)) {
			return "delFromPostPageSuccess";
		} else {
			if (authorId == uid) {
				request.setAttribute("message", "删除成功!");
				request.setAttribute("returnURL", request.getContextPath()
						+ "/admin/manage-comments-visitor.jsp");
				return "visitorDelSuccess";
			} else {
				request.setAttribute("message", "删除成功!");
				request.setAttribute("returnURL", request.getContextPath()
						+ "/admin/manage-comments.jsp");
				return "adminDelSuccess";
			}
		}
	}

	/**
	 * 修改评论
	 * 
	 * @return
	 */
	public String edit() {
		HttpServletRequest request = ServletActionContext.getRequest();
		CommentsVO comment = new CommentsVO();
		comment.setCoid(coid);
		comment.setAuthor(Html2Text.HtmlToText(author));
		comment.setMail(Html2Text.HtmlToText(mail));
		comment.setUrl(Html2Text.HtmlToText(url));
		comment.setText(Html2Text.HtmlToText(text));
		cs.edit(comment);

		/* 获取相关session */
		HttpSession session = request.getSession();
		int authorId = Integer.valueOf(request.getParameter("authorId"));
		int uid = ((UsersVO) session.getAttribute("user")).getUid();

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

		if (authorId == uid) {
			request.setAttribute("message", "修改成功!");
			request.setAttribute("returnURL", request.getContextPath()
					+ "/admin/manage-comments-visitor.jsp");
			return "visitorEditSuccess";
		} else {

			request.setAttribute("message", "修改成功!");
			request.setAttribute("returnURL", request.getContextPath()
					+ "/admin/manage-comments.jsp");
			return "adminEditSuccess";
		}

	}

	/**
	 * 通过coid查找评论
	 * 
	 * @return
	 */

	public String find() {
		HttpServletRequest request = ServletActionContext.getRequest();
		CommentsVO comment = cs.find(coid);
		request.setAttribute("comment", comment);
		return "findSuccess";
	}

	/**
	 * 批量删除
	 * 
	 * @return
	 */
	public String multiDel() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		String opreator = request.getParameter("opreatorId");
		if (opreator == null || "".equals(opreator))
			opreator = "-1";
		int opreatorId = Integer.valueOf(opreator);
		int uid = ((UsersVO) session.getAttribute("user")).getUid();
		String op = request.getParameter("multiOption");
		if (op.equals("multiDel")) {
			for (int i = 0; i < commentIdArray.length; i++) {
				int commentId = Integer.valueOf(commentIdArray[i]);
				int cid = (cs.find(Integer.valueOf(commentIdArray[i])))
						.getCid();
				contentService.editCommentsNum(cid, "-");
				cs.del(commentId);
			}

			/* 获取相关session */

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
			if (opreatorId == uid) {
				request.setAttribute("message", "删除成功!");
				request.setAttribute("returnURL", request.getContextPath()
						+ "/admin/manage-posts-visitor.jsp");
				return "visitorMultiDelSuccess";
			} else {

				request.setAttribute("message", "删除成功!");
				request.setAttribute("returnURL", request.getContextPath()
						+ "/admin/manage-comments.jsp");
				return "adminMultiDelSuccess";
			}
		} else {
			if (opreatorId == uid) {
				return "visitorDoNothing";
			} else {
				return "adminDoNothing";
			}
		}

	}
}
