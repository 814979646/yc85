package web;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.User;
import dao.OrderDao;
import dao.UserDao;


@WebServlet("/OrderDataServlet.do")
public class OrderDataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    UserDao u=new UserDao();
	OrderDao o=new OrderDao();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;utf-8");
		try {
			@SuppressWarnings("unchecked")
			Map<String, Object> user = (Map<String, Object>) request.getSession().getAttribute("loginedUser");
			String uid = "" + user.get("id");
			
			String id = request.getParameter("id");
			String ordernum = request.getParameter("ordernum");
			String name = request.getParameter("name");
			String address = request.getParameter("address");
			String phone = request.getParameter("phone");
			String status = request.getParameter("status");
			String status1 = request.getParameter("status1");
			System.out.println(status1);
			String orderprices = request.getParameter("orderprices");
			//淘金币减少
			if(status1.equals("已退款")) {
				List<User>list=u.query1(uid);
				double oldt=0;
				double news=Double.valueOf(orderprices);
				for(User uu:list) {
					oldt=Double.valueOf(uu.getTaojb());
				}
				String taojb=String.valueOf((oldt-news));
				u.updatercash(taojb, uid);
			}
			
	       o.save(ordernum, name, address, phone, status, status1,orderprices,  id);
		response.getWriter().append("修改成功");
		}catch(Exception e) {
			e.printStackTrace();
			response.getWriter().append("修改失败");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
