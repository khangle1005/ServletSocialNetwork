package control;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DAO;

@WebServlet("/Comment")
public class CommentControl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int post_id = Integer.parseInt(request.getParameter("post_id"));
		int user_id = Integer.parseInt(request.getParameter("user_id"));
		String post_comment = request.getParameter("post_comment");
		
		DAO dao = new DAO();
		dao.comment(user_id, post_id, post_comment);
	}

}
