package com.itheima.ebook.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itheima.ebook.domain.User;
import com.itheima.ebook.service.IBusinessService;
import com.itheima.ebook.utils.MyBeanUtils;
import com.itheima.ebook.web.BaseServlet;
import com.itheima.spring.BeanFactory;

public class UserServlet extends BaseServlet {

	/*
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//1 获得需要执行方法名称
		String method = request.getParameter("method");
		//2 判断
		if("register".equals(method)){
			register(request, response);
		} else if("login".equals(method)){
			login(request, response);
		} else if ("logout".equals(method)) {
			logout(request, response);
		} else {
			throw new RuntimeException("当前类["+this+"]，没有提供此方法["+method+"]");
		}
	}
	*/
	
	//用户注册
	public void register(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			//1 获得数据并封装
			User user = MyBeanUtils.populate(User.class, request.getParameterMap());
			
			//TODO 1.1 服务器端数据校验 -- 密码校验
			
			//1.2 一次性验证码
			//** 获得服务器端session缓存数据
			String sessionCacheData = (String) request.getSession().getAttribute("sessionCacheData");
			//** 获得用户提交
			String verifyCode = request.getParameter("verifyCode");
			//** 将session中缓存的数据移除，保证只使用一次
			request.getSession().removeAttribute("sessionCacheData");
			//** 处理
			if(sessionCacheData == null){
				request.setAttribute("msg", "请不要重复提交");
				request.getRequestDispatcher("/WEB-INF/pages/user/register.jsp").forward(request, response);
				return;
			}
			if(! sessionCacheData.equalsIgnoreCase(verifyCode)){
				request.setAttribute("msg", "验证码不正确");
				request.getRequestDispatcher("/WEB-INF/pages/user/register.jsp").forward(request, response);
				return;
			}
			
			//2 操作：注册
//			IBusinessService businessService = new BusinessServiceImpl();
			IBusinessService businessService = BeanFactory.getBean("businessServiceId", IBusinessService.class);
			businessService.register(user);
			
			//3 成功提交
			request.setAttribute("msg", "注册成功，请登录");
			request.getRequestDispatcher("/WEB-INF/pages/user/message.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			
			//错误提示
			request.setAttribute("msg", "注册没有成功，请稍后重试");
			request.getRequestDispatcher("/WEB-INF/pages/user/message.jsp").forward(request, response);
		}
	}
	//用户登录
	public void login(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			//1 获得请求参数，并封装
			User user = MyBeanUtils.populate(User.class, request.getParameterMap());
			
			//2 操作：登录
//			IBusinessService businessService = new BusinessServiceImpl();
			IBusinessService businessService = BeanFactory.getBean("businessServiceId", IBusinessService.class);
			User loginUser = businessService.login(user);
			
			//3提示
			if(loginUser != null){
				//成功
				request.getSession().setAttribute("loginUser", loginUser);
				response.sendRedirect(request.getContextPath() + "/index.jsp");
			} else {
				//密码不对
				request.setAttribute("msg", "用户名和密码不匹配");
				request.getRequestDispatcher("/WEB-INF/pages/user/login.jsp").forward(request, response);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace()	;
			//不可预测
			request.setAttribute("msg", "登录不成功，服务器繁忙，请稍后重试");
			request.getRequestDispatcher("/WEB-INF/pages/user/message.jsp").forward(request, response);
		}
	}
	//用户注销
	public void logout(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//将session用户登录状态给移除
		request.getSession().removeAttribute("loginUser");
		response.sendRedirect(request.getContextPath() + "/index.jsp");
	}

}
