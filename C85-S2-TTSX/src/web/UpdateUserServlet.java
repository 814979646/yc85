package web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;

@WebServlet("/UpdateUserServlet.do")
public class UpdateUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UserDao u=new UserDao();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;utf-8");
		try {
			String id=request.getParameter("id");
			String uname=request.getParameter("uname");
			String email=request.getParameter("email");
			String head=request.getParameter("head");
			String name=request.getParameter("name");
			String phone=request.getParameter("phone");
			String address=request.getParameter("address");
			String postal=request.getParameter("postal");
			u.update(id, uname, email, head);
			u.updateinfo(id, name, phone, address, postal);
			response.getWriter().append("修改成功");
		}catch(Exception e) {
			e.printStackTrace();
			response.getWriter().append("修改失败");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
