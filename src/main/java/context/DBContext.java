
package context;


import java.sql.Connection;
import java.sql.DriverManager;


public class DBContext {
    
	public Connection getConnection()throws Exception {

        Connection conn = null;
        //localhost database
        String url = "jdbc:mysql://localhost:3306/"; 
        String dbName = "socialnetwork"; 
        String strUnicode = "?useUnicode=true&characterEncoding=utf8";
        String driver = "com.mysql.jdbc.Driver";
        String userName = "root"; 
        String password = "";
        
        //online hosted database
//        String url = "jdbc:mysql://us-cdbr-east-04.cleardb.com:3306/"; 
//        String dbName = "heroku_6925db6fe242bf0";
//        String strUnicode = "?useUnicode=true&characterEncoding=utf8";
//        String driver = "com.mysql.jdbc.Driver";
//        String userName = "b80d97ca6ce8e7"; 
//        String password = "255ecfbf";
        
        Class.forName(driver).newInstance();
        return DriverManager.getConnection(url+dbName+strUnicode,userName,password);		
	}

}
 