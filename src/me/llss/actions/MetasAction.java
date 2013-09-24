package me.llss.actions;

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
 * Metas Action
 * 
 * @author Acris
 * @version 2.0 2013/09/21
 */
public class MetasAction extends ActionSupport implements Action {

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

	private String slug;
	private String name;
	private String type;
	private String description;
	private Integer mid;
	private Integer[] metaIdArray;

	
	
	public String getSlug() {
		return slug;
	}

	public void setSlug(String slug) {
		this.slug = slug;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getMid() {
		return mid;
	}

	public void setMid(Integer mid) {
		this.mid = mid;
	}

	public Integer[] getMetaIdArray() {
		return metaIdArray;
	}

	public void setMetaIdArray(Integer[] metaIdArray) {
		this.metaIdArray = metaIdArray;
	}

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 添加Metas
	 * 
	 * @return
	 */
	public String add() {
		HttpServletRequest request = ServletActionContext.getRequest();
		MetasVO meta = new MetasVO();
		meta.setName(Html2Text.HtmlToText(name));
		meta.setSlug(Html2Text.HtmlToText(slug));
		meta.setType(Html2Text.HtmlToText(type));
		meta.setDescription(Html2Text.HtmlToText(description));
		ms.add(meta);
		List<MetasVO> metas = ms.listAll();
		HttpSession session = request.getSession();
		session.setAttribute("metas", metas);
		if (type.equals("category")) {
			request.setAttribute("message", "添加成功!");
			request.setAttribute("returnURL", request.getContextPath()
					+ "/admin/manage-categories.jsp");
			return "addCategorySuccess";
		} else {
			request.setAttribute("message", "添加成功!");
			request.setAttribute("returnURL", request.getContextPath()
					+ "/admin/manage-tags.jsp");
			return "addTagSuccess";
		}
	}

	/**
	 * 删除Metas
	 * 
	 * @return
	 */
	public String del() {
		HttpServletRequest request = ServletActionContext.getRequest();
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
			return "delCategorySuccess";
		} else {
			request.setAttribute("message", "删除成功!");
			request.setAttribute("returnURL", request.getContextPath()
					+ "/admin/manage-tags.jsp");
			return "delTagSuccess";
		}

	}

	/**
	 * 修改Metas
	 * 
	 * @return
	 */
	public String edit() {
		HttpServletRequest request = ServletActionContext.getRequest();
		MetasVO meta = new MetasVO();
		meta.setMid(mid);
		meta.setName(Html2Text.HtmlToText(name));
		meta.setSlug(Html2Text.HtmlToText(slug));
		meta.setType(Html2Text.HtmlToText(type));
		meta.setDescription(Html2Text.HtmlToText(description));
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
			return "editCategorySuccess";
		} else {
			request.setAttribute("message", "更新成功!");
			request.setAttribute("returnURL", request.getContextPath()
					+ "/admin/manage-tags.jsp");
			return "editTagSuccess";
		}

	}

	/**
	 * 根据mid查找Meta
	 * 
	 * @return
	 */
	public String find() {
		HttpServletRequest request = ServletActionContext.getRequest();
		MetasVO meta = ms.findByMid(mid);
		request.setAttribute("meta", meta);
		if (type.equals("category")) {
			return "findCategorySuccess";
		} else {
			return "findTagSuccess";
		}

	}

	/**
	 * 批量删除
	 * 
	 * @return
	 */
	public String multiDel() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String type = request.getParameter("type");
		String op = request.getParameter("multiOption");
		if (op.equals("multiDel")) {
			for (int i = 0; i < metaIdArray.length; i++) {
				int metaId = Integer.valueOf(metaIdArray[i]);
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

			if (type.equals("category")) {
				request.setAttribute("message", "删除成功!");
				request.setAttribute("returnURL", request.getContextPath()
						+ "/admin/manage-categories.jsp");
				return "MultiDelCategorySuccess";
			} else {
				request.setAttribute("message", "删除成功!");
				request.setAttribute("returnURL", request.getContextPath()
						+ "/admin/manage-tags.jsp");
				return "MultiDelTagSuccess";
			}
		} else {
			if (type.equals("category")) {
				return "CategoryDoNothing";
			} else {
				return "TagDoNothing";
			}
		}

	}

}
