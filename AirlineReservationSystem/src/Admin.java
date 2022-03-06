import java.sql.*;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Admin extends User {
    float salary;
    String role;
    DatabaseConnection gConnection = new DatabaseConnection();

    Admin(String FirstName, String LastName, String Password, String Email, String Residence, String Nationality,
            String Sex, Date age, int PhoneNumber, float salary, String role) {
        super(FirstName, LastName, Password, Email, Residence, Nationality, Sex, age, PhoneNumber);
        this.salary = salary;
        this.role = role;
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
        pt = conn.prepareStatement("select phone_no, password from admintbl where phone_no=" + phonenumber);
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
        System.out.println("Which information do you want to update? ");
        System.out.println("ENTER 1 TO UPDATE YOUR FIRST NAME: ");
        System.out.println("ENTER 2 TO UPDATE YOUR LAST NAME: ");
        System.out.println("ENTER 3 TO UPDATE YOUR Password: ");
        System.out.println("ENTER 4 TO UPDATE YOUR Email: ");
        System.out.println("ENTER 5 TO UPDATE YOUR Sex: ");
        System.out.println("ENTER 6 TO UPDATE YOUR date of birth: ");
        String success = null;
        int key = input.nextInt();
        switch (key) {
            case 1:
                try {
                    System.out.print("Enter your PhoneNumber: ");
                    PhoneNumber = input.nextInt();
                    System.out.print("Enter your new FirstName: ");
                    FirstName = input.next();
                    Connection conn = gConnection.Connection();
                    String strUpdate = "update admintbl set fname = ? where phone_no = ?";
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
                    System.out.print("Enter your new LastName: ");
                    LastName = input.next();
                    Connection conn = gConnection.Connection();
                    String strUpdate = "update admintbl set lname =? where phone_no =?";
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
                    System.out.print("Enter your new Password: ");
                    Password = input.next();
                    Connection conn = gConnection.Connection();
                    String strUpdate = "update admintbl set password = ? where phone_no = ?";
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
                    System.out.print("Enter your new Email: ");
                    Email = input.next();
                    Connection conn = gConnection.Connection();
                    String strUpdate = "update admintbl set email = ? where phone_no = ?";
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
                    System.out.print("Enter your Sex: ");
                    Sex = input.next();
                    Connection conn = gConnection.Connection();
                    String strUpdate = "update admintbl set sex = ? where phone_no = ?";
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
            case 6:
                try {
                    System.out.print("Enter your PhoneNumber: ");
                    PhoneNumber = input.nextInt();
                    System.out.println("Enter your date of birth in the format yyyy-mm-dd");
                    String sDate1 = input.next();
                    date_of_birth = Date.valueOf(sDate1);
                    Connection conn = gConnection.Connection();
                    String strUpdate = "update admintbl set date_of_birth = ? where phone_no = ? "
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

    @Override
    void showMyPersonalInfo(int Phone_no, String Password) {
        System.out.println("Enter your phone number: ");
        PhoneNumber = input.nextInt();
        Connection conn;
        try {
            conn = gConnection.Connection();
            Statement statement = (Statement) conn.createStatement();
            String query = "select fname, lname, email, sex, date_of_birth, phone_no from admintbl where phone_no="
                    + PhoneNumber;
            ResultSet rset = statement.executeQuery(query);
            System.out.printf(
                    "\nPhone number\t First Name\t Last Name\t \tEmail \t sex \t Birth date \t");
            while (rset.next()) {
                FirstName = rset.getString("fname");
                LastName = rset.getString("lname");
                PhoneNumber = rset.getInt("phone_no");
                Email = rset.getString("email");
                Sex = rset.getString("sex");
                date_of_birth = rset.getDate("date_of_birth");

                System.out.printf("\n%d \t%s \t%s \t%s\t \t%s \t%s\n", PhoneNumber, FirstName, LastName,
                        Email, Sex, date_of_birth);
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Oops some thing went wrong please try again" + e);
        }
        System.out.println();

    }

    @Override
    String Register() throws ClassNotFoundException {
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
            System.out.print("Enter your Sex: ");
            Sex = input.next();
            System.out.print("Enter your PhoneNumber: ");
            PhoneNumber = input.nextInt();
            System.out.println("Enter your date of birth in the format yyyy-mm-dd");
            String sDate1 = input.next();
            date_of_birth = Date.valueOf(sDate1);
            Connection conn = gConnection.Connection();
            String sqlInsert = "insert into admintbl (fname, lname, password, email, sex, date_of_birth, phone_no)"
                    + " value (?,?,?,?,?,?,?)";
            PreparedStatement pstmt = conn.prepareStatement(sqlInsert);

            pstmt.setString(1, FirstName);
            pstmt.setString(2, LastName);
            pstmt.setString(3, Password);
            pstmt.setString(4, Email);
            pstmt.setString(5, Sex);
            pstmt.setDate(6, date_of_birth);
            pstmt.setInt(7, PhoneNumber);
            pstmt.executeUpdate();
            succed = "Registered succssesfuly, now you can login to your user account and get our servicecs";

        } catch (SQLException e) {
            succed = "oops something went wrong please try";
        }

        return succed;
    }

    void ViewFlightInformation() throws ClassNotFoundException {
        try {
            Connection conn = gConnection.Connection();
            Statement statement = (Statement) conn.createStatement();
            String query = "SELECT fname,lname,email,passengertbl.phone_no,ticketID,schedulId,journeyId FROM passengertbl INNER JOIN bookedtbl ON passengertbl.phone_no=bookedtbl.phone_no;";
            ResultSet rset = statement.executeQuery(query);
            System.out.printf(
                    "\nFirst name   Last name\t  Email\t     \tPhone Number\t      \tticket ID\t   \tscheduleId\t   \tjourneyId");
            while (rset.next()) {
                int PH = rset.getInt("passengertbl.phone_no");
                String FirstName = rset.getString("fname");
                String LastName = rset.getString("lname");
                String Email = rset.getString("email");
                int TicketId = rset.getInt("ticketID");
                int scheduleId = rset.getInt("schedulId");
                int journeyId = rset.getInt("journeyId");
                System.out.printf("\n%s\t%s\t%18s\t%10d\t%7d\t%18d\t%15d\n", FirstName, LastName, Email, PH, TicketId,
                        scheduleId,
                        journeyId);
            }
            System.out.println();
        } catch (InputMismatchException e) {
            System.out.println("something went wrong please try again! please enter integer number only");
        } catch (SQLException e) {
            System.out.println("something went wrong please try again");
        }

    }
}
