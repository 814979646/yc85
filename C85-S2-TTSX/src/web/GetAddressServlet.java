package web;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import redis.clients.jedis.Jedis;


@WebServlet("/GetAddressServlet.do")
public class GetAddressServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;utf-8");
		@SuppressWarnings("unchecked")
		Map<String, Object> user = (Map<String, Object>)
		request.getSession().getAttribute("loginedUser");
		String uid = "" + user.get("id");
		Jedis jd=new Jedis("127.0.0.1",6379);
		Double address=jd.zscore("ttsx_address", uid);
		String aid=String.valueOf(address);
		jd.close();
		response.getWriter().append(aid);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
