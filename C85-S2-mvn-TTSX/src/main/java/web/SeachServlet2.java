package web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import bean.Product;
import dao.ProductDao;

@WebServlet("/SeachServlet2.do")
public class SeachServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ProductDao p = new ProductDao();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;utf-8");
		String productname = (String) request.getSession().getAttribute("productname");
		System.out.println(productname);
		List<Product> list = p.seach(productname);
		response.getWriter().append(new Gson().toJson(list));
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
