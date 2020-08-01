package com.yc.d0729;

import java.io.PrintWriter;

public class HelloServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) {

		String name = request.getParameter("name");

		PrintWriter out = response.getWriter();

		out.print("<h1>hello</h1>");

		System.out.println("hello world!");
	}

}