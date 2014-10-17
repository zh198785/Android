package com.itheima.ebook.web.ext;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AutoClock extends HttpServlet {


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int diameter = 100; //直径
		int radius = diameter / 2; //半径
		
		//生成图片
		BufferedImage image = new BufferedImage(diameter, diameter, BufferedImage.TYPE_INT_RGB);
		Graphics2D g = (Graphics2D) image.getGraphics();
		
		//矩形边框 
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, diameter, diameter);
//		g.setColor(Color.WHITE);
//		g.fillRect(1, 1, diameter-2, diameter-2);
		
		//绘制表框
		g.setColor(Color.ORANGE);
		g.fillOval(0, 0, diameter, diameter);
		g.setColor(Color.WHITE);
		g.fillOval(1, 1, diameter-2, diameter-2);
		
		//中间画一个圆点 
		int midDiameter = 10;
		int midRadius = 5;
		g.setColor(Color.YELLOW);
		g.fillOval( (diameter - midDiameter) / 2, (diameter - midDiameter) / 2, midDiameter, midDiameter);

		//画数字 1 - 12
		g.setColor(Color.BLACK);
		int interDiameter = diameter - 20;//内部直径
		int interRadius =  interDiameter / 2;
		g.setFont(new Font("arial",Font.PLAIN,15));
		for(int i = 1 ; i <= 12 ; i ++){
			//数字坐标
			Double x = interRadius + (Math.sin( (30 * i) * Math.PI / 180 ) * interRadius);
			Double y = interRadius - Math.cos( (30 * i) * Math.PI / 180 )* interRadius;
			
//			g.rotate((30 * i) * Math.PI / 180, x, y);
			g.drawString(String.valueOf(i), x.intValue() + 7, y.intValue() + 17);
//			g.rotate( (-30 * i) * Math.PI / 180, x, y);
			
		}
		Date date = new Date();
		
		//秒针
		int secondRadius = radius - 10; //秒针半径
		int second = date.getSeconds(); //获得当前秒
		int secondx1 = radius;
		int secondy1 = radius;
		Double secondx2 = radius + Math.sin( second * Math.PI / 30 ) * secondRadius;
		Double secondy2 = radius - Math.cos( second * Math.PI / 30 ) * secondRadius;
		g.setColor(Color.BLUE);
		g.drawLine(secondx1, secondy1, secondx2.intValue(), secondy2.intValue());
		
		
		//分针
		int minuteRadius = radius - 15; //分针半径
		int minute = date.getMinutes(); //获得当前分钟
		int minutex1 = radius;
		int minutey1 = radius;
		Double minutex2 = radius + Math.sin( minute * Math.PI / 30 ) * minuteRadius;
		Double minutey2 = radius - Math.cos( minute * Math.PI / 30 ) * minuteRadius;
		g.setColor(Color.GRAY);
		g.drawLine(minutex1, minutey1, minutex2.intValue(), minutey2.intValue());
		
		//时针
		int hourRadius = radius - 15; //小时半径
		int hour = date.getHours(); //获得当前小时
		int hourx1 = radius;
		int houry1 = radius;
		Double hourx2 = radius + Math.sin( hour * Math.PI / 6 ) * hourRadius;
		Double houry2 = radius - Math.cos( hour * Math.PI / 6 ) * hourRadius;
		g.setColor(Color.RED);
		g.drawLine(hourx1, houry1, hourx2.intValue(), houry2.intValue());
		
		//毫秒
		int timezoneRadius = radius - 25; //秒针半径
		int timezone = new Long(date.getTime() % 1000).intValue(); //获得当前秒
		System.out.println(timezone);
		int timezonex1 = radius;
		int timezoney1 = radius;
		Double timezonex2 = radius + Math.sin( timezone * Math.PI / 500 ) * timezoneRadius;
		Double timezoney2 = radius - Math.cos( timezone * Math.PI / 500 ) * timezoneRadius;
		g.setColor(Color.BLUE);
		g.drawLine(timezonex1, timezoney1, timezonex2.intValue(), timezoney2.intValue());
		
		g.dispose();
		image.flush();
		
		//自动跳转
		//response.setHeader("refresh", "1");
		response.setContentType("image/jpeg");
		response.setHeader("pragma", "no-cache");
		response.setHeader("cache-control", "no-cache");
		response.setHeader("expires", "0");
		//需要将图片发送到浏览器
		ImageIO.write(image, "jpeg", response.getOutputStream());

	}

}
