import java.util.Scanner;
import java.sql.*;

public class Passenger extends User {
    Passenger(String FirstName, String LastName, String Password, String Email, String Residence, String Nationality,
            char Sex, int Age, int PhoneNumber) {
        super(FirstName, LastName, Password, Email, Residence, Nationality, Sex, Age, PhoneNumber);
    }

    Scanner input = new Scanner(System.in);

    @Override
    boolean Login(int phonenumber, String passwored) throws ClassNotFoundException, SQLException {
        boolean validate = false;
        int Pphonenumber;
        String Ppasswored = null;
        System.out.println("enter your phone number: ");
        phonenumber = input.nextInt();
        System.out.println("enter your password: ");
        passwored = input.next();
        DatabaseConnection gConnection = new DatabaseConnection();
        Connection conn = gConnection.Connection();
        PreparedStatement pt = null;
        pt = conn.prepareStatement("select phone_no, password from passengertbl where phone_no=" + phonenumber);
        ResultSet rSet = pt.executeQuery();
        while (rSet.next()) {
            Pphonenumber = rSet.getInt("phone_no");
            Ppasswored = rSet.getString("password");
        }
        if (Ppasswored != null) {
            System.out.println("checking.....");
            if (Ppasswored.equals(passwored)) {
                validate = true;
                rSet.close();
            } else {
                validate = false;
                System.out.println("Incorrect password");
            }
        } else {
            System.out.println("checking");
            System.out.println("Incorrect phonenuber");
            validate = false;
        }
        return validate;

    }

    @Override
    String UpdatePersonalInfo(int phonenumber, String passwored) {
        // TODO Auto-generated method stub
        return null;
    }

    void ViewFlightSchedule() {
        DatabaseConnection connection = new DatabaseConnection();
        // connection.getConnection();
        // strSelect = "select * from journeytbl";
        // do the methods correctly
    };

    String BookflightTicket(int PhoneNumber, String Password, int JourneyId, int TicketId, int ScheduleId, String Class,
            int SeatNumber) {

        return "Ticket booked successfully";
    };

    void ShowMyTicket(int PhoneNumber, String Password, int TicketId) {
        System.out.print("Enter your phone number:");
        PhoneNumber = input.nextInt();
        System.out.print("Enter your passwored");
        Password = input.next();
        System.out.print("Enter the ticket that you selected");
        TicketId = input.nextInt();
    };

    String cancelTicket(int phoneNumber, String Password, int TicketId) {
        System.out.print("Enter your phone number");
        PhoneNumber = input.nextInt();
        System.out.print("Enter your passwored");
        Password = input.next();
        System.out.print("Enter your ticket ID");
        TicketId = input.nextInt();

        return "Ticket canceled successfuly";
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
    };

}
