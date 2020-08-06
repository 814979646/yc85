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


@WebServlet("/UpdateOrderServlet.do")
public class UpdateOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	OrderDao o=new OrderDao();
	UserDao u=new UserDao();
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;utf-8");
		try {
	    	 String address=request.getParameter("address");
	    	 String phone=request.getParameter("phone");
	    	 String name=request.getParameter("name");
	    	 String orderprices=request.getParameter("orderprices");
	    	 String oid=(String) request.getSession().getAttribute("oid");
		     o.update(oid,address,phone,name,orderprices);
		     
		     String taojb1=request.getParameter("taojb");
		     double t1=Integer.valueOf(taojb1);
		     double t2= 0;
		     @SuppressWarnings("unchecked")
			Map<String, Object> user = (Map<String, Object>) request.getSession().getAttribute("loginedUser");
			String id = "" + user.get("id");
			List<User>list=u.queryimg(id);
			for(User uu:list) {
				t2=Double.valueOf(uu.getTaojb());
			}
			String taojb=String.valueOf(t2-t1);
			System.out.println(t1);
			System.out.println(t2);
			System.out.println(taojb);
			u.changetaojb(id, taojb);
		     
	     }catch(Exception e) {
	    	 e.printStackTrace();
	     }
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
