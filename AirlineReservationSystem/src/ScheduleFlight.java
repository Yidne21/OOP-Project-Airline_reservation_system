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
 //i have to check it again.
    String AddFlightSchedule(int scheduleId, int journeyId, Date DDate, Time departure, Time arrival,
    int no_of_seat) throws ClassNotFoundException, SQLException {
               DatabaseConnection gConnection = new DatabaseConnection();
         Connection conn = gConnection.Connection();
         Statement statement = (Statement) conn.createStatement();
         String query = "create table scheduletbl";
         ResultSet rset = statement.executeupdate(query);
         System.out.printf(
                "\nTicketID\t  class\t     \tstatus\t      \tseat_no\t   \tscheduleId\t   \tjourneyId");
         while (rset.next()) {
                scheduleId = rset.getInt("schedulId");
              journeyId = rset.getInt("journeyId");
             DDate = rset.getDate("DDate");
            departure = rset.getTime("departure");
             arrival = rset.getTime("arrival");
            no_of_seat=rset.getInt("no_of_seat"); 
             System.out.printf("\n%d\t%18s\t%10s\t%7d\t%18d\t%15d\n", scheduleId, journeyId, DDate, departure, arrival,
             no_of_seat);
         }
         System.out.println();
        return "Flight scheduled succssesfuly";

        } catch (SQLException e) {
            Added = "Something goes wrong,Please make sure you Added the correct information\n";
        }
        return Added;
    }
//i have to check it again.
    String updateFlightScheduld(int scheduleId)throws ClassNotFoundException, SQLException {
         DatabaseConnection gConnection = new DatabaseConnection();
         Connection conn = gConnection.Connection();
         Statement statement = (Statement) conn.createStatement();
         Scanner input = new Scanner(System.in);
        System.out.println("Enter scheduleId you want to update:");
        scheduleId=input.nextInt();
         String updateschedule = "update table scheduletbl  qty = qty+1 where scheduleId =scheduleId";
     ResultSet rset = statement.executeupdate(updateschedule);
        return "Flight deleted succssesfuly";
    }
//i have to check it again.

    String DeleteFlightSchedul(int scheduleId)throws ClassNotFoundException, SQLException {
        DatabaseConnection gConnection = new DatabaseConnection();
         Connection conn = gConnection.Connection();
         Statement statement = (Statement) conn.createStatement();
         Scanner input = new Scanner(System.in);
        System.out.println("Enter scheduleId you want to delete:");
         scheduleId=input.nextInt();
         String deleteschedule = "update table scheduletbl  qty = qty+1 where scheduleId =scheduleId";
         ResultSet rset = statement.executeupdate(deleteschedule);
        return "Flight deleted succssesfuly";

    }
}
