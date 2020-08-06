package web;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;


@MultipartConfig()
@WebServlet("/UploadServlets.do")
public class UploadServlets extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
		
		response.getWriter().append("/upload/" + part.getSubmittedFileName());
	
	}

}
