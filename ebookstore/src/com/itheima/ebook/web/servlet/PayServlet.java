package com.itheima.ebook.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itheima.ebook.utils.PaymentUtil;

public class PayServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	//为第三方 准备参数
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//1获得实际值
		// * 订单号
		String orderId = request.getParameter("orderId");
		// * 价格
		String amount = request.getParameter("amount");
		// * 支付银行
		String frpId = request.getParameter("frpId");
		
		//2准备参数
		// 2.1 业务类型
		String p0_Cmd = "Buy";
		// 2.2 商户编号 -- 注册账号
		String p1_MerId = "10001126856";
		// 2.3商户订单号  -- 单号必须在自身账户交易中唯一
		String p2_Order = orderId;
		// 2.4 支付金额
		String  p3_Amt = amount;
		// 2.5交易币种
		String p4_Cur = "CNY" ;
		// 2.6 商品名称
		String p5_Pid = "itheima";
		// 2.7商品种类
		String p6_Pcat = "";
		// 2.8 商品描述
		String p7_Pdesc = "";
		// 2.9 商户接收支付成功数据的地址  --最后从第三方回到网站的地址
		String  p8_Url = "http://localhost:8080/day20_pay/CallbackServlet";
		// 2.10 送货地址
		String p9_SAF = "0";
		// 2.11商户扩展信息
		String pa_MP = "";
		// 2.12 支付通道编码 -- 选择银行在第三方公司的编号
		String pd_FrpId = frpId;
		// 2.13 应答机制 -- 必须提供外网IP地址(当前唯一)
		String pr_NeedResponse = "1";
		
		//#####第三方提供的加密key值
		String keyValue = "69cl522AV6q613Ii4W6u8K6XuW8vM1N6bFgyv769220IuYe9u37N4y7rI4Pl";
		
		// 2.14  签名数据 , 将以上13个参数进行加密结果
		String hmac = PaymentUtil.buildHmac(p0_Cmd, p1_MerId, p2_Order, p3_Amt, p4_Cur, p5_Pid, p6_Pcat, p7_Pdesc, p8_Url, p9_SAF, pa_MP, pd_FrpId, pr_NeedResponse, keyValue);
		
		
		
		//3重定向到第三方
		String url = "https://www.yeepay.com/app-merchant-proxy/node" +
					"?p0_Cmd=" + p0_Cmd +
					"&p1_MerId=" + p1_MerId+
					"&p2_Order=" + p2_Order+
					
					"&p3_Amt=" + p3_Amt+
					"&p4_Cur=" + p4_Cur+
					"&p5_Pid=" + p5_Pid+
					
					"&p6_Pcat=" + p6_Pcat+
					"&p7_Pdesc=" + p7_Pdesc+
					"&p8_Url=" + p8_Url+
					
					"&p9_SAF=" + p9_SAF+
					"&pa_MP=" + pa_MP+
					"&pd_FrpId=" + pd_FrpId+
					
					"&pr_NeedResponse=" + pr_NeedResponse+
					"&hmac=" + hmac;
		
		response.sendRedirect(url);
		
	}

}
