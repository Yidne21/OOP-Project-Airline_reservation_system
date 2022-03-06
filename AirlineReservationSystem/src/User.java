import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;

public abstract class User {
    public String FirstName, LastName, Password, Email, Residence, Nationality;
    public String Sex;
    public int  PhoneNumber;
    Date Age;

    User(String FirstName, String LastName, String Password, String Email, String Residence, String Nationality,
            String Sex, Date Age, int PhoneNumber) {
        this.FirstName = FirstName;
        this.LastName = LastName;
        this.Password = Password;
        this.Email = Email;
        this.Residence = Residence;
        this.Nationality = Nationality;
        this.Age = Age;
        this.Sex = Sex;
        this.PhoneNumber = PhoneNumber;
    }

    abstract boolean Login(int phonenumber, String passwored) throws ClassNotFoundException, SQLException;

    abstract String Register() throws ClassNotFoundException, IOException, ParseException, SQLException;

    abstract String UpdatePersonalInfo(int phonenumber, String passwored) throws ClassNotFoundException;

    abstract void ViewFlightInformation();

    abstract void showMyPersonalInfo(int Phone_no, String Password);
}
