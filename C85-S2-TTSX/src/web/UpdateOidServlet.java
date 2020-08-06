package web;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Car;
import dao.Cardao;
import dao.PorderDao;
import redis.clients.jedis.Jedis;


@WebServlet("/UpdateOidServlet.do")
public class UpdateOidServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       Cardao c=new Cardao();
       PorderDao p=new PorderDao();
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;utf-8");
		Jedis jd=new Jedis();
	      @SuppressWarnings("unchecked")
		  Map<String, Object> user = (Map<String, Object>)
		  request.getSession().getAttribute("loginedUser");
		  String uid = "" + user.get("id");
	      Set<String>set=jd.smembers("ttsx_oid");
	      String oid=request.getParameter("oid");
	      request.getSession().setAttribute("oid", oid);
	      for(String id:set) {
	    	  List<Car>list=c.queryprices(id);
	    	  for(Car cc:list) {
	    		  String pname=cc.getProductname();
	    		  String price=cc.getPrice();
	    		  String spesc=cc.getSpecs();
	    		  String images=cc.getImages();
	    		  Integer count1=cc.getCount();
	    		  String count=String.valueOf(count1);
	    		  String prices=cc.getPrices();
	    		  p.insert(oid, pname, price, spesc, count, images, prices, uid);
	    	  }
	    	  c.del(id);
	      }
	      jd.del("ttsx_oid");
	      jd.close();
	      
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
