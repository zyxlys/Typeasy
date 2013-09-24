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
import me.llss.vo.CommentsVO;
import me.llss.vo.ContentsVO;
import me.llss.vo.MetasVO;
import me.llss.vo.OptionsVO;
import me.llss.vo.RelationshipsVO;
import me.llss.vo.UsersVO;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class OptionsAction extends ActionSupport implements Action {

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


	private String title;
	private String description;
	private String date;
	private String time;
	private String excerpt;
	private String number;
	private String count;

	/*
	 * Getters and setters
	 */
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getExcerpt() {
		return excerpt;
	}

	public void setExcerpt(String excerpt) {
		this.excerpt = excerpt;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		HttpServletRequest request = ServletActionContext.getRequest();
		OptionsVO o1 = new OptionsVO();
		OptionsVO o2 = new OptionsVO();
		OptionsVO o3 = new OptionsVO();
		OptionsVO o4 = new OptionsVO();
		OptionsVO o5 = new OptionsVO();
		OptionsVO o6 = new OptionsVO();
		OptionsVO o7 = new OptionsVO();
		o1.setValue(title);
		o1.setName("title");
		o2.setValue(description);
		o2.setName("description");
		o3.setValue(date);
		o3.setName("date");
		o4.setValue(time);
		o4.setName("time");
		o5.setValue(excerpt);
		o5.setName("excerpt");
		o6.setValue(number);
		o6.setName("number");
		o7.setValue(count);
		o7.setName("count");

		os.edit(o1);
		os.edit(o2);
		os.edit(o3);
		os.edit(o4);
		os.edit(o5);
		os.edit(o6);
		os.edit(o7);

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

		request.setAttribute("message", "更改成功!");
		request.setAttribute("returnURL", request.getContextPath()
				+ "/admin/options.jsp");

		return "success";
	}

}
