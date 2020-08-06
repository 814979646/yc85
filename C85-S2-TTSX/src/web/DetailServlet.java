package web;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import bean.Product;
import dao.ProductDao;
import redis.clients.jedis.Jedis;


@WebServlet("/DetailServlet.do")
public class DetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ProductDao p=new ProductDao();
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;utf-8");
		@SuppressWarnings("unchecked")
		Map<String, Object> user = (Map<String, Object>) request.getSession().getAttribute("loginedUser");
		String uid = "" + user.get("id");
		String id=request.getParameter("id");
	    List<Product>list=p.queryid(id);
	    response.getWriter().append(new Gson().toJson(list));
	   
	    Jedis jd=new Jedis();
	    double uid1=Double.valueOf(uid);
	    jd.zadd("ttsx_detail", uid1, id);
	    jd.close();
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
