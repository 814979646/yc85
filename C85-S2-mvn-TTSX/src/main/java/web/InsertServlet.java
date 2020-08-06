package web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import bean.Product;
import dao.ProductDao;


@WebServlet("/InsertServlet.do")
public class InsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ProductDao p = new ProductDao();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;utf-8");
		Product dp=new Product();
		
			try {
				BeanUtils.populate(dp, request.getParameterMap());
				//数据验证
				if(dp.getProductname()==null || dp.getProductname().trim().isEmpty()) {
					response.getWriter().append("商品名称不能为空");	 
					return;
				}
				if(dp.getPrice()<0) {
					response.getWriter().append("商品价格必须大于0");	 
					return;
				}
				p.insert(dp);
				response.getWriter().append("增加成功");	
			} catch (Exception e) {
				e.printStackTrace();
			} 
			
		/*
		 * String productname = request.getParameter("productname"); String price =
		 * request.getParameter("price"); String cid = request.getParameter("cid");
		 * String specs = request.getParameter("specs"); String images =
		 * request.getParameter("images"); String products =
		 * request.getParameter("products"); String productinfo =
		 * request.getParameter("productinfo"); p.insert(productname, price, cid, specs,
		 * images, products,productinfo);
		 */
			
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
