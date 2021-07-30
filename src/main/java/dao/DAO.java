package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import context.DBContext;
import entity.FriendRequest;
import entity.Message;
import entity.Post;
import entity.User;

public class DAO {
	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	public List<User> getAllUsers(){
		List<User> users = new ArrayList<>();
		String query  = "select * from user";
		try {
			conn = new DBContext().getConnection();
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				users.add(new User(rs.getInt(1), rs.getString(2), rs.getString(3)));
			}
			
			if(conn!=null)
                conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return users;
	}
	
	public User getUserById(int id){
		User user = null;
		String query  = "select * from user where user_id=" + id;
		try {
			conn = new DBContext().getConnection();
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				user = new User(rs.getInt(1), rs.getString(2), rs.getString(3));
			}
			
			if(conn!=null)
                conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return user;
	}
	
	public String getUsernameById(int id){
		String username = null;
		String query  = "select username from user where user_id=" + id;
		try {
			conn = new DBContext().getConnection();
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				username = rs.getString(1);
			}
			
			if(conn!=null)
                conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return username;
	}
	
	public User getUserByUsername(String username){
		User user = null;
		String query  = "select * from user where username='" + username + "'";
		try {
			conn = new DBContext().getConnection();
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				user = new User(rs.getInt(1), rs.getString(2), rs.getString(3));
			}
			
			if(conn!=null)
                conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return user;
	}

	public boolean checkUserExists(String username) {
		String query  = "select * from user where username='";
		query += String.valueOf(username) + "'";
		boolean isExists = false;
		
		try {
			conn = new DBContext().getConnection();
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				isExists = true;
			}
			else {
				isExists = false;
			}
			
			if(conn!=null)
                conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return isExists;
	}
	
	public boolean checkUsernamePassword(String username, String password) {
		String query  = "select * from user where username='";
		query += String.valueOf(username) + "'";
		boolean isExists = false;
		
		try {
			conn = new DBContext().getConnection();
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				String query1  = "select * from user where username='";
				query1 += String.valueOf(username) + "'";
				query1 += " and " + "password='" + password + "'";
				try {
					ps = conn.prepareStatement(query1);
					rs = ps.executeQuery();
					
					if(rs.next()) {
						isExists = true;
					}
					else {
						isExists = false;
					}
				}catch (Exception e){
					e.printStackTrace();
				}
			}
			else {
				isExists = false;
			}
			
			if(conn!=null)
                conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return isExists;
	}
	
	public List<Post> getAllPosts(){
		List<Post> posts = new ArrayList<>();
		String query  = "select * from post";
		try {
			conn = new DBContext().getConnection();
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				posts.add(new Post(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getInt(4), rs.getString(5)));
			}
			
			if(conn!=null)
                conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return posts;
	}
	
	public List<Vector> getAllPostsWithUsers(){
		List<Vector> posts = new ArrayList<>();
		String query = "select * from post, user where post.user_id = user.user_id ORDER BY date DESC";
		try {
			conn = new DBContext().getConnection();
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Vector v = new Vector();
				v.addElement(rs.getInt(1));
				v.addElement(rs.getInt(2));
				v.addElement(rs.getString(3)); //date
				v.addElement(rs.getInt(4)); //likes
				v.addElement(rs.getString(5)); //text
				v.addElement(rs.getString(6)); //images
				v.addElement(rs.getString(8)); //username posted
				v.addElement(0); //comments
				posts.add(v);
			}
			
			if(conn!=null)
                conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return posts;
	}
	
	public List<Vector> getAllProfilePostsWithUsers(int user_id){
		List<Vector> posts = new ArrayList<>();
		String query = "select * from post, user where post.user_id = user.user_id ";
		query += "and user.user_id=" + user_id;
		query += " ORDER BY date DESC";

		try {
			conn = new DBContext().getConnection();
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Vector v = new Vector();
				v.addElement(rs.getInt(1));
				v.addElement(rs.getInt(2));
				v.addElement(rs.getString(3)); //date
				v.addElement(rs.getInt(4)); //likes
				v.addElement(rs.getString(5)); //text
				v.addElement(rs.getString(6)); //images
				v.addElement(rs.getString(8)); //username posted
				v.addElement(0); //comments
				posts.add(v);
			}
			
			if(conn!=null)
                conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return posts;
	}
	
	public void post(int user_id, String text, String image) {
//		String query = "INSERT INTO post (user_id, date, likes, text, images) VALUES (";
//		query += String.valueOf(user_id) + ", ";
//		query += "NOW()" + ", " + "0, '"; //'CURRENT_TIMESTAMP' neu dung cho sql server
//		query += text + "', '";
//		query += image + "')";
		
		try {
			conn = new DBContext().getConnection();
			//ps = conn.prepareStatement(query);
			//ps.executeUpdate();
			
			
			java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());//lay thoi gian hien tai
			//Dung preparedStatement de tranh mat dau backslash trong mysql
			PreparedStatement preparedStatement = conn.prepareStatement("insert into post (user_id, date, likes, text, images) values (?, ?, ?, ?, ?)");
			preparedStatement.setInt(1, user_id);
			preparedStatement.setTimestamp(2, date);
			preparedStatement.setInt(3, 0);
			preparedStatement.setString(4, text);
			preparedStatement.setString(5, image);
			preparedStatement.executeUpdate();

			if(conn!=null)
                conn.close();

		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public int getNumberOfLikes(int post_id) {
		int likes = 0;
		String query = "select count(user_id) from `like` where post_id=";
		query += post_id;
		try {
			conn = new DBContext().getConnection();
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				likes = rs.getInt(1);
			}
			
			if(conn!=null)
                conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return likes;
	}
	
	public int getNumberOfComments(int post_id) {
		int comments = 0;
		String query = "select count(user_id) from comment where post_id=";
		query += post_id;

		try {
			conn = new DBContext().getConnection();
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				comments = rs.getInt(1);
			}
			
			if(conn!=null)
                conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return comments;
	}
	
	public void like(int user_id, int post_id) {
		String query = "INSERT INTO `like` VALUES (";
		query += post_id + ", ";
		query += user_id + ")";
		try {
			conn = new DBContext().getConnection();
			ps = conn.prepareStatement(query);
			ps.executeUpdate();
			
			if(conn!=null)
                conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void unlike(int user_id, int post_id) {
		String query  = "delete from `like` where user_id=";
		query += String.valueOf(user_id);
		query += " and post_id=" + String.valueOf(post_id);
		
		if(checkLike(user_id, post_id) == true) {
			try {
				conn = new DBContext().getConnection();
				ps = conn.prepareStatement(query);
				ps.executeUpdate();
				
				if(conn!=null)
                    conn.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public List<Vector> getAllCommentsOfPost(int post_id){
		List<Vector> comments = new ArrayList<>();
		String query = "select user.username, comment.comment from comment, user where comment.user_id = user.user_id";
		query += " and comment.post_id=" + post_id;
		try {
			conn = new DBContext().getConnection();
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Vector v = new Vector();
				v.addElement(rs.getString(1)); //username
				v.addElement(rs.getString(2)); //comment
				comments.add(v);
			}
			
			if(conn!=null)
                conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return comments;
	}
	
	public void comment(int user_id, int post_id, String text) {
		String query = "INSERT INTO comment VALUES (";
		query += String.valueOf(post_id) + ", ";
		query += String.valueOf(user_id) + ", ";
		query += "'" + text + "')";
		
		try {
			conn = new DBContext().getConnection();
			ps = conn.prepareStatement(query);
			ps.executeUpdate();

			if(conn!=null)
                conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean checkLike(int user_id, int post_id) {
		String query  = "select * from `like` where user_id=";
		query += String.valueOf(user_id);
		query += " and post_id=" + String.valueOf(post_id);
		boolean isLiked = false;
		
		try {
			conn = new DBContext().getConnection();
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				isLiked = true;
			}
			else {
				isLiked = false;
			}
			
			if(conn!=null)
                conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return isLiked;
	}	 
	
	public void addFriend(int user_id1, int user_id2) {
		String query = "INSERT INTO `friend` VALUES (";
		query += user_id1 + ", ";
		query += user_id2 + ")";
		try {
			conn = new DBContext().getConnection();
			ps = conn.prepareStatement(query);
			ps.executeUpdate();
			
			if(conn!=null)
                conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void unFriend(int user_id1, int user_id2) {
		String query  = "delete from `friend` where (user_id1=";
		query += String.valueOf(user_id1);
		query += " and user_id2=" + String.valueOf(user_id2) + ")";
		query += "or (user_id1=" + String.valueOf(user_id2) + " and user_id2=" + String.valueOf(user_id1) + ")";
		
		try {
			conn = new DBContext().getConnection();
			ps = conn.prepareStatement(query);
			ps.executeUpdate();
			
			if(conn!=null)
                conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void sendFriendRequest(int user_id1, int user_id2) {
		String query = "INSERT INTO `friend_request` VALUES (";
		query += user_id1 + ", ";
		query += user_id2 + ")";
		try {
			conn = new DBContext().getConnection();
			ps = conn.prepareStatement(query);
			ps.executeUpdate();
			
			if(conn!=null)
                conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void deleteFriendRequest(int user_id1, int user_id2) {
		String query  = "delete from `friend_request` where (user_id1=";
		query += String.valueOf(user_id1);
		query += " and user_id2=" + String.valueOf(user_id2) + ")";
		query += "or (user_id1=" + String.valueOf(user_id2) + " and user_id2=" + String.valueOf(user_id1) + ")";
		
		try {
			conn = new DBContext().getConnection();
			ps = conn.prepareStatement(query);
			ps.executeUpdate();
			
			if(conn!=null)
                conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public String checkFriend(int user_id1, int user_id2) {
		String query  = "select * from `friend` where (user_id1=";
		query += String.valueOf(user_id1);
		query += " and user_id2=" + String.valueOf(user_id2) + ")";
		query += "or (user_id1=" + String.valueOf(user_id2) + " and user_id2=" + String.valueOf(user_id1) + ")";
		
		String isFriend = "false";
		
		try {
			conn = new DBContext().getConnection();
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			
			if(user_id1 == user_id2) {
				return "me";
			}
			
			if(rs.next()) {
				isFriend = "true";
			}
			else {
				isFriend = "false";
			}
			
			if(conn!=null)
                conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return isFriend;
	}
	
	public List<FriendRequest> getAllFriendRequests(int user_id){
		List<FriendRequest> requests = new ArrayList<>();
		String query  = "select * from friend_request where user_id2=" + user_id;
		
		try {
			conn = new DBContext().getConnection();
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				requests.add(new FriendRequest(rs.getInt(1), rs.getInt(2)));
			}
			
			if(conn!=null)
                conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return requests;
	}
	
	public List<Vector> getAllFriendRequestsWithUsername(int user_id){
		List<Vector> requests = new ArrayList<>();
		String query  = "select friend_request.user_id1, user.username, friend_request.user_id2 from friend_request, user where friend_request.user_id2=" + user_id;
		query += " and friend_request.user_id1=user.user_id";

		try {
			conn = new DBContext().getConnection();
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Vector v = new Vector();
				v.addElement(rs.getInt(1));
				v.addElement(rs.getString(2));
				v.addElement(rs.getInt(3));
				requests.add(v);
			}
			
			if(conn!=null)
                conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return requests;
	}
	
	public List<User> getAllFriends(int user_id){
		List<User> friends = new ArrayList<>();
		String query  = "select * from user";
		
		try {
			conn = new DBContext().getConnection();
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				friends.add(new User(rs.getInt(1), rs.getString(2), "password"));
			}
			
			if(conn!=null)
				rs.close();
                conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		Iterator<User> iterator = friends.iterator();
		while (iterator.hasNext()) {
			User u = iterator.next();
			int i = u.getUser_id();
		    if (checkFriend(user_id, i) != "true") {
		        iterator.remove();
		    }
		}
		
		return friends;
	}
	
	public List<Vector> getAllFriendPostsWithUsers(int user_id){
		List<Vector> posts = new ArrayList<>();
		String query = "select * from post, user where post.user_id = user.user_id ORDER BY date DESC";
		try {
			conn = new DBContext().getConnection();
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Vector v = new Vector();
				v.addElement(rs.getInt(1)); //post_id
				v.addElement(rs.getInt(2)); //user_id
				v.addElement(rs.getString(3)); //date
				v.addElement(rs.getInt(4)); //likes
				v.addElement(rs.getString(5)); //text
				v.addElement(rs.getString(6)); //images
				v.addElement(rs.getString(8)); //username posted
				v.addElement(0); //comments
				posts.add(v);
			}
			
			if(conn!=null)
                conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		//Loai bo nhung posts khong phai cua ban be
			Iterator<Vector> iterator = posts.iterator();
			while (iterator.hasNext()) {
				Vector post = iterator.next();
				String str = post.get(1).toString();
				int user_id_of_post = Integer.parseInt(str);
			    if (checkFriend(user_id, user_id_of_post) == "false") {
			        iterator.remove();
			    }
			}
		
		
		return posts;
	}
	
	public List<User> searchUsersbyUsername(String username){
		List<User> users = new ArrayList<>();
		if (username == "")
			return users;
		
		String query  = "select * from user where username LIKE '%" + username +"%'";
		try {
			conn = new DBContext().getConnection();
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				users.add(new User(rs.getInt(1), rs.getString(2), rs.getString(3)));
			}
			
			if(conn!=null)
                conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return users;
	}
	
	public void sendMessage(int user_id1, int user_id2, String text) {
		
		if(text == "") {
			return;
		}
		
		try {
			conn = new DBContext().getConnection();
			
			java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());//lay thoi gian hien tai
			//Dung preparedStatement de tranh mat dau backslash trong mysql
			PreparedStatement preparedStatement = conn.prepareStatement("insert into message (user_id1, user_id2, time, text) values (?, ?, ?, ?)");
			preparedStatement.setInt(1, user_id1);
			preparedStatement.setInt(2, user_id2);
			preparedStatement.setTimestamp(3, date);
			preparedStatement.setString(4, text);
			preparedStatement.executeUpdate();

			if(conn!=null)
                conn.close();

		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	public List<Message> getAllMessages(int user_id1, int user_id2){
		List<Message> messages = new ArrayList<>();
		String query  = "select * from message where (user_id1=" + user_id1 + " and user_id2=" + user_id2 + ") or (user_id1=" + user_id2 + " and user_id2=" + user_id1 + ") ORDER BY time ASC";
		try {
			conn = new DBContext().getConnection();
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				messages.add(new Message(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4)));
			}
			
			if(conn!=null)
                conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return messages;
	}
	
//	public static void main(String[] args) {
//		DAO dao = new DAO();
//		List<Message> list = dao.getAllMessages(1, 2);
//	}
}
