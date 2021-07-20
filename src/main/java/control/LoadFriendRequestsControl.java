package control;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dao.DAO;
import entity.FriendRequest;

@WebServlet("/LoadFriendRequestsControl")
public class LoadFriendRequestsControl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		
		int user_id = Integer.parseInt(request.getParameter("user_id"));
		
		DAO dao = new DAO();
		List<FriendRequest> frequests = dao.getAllFriendRequests(user_id);
		String json = new Gson().toJson(frequests);

	    response.getWriter().write(json);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
