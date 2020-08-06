package web;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Product;
import dao.Cardao;
import dao.ProductDao;


@WebServlet("/InsertCarServlet.do")
public class InsertCarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Cardao c=new Cardao();
	ProductDao p=new ProductDao();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;utf-8");
		try {
		  String id=request.getParameter("id");
		  String op=request.getParameter("op");
		  @SuppressWarnings("unchecked")
		  Map<String, Object> user = (Map<String, Object>)
		  request.getSession().getAttribute("loginedUser");
		  Integer uid = (Integer) user.get("id");
		  if("1".equals(op)) {
			  List<Product>list=p.queryid(id);
			  for(Product product:list) {
				  String porductname=product.getProductname();
				  String specs=product.getSpecs();
				  Integer price=product.getPrice();
				  String price1=String.valueOf(price);
				  Integer count=1;
				  String prices=""+count*price;
				  String comunit=product.getSpecs();
				  String images=product.getImages();
				  c.insert(porductname, specs, price1, count, prices, comunit,uid,images);
				  response.getWriter().append("加入成功");
			  }  
		  }else if("2".equals(op)) {
			  String count=request.getParameter("count");
			  String prices=request.getParameter("prices");
			  Integer count1=Integer.valueOf(count);
			  List<Product>list=p.queryid(id);
			  for(Product product:list) {
				  String porductname=product.getProductname();
				  String specs=product.getSpecs();
				  Integer price=product.getPrice();
				  String price1=String.valueOf(price);
				  String comunit=product.getSpecs();
				  String images=product.getImages();
				  c.insert(porductname, specs, price1, count1, prices, comunit,uid,images);
				  response.getWriter().append("加入成功");
			  }  
		  }else if("3".equals(op)) {
			  List<Product>list=p.queryid(id);
			  for(Product product:list) {
				  String porductname=product.getProductname();
				  String specs=product.getSpecs();
				  int  price=product.getPrice();
				  //int price1=(int) (price*0.5);//打5折
				  String price2=""+price/2;
				  Integer price3=Integer.valueOf(price2);
				  Integer count=1;
				  String prices=""+count*price3;
				  String comunit=product.getSpecs();
				  String images=product.getImages();
				  c.insert(porductname, specs, price2, count, prices, comunit,uid,images);
				  response.getWriter().append("加入成功");
			  }  
		  }else if("4".equals(op)) {
			  List<Product>list=p.queryid(id);
			  for(Product product:list) {
				  String porductname=product.getProductname();
				  String specs=product.getSpecs();
				  int  price=product.getPrice();
				  //int price1=(int) (price*0.5);//打5折
				  String price2=""+price/2;
				  Integer price3=Integer.valueOf(price2);
				  Integer count=1;
				  String prices=""+count*price3;
				  String comunit=product.getSpecs();
				  String images=product.getImages();
				  c.insert(porductname, specs, price2, count, prices, comunit,uid,images);
				  response.getWriter().append("加入成功");
			  }  
		  }
		 
	  }catch(Exception e) {
		  e.printStackTrace();
		  response.getWriter().append("加入失败");
	  }
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
