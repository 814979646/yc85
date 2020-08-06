package util;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import util.*;
import com.alipay.api.*;
import com.alipay.api.request.*;

import bean.Order;
import bean.Porder;
import bean.Product;
import bean.User;
import bean.Userinfo;
import dao.OrderDao;
import dao.PorderDao;
import dao.ProductDao;
import dao.UserDao;
import dao.UserinfoDao;
import redis.clients.jedis.Jedis;

/**
 * Servlet implementation class PayMoney
 */
@WebServlet("/payMoney.do")
public class PayMoney extends HttpServlet {
	private static final long serialVersionUID = 1L;
	OrderDao o = new OrderDao();
	PorderDao p = new PorderDao();
	ProductDao pd = new ProductDao();
	UserDao u = new UserDao();

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String oid = (String) request.getSession().getAttribute("oid");
		o.updateorder(oid, "已支付", "待发货");
		// 设置销量
		List<Porder> list = p.query(oid);
		int xl=0;
		for (Porder pp : list) {//订单详情
			List<Product> list1 = pd.queryxl(pp.getPname());
			for (Product pp1 : list1) {
				if(pp1.getXl()==null) {
					xl=0;
					xl++;
					pd.updatexl(Integer.valueOf(xl), String.valueOf(pp1.getId()));
				}else {
					xl=pp1.getXl();
					xl++;
					pd.updatexl(Integer.valueOf(xl), String.valueOf(pp1.getId()));
				}
			}
		}
		
		@SuppressWarnings("unchecked")
		Map<String, Object> user = (Map<String, Object>) request.getSession().getAttribute("loginedUser");
		String uid = "" + user.get("id");
		// 设置积分
		double socre1 = 0;
		double socre2 = 0;
		List<Order> plist = o.queryprices(oid);
		for (Order or : plist) {
			socre1 = Double.valueOf(or.getOrderprices());
		}
		List<User> ulist = u.query1(uid);
		for (User uu : ulist) {
			socre2 = Double.valueOf(uu.getTaojb());
		}
		double socre3 = socre1 + socre2;
		String socre4 = String.valueOf(socre3);
		u.updatatao(socre4, uid);

		 //设置会员
		int vipcount=p.count(oid);//查找买了几件商品 
		int count = 0;
		for (User uu : ulist) {
			if(uu.getCount()==null) {
				count = 0;
			}else {
				count = uu.getCount();
			}
		}
		int allcount=count+vipcount;
		u.updatacount(String.valueOf(allcount), uid);
		if (allcount >= 10) {
			u.updata("白银会员", uid);
		} else if (allcount >= 30) {
			u.updata("黄金会员", uid);
		} else if (allcount >= 60) {
			u.updata("铂金会员", uid);
		} else if (allcount >= 100) {
			u.updata("钻石会员", uid);
		} else if (allcount >= 500) {
			u.updata("superVIP", uid);
		}

		// 获得初始化的AlipayClient
		AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id,
				AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key,
				AlipayConfig.sign_type);
		// 设置请求参数
		AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
		alipayRequest.setReturnUrl(AlipayConfig.return_url);
		alipayRequest.setNotifyUrl(AlipayConfig.notify_url);
		// 商户订单号，商户网站订单系统中唯一订单号，必填
		String out_trade_no = request.getParameter("WIDout_trade_no");
		// 付款金额，必填
		String total_amount = request.getParameter("WIDtotal_amount");
		// 订单名称，必填
		String subject = request.getParameter("WIDsubject");
		// 商品描述，可空
		String body = request.getParameter("WIDbody");
		alipayRequest.setBizContent("{\"out_trade_no\":\"" + out_trade_no + "\"," + "\"total_amount\":\"" + total_amount
				+ "\"," + "\"subject\":\"" + subject + "\"," + "\"body\":\"" + body + "\","
				+ "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");

		// 请求
		String result;
		try {
			result = alipayClient.pageExecute(alipayRequest).getBody();
			response.setContentType("text/html;charset=" + AlipayConfig.charset);
			response.getWriter().write(result);// 直接将完整的表单html输出到页面
			response.getWriter().flush();
			response.getWriter().close();
		} catch (AlipayApiException e) {
			e.printStackTrace();
			response.getWriter().write("捕获异常出错");
			response.getWriter().flush();
			response.getWriter().close();
		}
	}
}
