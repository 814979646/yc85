package web;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import bean.User;
import dao.UserDao;


@WebServlet("/UserandUserinfoServlet.do")
public class UserandUserinfoServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	UserDao u=new UserDao();
	
	protected void query(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, IllegalAccessException, InvocationTargetException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;utf-8");
		String page=request.getParameter("page");
        String rows=request.getParameter("rows");
        User dp = new User();
        // 装载方法
        	BeanUtils.populate(dp, request.getParameterMap());
        	int total = u.count1(dp);
   		List<Map<String,Object>> list = u.query(dp, page, rows);
   		HashMap<String,Object> data = new HashMap<>();
   		data.put("rows", list);
   		data.put("total", total);
   		print( response, data);
	
	}
	
	protected void del(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, IllegalAccessException, InvocationTargetException {
		try {
			String id=request.getParameter("id");
			u.del(id);
			u.delinfo(id);
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
