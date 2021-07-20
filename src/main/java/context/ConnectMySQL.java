package context;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectMySQL {
	public Connection getConnection()throws Exception {

        Connection conn = null;
        String url = "jdbc:mysql://localhost:3306/"; 
        String dbName = "socialnetwork"; 
        String strUnicode = "?useUnicode=true&characterEncoding=utf8";
        String driver = "com.mysql.jdbc.Driver";
        String userName = "root"; 
        String password = "";
		
        return DriverManager.getConnection(url+dbName+strUnicode,userName,password);		
	}
}
