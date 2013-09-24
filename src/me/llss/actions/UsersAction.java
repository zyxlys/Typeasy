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
import me.llss.service.impl.LoginServiceImpl;
import me.llss.service.impl.MetasServiceImpl;
import me.llss.service.impl.OptionsServiceImpl;
import me.llss.service.impl.RelationshipsServiceImpl;
import me.llss.service.impl.UsersServiceImpl;
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

public class UsersAction extends ActionSupport implements Action {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private LoginServiceImpl ls = new LoginServiceImpl();
	private RelationshipsServiceImpl rs = new RelationshipsServiceImpl();
	private ContentsServiceImpl cs = new ContentsServiceImpl();
	private CommentsServiceImpl cos = new CommentsServiceImpl();
	private MetasServiceImpl ms = new MetasServiceImpl();
	private OptionsServiceImpl os = new OptionsServiceImpl();
	private UsersServiceImpl us = new UsersServiceImpl();
	private MD5 md5 = new MD5();

	

	private String name;
	private String password;
	private String screenName;
	private String mail;
	private String url;
	private Integer uid;
	private Integer adminId;
	private String avatar;
	private String group;
	private String new_password;
	private String new_password_again;
	private Integer[] userIdArray;

	/*
	 * Getters and setters
	 */
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getScreenName() {
		return screenName;
	}

	public void setScreenName(String screenName) {
		this.screenName = screenName;
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

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public Integer getAdminId() {
		return adminId;
	}

	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public String getNew_password() {
		return new_password;
	}

	public void setNew_password(String new_password) {
		this.new_password = new_password;
	}

	public String getNew_password_again() {
		return new_password_again;
	}

	public void setNew_password_again(String new_password_again) {
		this.new_password_again = new_password_again;
	}

	public Integer[] getUserIdArray() {
		return userIdArray;
	}

	public void setUserIdArray(Integer[] userIdArray) {
		this.userIdArray = userIdArray;
	}

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 后台添加用户
	 * 
	 * @return
	 */
	public String add() {
		HttpServletRequest request = ServletActionContext.getRequest();
		UsersVO user = new UsersVO();
		String nowtime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
				.format(Calendar.getInstance().getTime());
		user.setName(Html2Text.HtmlToText(name));
		user.setPassword(md5.getMD5ofStr(Html2Text.HtmlToText(password)));
		user.setScreenName(Html2Text.HtmlToText(screenName));
		user.setMail(Html2Text.HtmlToText(mail));
		user.setUrl(Html2Text.HtmlToText(url));
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
			request.setAttribute("message", "添加成功!");
			request.setAttribute("returnURL", request.getContextPath()
					+ "/admin/manage-users.jsp");
			return "addUserSuccess";
		} else {
			request.setAttribute("message", "添加失败!用户名或邮箱已存在！");
			request.setAttribute("returnURL", request.getContextPath()
					+ "/admin/add-user.jsp");
			return "addUserFail";
		}
	}

	/**
	 * 修改用户信息
	 * 
	 * @return
	 */
	public String edit() {
		HttpServletRequest request = ServletActionContext.getRequest();
		UsersVO user = new UsersVO();
		user.setScreenName(Html2Text.HtmlToText(screenName));
		user.setMail(Html2Text.HtmlToText(mail));
		user.setUrl(Html2Text.HtmlToText(url));
		user.setAvatar(Html2Text.HtmlToText(avatar));
		user.setGroup(Html2Text.HtmlToText(group));
		user.setUid(uid);
		us.modifyProfile(user);
		UsersVO u = us.find(adminId);

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

		session.setAttribute("user", u);
		request.setAttribute("message", "修改成功!");
		if (adminId == uid) {
			request.setAttribute("returnURL", request.getContextPath()
					+ "/admin/my-profiles.jsp");
			return "editMyProfilesSuccess";
		} else {
			request.setAttribute("returnURL", request.getContextPath()
					+ "/admin/manage-users.jsp");
			return "editUserSuccess";
		}

	}

	/**
	 * 删除用户
	 * 
	 * @return
	 */
	public String del() {
		HttpServletRequest request = ServletActionContext.getRequest();
		us.del(uid);

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

		request.setAttribute("message", "删除成功!");
		request.setAttribute("returnURL", request.getContextPath()
				+ "/admin/manage-users.jsp");
		return "delUserSuccess";
	}

	/**
	 * 根据uid查找用户
	 * 
	 * @return
	 */
	public String find() {
		HttpServletRequest request = ServletActionContext.getRequest();
		UsersVO user = us.find(uid);
		if (user != null) {
			request.setAttribute("u", user);
		}
		return "findUserSuccess";
	}

	/**
	 * 修改密码
	 * 
	 * @return
	 */
	public String modifyPwd() {
		HttpServletRequest request = ServletActionContext.getRequest();
		boolean flag = validatePwd();
		if (flag) {
			UsersVO user = new UsersVO();
			user.setPassword(md5.getMD5ofStr(new_password));
			user.setUid(uid);
			us.security(user);
			UsersVO u = us.find(adminId);
			HttpSession session = request.getSession();
			session.setAttribute("user", u);
			request.setAttribute("message", "更新成功!");
			request.setAttribute("returnURL", request.getContextPath()
					+ "/login.jsp");
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

			return "modifyPwdSuccess";
		} else {
			request.setAttribute("returnURL", request.getContextPath()
					+ "/admin/security.jsp");
			return "modifyPwdFail";
		}

	}

	/**
	 * 验证修改密码额表单
	 * 
	 * @return
	 */
	@SuppressWarnings("null")
	public boolean validatePwd() {
		HttpServletRequest request = ServletActionContext.getRequest();
		boolean flag = true;
		String message = "";
		String md5Pwd = md5.getMD5ofStr(password);

		HttpSession session = request.getSession();
		UsersVO vo = (UsersVO) session.getAttribute("user");
		String pwd = vo.getPassword();

		if (md5Pwd == null || md5Pwd.equals("")) {
			flag = false;
			message = "原密码不能为空！";
		}
		if ((md5Pwd != null || !md5Pwd.equals("")) && (md5Pwd.equals(pwd))
				&& (new_password == null || new_password.equals(""))) {
			flag = false;
			message = "新密码不能为空！";
		}
		if ((md5Pwd == null || md5Pwd.equals(""))
				&& (new_password == null || new_password.equals(""))) {
			flag = false;
			message = "密码不能为空！";
		}
		if ((md5Pwd != null || !md5Pwd.equals("")) && (!md5Pwd.equals(pwd))) {
			flag = false;
			message = "原密码错误！";
		}
		if ((new_password != null || !new_password.equals(""))
				&& (!new_password.equals(new_password_again))) {
			flag = false;
			message = "两次输入密码不一致！";
		}

		request.setAttribute("message", message);
		return flag;
	}

	/**
	 * 批量删除
	 * 
	 * @return
	 */
	public String multiDel() {
		HttpServletRequest request = ServletActionContext.getRequest();

		String op = request.getParameter("multiOption");
		if (op.equals("multiDel")) {
			for (int i = 0; i < userIdArray.length; i++) {
				int userId = Integer.valueOf(userIdArray[i]);
				us.del(userId);
			}

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

			request.setAttribute("message", "删除成功!");
			request.setAttribute("returnURL", request.getContextPath()
					+ "/admin/manage-users.jsp");
			return "multiDelUserSuccess";
		} else {
			return "doNothing";
		}

	}

}
