package web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import bean.Car;
import dao.Cardao;
import redis.clients.jedis.Jedis;

@WebServlet("/QueryPricesServlet.do")
public class QueryPricesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Cardao c = new Cardao();
	List<Integer>list=new ArrayList<>();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;utf-8");
		String id = request.getParameter("id");
			Integer id1 = Integer.valueOf(id);
			Jedis jd = new Jedis();
			if (list.indexOf(id1)==-1) {
				jd.sadd("ttsx_oid", id);
				list.add(id1);
			} else {
				jd.srem("ttsx_oid", id);
				list.remove(id1);
			}
			List<Car> list = c.queryprices(id);
			//jd.del("ttsx_oid");
			jd.close();
			response.getWriter().append(new Gson().toJson(list));
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
