package me.llss.actions;

import java.io.File;
import java.io.FileOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import sun.misc.BASE64Decoder;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 头像上传
 * 
 * @author Acris
 * @version 2.0 2013/09/21 00:30
 */
public class AvatarAction extends ActionSupport implements Action {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String pic;
	private String pic1;
	private String pic2;
	private String pic3;


	/*
	 * Getters and setters
	 */

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public String getPic1() {
		return pic1;
	}

	public void setPic1(String pic1) {
		this.pic1 = pic1;
	}

	public String getPic2() {
		return pic2;
	}

	public void setPic2(String pic2) {
		this.pic2 = pic2;
	}

	public String getPic3() {
		return pic3;
	}

	public void setPic3(String pic3) {
		this.pic3 = pic3;
	}

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		long time = System.currentTimeMillis();

		String path = request.getSession().getServletContext().getRealPath("/")
				+ "uploads/avatar/";

		String picName = "src-" + time;
		String pic1Name = "1-" + time;
		String pic2Name = "2-" + time;
		String pic3Name = "3-" + time;

		if (!pic.equals("") && pic != null) {
			// 原图
			File file = new File(path + picName + ".png");
			FileOutputStream fout = null;
			fout = new FileOutputStream(file);
			fout.write(new BASE64Decoder().decodeBuffer(pic));
			fout.close();
		}

		// 图1
		File file1 = new File(path + pic1Name + ".png");
		FileOutputStream fout1 = null;
		fout1 = new FileOutputStream(file1);
		fout1.write(new BASE64Decoder().decodeBuffer(pic1));
		fout1.close();

		// 图2
		File file2 = new File(path + pic2Name + ".png");
		FileOutputStream fout2 = null;
		fout2 = new FileOutputStream(file2);
		fout2.write(new BASE64Decoder().decodeBuffer(pic2));
		fout2.close();

		// 图3
		File file3 = new File(path + pic3Name + ".png");
		FileOutputStream fout3 = null;
		fout3 = new FileOutputStream(file3);
		fout3.write(new BASE64Decoder().decodeBuffer(pic3));
		fout3.close();

		HttpSession session = request.getSession();
		session.setAttribute("picName", picName);
		session.setAttribute("pic1Name", pic1Name);
		session.setAttribute("pic2Name", pic2Name);
		session.setAttribute("pic3Name", pic3Name);

		response.getWriter().println("{\"status\":1}");
		return null;
	}

}
