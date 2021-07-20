package control;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.DAO;

@WebServlet("/CheckLike")
public class CheckLikeControl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CheckLikeControl() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/plain");
		response.setCharacterEncoding("UTF-8");

		DAO dao = new DAO();
		HttpSession session = request.getSession();

		int post_id = Integer.parseInt(request.getParameter("post_id"));
		String username = (String) session.getAttribute("username");
		int user_id = dao.getUserByUsername(username).getUser_id();

		// kiem tra da like bai post hay chua
		if (dao.checkLike(user_id, post_id) == false) {
			response.getWriter().write("notliked");
		} else {
			response.getWriter().write("liked");
		}

//		final PrintWriter writer = response.getWriter();
//		writer.print(post_id);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
