package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DB {

    private static Connection Conn;

    public static String hostname = "localhost:5432";
    public static String database = "aa2";

    public static Connection Connect() throws SQLException {

        if (Conn == null) {

            String url = "jdbc:postgresql://" + hostname + "/" + database;
            Properties props = new Properties();
            props.setProperty("user", "abe");
            props.setProperty("password", "1234");
            Conn = DriverManager.getConnection(url, props);
        }
        return Conn;
        
    }

}
