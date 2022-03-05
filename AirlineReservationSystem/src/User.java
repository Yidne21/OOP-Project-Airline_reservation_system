import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;

public abstract class User {
    public String FirstName, LastName, Password, Email, Residence, Nationality;
    public String Sex;
    public int PhoneNumber;
    public Date date_of_birth;

    User(String FirstName, String LastName, String Password, String Email, String Residence, String Nationality,
            String Sex, Date date_of_birth, int PhoneNumber) {
        this.FirstName = FirstName;
        this.LastName = LastName;
        this.Password = Password;
        this.Email = Email;
        this.Residence = Residence;
        this.Nationality = Nationality;
        this.date_of_birth = date_of_birth;
        this.Sex = Sex;
        this.PhoneNumber = PhoneNumber;
    }

    abstract boolean Login(int phonenumber, String passwored) throws ClassNotFoundException, SQLException;

    abstract String Register() throws ClassNotFoundException, SQLException, IOException, ParseException;

    abstract String UpdatePersonalInfo(int phonenumber, String passwored) throws ClassNotFoundException;

    abstract void showMyPersonalInfo(int Phone_no, String Password);
}
