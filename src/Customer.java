import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;

public class Customer {
    private int customerID;
    private String name;
    private String address;
    private int zipCode;
    private String city;
    private int phoneNumber;
    private String email;
    private int licenseNumber;
    private LocalDate licenseIssueDate;


    public Customer(int customerID, String name, String address, int zipCode, int phoneNumber, String email, int licenseNumber, LocalDate licenseIssueDate) {
        this.customerID = customerID;
        this.name = name;
        this.address = address;
        this.zipCode = zipCode;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.licenseNumber = licenseNumber;
        this.licenseIssueDate = licenseIssueDate;
    }

    public Customer(String name, String address, int zipCode, int phoneNumber, String email, int licenseNumber, LocalDate licenseIssueDate) {
        this.name = name;
        this.address = address;
        this.zipCode = zipCode;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.licenseNumber = licenseNumber;
        this.licenseIssueDate = licenseIssueDate;
    }

   /*public void findCityInDB() {
       String query = "select * FROM customer INNER JOIN zipcode_city ON Customer.Zipcode = zipcode_city.zipcode;";
       try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
           preparedStatement.setInt(1, customerId);
           ResultSet rs = preparedStatement.executeQuery();
           if (rs.next()) {
               String city = rs.getString("City");
           }
*/
    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setLicenseNumber(int licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public void setLicenseIssueDate(LocalDate licenseIssueDate) {
        this.licenseIssueDate = licenseIssueDate;
    }

    public int getCustomerID() {
        return customerID;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public int getZipCode() {
        return zipCode;
    }

    public String getCity() {
        return city;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public int getLicenseNumber() {
        return licenseNumber;
    }

    public LocalDate getLicenseIssueDate() {
        return licenseIssueDate;
    }

    @Override
    public String toString() {
        return String.format("%5d. %-20s " +
                        "| Address: %-25s " +
                        "| ZipCode: %-10d " +
                        "| City: %-15s " +
                        "| PhoneNumber: %-15d " +
                        "| Email: %-30s " +
                        "| LicenseNumber: %-15d " +
                        "| License Issue Date: %s",
                customerID, name, address, zipCode, city, phoneNumber, email, licenseNumber, licenseIssueDate);
    }
}
