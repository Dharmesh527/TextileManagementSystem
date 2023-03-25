import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class Customer extends User{
    public Customer(String name, String address, String mail, String loginId, String password, long contactNo,String companyName) {
        super(name, address, mail, loginId, password,companyName, contactNo, 'C');
    }

    public static boolean validateCustomer(){
        //todo validateCustomer
        return false;
    }

    public static void display(String id,String date) throws SQLException {
        String companyName=CustomerDAO.fetchCompanyName(id);
        User.display(companyName,date);
    }
}
