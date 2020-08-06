package web;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import bean.Porder;
import dao.PorderDao;
import redis.clients.jedis.Jedis;


@WebServlet("/QueryPorderServlet.do")
public class QueryPorderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	PorderDao p=new PorderDao();
    Integer prices=0;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;utf-8");
		
		String oid=(String) request.getSession().getAttribute("oid");
		List<Porder>list=p.query(oid);
		for(Porder pp:list) {
			Integer pp1=Integer.valueOf(pp.getPrices());
			if(list.size()>1) {
				prices+=pp1;
			}else {
				prices=pp1;
			}
			
		}
		String prices1=String.valueOf(prices);
		prices=0;
		int count1=p.count(oid);
		String count=String.valueOf(count1);
		Map<String,Object>map=new HashMap<>();
		map.put("list", list);
		map.put("count", count);
		map.put("prices", prices1);
		
		Jedis jd=new Jedis();
		jd.del("ttsx_oid");
		jd.close();
		
		response.getWriter().append(new Gson().toJson(map));
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
