package control;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.DAO;

@WebServlet("/LikeControl")
public class LikeControl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		DAO dao = new DAO();
		HttpSession session = request.getSession();
		
		int post_id = Integer.parseInt(request.getParameter("post_id"));
		String username = (String) session.getAttribute("username");

		int user_id = dao.getUserByUsername(username).getUser_id();
		
		response.setContentType("text/plain");
	    response.setCharacterEncoding("UTF-8");
		
		//kiem tra da like bai post hay chua
		if(dao.checkLike(user_id, post_id) == false) {
			dao.like(user_id, post_id);
			response.getWriter().write("done");
		}else {
			dao.unlike(user_id, post_id);
			response.getWriter().write("liked");
		}
		
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
