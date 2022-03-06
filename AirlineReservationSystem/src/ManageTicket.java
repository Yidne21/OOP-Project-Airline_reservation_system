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
    // i have to check it again.
    String addTicket() throws ClassNotFoundException, SQLException {
        DatabaseConnection gConnection = new DatabaseConnection();
        Connection conn = gConnection.Connection();
         Statement statement = (Statement) conn.createStatement();
         String query = "create table tickettbl";
         ResultSet rset = statement.executeupdate(query);
        System.out.printf(
                "\nTicketID\t  class\t     \tstatus\t      \tseat_no\t   \tscheduleId\t   \tjourneyId");
         while (rset.next()) {
            TicketId = rset.getInt("TicketID");
        journeyId = rset.getInt("journeyId");
            Class = rset.getString("class");
             Status = rset.getString("status");
             scheduleId = rset.getInt("schedulId");
            SeatNumber = rset.getInt("seat_no"); 
             System.out.printf("\n%d\t%18s\t%10s\t%7d\t%18d\t%15d\n", TicketId, Class, Status, SeatNumber, scheduleId,
                     journeyId);
         }
         System.out.println();
        return "Ticket Added succssesfuly";
    };
 // i have to check it again.
    String updateTicket(int ticketID)throws ClassNotFoundException, SQLException {
          DatabaseConnection gConnection = new DatabaseConnection();
         Connection conn = gConnection.Connection();
         Statement statement = (Statement) conn.createStatement();
         Scanner input = new Scanner(System.in);
       System.out.println("Enter ticketID you want to update:");
         ticketID=input.nextInt();
        String updateticket = "update table tickettbl  qty = qty+1 where ticketID =ticketID";
        ResultSet rset = statement.executeUpdate(updateticket);
       
        return "Ticket updated succssesfuly";
    }
    //i have to check it again.
    String DeleteTicket(int ticketID)throws ClassNotFoundException, SQLException {
          DatabaseConnection gConnection = new DatabaseConnection();
         Connection conn = gConnection.Connection();
        Statement statement = (Statement) conn.createStatement();
        Scanner input = new Scanner(System.in);
        System.out.println("Enter ticketID you want to delete:");
         ticketID=input.nextInt();
        String deleteticket = "update table tickettbl  qty = qty+1 where ticketID =ticketID";
         boolean rset = statement.execute(deleteticket);
        return "Ticket deleted succssesfuly";

    }
}
