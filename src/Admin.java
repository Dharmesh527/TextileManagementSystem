public class Admin extends User{

    public Admin(String name, String address, String mail, String loginId, String password, long contactNo,String companyName) {
        super(name, address, mail, loginId, password, companyName,contactNo, 'A');
    }
}
