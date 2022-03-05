import java.sql.SQLException;
import java.sql.*;
import java.util.Scanner;
import javax.imageio.IOException;

public class ManageJourney {
    String From, Destination, RouteInfo;
    float cost;
    int journeyId;
    int Addedjourney = 0;
    int Deletedjourney = 0;
    String rset = null;

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

    void Addjourney(int journeyId, String destination, String rout, float cost)
            throws ClassNotFoundException, SQLException {
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
            System.out.println("Your journey is added successfully");
        } catch (ClassNotFoundException e) {
            System.out.println("something went wrong please Enter the integer value only!");
        } catch (SQLException e) {
            System.out.println("Please maake sure you inserted the correct information");
        }
    };

    String updatejourney(int journeyId) throws ClassNotFoundException, SQLException {
        System.out.println("Select the journey Id from Jourenytb");
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
        return "journey deleted succssesfuly";
    }

    void Deleteupdatejourney(int journeyId) throws ClassNotFoundException, SQLException {
        try {
            System.out.print("Enter The JourenyId");
            journeyId = input.nextInt();
            DatabaseConnection gConnection = new DatabaseConnection();
            Connection conn = gConnection.Connection();
            Statement stmt = conn.createStatement();
            String select = "select journeyId from journeytbl";
            ResultSet rset = stmt.executeQuery(select);
            int deletedjourney = stmt.executeUpdate(select);
            Deletedjourney = deletedjourney;
            System.out.println("Your journey is delated successfully");
        } catch (ClassNotFoundException e) {
            System.out.println("something went wrong please Enter the integer value only!");
        } catch (SQLException e) {
            System.out.println("Please maake sure you inserted the correct information");
        }
    }
}
