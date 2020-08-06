package util;

import java.io.IOException;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Servlet 地址配置：
 * 1.文件路径匹配：/hello.s
 * 2.目录路径匹配：/page/   匹配/page/下所有的资源  /page/1   /page/2
 * 3.文件后缀名：*.jsp *.s ,*.do
 * 
 * 目录路径匹配+文件后缀名   是非法的   /page/*.jsp   是错误的
 * 
 * 
 */
//@WebFilter("/0524/")
@WebFilter(urlPatterns = {"*.jsp","*.do"})//拦截所有带有这些后缀的文件
public class LoginFilter implements Filter {

    //销毁
	public void destroy() {
		System.out.println("========destroy==========");
	}

	/**
	 * 执行过滤
	 * 
	 * Servlet
	 * HttpServletRequest,HttpServletResponse
	 * 
	 * ServletRequest, ServletResponse,
	 *  FilterChain  过滤器链
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;utf-8");
		//每次启动服务器都会使这个过滤器初始化
		/*
		 * HttpSession session=((HttpServletRequest)request).getSession();//转型 //获取属性值
		 * String user=(String) session.getAttribute("LoginedUser"); if(user==null) {
		 * response.getWriter().append("你已经被过滤了，进不去相应的文件");
		 * //中断执行,不执行chain.doFilter(request, response) return; }
		 */
		   //执行过滤器链中的下一个过滤器的doFilter，如果是最后一个过滤器了
		   //则允许访问目标资源(jsp,Servlet,html,js...)
		
		chain.doFilter(request, response);
	}

	//初始化
	public void init(FilterConfig fConfig) throws ServletException {
		System.out.println("========init==========");
	}

}
