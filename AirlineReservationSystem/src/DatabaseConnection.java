import java.sql.*;

public class DatabaseConnection {
    // JDBC driver name and database URL

    private static final String url = "jdbc:mysql://localhost:3306/ars";
    private static final String user = "root";
    private static final String password = "19ybb@21";
    private static final String driver = "com.mysql.cj.jdbc.Driver";
    // Database credentials

    Connection connection = null;

    public Connection Connection() throws ClassNotFoundException, SQLException {
        Class.forName(driver);
        connection = DriverManager.getConnection(url, user, password);
        return connection;
    }

    void TestDBconnection() {

    };

    void configureDatabase() {

    };

    void createDatabaseConnection() {

    };
}
