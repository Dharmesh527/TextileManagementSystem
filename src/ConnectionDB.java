import java.sql.*;

public class
ConnectionDB {
    static Connection  con;
    public static Connection createConnection(){
        if(con==null)
            try {
                con= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/tmangement","root","root");
            }
            catch (SQLException e){
                System.err.println("DB Connection Failed");
            }
        return con;
    }
}
