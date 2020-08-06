package web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.User;
import dao.UserDao;

@WebServlet("/FindPwdServlet.do")
public class FindPwdServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	UserDao u=new UserDao();
	protected void queryEmail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;utf-8");
		String name=request.getParameter("uname");
	     List<User>list=u.queryuname(name);
	     for(User uu:list) {
	    	 request.getSession().setAttribute("email", uu.getEmail());
	    	 response.getWriter().append(uu.getEmail());
	     }
	}
	protected void updatePwd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;utf-8");
		String vcode=request.getParameter("vcode");
	     Integer vcode1=Integer.valueOf(vcode);
	     String uname=request.getParameter("uname");
	     String upass=request.getParameter("upass");
	     Integer scode=(Integer) request.getSession().getAttribute("vcode");
	     System.out.println("=====svode"+scode);
	     System.out.println("=====vode"+vcode1);
	     if(upass==null||upass.trim().isEmpty()) {
	    	 response.getWriter().append("请输入密码");
	     }else if(vcode==null||vcode.trim().isEmpty()) {
	    	 response.getWriter().append("请输入验证码");
	     }else {
	    	 if(vcode1.equals(scode)==false) {
	    		 response.getWriter().append("输入验证码不正确");
	    	 }else {
	    		 u.changeupass(upass, uname);
	    		 response.getWriter().append("修改成功");
	    	 }
	     }
	     
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
