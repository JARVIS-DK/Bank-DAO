package bank.puthusu.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/banking_system";
    private static final String USER = "root";
    private static final String PASSWORD = "1234";

    private static Connection connection;


    private DataBaseConnection() {}

    public static Connection getConnection() {
        Connection conn = null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch(Exception e){
            e.printStackTrace();
        }

        String url = "jdbc:mysql://localhost:3308/banking_system";
        try{
            conn = DriverManager.getConnection(url, "root", "1234");
        }catch(Exception e){
            e.printStackTrace();
        }

        return conn;
////        if (connection == null) {
//            try {
//                Class.forName("com.mysql.cj.jdbc.Driver");
//                connection = DriverManager.getConnection(URL, USER, PASSWORD);
//                System.out.println("✅ Database Connected Successfully!");
//            } catch (ClassNotFoundException | SQLException e) {
//                System.out.println("❌ Database Connection Failed: " + e.getMessage());
//            }
////        }
//        return connection;
    }
}
