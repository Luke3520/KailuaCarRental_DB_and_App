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
    public void addRentalContract(RentalContract rentalContract) {
        try {
            String query = "INSERT INTO contract (CustomerID, CarID, FromDateAndTime, ToDateAndTime, MaxKm) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, rentalContract.getCustomerId());
            preparedStatement.setInt(2, rentalContract.getCarId());
            preparedStatement.setDate(3,Date.valueOf(rentalContract.getFromDateAndTime()));
            preparedStatement.setDate(4, Date.valueOf(rentalContract.getToDateAndTime()));
            preparedStatement.setInt(5, rentalContract.getMaxKm());

            preparedStatement.executeUpdate();

            System.out.println("Rental contract added successfully.");
        } catch (SQLException e) {
            System.out.println("EXCEPTION: " + e.getStackTrace());
        }
    }
    public Customer getCustomer(int customerId) {
        String query = "SELECT * FROM Customer WHERE ID = ?;";
        Customer customer = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, customerId);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                int customerIdFromDB = rs.getInt("ID");
                String name = rs.getString("Name");
                String address = rs.getString("Address");
                int zipCode = rs.getInt("Zipcode");
                String city = rs.getString("City");
                int phoneNumber = rs.getInt("Mobile_phone");
                int licenseNumber = rs.getInt("License_Number");
                String email = rs.getString("Email");
                LocalDate licenseIssueDate = rs.getDate("License_Issue_Date").toLocalDate();
                customer = new Customer(customerIdFromDB, name, address, zipCode, city, phoneNumber, email, licenseNumber, licenseIssueDate);
            }
        } catch (SQLException e) {
            System.out.println("EXCEPTION: " + e.getMessage());
        }
        return customer;
    }

    public ArrayList<Customer> getAllCustomers() {
        ArrayList<Customer> customers = new ArrayList<>();
        String query = "SELECT * FROM Customer;";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
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
                Customer customer = new Customer(customerIdFromDB, name, address, zipCode, city, phoneNumber, email, licenseNumber, licenseIssueDate);
                customers.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }
    public void updateCustomer(Customer customer) {
        try {
            String query = "UPDATE customer SET Name = ?, Address = ?, Zipcode = ?, City = ?, Mobile_phone = ?, Email = ?, License_Number = ?, License_Issue_Date = ? WHERE ID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, customer.getName());
            preparedStatement.setString(2, customer.getAddress());
            preparedStatement.setInt(3, customer.getZipCode());
            preparedStatement.setString(4, customer.getCity());
            preparedStatement.setInt(5, customer.getPhoneNumber());
            preparedStatement.setString(6, customer.getEmail());
            preparedStatement.setInt(7, customer.getLicenseNumber());
            preparedStatement.setDate(8, Date.valueOf(customer.getLicenseIssueDate()));
            preparedStatement.setInt(9, customer.getCustomerID());

            preparedStatement.executeUpdate();

            System.out.println("Customer updated successfully.");
        } catch (SQLException e) {
            System.out.println("EXCEPTION: " + e.getStackTrace());
        }
    }

    public void addCar(CarCategory carCategory, Car car) {
        try {
            String query = "INSERT INTO car (Brand, Model, TypeOfFuel, License_Plate, Registration_YYYY_MM, Mileage, Car_CategoryID) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, car.getBrand());
            preparedStatement.setString(2, car.getModel());
            preparedStatement.setString(3, car.getTypeOfFuel());
            preparedStatement.setString(4, car.getLicensePlate());
            preparedStatement.setInt(5, car.getRegistrationYYYYMM());
            preparedStatement.setInt(6, car.getMileage());
            preparedStatement.setInt(7, carCategory.getCarCategoryID());


            preparedStatement.executeUpdate();

            System.out.println("Car added successfully.");
        } catch (SQLException e) {
            System.out.println("EXCEPTION: " + e.getStackTrace());
            e.printStackTrace();

        }
    }
}
