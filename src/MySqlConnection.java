import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlConnection {
    private String database = "jdbc:mysql://localhost:3306/CarRental";
    private String username = "root";
    private String password = "YaT1coY8or";
    private Connection connection = null;

    public MySqlConnection() {
        createConnection();
    }

    public static void main(String[] args) {
        MySqlConnection test = new MySqlConnection();
        test.createConnection();
    }
    private void createConnection() {
        if (connection != null)
            return; // If connection already created, just return that to ensure singleton

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(database, username, password);
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }

    public void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            System.out.println("EXCEPTION: " + e.getStackTrace());
        }
        connection = null;
    }
}
