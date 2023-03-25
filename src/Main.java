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
            if(validUser(uId,password,designation)){
                switch (designation){
                    case 'A':
                        displayAdminView();
                        break;
                    case 'W':
                        displayWorkerView();
                        break;
                    case 'C':
                        displayCustomerView();
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
                    String clothName=""+warpEnds+"_"+weftCount+"x"+warpCount+"_"+width+" ";
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
        System.out.println("Worker");
    }

    private static void displayCustomerView() {
        System.out.println("Customer");
    }

    private static boolean validUser(String uId, String password, char designation) {
        return User.validate(uId,password, designation);
    }
}