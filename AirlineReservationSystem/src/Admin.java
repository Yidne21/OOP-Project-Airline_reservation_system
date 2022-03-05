import java.sql.Date;
import java.sql.SQLException;
import java.util.Scanner;

public class Admin extends User {
    float salary;
    String role;

    Admin(String FirstName, String LastName, String Password, String Email, String Residence, String Nationality,
            String Sex, Date age, int PhoneNumber, float salary, String role) {
        super(FirstName, LastName, Password, Email, Residence, Nationality, Sex, age, PhoneNumber);
        this.salary = salary;
        this.role = role;
    }

    Scanner input = new Scanner(System.in);

    @Override
    boolean Login(int phonenumber, String passwored) throws ClassNotFoundException, SQLException {
        System.out.println("enter phoneNumber: ");
        phonenumber = input.nextInt();
        System.out.println("enter password: ");
        passwored = input.next();
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    String UpdatePersonalInfo(int phonenumber, String passwored) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    void ViewFlightInformation() {
        // TODO Auto-generated method stub

    }

    @Override
    void showMyPersonalInfo(int Phone_no, String Password) {
        // TODO Auto-generated method stub

    }

    @Override
    String Register() {
        // TODO Auto-generated method stub
        return null;
    }

}
