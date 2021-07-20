package control;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Paths;
import java.util.List;
import java.util.Vector;

import javax.print.DocFlavor.URL;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import dao.DAO;

@WebServlet("/PostControl")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
maxFileSize = 1024 * 1024 * 50, // 50MB
maxRequestSize = 1024 * 1024 * 50) // 50MB
public class PostControl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		int user_id = Integer.parseInt(request.getParameter("user_id"));
		String post_content = request.getParameter("post_content");
		Part filePart = request.getPart("file");
		String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
		InputStream fileContent = filePart.getInputStream();

		String finalPath = this.getFolderUpload().getAbsolutePath() + File.separator + fileName;
		//System.out.println("write to: " + finalPath);
		filePart.write(this.getFolderUpload().getAbsolutePath() + File.separator + fileName);
		DAO dao = new DAO();
		dao.post(user_id, post_content, "/images/" + fileName);
		
//		HomeControl home = new HomeControl();
//		home.doPost(request, response);
//		List<Vector> posts = dao.getAllPostsWithUsers();
//        request.setAttribute("posts", posts);
//        request.getRequestDispatcher("Index.jsp").forward(request, response);
//        response.sendRedirect("Index.jsp");
        
//        request.getRequestDispatcher("HomeControl").forward(request, response);
		response.sendRedirect("Home");
	}

	  private String extractFileName(Part part) {
		    String contentDisp = part.getHeader("content-disposition");
		    String[] items = contentDisp.split(";");
		    for (String s : items) {
		      if (s.trim().startsWith("filename")) {
		        return s.substring(s.indexOf("=") + 2, s.length() - 1);
		      }
		    }
		    return "";
		  }
		  public File getFolderUpload() throws MalformedURLException {
			  //Tao duong dan luu file
			  //Ap dung cho local
			  //File folderUpload = new File(System.getProperty("user.home") + "/Java work-space/social_network/src/main/webapp/images");
			  
			  //Luu anh trong thu muc /images tu root tren server deploy
			  String relativeWebPath = "/images";
			  String absoluteDiskPath = getServletContext().getRealPath(relativeWebPath);
			  //System.out.println("getFolderUpload: "+ absoluteDiskPath);
			  File folderUpload = new File(absoluteDiskPath);
			  
		    if (!folderUpload.exists()) {
		      folderUpload.mkdirs();
		    }
		    return folderUpload;
		  }
}
