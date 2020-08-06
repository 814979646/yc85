package web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.User;
import dao.UserDao;


@WebServlet("/RegServlet.do")
public class RegServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private UserDao udao = new UserDao();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;utf-8");
		String uname = request.getParameter("uname");
		String upass = request.getParameter("upass");
		String reupass = request.getParameter("reupass");
		String email = request.getParameter("email");
		String head = request.getParameter("head");

		if (uname == null || uname.trim().isEmpty()) {
			response.getWriter().append("请填写用户名!");
		} else if (uname!=null) {
			List<User>list=udao.query(uname);
			if(list.size()!=0) {
				response.getWriter().append("该用户已注册!");
			}else if (upass == null || upass.trim().isEmpty()) {
				response.getWriter().append("请填写密码!");
			} else if (upass.equals(reupass) == false) {
				response.getWriter().append("两次输入密码不一致!");
			} else {
				udao.insert(uname, upass, email, head);
				response.getWriter().append("用户注册完成!");
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
