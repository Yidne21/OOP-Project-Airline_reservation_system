import java.sql.Date;
import java.sql.Time;
import java.sql.SQLException;
import java.sql.*;

public class ScheduleFlight {
    int scheduleId;
    int journeyId;
    int numberOfSeat;
    Date date;
    Time Departure;
    Time Arrival;
    int SeatNumber;

    void displayFlightSchedule() throws ClassNotFoundException, SQLException {
        DatabaseConnection gConnection = new DatabaseConnection();
        Connection conn = gConnection.Connection();
        Statement statement = (Statement) conn.createStatement();
        String query = "select * from scheduletbl";
        ResultSet rset = statement.executeQuery(query);
        System.out.println("ScheduleId    JourneyId     date      Departure      Arrival      SeatNumber");

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

    String AddFlightSchedule(int scheduleId, int journeyId, Date DDate, Time departure, Time arrival,
            int no_of_seat) {
        return "Flight scheduled succssesfuly";

    }

    String updateFlightScheduld(int scheduleId) {
        return "Flight deleted succssesfuly";
    }

    String DeleteFlightSchedul(int scheduleId) {
        return "Flight deleted succssesfuly";

    }
}
