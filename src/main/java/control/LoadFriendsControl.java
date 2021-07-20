package control;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.DAO;
import entity.User;

@WebServlet("/LoadFriendsControl")
public class LoadFriendsControl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/plain");
	    response.setCharacterEncoding("UTF-8");
	    
		DAO dao = new DAO();
		HttpSession session = request.getSession();
		int user_id = (int)session.getAttribute("user_id");
		
		List<User> friends = dao.getAllFriends(user_id);
		
		String html = "";

		for(User user : friends) {
			html += "<li class='person' data-chat='person1' id='" + user.getUser_id() + "'><div class='user'><img src='https://st3.depositphotos.com/15648834/17930/v/600/depositphotos_179308454-stock-illustration-unknown-person-silhouette-glasses-profile.jpg' alt='Retail Admin'><span class='status away'></span></div><p class='name-time'><span class='name'>" + user.getUsername() + "</span><span class='time'> 01/02/2021</span></p></li>";
		}
		
		response.getWriter().write(html);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
