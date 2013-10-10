package me.llss.actions;

import java.util.Collections;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import me.llss.service.impl.CommentsServiceImpl;
import me.llss.service.impl.ContentsServiceImpl;
import me.llss.service.impl.LoginServiceImpl;
import me.llss.service.impl.MetasServiceImpl;
import me.llss.service.impl.OptionsServiceImpl;
import me.llss.service.impl.RelationshipsServiceImpl;
import me.llss.service.impl.UsersServiceImpl;
import me.llss.vo.CommentsVO;
import me.llss.vo.ContentsVO;
import me.llss.vo.MetasVO;
import me.llss.vo.OptionsVO;
import me.llss.vo.RelationshipsVO;
import me.llss.vo.UsersVO;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

/**
 * Index Action
 * 
 * @author Acris
 * @version 2.0 2013/09/21 14:24
 */
public class IndexAction extends ActionSupport implements Action {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private RelationshipsServiceImpl rs = new RelationshipsServiceImpl();
	private ContentsServiceImpl cs = new ContentsServiceImpl();
	private CommentsServiceImpl cos = new CommentsServiceImpl();
	private MetasServiceImpl ms = new MetasServiceImpl();
	private OptionsServiceImpl os = new OptionsServiceImpl();
	private UsersServiceImpl us = new UsersServiceImpl();
	private LoginServiceImpl ls = new LoginServiceImpl();

	private String type;
	private String cid;
	private String error;

	/*
	 * Getters and setters
	 */
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		
		HttpServletRequest request = ServletActionContext.getRequest();
		
		String name = null;
		String pwd = null;
		String cookieStr = null;
		UsersVO user = null;
		UsersVO u = null;

		/* 获取相关session */
		HttpSession session = request.getSession();

		if (((UsersVO) session.getAttribute("user")) == null) {

			Cookie[] cookies = request.getCookies();
			if (cookies != null) {
				for (Cookie c : cookies) {
					if (c.getName().equals("UserCookie")) {
						cookieStr = c.getValue();
					}
				}
				if (cookieStr != null) {
					String cStrArray[] = cookieStr.trim().split(",");
					name = cStrArray[0];
					pwd = cStrArray[1];
				}
				if (name != null && pwd != null) {
					user = new UsersVO();
					u = new UsersVO();
					u.setName(name);
					u.setPassword(pwd);
					user = ls.login(u);
					session.setAttribute("user", user);
				}
			}
		}

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
		
		String theme = "";
		for (OptionsVO option : options) {
			if ("theme".equals(option.getName()))
				theme = option.getValue();
		}
		String themePath = "contents/themes/" + theme + "/";
		ServletContext context = ServletActionContext.getServletContext();
		context.setAttribute("themeType", theme);
		context.setAttribute("themePath", themePath);

		String code = error;
		if (code != null) {
			if (code.equals("404"))
				return "404";
			else if (code.equals("500"))
				return "500";
			else {
				if (("".equals(cid) || cid == null)
						|| ("".equals(type) || type == null))
					return "index";
				else if (type.equals("post"))
					return "goPost";
				else
					return "goPage";
			}
		} else {
			if (("".equals(cid) || cid == null)
					|| ("".equals(type) || type == null))
				return "index";
			else if (type.equals("post"))
				return "goPost";
			else
				return "goPage";
		}
	}

}
