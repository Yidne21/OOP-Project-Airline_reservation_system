import java.sql.SQLException;
import java.sql.*;
import java.util.Scanner;
import javax.print.attribute.standard.Destination;

public class ManageJourney {
    String From, Destination, RouteInfo;
    float cost;
    int journeyId;
    int Addedjourney = 0;
    int Deletedjourney = 0;
    int updatedjourney = 0;
    String rset = null;
    DatabaseConnection gConnection = new DatabaseConnection();

    void displayJourneySchedule() throws ClassNotFoundException, SQLException {
        DatabaseConnection gConnection = new DatabaseConnection();
        Connection conn = gConnection.Connection();
        Statement statement = (Statement) conn.createStatement();
        String query = "select * from journeytbl";
        ResultSet rset = statement.executeQuery(query);
        System.out.printf("\nJourneyId\tfrom\tdestination\trout\tcost");
        while (rset.next()) {
            journeyId = rset.getInt("journeyId");
            From = rset.getString("from");
            Destination = rset.getString("destination");
            RouteInfo = rset.getString("rout");
            cost = rset.getFloat("cost");
            System.out.printf("\n%d  \t%s \t%s \t%s\t \t%f\n", journeyId, From, Destination, RouteInfo, cost);
        }
        System.out.println();
    };

    Scanner input = new Scanner(System.in);

    String Addjourney(int journeyId, String destination, String rout, float cost)
            throws ClassNotFoundException, SQLException {
        String Added = null;
        try {
            System.out.println("Enter the journey Id");
            journeyId = input.nextInt();
            System.out.println("Enter InputMismatchExceptionthe destination");
            destination = input.next();
            System.out.println("Enter the rout");
            rout = input.next();
            System.out.println("Enter the cost");
            cost = input.nextInt();
            DatabaseConnection gConnection = new DatabaseConnection();
            Connection conn = gConnection.Connection();
            Statement stmt = conn.createStatement();
            String sqlInsert = "insert into journeybl values (" + journeyId + "," + destination + "," + rout + ","
                    + cost + ")";
            int addedjourney = stmt.executeUpdate(sqlInsert);
            Addedjourney = addedjourney;
            Added = "Your journey is added successfully";
        } catch (SQLException e) {
            Added = "uncatched  error existed";
        }
        return Added;
    };

    String updatejourney(int journeyId) throws ClassNotFoundException, SQLException {
        String updated = null;
        System.out.println("what type of data do you want to update");
        System.out.println("Enter 1 to update destination of the journey");
        System.out.println("Enter 2 to update the source address journey");
        System.out.println("Enter 3 to update the cost of the journey: ");
        System.out.println("Enter 4 to update the rout of the journey: ");
        int key = input.nextInt();
        System.out.println("Enter the journey ID of the journey");
        journeyId = input.nextInt();
        switch (key) {
            case 1:
                try {
                    System.out.println("Enter the new destionation of the journey: ");
                    Destination = input.next();
                    Connection conn = gConnection.Connection();
                    String sql = "update journeytbl set destination = ? where journeyId = ?";
                    PreparedStatement psmt = conn.prepareStatement(sql);
                    psmt.setString(1, Destination);
                    psmt.setInt(2, journeyId);
                    psmt.executeUpdate();
                    updated = "Updated succssefuly";
                } catch (SQLException e) {
                    updated = "Updatting failed please try again";
                }
                break;
            case 2:

                break;
            case 3:

                break;
            case 4:

                break;

            default:
                break;
        }
        return updated;
    }

    String Deleteupdatejourney(int journeyId) throws ClassNotFoundException, SQLException {
        String Delated = null;
        try {
            System.out.print("Enter The JourenyId");
            journeyId = input.nextInt();
            DatabaseConnection gConnection = new DatabaseConnection();
            Connection conn = gConnection.Connection();
            Statement stmt = conn.createStatement();
            String Query = "select journeyId from journeytbl";
            ResultSet rset = stmt.executeQuery(Query);
            while (rset.next()) {
                journeyId = rset.getInt("journeyId");
            }
            Delated = "Your journey is delated successfully";
        } catch (SQLException e) {
            Delated = "something went wrong please Enter the correct value only!";
        }
        return Delated;
    };
}
