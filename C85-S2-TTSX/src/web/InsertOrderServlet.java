package web;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Order;
import dao.OrderDao;

@WebServlet("/InsertOrderServlet.do")
public class InsertOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	OrderDao o = new OrderDao();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;utf-8");
		try {
			@SuppressWarnings("unchecked")
			Map<String, Object> user = (Map<String, Object>) request.getSession().getAttribute("loginedUser");
			String uid = "" + user.get("id");
			String orderprices=request.getParameter("orderprices");
			o.insert(null, orderprices, uid, null);
			List<Order>list=o.query();
			for(Order o1:list) {
				Integer oid=o1.getId();
				String id=String.valueOf(oid);
				response.getWriter().append(id);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
