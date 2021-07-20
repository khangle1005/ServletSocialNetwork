package control;

import java.io.IOException;
import java.util.List;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dao.DAO;

@WebServlet("/LoadCommentsControl")
public class LoadCommentsControl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int post_id = Integer.parseInt(request.getParameter("post_id"));

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		
		DAO dao = new DAO();
		List<Vector> comments = dao.getAllCommentsOfPost(post_id);
	    String json = new Gson().toJson(comments);

	    response.getWriter().write(json);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
