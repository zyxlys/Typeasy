package me.imomo.typeasy.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import me.imomo.typeasy.service.OptionsService;
import me.imomo.typeasy.vo.Contents;
import me.imomo.typeasy.vo.Options;

/**
 * 配置表相关的Servlet
 * @version 1.0 2013/05/18
 * @author YL
 *
 */
@SuppressWarnings("serial")
public class OptionsServlet extends HttpServlet {

	private OptionsService os = new OptionsService();
	/**
	 * Constructor of the object.
	 */
	public OptionsServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * 此处交给doPost方法处理
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * doPost方法
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		
		if(action.equals("modify"));
		{
			this.modify(request, response);
		}
		
	}
	
	/**
	 * 更新基本信息
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void modify(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		boolean flag = false;
		Options op = new Options();
		op.setName(request.getParameter("blog_title"));
		op.setValue(request.getParameter("blog_description"));
		flag = os.Modify(op);
		if(flag)
		{
			request.setAttribute("message","更改成功!");
			request.setAttribute("returnURL",request.getContextPath()+"/servlet/OptionsServlet");
			request.getRequestDispatcher("../admin/options_one.jsp").forward(request, response);
		}
		
	}
	
	/*public void listAll(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		List<Options> optionsList = os.findAll();
		request.setAttribute("optionsList", optionsList);
		request.getRequestDispatcher("../admin/options_list.jsp").forward(request, response);
	}*/

}
