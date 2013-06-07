package me.imomo.typeasy.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import sun.misc.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 上传头像
 * 
 * @author Administrator
 * 
 */
@SuppressWarnings("serial")
public class AvatarServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public AvatarServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to
	 * post.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String pic = request.getParameter("pic");
		String pic1 = request.getParameter("pic1");
		String pic2 = request.getParameter("pic2");
		String pic3 = request.getParameter("pic3");
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
	}

	/**
	 * Initialization of the servlet. <br>
	 * 
	 * @throws ServletException
	 *             if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
