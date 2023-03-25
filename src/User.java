import java.sql.SQLException;

public class User {
    private String name,address,mail,loginId,password,companyName;
    private long contactNo;
    private char designation;

    public static boolean validate(){

        return false;
    }
    public User(String name, String address, String mail, String loginId, String password,String companyName, long contactNo, char designation) {
        this.name = name;
        this.address = address;
        this.mail = mail;
        this.loginId = loginId;
        this.password = password;
        this.contactNo = contactNo;
        this.designation = designation;
        this.companyName=companyName;
    }

    public static boolean validate(String uId, String password, char designation) {
        try {
            String pass=UserDAO.validate(uId,password,designation);
            if(pass.equals(password))
                return true;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }
    public void addUser(){
        try{
            UserDAO.addUser(name,address,mail,loginId,password,contactNo,designation,companyName);
            System.out.println("Added Successful");
        } catch (SQLException e) {
            System.out.println("Insertion Failed\n"+e);
        }
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getContactNo() {
        return contactNo;
    }

    public void setContactNo(long contactNo) {
        this.contactNo = contactNo;
    }

    public char getDesignation() {
        return designation;
    }

    public void setDesignation(char designation) {
        this.designation = designation;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public static boolean validateId(String id){
        if(id.length()>=8&&id.length()<=10&&id.matches(".*[^;'\"].*")) {
            return true;
        }
        else {
            System.out.println("Enter valid ID with length between 8-16.");
            return false;
        }
    }
}
