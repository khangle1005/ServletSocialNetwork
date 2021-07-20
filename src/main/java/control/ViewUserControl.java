package control;

import java.io.IOException;
import java.util.List;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.DAO;

@WebServlet("/ViewUser")
public class ViewUserControl extends HttpServlet {
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

		//lay thong tin nguoi dung de kiem tra add friend hay chua
		HttpSession session = request.getSession();
		int user_id1 = (int) session.getAttribute("user_id"); //user_id dang login
		if (dao.checkFriend(user_id1, user_id) == "true") {
			request.setAttribute("check_friend", "friend");
		}else if(dao.checkFriend(user_id1, user_id) == "false") {
			request.setAttribute("check_friend", "notfriend");
		}else if(dao.checkFriend(user_id1, user_id) == "me") {
			request.setAttribute("check_friend", "me");
		}
		
		request.setAttribute("user_id2", user_id);//truyen user_id dang xem
		request.setAttribute("posts", posts);
		request.getRequestDispatcher("viewUser.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
