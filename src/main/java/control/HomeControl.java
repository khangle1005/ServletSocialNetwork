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
import entity.Post;

@WebServlet("/Home")
public class HomeControl extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public HomeControl() {
        super();
        // TODO Auto-generated constructor stub
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//lay thong tin bai post
		DAO dao = new DAO();
		HttpSession session = request.getSession();
		int user_id = (int)session.getAttribute("user_id");
		
		//lay tat cac cac bai post
		//List<Vector> posts = dao.getAllPostsWithUsers();
		
		//lay cac bai post tu ban be
		List<Vector> posts = dao.getAllFriendPostsWithUsers(user_id);
		
		//lay so luot likes va comments tu lop DAO sau do set lai vao Vector
		for (Vector post : posts) {
			int likes = dao.getNumberOfLikes(Integer.parseInt(String.valueOf(post.get(0))));
			int comments = dao.getNumberOfComments(Integer.parseInt(String.valueOf(post.get(0))));
			post.set(7, comments);
			post.set(3, likes);
		}
		
        request.setAttribute("posts", posts);
        request.getRequestDispatcher("Index.jsp").forward(request, response);
	}

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
