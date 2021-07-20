package control;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DAO;
import entity.User;

@WebServlet("/SearchControl")
public class SearchControl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String username = request.getParameter("username");
		DAO dao = new DAO();
		
		List<User> users = dao.searchUsersbyUsername(username);
		
		String html = "";
		
		for (User user : users) {
			html += "<a href='ViewUser?user_id=" + user.getUser_id() + "'>" + user.getUsername() + "</a><br>";
		}
		
		response.getWriter().write(html);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
