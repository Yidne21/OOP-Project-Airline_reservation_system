import java.sql.SQLException;
import java.sql.*;

public class ManageTicket {
    int TicketId;
    int journeyId;
    int scheduleId;
    int SeatNumber;
    String Class;
    String Status;

    void displayAvailabeFlightTickets() throws ClassNotFoundException, SQLException {
        DatabaseConnection gConnection = new DatabaseConnection();
        Connection conn = gConnection.Connection();
        Statement statement = (Statement) conn.createStatement();
        String query = "select * from tickettbl";
        ResultSet rset = statement.executeQuery(query);
        System.out.println("TicketID        class      status         seat_no        scheduleId           journeyId");
        while (rset.next()) {
            TicketId = rset.getInt("TicketID");
            Class = rset.getString("class");
            Status = rset.getString("status");
            SeatNumber = rset.getInt("seat_no");
            scheduleId = rset.getInt("schedulId");
            journeyId = rset.getInt("journeyId");
            System.out.println(
                    TicketId + "          " + Class + "       " + Status + "     " + SeatNumber + "      "
                            + scheduleId + "     " + journeyId);
        }
        System.out.println();
    };

    String addTicket(int ticketID, int journeyId, String Class, String status, int scheduleId, int seat_no) {
        return "Ticket Added succssesfuly";
    }

    String updateTicket(int ticketID) {
        return "Ticket deleted succssesfuly";
    }

    String DeleteTicket(int ticketID) {
        return "Ticket deleted succssesfuly";

    }
}
