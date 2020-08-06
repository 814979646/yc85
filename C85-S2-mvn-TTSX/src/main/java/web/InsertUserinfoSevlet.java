package web;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserinfoDao;

@WebServlet("/InsertUserinfoSevlet.do")
public class InsertUserinfoSevlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UserinfoDao u=new UserinfoDao();
	
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
    		if(name==null || name.trim().isEmpty()) {
    			response.getWriter().append("收件人不能为空");
    		}else if(address==null || address.trim().isEmpty()) {
    			response.getWriter().append("地址不能为空");
    		}else if(postal==null || postal.trim().isEmpty()) {
    			response.getWriter().append("邮编不能为空");
    		}else if(phone==null || phone.trim().isEmpty()) {
    			response.getWriter().append("联系方式不能为空");
    		}else {
    			@SuppressWarnings("unchecked")
        		Map<String, Object> user = (Map<String, Object>) request.getSession().getAttribute("loginedUser");
        		String uid = "" + user.get("id");
        		u.insert(name, address, postal, phone, uid);
        		response.getWriter().append("新增地址成功");
    		}
        }catch(Exception e) {
        	e.printStackTrace();
        	response.getWriter().append("新增地址失败");
        }
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
