package web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Order;
import bean.Product;
import dao.OrderDao;
import dao.ProductDao;


@WebServlet("/LastServlet.do")
public class LastServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
       OrderDao o=new OrderDao();
       ProductDao p=new ProductDao();
    
	protected void queryorder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;utf-8");
		String ordernum=request.getParameter("ordernum");
	    request.getSession().setAttribute("recash_num", ordernum);
	    List<Order>list=o.queryordernum(ordernum);
	    	if(list.size()==0) {
	    		response.getWriter().append("你输入的订单号不存在，请重新输入");
	    	}else if(list.size()!=0){
	    		for(Order od:list) {
	    			if(od.getStatus1().equals("已评价")) {
	    				response.getWriter().append("该订单不可进行该操作");
	    			}else {
	    				response.getWriter().append("1");	
	    			}
	    		}
	    	}
		
	}
	protected void queryordernum(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;utf-8");
		String ordernum=(String) request.getSession().getAttribute("recash_num");
		List<Order>list=o.queryordernum(ordernum);
		print(response,list);
		
	}
	protected void querynew(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;utf-8");
		List<Product>list=p.queryallnew();
		print(response,list);
	}
	protected void closeLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;utf-8");
		request.getSession().removeAttribute("loginedUser");//清除会话
		
	}
	

}
