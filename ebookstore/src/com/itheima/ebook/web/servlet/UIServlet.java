package com.itheima.ebook.web.servlet;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UIServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//1.1 获得路径前缀 ， 目录
		String servletPath = request.getServletPath();  // "/admin/UIServlet"
		String allPath[] = servletPath.split("/");
		if(allPath.length < 2){
			throw new  RuntimeException ("编写路径["+servletPath+"]存在使用问题");
		}
		String dirName = allPath[1];
		//1.2 获得参数n，jsp文件名  // ?n=top
		String fileName = request.getParameter("n");
		
		//2 请求转发到指定的jsp  /WEB-INF/pages/admin/top.jsp
		String jspPath = "/WEB-INF/pages/"+dirName+"/"+fileName+".jsp";
		request.getRequestDispatcher(jspPath).forward(request, response);
	}
	

}
