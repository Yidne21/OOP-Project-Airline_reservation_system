import java.sql.Date;
import java.sql.Time;
import java.util.Scanner;
import java.sql.SQLException;
import java.sql.*;
import java.util.Scanner;

public class ScheduleFlight {
    int scheduleId;
    int journeyId;
    int numberOfSeat;
    Date date;
    Time Departure;
    Time Arrival;
    int SeatNumber;
    int Deletedschedule = 0;
    Scanner input = new Scanner(System.in);
    DatabaseConnection gConnection = new DatabaseConnection();

    void displayFlightSchedule() throws ClassNotFoundException, SQLException {
        Connection conn = gConnection.Connection();
        Statement statement = (Statement) conn.createStatement();
        String query = "select * from scheduletbl";
        ResultSet rset = statement.executeQuery(query);
        System.out.println("ScheduleId" + "JourneyId" + "date" + "Departure" + "Arrival" + "SeatNumber");

        while (rset.next()) {
            scheduleId = rset.getInt("schedulId");
            journeyId = rset.getInt("journeyId");
            date = rset.getDate("DDate");
            Departure = rset.getTime("departure");
            Arrival = rset.getTime("arrival");
            SeatNumber = rset.getInt("no_of_seat");
            System.out.println(
                    scheduleId + "     " + journeyId + "     " + date + "      " + Departure
                            + "     " + Arrival
                            + "       " + SeatNumber);
        }
        System.out.println();
    };

    // i have to check it again.
    String AddFlightSchedule(int scheduleId, int journeyId, Date DDate, Time departure, Time arrival,
            int no_of_seat) throws ClassNotFoundException, SQLException {
        DatabaseConnection gConnection = new DatabaseConnection();
        Connection conn = gConnection.Connection();
        Statement statement = (Statement) conn.createStatement();

        return "Flight scheduled succssesfuly";
    }

    // i have to check it again.
    String updateFlightScheduld(int scheduleId) throws ClassNotFoundException, SQLException {
        DatabaseConnection gConnection = new DatabaseConnection();
        Connection conn = gConnection.Connection();
        Statement statement = (Statement) conn.createStatement();
        Scanner input = new Scanner(System.in);
        return "Flight deleted succssesfuly";
    }
    // i have to check it again.

    String DeleteFlightSchedul(int scheduleId) throws ClassNotFoundException, SQLException {
        DatabaseConnection gConnection = new DatabaseConnection();
        Connection conn = gConnection.Connection();
        Statement statement = (Statement) conn.createStatement();
        Scanner input = new Scanner(System.in);
        return "Flight deleted succssesfuly";

    }
}
