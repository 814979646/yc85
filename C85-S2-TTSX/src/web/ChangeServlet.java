package web;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import dao.BuyerShowDao;
import dao.UserDao;

@MultipartConfig()
@WebServlet("/ChangeServlet.do")
public class ChangeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UserDao u = new UserDao();
	BuyerShowDao b = new BuyerShowDao();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;utf-8");
		//使用户上传的图片能使用
		Part part = request.getPart("file");
		String webpath = "/";
		String diskpath = request.getServletContext().getRealPath(webpath);
		File proDir = new File(diskpath);

		System.out.println(proDir);

		File tomcatDir = proDir.getParentFile().getParentFile();
		File uploadDir = new File(tomcatDir, "/webapps/ROOT/upload");

		System.out.println(uploadDir);

		diskpath = uploadDir.getAbsolutePath();

		System.out.println(diskpath);
		diskpath = diskpath + "/" + part.getSubmittedFileName();
		webpath += "/" + part.getSubmittedFileName();

		webpath = webpath.substring(1);
		part.write(diskpath);

		@SuppressWarnings("unchecked")
		Map<String, Object> user = (Map<String, Object>) request.getSession().getAttribute("loginedUser");
		String id = "" + user.get("id");
		String name = "" + user.get("uname");
		u.changehead(id, "/upload/" + part.getSubmittedFileName());
		b.changehead("/upload/" + part.getSubmittedFileName(), name);

	}

}
