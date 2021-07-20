package control;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.DAO;

@WebServlet("/SendMessageControl")
public class SendMessageControl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		DAO dao = new DAO();
		
		int user_id1 = (int) session.getAttribute("user_id");
		int user_id2 = Integer.parseInt(request.getParameter("user_id2"));
		String text = request.getParameter("text");
		dao.sendMessage(user_id1, user_id2, text);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
