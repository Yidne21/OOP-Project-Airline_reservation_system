import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.IOException;
import java.sql.*;

public class Passenger extends User {
    static int BookedTicket = 0;
    static int canceledTicket = 0;
    DatabaseConnection gConnection = new DatabaseConnection();

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
    String UpdatePersonalInfo(int phonenumber, String passwored) throws ClassNotFoundException {

        return null;
    }

    // void ViewFlightSchedule() {
    // DatabaseConnection connection = new DatabaseConnection();

    // }; it not necessary the passenger can see their flight in the main menu

    void BookflightTicket(int PhoneNumber, String Password, int JourneyId, int TicketId, int ScheduleId, String Class,
            int SeatNumber) throws ClassNotFoundException, IOException {
        try {

            System.out.print("Enter your PhoneNumber: ");
            PhoneNumber = input.nextInt();
            System.out.print("Enter JourneyId that you selected from the avilable journey: ");
            JourneyId = input.nextInt();
            System.out.print("Enter TicketId that you seleceted from the avilable ticket: ");
            TicketId = input.nextInt();
            System.out.print("Enter your ScheduleId from the flight schedule : ");
            ScheduleId = input.nextInt();

            Connection conn = gConnection.Connection();
            Statement stmt = conn.createStatement();
            String sqlInsert = "insert into bookedtbl values (" + PhoneNumber + "," + TicketId + "," + ScheduleId
                    + ","
                    + JourneyId + ")";
            int bookedTicket = stmt.executeUpdate(sqlInsert);
            BookedTicket = bookedTicket;
            System.err.println("Your Ticket is booked successfully");

        } catch (InputMismatchException e) {
            System.out.println("something went wrong please Enter the integer value only!");
        } catch (SQLException e) {
            System.out.println("Please maake sure you inserted the correct information");
        }
    };

    void ShowMyTicket(int PhoneNumber, String Password, int TicketId) throws ClassNotFoundException, IOException {
        try {
            System.out.print("Enter your phone number: ");
            PhoneNumber = input.nextInt();
            Connection conn = gConnection.Connection();
            Statement statement = (Statement) conn.createStatement();
            String query = "SELECT * from tickettbl t INNER JOIN bookedtbl b ON t.ticketID = b.ticketID where b.phone_no="
                    + PhoneNumber;
            ResultSet rset = statement.executeQuery(query);
            System.out.printf(
                    "\nPhone number   TicketID\t  class\t     \tstatus\t      \tseat_no\t   \tscheduleId\t   \tjourneyId");
            while (rset.next()) {
                int PH = rset.getInt("phone_no");
                int ticketId = rset.getInt("ticketID");
                String Class = rset.getString("class");
                String Status = rset.getString("status");
                int SeatNumber = rset.getInt("seat_no");
                int scheduleId = rset.getInt("schedulId");
                int journeyId = rset.getInt("journeyId");
                System.out.printf("\n%d\t%d\t%18s\t%10s\t%7d\t%18d\t%15d\n", PH, ticketId, Class, Status,
                        SeatNumber,
                        scheduleId,
                        journeyId);
            }
            System.out.println();
        } catch (InputMismatchException e) {
            System.out.println("something went wrong please try again! please enter integer number only");
        } catch (SQLException e) {
            System.out.println("something went wrong please try again");
        }

    };

    String cancelTicket(int phoneNumber, String Password, int TicketId) {
        int totalNumberOfCanceledTicket = 0;
        System.out.print("Enter your phone number");
        PhoneNumber = input.nextInt();
        System.out.print("Enter your passwored");
        Password = input.next();
        System.out.print("Enter your ticket ID");
        TicketId = input.nextInt();

        canceledTicket = totalNumberOfCanceledTicket;
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
        System.out.print("Enter your FirstName: ");
        FirstName = input.next();
        System.out.print("Enter your LastName: ");
        LastName = input.next();
        System.out.print("Enter your Password: ");
        Password = input.next();
        System.out.print("Enter your Email: ");
        Email = input.next();
        System.out.print("Enter your Residence: ");
        Residence = input.next();
        System.out.print("Enter your Nationality: ");
        Nationality = input.next();
        System.out.print("Enter your Sex: ");
        Sex = input.nextLine();
        System.out.print("Enter your Age: ");
        Age = input.nextInt();
        System.out.print("Enter your PhoneNumber: ");
        PhoneNumber = input.nextInt();

        return null;
    };

}
