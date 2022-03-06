import java.sql.SQLException;
import java.io.IOException;
import java.sql.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ManageJourney {
    String From, Destination, RouteInfo;
    float cost;
    int journeyId;
    int Addedjourney = 0;
    int Deletedjourney = 0;
    int updatedjourney = 0;
    String rset = null;
    DatabaseConnection gConnection = new DatabaseConnection();
    Scanner input = new Scanner(System.in);

    void displayJourneySchedule() throws ClassNotFoundException, SQLException {
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

    String Addjourney(int journeyId, String destination, String From, String rout, float cost)
            throws ClassNotFoundException, SQLException {
        String Added = null;
        try {
            System.out.println("Enter the journey Id:");
            journeyId = input.nextInt();
            System.out.println("Enter the destination:");
            destination = input.next();
            System.out.println("Enter the source:");
            From = input.next();
            System.out.println("Enter the rout:");
            rout = input.next();
            System.out.println("Enter the cost:");
            cost = input.nextFloat();

            Connection conn = gConnection.Connection();
            String sqlAdd = "insert into journeytbl values (?,?,?,?,?)";
            PreparedStatement pstmt = conn.prepareStatement(sqlAdd);
            pstmt.setInt(1, journeyId);
            pstmt.setString(2, destination);
            pstmt.setString(3, From);
            pstmt.setString(4, rout);
            pstmt.setFloat(5, cost);
            pstmt.executeUpdate();
            Added = "Your journey is added successfully\n";

        } catch (SQLException | InputMismatchException e) {
            Added = "Something goes wrong,Please make sure you Added the correct information\n";
        }
        return Added;
    };

    // it not working
    String updatejourney(int journeyId) throws ClassNotFoundException, SQLException, IOException {
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
                    updated = "your Updation done  succssefuly\n";
                } catch (SQLException | InputMismatchException e) {
                    updated = "Updattion is failed please try again\n";
                }
                break;
            case 2:
                try {
                    System.out.println("Enter the new source of the journey: ");
                    From = input.next();
                    Connection conn = gConnection.Connection();
                    String qurey = "update journeytbl set From= ? where journeyId = ?";
                    PreparedStatement psmt = conn.prepareStatement(qurey);
                    psmt.setString(1, From);
                    psmt.setInt(2, journeyId);
                    psmt.executeUpdate();
                    updated = "Updated succssefuly\n";
                } catch (SQLException | InputMismatchException e) {
                    updated = "Updatting failed please try again\n";
                }
                break;
            case 3:
                try {
                    System.out.println("Enter the new cost of the journey: ");
                    cost = input.nextFloat();
                    Connection conn = gConnection.Connection();
                    String sql1 = "update journeytbl set cost = ? where journeyId = ?";
                    PreparedStatement psmt = conn.prepareStatement(sql1);
                    psmt.setFloat(2, cost);
                    psmt.setInt(2, journeyId);
                    psmt.executeUpdate();
                    updated = "Updated succssefuly\n";
                } catch (SQLException | InputMismatchException e) {
                    updated = "Updatting failed please try again\n";
                }
                break;
            case 4:
                try {
                    System.out.println("Enter the new rout of the journey: ");
                    RouteInfo = input.next();
                    Connection conn = gConnection.Connection();
                    String sql2 = "update journeytbl set rout = ? where journeyId = ?";
                    PreparedStatement psmt = conn.prepareStatement(sql2);
                    psmt.setString(1, RouteInfo);
                    psmt.setInt(2, journeyId);
                    psmt.executeUpdate();
                    updated = "Updated succssefuly\n";
                } catch (SQLException | InputMismatchException e) {
                    updated = "Updatting failed please try again\n";
                }
                break;

            default:
                break;
        }
        return updated;
    }

    String Deleteupdatejourney(int journeyId) throws ClassNotFoundException, SQLException {
        boolean isdeleted = false;
        String deleted = null;
        try {
            System.out.print("Enter the journyId:");
            journeyId = input.nextInt();
            String delquery = "delete from journeytbl where journeyId = ?";
            Connection conn = gConnection.Connection();
            PreparedStatement preparedStmt;
            preparedStmt = conn.prepareStatement(delquery);
            preparedStmt.setInt(1, journeyId);
            isdeleted = preparedStmt.execute();
            deleted = "journey deleted successfuly\n";
        } catch (SQLException | ClassNotFoundException | InputMismatchException e) {
            deleted = "journey deletion failed please try again\n";
        }
        if (isdeleted == true) {
            Deletedjourney++;
        }
        return deleted;
    }
}
