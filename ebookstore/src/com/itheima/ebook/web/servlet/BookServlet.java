package com.itheima.ebook.web.servlet;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;

import com.itheima.ebook.domain.Book;
import com.itheima.ebook.domain.Cart;
import com.itheima.ebook.domain.Category;
import com.itheima.ebook.domain.Page;
import com.itheima.ebook.service.IBusinessService;
import com.itheima.ebook.utils.MyBeanUtils;
import com.itheima.ebook.utils.StringUtils;
import com.itheima.ebook.web.BaseServlet;
import com.itheima.spring.BeanFactory;

public class BookServlet extends BaseServlet {

	/*
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String method = request.getParameter("method");
		if("addUI".equals(method)){
			this.addUI(request, response);
		} else if("add".equals(method)){
			this.add(request, response);
		} else if("findAllWithCategory".equals(method)){
			this.findAllWithCategory(request, response);
		} else if("addBookToCart".equals(method)){
			this.addBookToCart(request, response);
		}  else if("removeBookToCart".equals(method)){
			this.removeBookToCart(request, response);
		} else {
			throw new RuntimeException("当前类["+this+"]，没有提供此方法["+method+"]");
		}
	}
	*/
	//移除
	public void removeBookToCart(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			//1 获得参数
			// * 书籍id
			String bookId = request.getParameter("bookId");
			// * 获得购物车,存放session
			Cart cart = (Cart) request.getSession().getAttribute("cart");
			// *** 第一次
			if(cart == null){
				// 创建购物车
				cart = new Cart();
				// 存放到session
				request.getSession().setAttribute("cart", cart);
			}
			
			//2 添加到购物车
			IBusinessService businessService = (IBusinessService) BeanFactory.getBean("businessServiceId");
			businessService.removeBookToCart(bookId,cart);
			
			//3 显示购物车 -- 重定向显示jsp页面
			String url = request.getContextPath() + "/user/UIServlet?n=cart_show";
			response.sendRedirect(url);
			
		} catch (Exception e) {
			e.printStackTrace();
			
			request.setAttribute("msg", "添加购物车不成功，请稍后重试");
			request.getRequestDispatcher("/WEB-INF/pages/admin/message.jsp").forward(request, response);
		}
		
		
	}
	//添加书籍到购物车
	public void addBookToCart(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			//1 获得参数
			// * 书籍id
			String bookId = request.getParameter("bookId");
			// * 获得购物车,存放session
			Cart cart = (Cart) request.getSession().getAttribute("cart");
			// *** 第一次
			if(cart == null){
				// 创建购物车
				cart = new Cart();
				// 存放到session
				request.getSession().setAttribute("cart", cart);
			}
			
			//2 添加到购物车
			IBusinessService businessService = (IBusinessService) BeanFactory.getBean("businessServiceId");
			businessService.addBookToCart(bookId,cart);
			
			//3 显示购物车 -- 重定向显示jsp页面
			String url = request.getContextPath() + "/user/UIServlet?n=cart_show";
			response.sendRedirect(url);
			
		} catch (Exception e) {
			e.printStackTrace();
			
			request.setAttribute("msg", "添加购物车不成功，请稍后重试");
			request.getRequestDispatcher("/WEB-INF/pages/admin/message.jsp").forward(request, response);
		}
		
		
	}
	
	// 显示添加页面
	public void addUI(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//2 操作：查询所有分类
		IBusinessService businessService = BeanFactory.getBean("businessServiceId",IBusinessService.class);
		List<Category> allCategory = businessService.findAllCategory();
		
		//3 显示jsp页面
		request.setAttribute("allCategory", allCategory);
		request.getRequestDispatcher("/WEB-INF/pages/admin/book_add.jsp").forward(request, response);
	}
	
	// 添加书籍
	public void add(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//0判断
		if(! ServletFileUpload.isMultipartContent(request)){
			throw new RuntimeException("从表单提交");
		}
		File file = null; //上传文件
		try {
			//1 获得数据并封装
			Book book = new Book();
			//##上传内容的数据封装
			//#1.1 工厂
			DiskFileItemFactory fileItemFactory = new DiskFileItemFactory();
			//#1.2 核心类
			ServletFileUpload servletFileUpload = new ServletFileUpload(fileItemFactory);
			//#1.3 解析获得所有内容
			List<FileItem> allFileItem = servletFileUpload.parseRequest(request);
			//#1.4 遍历
			for(FileItem fi : allFileItem){
				// #1.5 判断是否普通字段
				if(fi.isFormField()){
					// #1.6 普通字段
					// * 字段名称
					String fieldName = fi.getFieldName();
					// * 字段值
					String fieldValue = fi.getString("UTF-8");
					/**### 将普通的字段内置封装到Book对象*/ 
					BeanUtils.setProperty(book, fieldName, fieldValue);
				} else {
					// #1.7 上传字段
					// #1.7.1 获得文件名称
					String fileName = fi.getName();
					// #### 处理浏览器兼容
					fileName = fileName.substring(fileName.lastIndexOf("\\") + 1);
					// #1.7.2 扩展名  (自己完成，校验)
					String extName = fileName.substring(fileName.lastIndexOf("."));
					// #1.7.3 新文件名称 , 32.jpg
					String newFileName = StringUtils.getUUID() + extName;
					
					// #1.7.4 子目录  /4/5
					String subDir = StringUtils.getDir(newFileName);
					
					// #1.7.5 文件位置
					File parentDir = new File(this.getServletContext().getRealPath("/upload"),subDir);
					file = new File(parentDir,newFileName);
					
					// #1.7.6 写入
					FileUtils.copyInputStreamToFile(fi.getInputStream(), file);
					
					// #1.7.7 删除临时文件
					fi.delete();
					
					/**封装 上传文件路径 , 例如： /upload/4/5/uuid.jpg **/
					book.setImgUrl("/upload" + subDir + "/" + newFileName);
				}
			}
			
			//2 操作：添加
			IBusinessService businessService = BeanFactory.getBean("businessServiceId",IBusinessService.class);
			businessService.addBook(book);
			
			//3 成功提示
			request.setAttribute("msg", "书籍添加成功");
			request.getRequestDispatcher("/WEB-INF/pages/admin/message.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			
			//判断，如果上传成功，删除文件
			if(file != null && file.exists()){
				file.delete();
			}
			
			request.setAttribute("msg", "书籍没有添加成功，请稍后重试");
			request.getRequestDispatcher("/WEB-INF/pages/admin/message.jsp").forward(request, response);
		}
	}
	
	// 查询指定分类的所有书籍
	public void findAllWithCategory(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			//1 获得请求参数
			// 1.1 分类的id , 如果没有指定分类，默认使用第一个分类id
			String categoryId = request.getParameter("categoryId");
			// 1.2 查询条件 (缺校验)
			Book conditionBook = MyBeanUtils.populate(Book.class, request.getParameterMap());
			// 1.3 分页
			String pageNumStr = request.getParameter("pageNum");
			int pageNum = 1;
			try {
				pageNum = Integer.parseInt(pageNumStr);
			} catch (Exception e) {
			}
			int pageSize = 2;  //固定值
			//2 操作
			IBusinessService businessService = BeanFactory.getBean("businessServiceId", IBusinessService.class);
			// 2.1 查询所有的分类
			List<Category> allCategory = businessService.findAllCategory();
			// **** 获得第一个分类的id
			if(categoryId == null){
				if(allCategory != null && allCategory.size() > 0){
					categoryId = allCategory.get(0).getId();
				}
			}
			// 2.2 查询指定分类的书籍,带有条件
			//List<Book> allBook = businessService.findAllBookWithCategory(categoryId,conditionBook);
			Page<Book> page = businessService.findAllBookWithCategory(categoryId, conditionBook, pageNum, pageSize);
			
			//3 显示
			request.setAttribute("allCategory", allCategory);		//所有分类
			request.setAttribute("page", page);						//指定分类的所有书籍,分页
			request.setAttribute("categoryId", categoryId);			//当前分类
			request.setAttribute("conditionBook", conditionBook);	//查询的条件
			request.getRequestDispatcher("/WEB-INF/pages/user/book_findAllWithCategory.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			
			request.setAttribute("msg", "查询没有成功，请稍后重试");
			request.getRequestDispatcher("/WEB-INF/pages/user/message.jsp").forward(request, response);
		}
	}

}
