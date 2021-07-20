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
import entity.Post;

@WebServlet("/Login")
public class LoginControl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		DAO dao = new DAO();

		
		if(dao.checkUsernamePassword(username, password) == true) {
			
			int user_id = dao.getUserByUsername(username).getUser_id();
			
			HttpSession session = request.getSession();
			session.setAttribute("user_id", user_id);
			session.setAttribute("username", username);
			session.setAttribute("password", password);
//			request.getRequestDispatcher("Index.jsp").forward(request, response);
			
//			HomeControl home = new HomeControl();
//			home.doPost(request, response);
			
			//request.getRequestDispatcher("HomeControl").forward(request, response);
			response.sendRedirect("Home");
		}
		else {
			request.getRequestDispatcher("Login.jsp").forward(request, response);
		}
	}

}
