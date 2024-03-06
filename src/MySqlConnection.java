import java.sql.*;
import java.time.LocalDate;

public class MySqlConnection {
    private String database = "jdbc:mysql://localhost:3306/CarRental";
    private String username = "root";
    private String password = "DBpassword95";
    private Connection connection = null;

    public MySqlConnection() {
        createConnection();
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
    public void addCustomer(Customer customer) {
        try {
            String query = "INSERT INTO customer (Name, Address, Zipcode, City, Mobile_phone, Email, License_Number, License_Issue_Date) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, customer.getName());
            preparedStatement.setString(2, customer.getAddress());
            preparedStatement.setInt(3, customer.getZipCode());
            preparedStatement.setString(4, customer.getCity());
            preparedStatement.setInt(5, customer.getPhoneNumber());
            preparedStatement.setString(6, customer.getEmail());
            preparedStatement.setInt(7, customer.getLicenseNumber());
            preparedStatement.setDate(8, Date.valueOf(customer.getLicenseIssueDate()));

            preparedStatement.executeUpdate();

            System.out.println("Customer added successfully.");
        } catch (SQLException e) {
            System.out.println("EXCEPTION: " + e.getStackTrace());
        }
    }

    public Customer getCustomer(int customerId) {
        String query = "SELECT * FROM CUSTOMER WHERE ID = ?;";
        Customer customer = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, customerId);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int customerIdFromDB = rs.getInt("Customer.ID");
                String name = rs.getString("Name");
                String address = rs.getString("Address");
                int zipCode = rs.getInt("Zipcode");
                String city = rs.getString("City");
                int phoneNumber = rs.getInt("Mobile_phone");
                int licenseNumber = rs.getInt("License_Number");
                String email = rs.getString("Email");
                LocalDate licenseIssueDate;
                licenseIssueDate = rs.getDate("License_Issue_Date").toLocalDate();
                customer = new Customer(customerIdFromDB, name, address, zipCode,city, phoneNumber, email, licenseNumber, licenseIssueDate);
            }
        } catch (SQLException e) {
            System.out.println("EXCEPTION: " + e.getStackTrace());
        }
        return customer;
    }
}
