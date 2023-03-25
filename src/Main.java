import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    static Scanner sc=new Scanner(System.in);
    public static void main(String[] args) {
        System.out.println("----------- Welcome -----------");
        char designation='0';
        do {
            System.out.print("Enter Login Id: ");
            String uId=sc.nextLine();
            System.out.print("Enter Password: ");
            String password=sc.nextLine();
            System.out.println("-------------------------------------------------------------");
            System.out.print("Menu:\n1. Admin\n2. Worker\n3. Customer\n4. Exit\nEnter Your Choice: ");
            switch (sc.nextLine()){
                case "1":
                    designation='A';
                    break;
                case "2":
                    designation='W';
                    break;
                case "3":
                    designation='C';
                    break;
                case "4":
                    System.out.println("Thank You ");
                    return;
                default:
                    System.out.println("Enter valid option.");
                    continue;
            }
            if(User.validate(uId,password, designation)){
                switch (designation){
                    case 'A':
                        displayAdminView();
                        break;
                    case 'W':
                        displayWorkerView();
                        break;
                    case 'C':
                        displayCustomerView(uId);
                        break;
                }
            }
            else {
                System.out.println("Id Password is Incorrect.");
            }
        }while (designation!='E');

    }

    private static void displayAdminView() {
        String choice;
        do{
            System.out.println("-------------------------------------------------------------");
            System.out.println("Menu:\n1. Add New User\n2. Add New Cloth\n3. Make Transaction \n4. Logout");
            choice= sc.nextLine();
            switch (choice) {
                case "1":
                    //TODO validate al details.
                    System.out.print("Enter User Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter Mail: ");
                    String mail = sc.nextLine();
                    System.out.print("Enter Login id: ");
                    String loginId = sc.nextLine();
                    System.out.print("Enter Login Password: ");
                    String password = sc.nextLine();
                    System.out.print("Enter Contact.no: ");
                    long contactNo = sc.nextLong();
                    sc.nextLine();
                    System.out.print("Enter Address: ");
                    String address = sc.nextLine();
                    while (true) {
                        System.out.println("Enter User Designation :\n1. Admin\n2. Worker\n3. Customer");
                        boolean b = true;
                        switch (sc.nextLine()) {
                            case "1":
                                User admin = new Admin(name, address, mail, loginId, password, contactNo,"");
                                admin.addUser();
                                break;
                            case "2":
                                User worker = new Worker(name, address, mail, loginId, password, contactNo,"");
                                worker.addUser();
                                break;
                            case "3":
                                System.out.println("Enter Company Name: ");
                                String companyName=sc.nextLine();
                                User customer = new Customer(name, address, mail, loginId, password, contactNo,companyName);
                                customer.addUser();
                                break;
                            default:
                                System.out.println("Enter Valid Option");
                                b = false;
                        }
                        if (b) break;
                    }
                case "2":
                    //TODO validate al details.
                    System.out.print("Enter Pick: ");
                    int pick=sc.nextInt();
                    System.out.print("Enter Weft_count: ");
                    int weftCount=sc.nextInt();
                    System.out.print("Enter Warp_count: ");
                    int warpCount=sc.nextInt();
                    System.out.print("Enter Warp_ends: ");
                    int warpEnds=sc.nextInt();
                    System.out.print("Enter Width: ");
                    int width=sc.nextInt();
                    System.out.print("Enter Pick_rate: ");
                    int pickRate=sc.nextInt();
                    System.out.print("Enter Read Value: ");
                    int read=sc.nextInt();
                    sc.nextLine();
                    String clothName=""+warpEnds+"_"+weftCount+"x"+warpCount+"_"+width+" ";
                    Cloth c=new Cloth(pick,weftCount,warpCount,warpEnds,width,pickRate,read,clothName);
                    if(c.addCloth())
                        System.out.println("Cloth Added Successful");
                    else System.out.println("Try Again.");
                    break;
                case "3":
                    displayWorkerView();
                    break;
                case "4":
                    System.out.println("Logged Out.");
                    return;
                }
            }while (true);
    }

    private static void displayWorkerView() {
        while(true){
            System.out.println("-------------------------------------------------------------");
            System.out.println("Menu:\n1. Cloth credit\n2. Weft debit\n3. Warp debit \n4. Amount debit \n5. Display \n6. Exit");
            Transaction t;
            String clothName;
            String companyName;
            switch (sc.nextLine()){
                case "1":
                    System.out.println("Enter Company Name: ");
                    companyName=sc.nextLine();
                    System.out.println("Enter Cloth Name: ");
                    clothName=sc.nextLine();
                    float meter=inputMeter();
                    t=new Transaction(meter,0f);
                    try {
                        t.insertCreditedCloth(companyName,clothName);
                    } catch (SQLException e) {
                        System.out.println("Not Added\n"+e);
                    }
                    break;
                case "2":
                    System.out.println("Enter Company Name: ");
                    companyName=sc.nextLine();
                    System.out.println("Enter Cloth Name: ");
                    clothName=sc.nextLine();
                    float weight=inputWeight();
                    t=new Transaction(0f,weight);
                    try {
                        t.insertWeftCredit(companyName,clothName);
                    } catch (SQLException e) {
                        System.out.println("Not Added\n"+e);
                    }
                    break;
                case "3":
                    System.out.println("Enter Company Name: ");
                    companyName=sc.nextLine();
                    meter=inputMeter();
                    t=new Transaction(meter,0f);
                    try {
                        t.insertWarpCredit(companyName);
                    } catch (SQLException e) {
                        System.out.println("Not Added\n"+e);
                    }
                    break;
                case "4":
                    System.out.println("Enter Company Name: ");
                    companyName=sc.nextLine();
                    System.out.print("Enter Amount: ");
                    float amount= sc.nextFloat();
                    sc.nextLine();
                    t=new Transaction(amount);
                    try {
                        t.insertAmountDebit(companyName);
                    } catch (SQLException e) {
                        System.out.println("Not Added\n"+e);
                    }
                    break;
                case "5":
                    System.out.println("Enter Company Name: ");
                    companyName=sc.nextLine();
                    System.out.print("Enter the Starting Date: ");
                    String date=sc.nextLine();
                    try {
                        Worker.display(companyName,date);
                    } catch (Exception e) {
                        System.out.println("Error\n"+e);
                    }
                    break;
                case "6":
                    return;
                default:
                    System.out.println("Enter Valid Input.");
            }
        }
    }

    private static void displayCustomerView(String id) {
        System.out.println("Enter the Starting Date: ");
        String date=sc.nextLine();
        try {
            Customer.display(id,date);
        } catch (SQLException e) {
            System.out.println("Error\n"+e);
        }
    }
    public static float inputMeter(){
        System.out.println("Enter Meter: ");
        float meter=sc.nextFloat();
        sc.nextLine();
        return meter;
    }
    public static float inputWeight(){
        System.out.println("Enter Weigh: ");
        float weight=sc.nextFloat();
        sc.nextLine();
        return weight;
    }

}