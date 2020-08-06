package web;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import bean.Order;
import bean.Porder;
import bean.Product;
import bean.Status;
import dao.OrderDao;
import dao.PorderDao;

/**
 * Servlet implementation class OrderServlet
 */
@WebServlet("/OrderServlet.do")
public class OrderServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	OrderDao o=new OrderDao();
	PorderDao po=new PorderDao();
    
	protected void query(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, IllegalAccessException, InvocationTargetException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;utf-8");
		String page=request.getParameter("page");
        String rows=request.getParameter("rows");
        
        Order dp = new Order();
     // 装载方法
     	BeanUtils.populate(dp, request.getParameterMap());
     	int total = o.count1(dp);
		List<Map<String,Object>> list = o.queryorder(dp, page, rows);
		HashMap<String,Object> data = new HashMap<>();
		data.put("rows", list);
		data.put("total", total);
		print( response, data);
	}
	protected void del(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;utf-8");
		try {
			String id = request.getParameter("id");
			o.del(id);
			response.getWriter().append("删除成功");
		}catch(Exception e) {
			e.printStackTrace();
			response.getWriter().append("删除失败");
		}
	}
	protected void queryporder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;utf-8");
		String oid = request.getParameter("oid");
			String pname = request.getParameter("pname");
			String prices = request.getParameter("prices");
			List<Porder>list=po.queryporder(oid, pname, prices);
			print(response,list);
	}
	protected void querystatus(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;utf-8");
		List<Status>list=o.querystatus();
		print(response,list);
	}
	protected void querystatus1(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;utf-8");
		List<Status>list=o.querystatus1();
		print(response,list);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
