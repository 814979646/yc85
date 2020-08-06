package web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Cardao;


@WebServlet("/UpdateCarServlet.do")
public class UpdateCarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Cardao c=new Cardao();
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;utf-8");
		try {
		String id1=request.getParameter("id");
		String count1=request.getParameter("count");
		String price1=request.getParameter("price");
		Integer count=Integer.valueOf(count1);
		Integer price=Integer.valueOf(price1);
		Integer id=Integer.valueOf(id1);
		String prices=""+count*price;
		c.update(id, count, prices);
	}catch(Exception e) {
		e.printStackTrace();
	}
	
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
