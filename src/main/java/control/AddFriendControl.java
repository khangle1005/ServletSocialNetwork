package control;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DAO;

@WebServlet("/AddFriendControl")
public class AddFriendControl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int user_id1 = Integer.parseInt(request.getParameter("user_id1"));
		int user_id2 = Integer.parseInt(request.getParameter("user_id2"));
		
		DAO dao = new DAO();
		dao.addFriend(user_id1, user_id2);
		dao.deleteFriendRequest(user_id1, user_id2);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
