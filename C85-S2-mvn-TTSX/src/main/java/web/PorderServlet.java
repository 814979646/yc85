package web;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Userinfo;
import dao.OrderDao;
import dao.PorderDao;
import dao.UserinfoDao;


@WebServlet("/PorderServlet.do")
public class PorderServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	PorderDao p=new PorderDao();
	UserinfoDao u=new UserinfoDao();
	OrderDao o=new OrderDao();
	
	protected void insertporder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;utf-8");
		try {
	    	 String oid=request.getParameter("oid");
	    	 request.getSession().setAttribute("oid", oid);
		     String pname=request.getParameter("pname");
		     String price=request.getParameter("price");
		     String spesc=request.getParameter("spesc");
		     String count=request.getParameter("count");
		     String images=request.getParameter("images");
		     String prices=request.getParameter("prices");
		     @SuppressWarnings("unchecked")
			 Map<String, Object> user = (Map<String, Object>)
			 request.getSession().getAttribute("loginedUser");
			 String uid = "" + user.get("id");
			 p.insert(oid, pname, price, spesc, count, images, prices, uid);
			 response.getWriter().append("cg");
	    }catch(Exception e) {
	    	e.printStackTrace();
	    	response.getWriter().append("s失败");
	    }
	}
	protected void checkaddress(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;utf-8");
		@SuppressWarnings("unchecked")
		 Map<String, Object> user = (Map<String, Object>)
		 request.getSession().getAttribute("loginedUser");
		 String uid = "" + user.get("id");
		 List<Userinfo>list=u.queryaddress(uid);
		 if(list.size()==0) {
			 response.getWriter().append("0");
		 }else {
			 response.getWriter().append("1");
		 }
	}
	protected void delorder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;utf-8");
		try {
			 String id =request.getParameter("id");
			 o.del(id);
			 p.del(id);
			 response.getWriter().append("删除成功");
		 }catch(Exception e) {
			 e.printStackTrace();
			 response.getWriter().append("删除失败");
		 }
		 
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
