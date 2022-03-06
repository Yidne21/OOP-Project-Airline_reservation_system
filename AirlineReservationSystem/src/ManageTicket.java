import java.sql.SQLException;
import java.sql.*;
import java.util.Scanner;

public class ManageTicket {
    int TicketId;
    int journeyId;
    int scheduleId;
    int SeatNumber;
    String Class;
    String Status;
    int Addedticket = 0;
    int Deletedticket = 0;
    int updatedticket = 0;
    String rset = null;
    DatabaseConnection gConnection = new DatabaseConnection();

    Scanner input = new Scanner(System.in);

    void displayAvailabeFlightTickets() throws ClassNotFoundException, SQLException {
        Connection conn = gConnection.Connection();
        Statement statement = (Statement) conn.createStatement();
        String query = "select * from tickettbl";
        ResultSet rset = statement.executeQuery(query);
        System.out.printf(
                "\nTicketID\t  class\t     \tstatus\t      \tseat_no\t   \tscheduleId\t   \tjourneyId");
        int TotalNumberOfTicket = +Passenger.canceledTicket;
        while (rset.next()) {
            TicketId = rset.getInt("TicketID");
            Class = rset.getString("class");
            Status = rset.getString("status");
            SeatNumber = rset.getInt("seat_no");
            scheduleId = rset.getInt("schedulId");
            journeyId = rset.getInt("journeyId");
            System.out.printf("\n%d\t%18s\t%10s\t%7d\t%18d\t%15d\n", TicketId, Class, Status, SeatNumber, scheduleId,
                    journeyId);
            ++TotalNumberOfTicket;
        }
        System.out.println();
        int avilableTicket = TotalNumberOfTicket - Passenger.BookedTicket;

        System.out.print("Total number of avilable Tickets Are: " + avilableTicket + "\n");
        System.out.println();
    };

    String addTicket(int ticketID, int journeyId, String Class, String status, int scheduleId, int seat_no) {
        return "Ticket Added succssesfuly";
    }

    String updateTicket(int ticketID) throws ClassNotFoundException, SQLException {
       
        return null;

    }

    String DeleteTicket(int ticketID) throws ClassNotFoundException, SQLException {
        boolean isdeleted = false;
       
        return null;

    }
}
