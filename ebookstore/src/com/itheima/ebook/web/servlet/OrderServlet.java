package com.itheima.ebook.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itheima.ebook.domain.Cart;
import com.itheima.ebook.domain.Order;
import com.itheima.ebook.domain.User;
import com.itheima.ebook.service.IBusinessService;
import com.itheima.ebook.web.BaseServlet;
import com.itheima.spring.BeanFactory;

public class OrderServlet extends BaseServlet {

	//下订单
	public void addOrder(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			
			//1 获得数据
			// 1.1 获得购物车 -- 获得需要购买的数据
			Cart cart = (Cart) request.getSession().getAttribute("cart");
			// 1.2 获得登录用户
			User loginUser = (User) request.getSession().getAttribute("loginUser");
			// * 确保用户登录
			if(loginUser == null){
				request.setAttribute("msg", "请登录之后购买");
				request.getRequestDispatcher("/WEB-INF/pages/user/login.jsp").forward(request, response);
				return;
			}
			
			//2 操作：下订单(保存订单)
			IBusinessService businessService = BeanFactory.getBean("businessServiceId",IBusinessService.class);
			Order order = businessService.addOrder(cart,loginUser);
			
			//3 提示：在线支付
			request.setAttribute("order", order);
			request.getRequestDispatcher("/WEB-INF/pages/user/pay.jsp").forward(request, response);
			
			
		} catch (Exception e) {
			e.printStackTrace();
			
			
		}
		
		
		
		
	}

}
