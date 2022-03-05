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
        System.out.println("Which information do you want to update? ");
        System.out.println("ENTER 1 TO UPDATE YOUR FIRST NAME: ");
        System.out.println("ENTER 2 TO UPDATE YOUR LAST NAME: ");
        System.out.println("ENTER 3 TO UPDATE YOUR Password: ");
        System.out.println("ENTER 4 TO UPDATE YOUR Email: ");
        System.out.println("ENTER 5 TO UPDATE YOUR Residence: ");
        System.out.println("ENTER 6 TO UPDATE YOUR Nationality: ");
        System.out.println("ENTER 7 TO UPDATE YOUR Sex: ");
        System.out.println("ENTER 8 TO UPDATE YOUR date of birth: ");
        String success = null;
        int key = input.nextInt();
        switch (key) {
            case 1:
                try {
                    System.out.print("Enter your PhoneNumber: ");
                    PhoneNumber = input.nextInt();
                    System.out.print("Enter your FirstName: ");
                    FirstName = input.next();
                    Connection conn = gConnection.Connection();
                    String strUpdate = "update passengertbl set fname = ? where phone_no = ?";
                    PreparedStatement preparedStmt = conn.prepareStatement(strUpdate);
                    preparedStmt.setString(1, FirstName);
                    preparedStmt.setInt(2, PhoneNumber);
                    preparedStmt.executeUpdate();
                    success = "updated successfuly";
                } catch (Exception e) {
                    success = "oops some thing went wrong please try again" + e;
                }
                break;
            case 2:
                try {
                    System.out.print("Enter your PhoneNumber: ");
                    PhoneNumber = input.nextInt();
                    System.out.print("Enter your LastName: ");
                    LastName = input.next();
                    Connection conn = gConnection.Connection();
                    String strUpdate = "update passengertbl set lname =? where phone_no =?";
                    PreparedStatement preparedStmt = conn.prepareStatement(strUpdate);
                    preparedStmt.setString(1, LastName);
                    preparedStmt.setInt(2, PhoneNumber);
                    preparedStmt.executeUpdate();
                    success = "updated successfuly";
                } catch (Exception e) {
                    success = "oops some thing went wrong please try again" + e;
                }
                break;
            case 3:
                try {
                    System.out.print("Enter your PhoneNumber: ");
                    PhoneNumber = input.nextInt();
                    System.out.print("Enter your Password: ");
                    Password = input.next();
                    Connection conn = gConnection.Connection();
                    String strUpdate = "update passengertbl set password = ? where phone_no = ?";
                    PreparedStatement preparedStmt = conn.prepareStatement(strUpdate);
                    preparedStmt.setString(1, passwored);
                    preparedStmt.setInt(2, PhoneNumber);

                    // execute the java preparedstatement
                    preparedStmt.executeUpdate();
                    success = "updated successfuly";
                } catch (Exception e) {
                    success = "oops some thing went wrong please try again" + e;
                }
                break;
            case 4:
                try {
                    System.out.print("Enter your PhoneNumber: ");
                    PhoneNumber = input.nextInt();
                    System.out.print("Enter your Email: ");
                    Email = input.next();
                    Connection conn = gConnection.Connection();
                    String strUpdate = "update passengertbl set email = ? where phone_no = ?";
                    PreparedStatement preparedStmt = conn.prepareStatement(strUpdate);
                    preparedStmt.setString(1, Email);
                    preparedStmt.setInt(2, PhoneNumber);

                    // execute the java preparedstatement
                    preparedStmt.executeUpdate();
                    success = "updated successfuly";
                } catch (Exception e) {
                    success = "oops some thing went wrong please try again" + e;
                }
                break;
            case 5:
                try {
                    System.out.print("Enter your PhoneNumber: ");
                    PhoneNumber = input.nextInt();
                    System.out.print("Enter your Residence: ");
                    Residence = input.next();
                    Connection conn = gConnection.Connection();
                    String strUpdate = "update passengertbl set residence = ? where phone_no = ?";
                    PreparedStatement preparedStmt = conn.prepareStatement(strUpdate);
                    preparedStmt.setString(1, Residence);
                    preparedStmt.setInt(2, PhoneNumber);

                    // execute the java preparedstatement
                    preparedStmt.executeUpdate();
                    success = "updated successfuly";
                } catch (Exception e) {
                    success = "oops some thing went wrong please try again" + e;
                }
                break;
            case 6:
                try {
                    System.out.print("Enter your PhoneNumber: ");
                    PhoneNumber = input.nextInt();
                    System.out.print("Enter your Nationality: ");
                    Nationality = input.next();
                    Connection conn = gConnection.Connection();
                    String strUpdate = "update passengertbl set nationality = ? where phone_no = ?";
                    PreparedStatement preparedStmt = conn.prepareStatement(strUpdate);
                    preparedStmt.setString(1, Nationality);
                    preparedStmt.setInt(2, PhoneNumber);
                    preparedStmt.executeUpdate();
                    success = "updated successfuly";
                } catch (Exception e) {
                    success = "oops some thing went wrong please try again" + e;
                }
                break;
            case 7:
                try {
                    System.out.print("Enter your PhoneNumber: ");
                    PhoneNumber = input.nextInt();
                    System.out.print("Enter your Sex: ");
                    Sex = input.next();
                    Connection conn = gConnection.Connection();
                    String strUpdate = "update passengertbl set sex = ? where phone_no = ?";
                    PreparedStatement preparedStmt = conn.prepareStatement(strUpdate);
                    preparedStmt.setString(1, Sex);
                    preparedStmt.setInt(2, PhoneNumber);

                    // execute the java preparedstatement
                    preparedStmt.executeUpdate();
                    success = "updated successfuly";
                } catch (Exception e) {
                    success = "oops some thing went wrong please try again" + e;
                }
                break;
            case 8:
                try {
                    System.out.print("Enter your PhoneNumber: ");
                    PhoneNumber = input.nextInt();
                    System.out.println("Enter your date of birth in the format yyyy-mm-dd");
                    String sDate1 = input.next();
                    date_of_birth = Date.valueOf(sDate1);
                    Connection conn = gConnection.Connection();
                    String strUpdate = "update passengertbl set date_of_birth = ? where phone_no = ? "
                            + PhoneNumber;
                    PreparedStatement preparedStmt = conn.prepareStatement(strUpdate);
                    preparedStmt.setDate(1, date_of_birth);
                    preparedStmt.setInt(2, PhoneNumber);

                    // execute the java preparedstatement
                    preparedStmt.executeUpdate();
                    success = "updated successfuly";
                } catch (Exception e) {
                    success = "oops some thing went wrong please try again" + e;
                }
                break;

            default:
                System.out.println("incorrect choice please try again!");
                break;
        }

        return success;
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

    String cancelTicket(int phoneNumber, int TicketId) {
        boolean iscanceled = false;
        String success = null;
        try {
            System.out.print("Enter your phone number");
            PhoneNumber = input.nextInt();
            System.out.print("Enter your ticket ID");
            TicketId = input.nextInt();
            String query = "delete from bookedtbl where phone_no = ?";
            Connection conn = gConnection.Connection();
            PreparedStatement preparedStmt;
            preparedStmt = conn.prepareStatement(query);
            preparedStmt.setInt(1, PhoneNumber);
            iscanceled = preparedStmt.execute();
            success = "Ticket canceled successfuly";
        } catch (SQLException | ClassNotFoundException e) {
            success = "Ticket canceled failed please try again" + e;
        }
        if (iscanceled == true) {
            canceledTicket++;
        }
        return success;
    }

    @Override
    void showMyPersonalInfo(int Phone_no, String Password) {
        System.out.println("Enter your phone number: ");
        PhoneNumber = input.nextInt();
        Connection conn;
        try {
            conn = gConnection.Connection();
            Statement statement = (Statement) conn.createStatement();
            String query = "select fname, lname, email, residence, nationality, sex, date_of_birth, phone_no from passengertbl where phone_no="
                    + PhoneNumber;
            ResultSet rset = statement.executeQuery(query);
            System.out.printf(
                    "\nPhone number\t First Name\t Last Name\t \tEmail Residence \tNationality \t sex \t Birth date \t");
            while (rset.next()) {
                FirstName = rset.getString("fname");
                LastName = rset.getString("lname");
                PhoneNumber = rset.getInt("phone_no");
                Email = rset.getString("email");
                Residence = rset.getString("residence");
                Nationality = rset.getString("nationality");
                Sex = rset.getString("sex");
                date_of_birth = rset.getDate("date_of_birth");

                System.out.printf("\n%d \t%s \t%s \t%s\t \t%s \t%s \t%s \t%s\n", PhoneNumber, FirstName, LastName,
                        Email, Residence, Nationality, Sex, date_of_birth);
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Oops some thing went wrong please try again");
        }
        System.out.println();

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
            succed = "oops something went wrong please try";
        }

        return succed;
    };

}
