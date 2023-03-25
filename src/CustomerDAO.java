import java.sql.*;
import java.util.LinkedList;
import java.util.*;
public class CustomerDAO {

    public static String fetchCompanyName(String id) throws SQLException {
        String query="select Company_name from users where Login_id='"+id+"';";
        Connection con=ConnectionDB.createConnection();
        Statement stmt=con.createStatement();
        System.out.println(query);
        ResultSet rs=stmt.executeQuery(query);
        String companyName="";
        while (rs.next()){
            companyName=rs.getString("Company_name"+"");
        }
        return companyName;
    }
}
