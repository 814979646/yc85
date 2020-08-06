package web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserinfoDao;

@WebServlet("/UpdataUserinfoServlet.do")
public class UpdataUserinfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UserinfoDao u = new UserinfoDao();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;utf-8");
		try {
			String name = request.getParameter("name");
			String address = request.getParameter("address");
			String postal = request.getParameter("postal");
			String phone = request.getParameter("phone");
			String id = request.getParameter("id");
			u.updata(name, address, postal, phone, id);
			response.getWriter().append("修改成功");
		} catch (Exception e) {
			e.printStackTrace();
			response.getWriter().append("修改失败");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
