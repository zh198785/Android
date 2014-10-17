package com.itheima.ebook.web.servlet;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itheima.ebook.utils.gif.AnimatedGifEncoder;

public class VerifyCodeServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	//生成图片
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//1核心类
		AnimatedGifEncoder e = new AnimatedGifEncoder();
		//2设置输出位置--浏览器
		e.start(response.getOutputStream());
		//3设置图片显示的间隔时间，单位：毫秒
		e.setDelay(100);
		// 设置播放次数
		e.setRepeat(0);	//无限
		
		//4 写50张 干扰图片
		for(int i = 0 ; i < 20 ; i++){
			e.addFrame(getOther());  //不同图片
		}
		
		//5 
		BufferedImage data = getData(request);
		for(int i = 0 ; i < 30 ; i++){
			e.addFrame(data);
		}
		
		e.finish();
		
	}
	
	//获得验证码数据
	public BufferedImage getData(HttpServletRequest request){
		//0
		int width = 60;
		int height = 30;
		String data = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuvwxyz";
		Random random = new Random();

		//1 创建图片
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		
		//2获得画板
		Graphics g = image.getGraphics();
		
		//3 黑色矩形
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, width, height);
		
		//4 白色矩形
		g.setColor(Color.WHITE);
		g.fillRect(1, 1, width-2, height-2);
		
		/**#1 缓存随机4个字符*/
		StringBuilder builder = new StringBuilder();
		
		//5 文字--随机
		// * 字体
		g.setFont(new Font("宋体", Font.BOLD|Font.ITALIC, 20));
		for(int i = 0 ; i < 4; i ++){
			// 随机颜色
			g.setColor(new Color(random.nextInt(255),random.nextInt(255),random.nextInt(255)));  //#ff
			
			int index = random.nextInt(data.length());
			String str = data.substring(index, index + 1);  // [2,3)
			
			/**#2 添加*/
			builder.append(str);
			
			//画字符
			g.drawString(str, (width / 6) * (i + 1), 20);
		}
		
		/**#3 session缓存，服务器端缓存*/
		request.getSession().setAttribute("sessionCacheData", builder.toString());
		
		//5 干扰线(点)
		for(int i = 0 ; i < 5 ;i ++){
			// 随机颜色
			g.setColor(new Color(random.nextInt(255),random.nextInt(255),random.nextInt(255)));  //#ff
			
			// 线
			g.drawLine(random.nextInt(width), random.nextInt(height), random.nextInt(width),random.nextInt(height));
			// 点
			//g.drawOval(x, y, width, height)
		}
		
		return image;
	}
	
	//获得随机干扰图片
	public BufferedImage getOther(){
		//0
		int width = 60;
		int height = 30;
		Random random = new Random();

		//1 创建图片
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		
		//2获得画板
		Graphics g = image.getGraphics();
		
		//3 黑色矩形
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, width, height);
		
		//4 白色矩形
		g.setColor(Color.WHITE);
		g.fillRect(1, 1, width-2, height-2);
		
		//5 干扰线(点)
		for(int i = 0 ; i < 5 ;i ++){
			// 随机颜色
			g.setColor(new Color(random.nextInt(255),random.nextInt(255),random.nextInt(255)));  //#ff
			
			// 线
			g.drawLine(random.nextInt(width), random.nextInt(height), random.nextInt(width),random.nextInt(height));
			// 点
			//g.drawOval(x, y, width, height)
		}
		
		return image;
	}

}
