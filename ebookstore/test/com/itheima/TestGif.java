package com.itheima;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.itheima.ebook.utils.gif.AnimatedGifEncoder;

public class TestGif {
	
	public static void main(String[] args) throws IOException {
		//1核心类
		AnimatedGifEncoder e = new AnimatedGifEncoder();
		//2设置输出位置
		e.start("1.gif");
		//3设置图片显示的间隔时间，单位：毫秒
		e.setDelay(100);
		// 设置播放次数
		e.setRepeat(0);	//无限
		
		String dirStr = "D:\\other\\高清\\rihan\\20120330133737";
		File dir = new File(dirStr);
		File[] allFile = dir.listFiles();
		for(File f : allFile){
			e.addFrame(ImageIO.read(f));
			System.out.println(f);
		}
		
		e.finish();
		
		
	}

}
