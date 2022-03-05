import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.IOException;
import java.sql.*;
import java.text.ParseException;

public class Passenger extends User {
    static int BookedTicket = 0;
    static int canceledTicket = 0;
    DatabaseConnection gConnection = new DatabaseConnection();

    Passenger(String FirstName, String LastName, String Password, String Email, String Residence, String Nationality,
            String Sex, Date date_of_birth, int PhoneNumber) {
        super(FirstName, LastName, Password, Email, Residence, Nationality, Sex, date_of_birth, PhoneNumber);
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
            String sqlInsert = "insert into bookedtbl values (?,?,?,?)";
            PreparedStatement pstmt = conn.prepareStatement(sqlInsert);
            pstmt.setInt(1, PhoneNumber);
            pstmt.setInt(2, JourneyId);
            pstmt.setInt(3, TicketId);
            pstmt.setInt(4, ScheduleId);
            int bookedTicket = pstmt.executeUpdate(sqlInsert);
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
        System.out.println("Enter your phone number: ");
        PhoneNumber = input.nextInt();
        System.out.println("Enter your passwored: ");
        Password = input.next();

    }

    @Override
    String Register() throws ClassNotFoundException, IOException, ParseException, SQLException {
        String succed = null;
        try {
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
            Sex = input.next();
            System.out.print("Enter your PhoneNumber: ");
            PhoneNumber = input.nextInt();
            System.out.println("Enter your date of birth in the format yyyy-mm-dd");
            String sDate1 = input.next();
            date_of_birth = Date.valueOf(sDate1);
            Connection conn = gConnection.Connection();
            String sqlInsert = "insert into passengertbl (fname, lname, password, email, residence, nationality, sex, date_of_birth, phone_no)"
                    + " value (?,?,?,?,?,?,?,?,?)";
            PreparedStatement pstmt = conn.prepareStatement(sqlInsert);

            pstmt.setString(1, FirstName);
            pstmt.setString(2, LastName);
            pstmt.setString(3, Password);
            pstmt.setString(4, Email);
            pstmt.setString(5, Residence);
            pstmt.setString(6, Nationality);
            pstmt.setString(7, Sex);
            pstmt.setDate(8, date_of_birth);
            pstmt.setInt(9, PhoneNumber);
            pstmt.executeUpdate();
            succed = "Registered succssesfuly, now you can login to your user account and get our servicecs";

        } catch (SQLException e) {
            succed = "oops something went wrong please try" + e;
        }

        return succed;
    };

}
