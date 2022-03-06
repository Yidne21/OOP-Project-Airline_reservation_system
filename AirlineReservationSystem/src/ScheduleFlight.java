import java.sql.Date;
import java.sql.Time;
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

    String AddFlightSchedule(int scheduleId, int journeyId, Date DDate, Time departure, Time arrival,
            int no_of_seat) throws ClassNotFoundException, SQLException {
        String Added = null;
        try {
            System.out.println("Enter the scheduleId:");
            scheduleId = input.nextInt();
            System.out.println("Enter the journey Id:");
            journeyId = input.nextInt();
            System.out.println("Enter the DDate in the format yy-mm-dd:");
            String dDate1 = input.next();
            DDate = Date.valueOf(dDate1);
            System.out.println("Enter the departure time in the format tt:mm:ss :");
            String dtime = input.next();
            departure = Time.valueOf(dtime);
            System.out.println("Enter the arrival time in the format tt:mm:ss :");
            String atime = input.next();
            arrival = Time.valueOf(atime);
            System.out.println("Enter the seat_no:");
            no_of_seat = input.nextInt();
            Connection conn = gConnection.Connection();
            String sqlAdd = "insert into scheduletbl values (?,?,?,?,?,?)";
            PreparedStatement pstmt = conn.prepareStatement(sqlAdd);
            pstmt.setInt(1, scheduleId);
            pstmt.setInt(2, journeyId);
            pstmt.setDate(3, DDate);
            pstmt.setTime(4, Departure);
            pstmt.setTime(5, Arrival);
            pstmt.setInt(6, SeatNumber);
            pstmt.executeUpdate();
            Added = "Your  schedule is added successfully\n";

        } catch (SQLException e) {
            Added = "Something goes wrong,Please make sure you Added the correct information\n";
        }
        return Added;
    }

    String updateFlightScheduld(int scheduleId) throws ClassNotFoundException, SQLException {
        String updated = null;
        System.out.println("what type of data do you want to update:");
        System.out.println("Enter 1 to update departure date:");
        System.out.println("Enter 2 to update the  departure time:");
        System.out.println("Enter 3 to update the arrival time: ");
        System.out.println("Enter 4 to update the seat number: ");
        int key = input.nextInt();
        System.out.println("Enter the scheduleId of the schedule:");
        scheduleId = input.nextInt();
        switch (key) {
            case 1:
                try {
                    System.out.println("Enter the new DDate date: ");
                    String dDate2 = input.next();
                    date = Date.valueOf(dDate2);
                    Connection conn = gConnection.Connection();
                    String sql = "update scheduletbl set DDate = ? where scheduleId = ?";
                    PreparedStatement psmt = conn.prepareStatement(sql);
                    psmt.setDate(1, date);
                    psmt.setInt(2, scheduleId);
                    psmt.executeUpdate();
                    updated = "your Updation done succssefuly\n";
                } catch (SQLException e) {
                    updated = "Updattion is failed please try again\n";
                }
                break;
            case 2:
                try {
                    System.out.println("Enter the new departure time: ");
                    String dtime2 = input.next();
                    Departure = Time.valueOf(dtime2);
                    Connection conn = gConnection.Connection();
                    String sql = "update scheduletbl set departure = ? where scheduleId = ?";
                    PreparedStatement psmt = conn.prepareStatement(sql);
                    psmt.setTime(1, Departure);
                    psmt.setInt(2, scheduleId);
                    psmt.executeUpdate();
                    updated = "your Updation done  succssefuly\n";
                } catch (SQLException e) {
                    updated = "Updattion is failed please try again\n";
                }
                break;
            case 3:
                try {
                    System.out.println("Enter the new arrival time: ");
                    String dtime = input.next();
                    Arrival = Time.valueOf(dtime);
                    Connection conn = gConnection.Connection();
                    String sql1 = "update journeytbl set arrival = ? where scheduleId= ?";
                    PreparedStatement psmt = conn.prepareStatement(sql1);
                    psmt.setTime(2, Arrival);
                    psmt.setInt(2, scheduleId);
                    psmt.executeUpdate();
                    updated = "Updated succssefuly\n";
                } catch (SQLException e) {
                    updated = "Updatting failed please try again\n";
                }
                break;
            case 4:
                try {
                    System.out.println("Enter the new seat number: ");
                    SeatNumber = input.nextInt();
                    Connection conn = gConnection.Connection();
                    String sql2 = "update scheduletbl set no_of_seat= ? where scheduleId = ?";
                    PreparedStatement psmt = conn.prepareStatement(sql2);
                    psmt.setInt(1, SeatNumber);
                    psmt.setInt(2, scheduleId);
                    psmt.executeUpdate();
                    updated = "Updated succssefuly\n";
                } catch (SQLException e) {
                    updated = "Updatting failed please try again\n";
                }
                break;

            default:
                break;
        }
        return updated;
    }

    String DeleteFlightSchedul(int scheduleId) {
        boolean isdeleted = false;
        String deleted = null;
        try {
            System.out.print("Enter the scheduleId:");
            scheduleId = input.nextInt();
            String delquery = "delete from scheduletbl where scheduleId = ?";
            Connection conn = gConnection.Connection();
            PreparedStatement preparedStmt;
            preparedStmt = conn.prepareStatement(delquery);
            preparedStmt.setInt(1, scheduleId);
            isdeleted = preparedStmt.execute();
            deleted = "schedule deleted successfuly\n";
        } catch (SQLException | ClassNotFoundException e) {
            deleted = "schedule deletion failed please try again\n";
        }
        if (isdeleted == true) {
            Deletedschedule++;
        }
        return deleted;

    }
}
