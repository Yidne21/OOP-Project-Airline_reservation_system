import java.sql.SQLException;
import java.sql.*;
import java.util.InputMismatchException;
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

    String updateTicket(int ticketID) throws ClassNotFoundException, SQLException {
        String updated = null;
        System.out.println("what type of data do you want to update:");
        System.out.println("Enter 1 to update class of the ticket:");
        System.out.println("Enter 2 to update the status of the ticket: ");
        System.out.println("Enter 3 to update the seat_no: ");
        int key = input.nextInt();
        System.out.println("Enter the ticket of the journey");
        journeyId = input.nextInt();
        switch (key) {
            case 1:
                try {
                    System.out.println("Enter the new Class of the ticket: ");
                    Class = input.next();
                    Connection conn = gConnection.Connection();
                    String sql = "update tickettbl set Class = ? where ticketID = ?";
                    PreparedStatement psmt = conn.prepareStatement(sql);
                    psmt.setString(1, Class);
                    psmt.setInt(2, ticketID);
                    psmt.executeUpdate();
                    updated = "your Updation done  succssefuly";
                } catch (SQLException | InputMismatchException e) {
                    updated = "Updattion is failed please try again";
                }
                break;
            case 2:
                try {
                    System.out.println("Enter the new status of the journey: ");
                    Status = input.next();
                    Connection conn = gConnection.Connection();
                    String qurey = "update tickettbl set From= ? where ticketID = ?";
                    PreparedStatement psmt = conn.prepareStatement(qurey);
                    psmt.setString(1, Status);
                    psmt.setInt(2, ticketID);
                    psmt.executeUpdate();
                    updated = "Updated succssefuly";
                } catch (SQLException | InputMismatchException e) {
                    updated = "Updatting failed please try again";
                }
                break;
            case 3:
                try {
                    System.out.println("Enter the new seat_no of the journey: ");
                    SeatNumber = input.nextInt();
                    Connection conn = gConnection.Connection();
                    String sql1 = "update journeytbl set seat_no = ? where ticketID= ?";
                    PreparedStatement psmt = conn.prepareStatement(sql1);
                    psmt.setFloat(2, SeatNumber);
                    psmt.setInt(2, ticketID);
                    psmt.executeUpdate();
                    updated = "Updated succssefuly";
                } catch (SQLException | InputMismatchException e) {
                    updated = "Updatting failed please try again";
                }
                break;

            default:
                break;
        }
        return updated;

    }

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
        } catch (ClassNotFoundException | SQLException | InputMismatchException e) {
            success = "Ticket adding failed";
        }
        return success;
    }

    String DeleteTicket(int ticketID) throws ClassNotFoundException, SQLException {
        boolean isdeleted = false;
        String deleted = null;
        try {
            System.out.print("Enter the ticketID:");
            ticketID = input.nextInt();
            String delquery = "delete from tickettbl where ticketID= ?";
            Connection conn = gConnection.Connection();
            PreparedStatement preparedStmt;
            preparedStmt = conn.prepareStatement(delquery);
            preparedStmt.setInt(1, ticketID);
            isdeleted = preparedStmt.execute();
            deleted = "ticket deleted successfuly";
        } catch (SQLException | ClassNotFoundException | InputMismatchException e) {
            deleted = "Ticket deletion failed please try again" + e;
        }
        if (isdeleted == true) {
            Deletedticket++;
        }
        return deleted;

    }

}
