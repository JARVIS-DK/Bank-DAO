package bank.palasu.db;

import java.sql.Connection;
import java.sql.DriverManager;

public class DataBaseConnection {
    public Connection createConnection(){
        Connection conn = null;

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch(Exception e){
            e.printStackTrace();
        }

        String url = "jdbc:mysql://localhost:3308/bank";
        try{
            conn = DriverManager.getConnection(url, "root", "1234");
        }catch(Exception e){
            e.printStackTrace();
        }

        return conn;
    }
}
