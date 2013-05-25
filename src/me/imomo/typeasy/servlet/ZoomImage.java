package me.imomo.typeasy.servlet;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import me.imomo.typeasy.commons.ImageHepler;

public class ZoomImage extends HttpServlet
{
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{

		int imageWidth = Integer.parseInt(request.getParameter("txt_width"));
		int imageHeight = Integer.parseInt(request.getParameter("txt_height"));
		int cutTop = Integer.parseInt(request.getParameter("txt_top"));
		int cutLeft = Integer.parseInt(request.getParameter("txt_left"));
		int dropWidth = Integer.parseInt(request.getParameter("txt_DropWidth"));
		int dropHeight = Integer.parseInt(request.getParameter("txt_DropHeight"));
		float imageZoom = Float.parseFloat(request.getParameter("txt_Zoom"));
		String picture = request.getParameter("picture");
		
		System.out.println("imageWidth : "+imageWidth);
		System.out.println("imageHeight : "+imageHeight);
		System.out.println("cutTop : "+cutTop);
		System.out.println("cutLeft : "+cutLeft);
		System.out.println("dropWidth : "+dropWidth);
		System.out.println("dropHeight : "+dropHeight);
		System.out.println("imageZoom : "+imageZoom);
		System.out.println("picture : "+picture);
		System.out.println("url :"+request.getRealPath("")+"/uploads/UploadPhoto/"+picture);
		Rectangle rec = new Rectangle(cutLeft,cutTop,dropWidth,dropHeight);
		File file = new File(request.getRealPath("")+"/uploads/User/UserHeadImage/"+picture);
		//BufferedImage image = ImageIO.read(new File(request.getRealPath("")+"/UploadPhoto/"+picture));  
		saveSubImage(new File(request.getRealPath("")+"/uploads/UploadPhoto/"+picture),file,imageWidth,imageHeight,rec);
		response.sendRedirect("../uploadimage.jsp?Picurl="+picture+"&step=3");
	}
	
	private static void saveSubImage(File srcImageFile, File descDir,int width,int height,Rectangle rect) throws IOException   
	{   

		ImageHepler.cut(srcImageFile, descDir, width, height, rect);
	} 

}
