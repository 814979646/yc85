package web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import bean.BuyerShow;
import bean.Order;
import bean.Porder;
import bean.Product;
import bean.Reply;
import bean.User;
import dao.BuyerShowDao;
import dao.OrderDao;
import dao.PorderDao;
import dao.ProductDao;
import dao.ReplyDao;
import dao.UserDao;
import redis.clients.jedis.Jedis;


@WebServlet("/ReplyServlet.do")
public class ReplyServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	ReplyDao r=new ReplyDao();
    PorderDao p=new PorderDao();
    ProductDao p1=new ProductDao();
    UserDao u=new UserDao();
    OrderDao o=new OrderDao();
    BuyerShowDao b=new BuyerShowDao();
   
    protected void queryshow(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;utf-8");
    	String pid=request.getParameter("pid");
    	String page=request.getParameter("page");
    	int page1=Integer.valueOf(page);
    	List<BuyerShow>list=b.query(pid,page1);
    	int pages=b.countPages(pid);
    	Gson gson=new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
    	Map<String,Object>map=new HashMap<>();
    	@SuppressWarnings("unchecked")
    	Map<String, Object> user = (Map<String, Object>) request.getSession().getAttribute("loginedUser");
    	String uid = "" + user.get("id");
    	map.put("list", list);
    	map.put("pages", pages);
    	String json=gson.toJson(map);
    	response.getWriter().append(json);
    	}
	protected void query(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;utf-8");
		String pid=request.getParameter("pid");
	String page=request.getParameter("page");
	int page1=Integer.valueOf(page);
	List<Reply>list=r.query(pid,page1);
	int pages=r.countPages(pid);
	Gson gson=new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
	Map<String,Object>map=new HashMap<>();
	@SuppressWarnings("unchecked")
	Map<String, Object> user = (Map<String, Object>) request.getSession().getAttribute("loginedUser");
	String uid = "" + user.get("id");
	map.put("list", list);
	map.put("pages", pages);
	String json=gson.toJson(map);
	response.getWriter().append(json);
	}
	protected void queryoid(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;utf-8");
		String oid=request.getParameter("oid");
		List<Porder>list=p.queryname(oid);
		List<Map<String,Object>>list1=new ArrayList<>();
		for(Porder pp:list) {
			String name=pp.getPname();
			Map<String,Object>map=p1.queryall(name);
			list1.add(map);
		}
		print(response,list1);
		}
	protected void insert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BuyerShowDao b=new BuyerShowDao();
		try {
			@SuppressWarnings("unchecked")
			Map<String, Object> user = (Map<String, Object>) request.getSession().getAttribute("loginedUser");
			String name = "" + user.get("uname");
			String uid = "" + user.get("id");
			List<User>list=u.queryimg(uid);
			String images = null;
			for(User uu:list) {
				images=uu.getHead();
			}
			String image1=request.getParameter("image1");
			String image2=request.getParameter("image2");
			String image3=request.getParameter("image3");
			String pid=request.getParameter("pid");
			String content=request.getParameter("content");
			if(image1==null && image2==null && image3==null) {
				r.insert(pid, name, content, images);
				response.getWriter().append("评价成功");
			}else{
				b.insert(pid, name, content, image1, image2, image3,images);
				response.getWriter().append("评价成功");
			}
		}catch(Exception e) {
			e.printStackTrace();
			response.getWriter().append("评价失败");
		}
	}
	//
	protected void updateaddress(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;utf-8");
		String oid=(String) request.getSession().getAttribute("oid");
		 String name=request.getParameter("name");
		 String phone=request.getParameter("phone");
		 String address=request.getParameter("address");
		 o.updateaddress(address, phone, name, oid);
	
	}
	protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;utf-8");
		@SuppressWarnings("unchecked")
			Map<String, Object> user = (Map<String, Object>) request.getSession().getAttribute("loginedUser");
			String name = "" + user.get("uname");
			String uid = "" + user.get("id");
			List<User>list=u.queryimg(uid);
			String images = null;
			for(User uu:list) {
				images=uu.getHead();
			}
			r.update(images, name);
	}
	protected void queryid(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;utf-8");
		String pid=request.getParameter("pid");
		List<Reply>list=r.query1(pid);
		if(list.size()==0) {
			response.getWriter().append("不能回复");
		}
		
}
	protected void querydata(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;utf-8");
		@SuppressWarnings("unchecked")
		Map<String, Object> user = (Map<String, Object>) request.getSession().getAttribute("loginedUser");
		String uid = "" + user.get("id");
		List<User>list=u.query1(uid);
		print(response,list);
		
}
	protected void queryprice(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;utf-8");
		String cid=request.getParameter("cid");
		int pages=p1.countPages(cid);
		String page = request.getParameter("page");
		int page1=Integer.valueOf(page);
        List<Map<String,Object>> list = p1.querycid1(cid,page1);
        Map<String, Object> map = new HashMap<>();
        map.put("list", list);
		map.put("pages", pages);
		response.getWriter().append(new Gson().toJson(map));
		
}
	protected void setoid(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;utf-8");
		String oid=request.getParameter("oid");
		request.getSession().setAttribute("oid", oid);
	
}
	protected void queryorderdata(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;utf-8");
		String oid=(String) request.getSession().getAttribute("oid");
		List<Order>list=o.querydata(oid);
		print(response,list);
		
}
	protected void updateorder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;utf-8");
		try {
			String oid=request.getParameter("id");
			o.updatesaveorder(oid, "已支付", "已收货");
			response.getWriter().append("收货成功");
		}catch(Exception e) {
			e.printStackTrace();
			response.getWriter().append("收货失败");
		}
		
}
	protected void reply(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;utf-8");
		try {
			String oid=request.getParameter("oid");
			o.updatesaveorder(oid, "已支付","已评价");
		}catch(Exception e) {
			e.printStackTrace();
		}
		
}
	protected void zan(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;utf-8");
		@SuppressWarnings("unchecked")
		Map<String, Object> user = (Map<String, Object>) request.getSession().getAttribute("loginedUser");
		String uid = "" + user.get("id");//uid
		String id = request.getParameter("id");//rid
		Jedis jd=new Jedis();
		jd.sadd("ttsx_zan"+id, uid);
		jd.close();	
}
	protected void zanshow(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;utf-8");
		@SuppressWarnings("unchecked")
		Map<String, Object> user = (Map<String, Object>) request.getSession().getAttribute("loginedUser");
		String uid = "" + user.get("id");//uid
		String id = request.getParameter("id");//rid
		Jedis jd=new Jedis();
		jd.sadd("ttsx_zanshow"+id, uid);
		jd.close();	
}
	protected void zandel(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;utf-8");
		@SuppressWarnings("unchecked")
		Map<String, Object> user = (Map<String, Object>) request.getSession().getAttribute("loginedUser");
		String uid = "" + user.get("id");//uid
		String id = request.getParameter("id");//rid
		Jedis jd=new Jedis();
		jd.srem("ttsx_zan"+id, uid);
		jd.close();	
}
	protected void zanshowdel(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;utf-8");
		@SuppressWarnings("unchecked")
		Map<String, Object> user = (Map<String, Object>) request.getSession().getAttribute("loginedUser");
		String uid = "" + user.get("id");//uid
		String id = request.getParameter("id");//rid
		Jedis jd=new Jedis();
		jd.srem("ttsx_zanshow"+id, uid);
		jd.close();	
}
	protected void countzan(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;utf-8");
		@SuppressWarnings("unchecked")
		Map<String, Object> user = (Map<String, Object>) request.getSession().getAttribute("loginedUser");
		String uid = "" + user.get("id");
		String id = request.getParameter("id");
		Jedis jd=new Jedis();
		Long cnt=jd.scard("ttsx_zan"+id);//总数
		String zan=String.valueOf(cnt);
		r.updatezan(zan, id);
		jd.close();
}
	protected void countzanshow(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;utf-8");
		@SuppressWarnings("unchecked")
		Map<String, Object> user = (Map<String, Object>) request.getSession().getAttribute("loginedUser");
		String uid = "" + user.get("id");
		String id = request.getParameter("id");
		Jedis jd=new Jedis();
		Long cnt=jd.scard("ttsx_zanshow"+id);//总数
		String zan=String.valueOf(cnt);
		b.updatezan(zan, id);
		jd.close();
}
	protected void queryxl(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;utf-8");
		String id=request.getParameter("id");
		List<Product>list=p1.queryid(id);
		print(response,list);
		
		}
	//查找销量最多的四个商品
	protected void querymax(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;utf-8");
		String cid=request.getParameter("cid");
		List<Product>list=p1.queryxlmax(cid);
		print(response,list);
		}
	
	protected void querycount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;utf-8");
		@SuppressWarnings("unchecked")
		Map<String, Object> user = (Map<String, Object>) request.getSession().getAttribute("loginedUser");
		String uid = "" + user.get("id");
		List<User>list=u.query1(uid);
		print(response,list);
		
	}
	protected void seachmax(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;utf-8");
		String productname=request.getParameter("productname");
		List<Product>list=p1.seachid(productname);
		for(Product pp:list) {
			response.getWriter().append(String.valueOf(pp.getId()));
		}
	}
	

}
