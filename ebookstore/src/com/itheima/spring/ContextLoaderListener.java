package com.itheima.spring;

import java.io.InputStream;
import java.util.List;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * spring 框架在服务器启动时，进行解析配置文件监听器
 * @author lt
 *
 */
public class ContextLoaderListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {

		//1 获得配置文件位置  -- 通过web项目的初始化参数
		// * 获得初始化参数
		String contextConfigLocation = sce.getServletContext().getInitParameter("contextConfigLocation");
		// * 处理文件,获得资源流
		InputStream is = null;
		if(contextConfigLocation.startsWith("classpath:")){
			//src
			String filename = contextConfigLocation.substring("classpath:".length());
			is = ContextLoaderListener.class.getClassLoader().getResourceAsStream(filename);
		} else {
			//WEB-INF
			is = sce.getServletContext().getResourceAsStream("/WEB-INF/" + contextConfigLocation);
		}
		
		//2 解析 -- 并添加到BeanFactory
		parse(is);
		
	}

	/**
	 * 解析xml(dom4j)，并将数据封装到Bean对象，添加到BeanFactory中
	 * 
	 * @param is
	 */
	private void parse(InputStream is) {
		try {
			//1 获得document
			SAXReader saxReader = new SAXReader();
			Document document = saxReader.read(is);
			
			//2操作document
			// 2.1 获得根元素
			Element rootElement = document.getRootElement();
			// 2.2 获得所有<bean>元素
			List<Element> allBeanElement = rootElement.elements("bean");
			// 2.3 遍历
			for(Element beanElement :allBeanElement){
				// 2.4 获得所有的属性
				// * id属性
				String beanId = beanElement.attributeValue("id");
				// * class属性
				String beanClass = beanElement.attributeValue("class");
				// * scope属性
				String beanScope = beanElement.attributeValue("scope");
				
				// 2.5 封装到Bean对象中
				Bean bean = new Bean(beanId, beanClass, beanScope);
				
				/**添加所有<property> start*/
				// * 获得所有的子元素<property>
				List<Element> allPropElement = beanElement.elements("property");
				// * 遍历所有子元素
				for(Element propElement : allPropElement){
					// * name属性
					String propName = propElement.attributeValue("name");
					// * ref属性
					String propRef = propElement.attributeValue("ref");
					// * value属性
					String propValue = propElement.attributeValue("value");
					// * 将获得数据封装到自定义Property对象
					Property prop = new Property(propName, propRef, propValue);
					// * 添加到Bean对象提供List集合中
					bean.getAllProp().add(prop);
				}
				
				/**添加所有<property> end*/
				
				
				// 2.6 外部赋值
				BeanFactory.getDataMap().put(beanId, bean);
			}
			
			
			//3写入document
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {

	}

}
