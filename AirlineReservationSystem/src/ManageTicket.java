import java.sql.SQLException;
import java.util.Scanner;
import java.sql.*;
import java.util.Scanner;

public class ManageTicket {
    int TicketId;
    int journeyId;
    int scheduleId;
    int SeatNumber;
    String Class;
    String Status;
    Scanner input = new Scanner(System.in);
    DatabaseConnection gConnection = new DatabaseConnection();

    void displayAvailabeFlightTickets() throws ClassNotFoundException, SQLException {
        DatabaseConnection gConnection = new DatabaseConnection();
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
        String success = null;
        try {
            System.out.print("Enter the ticket ID: ");
            ticketID = input.nextInt();
            System.out.print("Enter the journey ID: ");
            journeyId = input.nextInt();
            System.out.print("Enter the Class name: ");
            Class = input.next();
            System.out.print("Enter status of the ticket: ");
            status = input.next();
            System.out.print("Enter the schedul ID: ");
            scheduleId = input.nextInt();
            System.out.print("Enter the seat number: ");
            seat_no = input.nextInt();
            Connection conn;
            conn = gConnection.Connection();
            String sqlInsert = "insert into tickettbl (ticketID, class, status, seat_no, schedulId,journeyId)"
                    + " value (?,?,?,?,?,?)";
            PreparedStatement pstmt = conn.prepareStatement(sqlInsert);

            pstmt.setInt(1, ticketID);
            pstmt.setString(2, Class);
            pstmt.setString(3, status);
            pstmt.setInt(4, seat_no);
            pstmt.setInt(5, scheduleId);
            pstmt.setInt(6, journeyId);
            pstmt.executeUpdate();
            success = "Ticket Added succssesfuly";
        } catch (ClassNotFoundException | SQLException e) {
            success = "Ticket adding failed";
        }
        return success;
    }

    String UpdateTicket(int ticketID) {
        String success = null;
        return success;
    }

    String DeleteTicket(int ticketID) throws ClassNotFoundException, SQLException {
        DatabaseConnection gConnection = new DatabaseConnection();
        Connection conn = gConnection.Connection();
        Statement statement = (Statement) conn.createStatement();
        Scanner input = new Scanner(System.in);
        return "Ticket deleted succssesfuly";

    }
}
