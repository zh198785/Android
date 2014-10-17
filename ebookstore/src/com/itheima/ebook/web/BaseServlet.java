package com.itheima.ebook.web;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * BaseServlet 为抽象类，可以保证不能再web.xml进行注册，从而进行实例化
 * @author lt
 *
 */
public abstract class BaseServlet extends HttpServlet {

	//final 保证 子类不能复写此方法
	public final void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public final void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String methodName = null;
		try {
			//1 获得请求参数，确定执行的方法
			methodName = request.getParameter("method");
			// * 默认值
			if(methodName == null){
				methodName = "execute";
			}
			//2 使用反射从当前运行类中获得指定的方法
			Method executeMethod = this.getClass().getMethod(methodName, HttpServletRequest.class,
					HttpServletResponse.class);
			//3 反射执行
			executeMethod.invoke(this, request,response);
		} catch (NoSuchMethodException e) {
			throw new RuntimeException("您请求的方法["+methodName+"]存在");
		} catch (Exception e){
			throw new RuntimeException(e);
		}
		
	}
	
	/**
	 * 默认方法，提供给子类复写
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}
