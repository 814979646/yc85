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

import bean.Product;
import dao.ProductDao;


@WebServlet("/ListServlet.do")
public class ListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ProductDao p = new ProductDao();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;utf-8");
		String cid=request.getParameter("cid");
		int pages=p.countPages(cid);
		String page = request.getParameter("page");
		int page1=Integer.valueOf(page);
        List<Map<String,Object>> list = p.querycid(cid,page1);
        Map<String, Object> map = new HashMap<>();
        map.put("list", list);
		map.put("pages", pages);
		response.getWriter().append(new Gson().toJson(map));
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
