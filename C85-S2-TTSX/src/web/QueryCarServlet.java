package web;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import bean.Car;
import dao.Cardao;

@WebServlet("/QueryCarServlet.do")
public class QueryCarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Cardao c = new Cardao();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;utf-8");
		@SuppressWarnings("unchecked")
		Map<String, Object> user = (Map<String, Object>) request.getSession().getAttribute("loginedUser");
		Integer uid = (Integer) user.get("id");
		String uid1=""+user.get("id");
		List<Car>list=c.query(uid);
		int count=c.count(uid1);
		Map<String,Object>map=new HashMap<>();
		map.put("list", list);
		map.put("count", count);
		response.getWriter().append(new Gson().toJson(map));

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
