import java.sql.SQLException;

public abstract class User {
    public String FirstName, LastName, Password, Email, Residence, Nationality;
    public char Sex;
    public int Age, PhoneNumber;

    User(String FirstName, String LastName, String Password, String Email, String Residence, String Nationality,
            char Sex, int Age, int PhoneNumber) {
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

    abstract String Register();

    abstract String UpdatePersonalInfo(int phonenumber, String passwored) throws ClassNotFoundException;

    abstract void ViewFlightInformation();

    abstract void showMyPersonalInfo(int Phone_no, String Password);
}
