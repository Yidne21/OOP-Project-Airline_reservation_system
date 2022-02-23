import java.sql.SQLException;
import java.sql.*;

public class ManageJourney {
    String From, Destination, RouteInfo;
    float cost;
    int journeyId;

    void displayJourneySchedule() throws ClassNotFoundException, SQLException {
        DatabaseConnection gConnection = new DatabaseConnection();
        Connection conn = gConnection.Connection();
        Statement statement = (Statement) conn.createStatement();
        String query = "select * from journeytbl";
        ResultSet rset = statement.executeQuery(query);
        System.out.println("JourneyId    from     destination     rout      cost");
        while (rset.next()) {
            journeyId = rset.getInt("journeyId");
            From = rset.getString("from");
            Destination = rset.getString("destination");
            RouteInfo = rset.getString("rout");
            cost = rset.getFloat("cost");
            System.out.println(
                    journeyId + "     " + From + "                " + Destination + "      " + RouteInfo + "      "
                            + cost);
        }
        System.out.println();
    };

    String Addjourney(int journeyId, String from, String destination, String rout, float cost) {
        return "journey Added succssesfuly";
    }

    String updatejourney(int journeyId) {
        return "journey deleted succssesfuly";
    }

    String Deleteupdatejourney(int journeyId) {
        return "journey deleted succssesfuly";

    }
}
