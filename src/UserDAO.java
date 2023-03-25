import java.sql.*;
import java.util.ArrayList;

public class UserDAO {
    public static void addUser(String name, String address, String mail, String loginId, String password, long contactNo, char designation, String compayName) throws SQLException {
        Connection con=ConnectionDB.createConnection();
        String query="INSERT INTO USERS (user_name,Login_id,Login_password,User_contact,User_mail,address,designation,Company_name)"
                + "VALUES( '"+name+"','"+loginId +"','"+ password +"',"
                +contactNo+",'"+mail+"','"+address+"','"+designation+"', '"+compayName+"');";
        Statement stmt=con.createStatement();
        stmt.execute(query);
    }

    public static String validate(String uId, String password, char designation) throws SQLException {
        Connection con=ConnectionDB.createConnection();
        String query="SELECT Login_password FROM users where designation= '"+designation+"' and Login_id = '"+uId+"';";
        Statement stmt=con.createStatement();
        ResultSet rs=stmt.executeQuery(query);
        String pass=null;
        if(rs!=null){
            pass=rs.getString("password");
        }
        return pass;
    }
}
