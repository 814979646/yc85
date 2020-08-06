package web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import bean.Userinfo;
import dao.UserinfoDao;


@WebServlet("/QueryIDUserinfoServlet.do")
public class QueryIDUserinfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UserinfoDao u=new UserinfoDao();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;utf-8");
		String id=request.getParameter("id");
	    List<Userinfo>list=u.queryid(id);
	    response.getWriter().append(new Gson().toJson(list));
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
