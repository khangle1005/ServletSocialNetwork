package control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.DAO;
import entity.Message;

@WebServlet("/LoadMessagesControl")
public class LoadMessagesControl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/plain");
	    response.setCharacterEncoding("UTF-8");
	    
		DAO dao = new DAO();
		HttpSession session = request.getSession();
		int user_id1 = (int)session.getAttribute("user_id");
		int user_id2 = Integer.parseInt(request.getParameter("user_id"));
		
		List<Message> messages = dao.getAllMessages(user_id1, user_id2);
		
		String html = "";
		for (Message mess : messages) {
			if(mess.getUser_id1() == user_id1) {
				html += "<li id='chat-right' class='chat-right'><div class='chat-hour'>"+ mess.getTime() +" <span class='fa fa-check-circle'></span></div><div class='chat-text'>" + mess.getText() + "</div><div class='chat-avatar'><img src='https://st3.depositphotos.com/15648834/17930/v/600/depositphotos_179308454-stock-illustration-unknown-person-silhouette-glasses-profile.jpg' alt='Retail Admin'><div id='chat-name' class='chat-name'>" + mess.getUser_id1() + "</div></div></li>";
			}
			else {
				html += "<li id='chat-left' class='chat-left'><div class='chat-avatar'><img src='https://st3.depositphotos.com/15648834/17930/v/600/depositphotos_179308454-stock-illustration-unknown-person-silhouette-glasses-profile.jpg' alt='Retail Admin'><div id='chat-name' class='chat-name'>" + mess.getUser_id1() + "</div></div><div class='chat-text'>" + mess.getText() + "</div><div class='chat-hour'>" + mess.getTime() + " <span class='fa fa-check-circle'></span></div></li>";
			}
		}
		
		response.getWriter().write(html);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
