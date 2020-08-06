package web;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import redis.clients.jedis.Jedis;


@WebServlet("/SaveAddressRedisServlet.do")
public class SaveAddressRedisServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;utf-8");
		@SuppressWarnings("unchecked")
		Map<String, Object> user = (Map<String, Object>)
		request.getSession().getAttribute("loginedUser");
		String uid = "" + user.get("id");
		String address=request.getParameter("address");
		double aid=Double.valueOf(address);
		System.out.println("uid===="+uid);
		System.out.println("aid===="+aid);
		Jedis jd=new Jedis("127.0.0.1",6379);
		jd.zadd("ttsx_address", aid, uid);
		jd.close();
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
