import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

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
        String query="SELECT Login_password FROM users where designation= '"+designation+"' and Login_id = '"+uId+"';";
        Connection con=ConnectionDB.createConnection();
        Statement stmt=con.createStatement();
        ResultSet rs=stmt.executeQuery(query);
        String pass=null;
        while(rs.next()){
            pass=rs.getString("Login_password");
        }
        return pass;
    }

    public static List<LinkedList<String>> getDisplayDetails(String companyName, String date) throws SQLException {
        String query="SELECT * FROM transaction where Company_name = '"+companyName+"' AND date >= '"+date+"';";
        System.out.println(query);
        ConnectionDB cDB=new ConnectionDB();
        Connection con=ConnectionDB.createConnection();
        Statement stmt= con.createStatement();
        ResultSet rs=stmt.executeQuery(query);
        List<LinkedList<String>> lst= new LinkedList<>();
        while(rs.next()) {
            LinkedList<String> temp=new LinkedList<>();
            temp.add(rs.getDate("Date")+"");
            temp.add(rs.getString("Company_name")+"");
            temp.add(rs.getString("Trans_id")+"");
            temp.add(rs.getString("Cloth_nick_name")+"");
            temp.add(rs.getFloat("amount_credit")+"");
            temp.add(rs.getFloat("amount_debit")+"");
            temp.add(rs.getFloat("amount_balance")+"");
            temp.add(rs.getFloat("warp_credit")+"");
            temp.add(rs.getFloat("warp_debit")+"");
            temp.add(rs.getFloat("warp_balance")+"");
            temp.add(rs.getFloat("weft_credit")+"");
            temp.add(rs.getFloat("weft_debit")+"");
            temp.add(rs.getFloat("weft_balance")+"");
            lst.add(temp);
        }
        return lst;
    }
}
