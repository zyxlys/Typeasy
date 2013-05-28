package me.imomo.typeasy.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import me.imomo.typeasy.service.OptionsService;
import me.imomo.typeasy.vo.ContentsVO;
import me.imomo.typeasy.vo.OptionsVO;

/**
 * 配置表相关的Servlet
 * @version 1.0 2013/05/18
 * @author YL
 *
 */
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
		
		if(action.equals("edit"));
		{
			this.edit(request, response);
		}
		
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void edit(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		OptionsVO option = new OptionsVO();
		option.setName(request.getParameter("name"));
		option.setValue(request.getParameter("value"));
		if(os.edit(option))
		{
			request.setAttribute("option", option);
			//List<Options> options = os.list();
			HttpSession session=request.getSession();
			session.setAttribute("option",option);			
			request.setAttribute("message","更改成功!");
			request.setAttribute("returnURL",request.getContextPath()+"/admin/options.jsp");
			request.getRequestDispatcher("../admin/message.jsp").forward(request, response);
		}
		
	}
	
}
