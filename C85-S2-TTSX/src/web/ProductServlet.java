package web;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import bean.Order;
import bean.Product;
import bean.ttsx_category;
import dao.CategoryDao;
import dao.OrderDao;
import dao.ProductDao;
import redis.clients.jedis.Jedis;
import util.DBhelper;

@WebServlet("/ProductServlet.do")
public class ProductServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	ProductDao p = new ProductDao();
	OrderDao o = new OrderDao();
	
	protected void querycid(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;utf-8");
		String cid = request.getParameter("cid");
		Map<String,Object> list = p.querycid(cid);
		print(response, list);

	}
	protected void query(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, IllegalAccessException, InvocationTargetException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;utf-8");
		String page=request.getParameter("page");
        String rows=request.getParameter("rows");
        Product dp = new Product();
		// 装载方法
		BeanUtils.populate(dp, request.getParameterMap());

		List<?> list = p.query1(dp,page, rows);
		int total = p.count1(dp);
        HashMap<String,Object> data = new HashMap<>();
		data.put("rows", list);
		data.put("total", total);
		print( response, data);
		

	}
	protected void querycname(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, IllegalAccessException, InvocationTargetException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;utf-8");
		CategoryDao c=new CategoryDao();
        List<?>list=c.query();
		print( response, list);
		

	}
	protected void querylasts(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;utf-8");
		Jedis jd=new Jedis();
		@SuppressWarnings("unchecked")
		Map<String, Object> user = (Map<String, Object>) request.getSession().getAttribute("loginedUser");
		String uid = "" + user.get("id");
		Set<String>set=jd.zrangeByScore("ttsx_detail", uid, uid);
		//jd.del("ttsx_detail");
		List<Map<String,Object>>list=new ArrayList<>();
		for(String s:set) {
			list.add(p.queryid1(s));
		}
		jd.close();
		print(response, list);
	}
	  protected void queryorderlist(HttpServletRequest request, HttpServletResponse
	  response) throws ServletException, IOException {
		  request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;utf-8");
		  @SuppressWarnings("unchecked")
			Map<String, Object> user = (Map<String, Object>) request.getSession().getAttribute("loginedUser");
			String uid = "" + user.get("id");
			String page=request.getParameter("page");
			int page1=Integer.valueOf(page);
			List<Map<String,Object>>list=o.query1(uid,page1);
			for(Map<String, Object> map:list) {
				String oid=String.valueOf(map.get("id")) ;
				map.put("olist", o.queryoid(oid));
			}
			Gson gson=new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
			int pages=o.countPages(uid);
			Map<String,Object>map=new HashMap<>();
			map.put("list", list);
			map.put("pages", pages);
			String json=gson.toJson(map);
			response.getWriter().append(json);
	  
	  
	  }
	  protected void queryid(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
		  request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;utf-8");
			String id = request.getParameter("id");
			List<Product>list=p.queryid(id);
			for(Product pp:list) {
				Map<String,Object> list1 = p.querycid(String.valueOf(pp.getCid()));
				print(response, list1);
			}
		}
	 

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
