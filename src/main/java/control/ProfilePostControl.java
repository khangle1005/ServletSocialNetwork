package control;

import java.io.IOException;
import java.util.List;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DAO;
import entity.FriendRequest;

@WebServlet("/Profile")
public class ProfilePostControl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int user_id = Integer.parseInt(request.getParameter("user_id"));
		
		// lay thong tin bai post
		DAO dao = new DAO();
		List<Vector> posts = dao.getAllProfilePostsWithUsers(user_id);

		// lay so luot likes va comments tu lop DAO sau do set lai vao Vector
		for (Vector post : posts) {
			int likes = dao.getNumberOfLikes(Integer.parseInt(String.valueOf(post.get(0))));
			int comments = dao.getNumberOfComments(Integer.parseInt(String.valueOf(post.get(0))));
			post.set(7, comments);
			post.set(3, likes);
		}

		request.setAttribute("posts", posts);
		
		//load friend request
		List<Vector> frequests = dao.getAllFriendRequestsWithUsername(user_id);
		request.setAttribute("frequests", frequests);
		
		request.getRequestDispatcher("myProfile.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
