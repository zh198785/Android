package com.itheima.ebook.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itheima.ebook.domain.Category;
import com.itheima.ebook.service.IBusinessService;
import com.itheima.ebook.utils.MyBeanUtils;
import com.itheima.ebook.web.BaseServlet;
import com.itheima.spring.BeanFactory;

public class CategoryServlet extends BaseServlet {

	/*
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//1 需要指定的方法名称
		String method = request.getParameter("method");
		//2 判断需要执行的具体功能
		if("add".equals(method)){
			add(request,response);
		} else if("findAll".equals(method)){
			findAll(request,response);
		} else {
			throw new RuntimeException("当前类["+this+"]，没有提供此方法["+method+"]");
		}
	}
	*/

	//查询所有
	public void findAll(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			//1 
			
			//2 操作：查询所有
//			IBusinessService businessService = new BusinessServiceImpl();
			IBusinessService businessService = BeanFactory.getBean("businessServiceId", IBusinessService.class);
			List<Category> allCategory = businessService.findAllCategory();
			
			//3 显示
			request.setAttribute("allCategory", allCategory);
			request.getRequestDispatcher("/WEB-INF/pages/admin/category_show_all.jsp").forward(request, response);
		} catch (Exception e) {
			//日志记录
			e.printStackTrace();
			//错误提示
			request.setAttribute("msg", "查询分类异常，请重试");
			request.getRequestDispatcher("/WEB-INF/pages/admin/message.jsp").forward(request, response);
		}
		
	}

	//添加
	public void add(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		try {
			//1 获得数据并封装
			Category category = MyBeanUtils.populate(Category.class, request.getParameterMap());
			
			//2 操作：添加
//			IBusinessService businessService = new BusinessServiceImpl();
			IBusinessService businessService = BeanFactory.getBean("businessServiceId", IBusinessService.class);
			businessService.addCategory(category);
			
			//3成功提示
			request.setAttribute("msg", "添加分类成功");
			request.getRequestDispatcher("/WEB-INF/pages/admin/message.jsp").forward(request, response);
		} catch (Exception e) {
			//日志记录
			e.printStackTrace();
			//错误提示
			request.setAttribute("msg", "分类没有添加成功，请重试");
			request.getRequestDispatcher("/WEB-INF/pages/admin/message.jsp").forward(request, response);
		}
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	

}
