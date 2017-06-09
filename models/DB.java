package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author qwerty
 */
public class DB {
        
       private static Connection Conn;
    
    public static Connection Connect() throws SQLException{
        
        if (Conn == null) {
            
            String url = "jdbc:postgresql://localhost:5432/aa2";
            Properties props = new Properties();
            props.setProperty("user", "abe");
            props.setProperty("password", "1234");
            Conn = DriverManager.getConnection(url, props);
        }        
        return Conn;
    }
    
}
