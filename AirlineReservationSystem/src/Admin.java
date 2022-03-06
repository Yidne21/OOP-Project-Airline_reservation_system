import java.sql.*;
import java.sql.SQLException;
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

}
