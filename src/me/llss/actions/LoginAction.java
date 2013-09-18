package me.llss.actions;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import me.llss.service.impl.CommentsServiceImpl;
import me.llss.service.impl.ContentsServiceImpl;
import me.llss.service.impl.LoginServiceImpl;
import me.llss.service.impl.MetasServiceImpl;
import me.llss.service.impl.OptionsServiceImpl;
import me.llss.service.impl.RelationshipsServiceImpl;
import me.llss.service.impl.UsersServiceImpl;
import me.llss.utils.CookieUtil;
import me.llss.utils.Html2Text;
import me.llss.utils.MD5;
import me.llss.vo.CommentsVO;
import me.llss.vo.ContentsVO;
import me.llss.vo.MetasVO;
import me.llss.vo.OptionsVO;
import me.llss.vo.RelationshipsVO;
import me.llss.vo.UsersVO;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

/**
 * Login,Register and Logout Action
 * 
 * @author Acris
 * @version 2.0 2013/09/18 22:11
 * 
 */

public class LoginAction extends ActionSupport implements Action {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private RelationshipsServiceImpl rs = new RelationshipsServiceImpl();
	private LoginServiceImpl ls = new LoginServiceImpl();
	private ContentsServiceImpl cs = new ContentsServiceImpl();
	private CommentsServiceImpl cos = new CommentsServiceImpl();
	private MetasServiceImpl ms = new MetasServiceImpl();
	private OptionsServiceImpl os = new OptionsServiceImpl();
	private UsersServiceImpl us = new UsersServiceImpl();
	private MD5 md5 = new MD5();

	HttpServletRequest request = ServletActionContext.getRequest();
	HttpServletResponse response = ServletActionContext.getResponse();

	private String user_login;
	private String user_pwd;
	private String rememberMe;
	private String user_email;
	private String user_nickname;

	/*
	 * getters and setters
	 */
	public String getUser_login() {
		return user_login;
	}

	public void setUser_login(String user_login) {
		this.user_login = user_login;
	}

	public String getUser_pwd() {
		return user_pwd;
	}

	public void setUser_pwd(String user_pwd) {
		this.user_pwd = user_pwd;
	}

	public String getRememberMe() {
		return rememberMe;
	}

	public void setRememberMe(String rememberMe) {
		this.rememberMe = rememberMe;
	}

	public String getUser_email() {
		return user_email;
	}

	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}

	public String getUser_nickname() {
		return user_nickname;
	}

	public void setUser_nickname(String user_nickname) {
		this.user_nickname = user_nickname;
	}

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 用户登录
	 * 
	 * @return
	 */
	public String login() {
		UsersVO user = new UsersVO();
		String name = Html2Text.HtmlToText(user_login);
		String password = md5.getMD5ofStr(Html2Text.HtmlToText(user_pwd));
		user.setName(name);
		user.setPassword(password);
		UsersVO u = ls.login(user);

		/* 获取相关session */
		HttpSession session = request.getSession();

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

		if (u.getUid() == null) {
			this.addActionError("用户名或密码错误");
			return "loginFail";
		} else {
			session.setAttribute("user", u);
			if (rememberMe != null && rememberMe.endsWith("forever")) {
				String cookieValue = name + "," + password;
				Cookie cookie = new Cookie("UserCookie", cookieValue);
				cookie.setMaxAge(864000); // 设置有效期为十天
				cookie.setPath(request.getContextPath());
				response.addCookie(cookie);
			}
			return "loginSuccess";
		}
	}

	/**
	 * 用户注册
	 * 
	 * @return
	 */
	public String register() {
		String name = Html2Text.HtmlToText(user_login);
		String password = md5.getMD5ofStr(Html2Text.HtmlToText(user_pwd));
		String mail = Html2Text.HtmlToText(user_email);
		String screenName = Html2Text.HtmlToText(user_nickname);
		String nowtime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
				.format(Calendar.getInstance().getTime());
		UsersVO user = new UsersVO();
		user.setName(name);
		user.setPassword(password);
		user.setMail(mail);
		user.setScreenName(screenName);
		user.setCreated(nowtime);
		boolean flag = ls.register(user);

		/* 获取相关session */
		HttpSession session = request.getSession();

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

		if (flag) {
			UsersVO u = ls.login(user);
			session.setAttribute("user", u);
			return "regSuccess";
		} else {
			this.addActionError("用户名或邮箱已被注册,请重新输入.");
			return "regFail";
		}
	}

	/**
	 * 用户注销
	 * 
	 * @return
	 */
	public String logout() {
		// 删除cookie中的值以及session中的值
		CookieUtil.removeCookie("UserCookie", request, response);
		request.getSession().removeAttribute("user");
		this.addActionMessage("您已经安全退出.");

		/* 获取相关session */
		HttpSession session = request.getSession();

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

		return "success";
	}

}
