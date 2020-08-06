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


@WebServlet("/GetUpassServlet.do")
public class GetUpassServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UserDao u=new UserDao();
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;utf-8");
		String uname=request.getParameter("uname");
         List<User>list=u.query(uname);
         for(User use:list) {
        	 String upass=use.getUpass();
        	 response.getWriter().append(upass);
         }
         
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
