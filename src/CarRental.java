import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class CarRental {
    MySqlConnection mySqlConnection;

    public CarRental() {
        mySqlConnection = new MySqlConnection();
    }

    public static void main(String[] args) {
        new CarRental().run();
    }

    private void run() {
        boolean running = true;

        while (running) {
            MenuChoice menuChoice = showMainMenu(); //forstår ikke den her linje.
            switch (menuChoice) {
                case CREATE_CUSTOMER -> createCustomer();
                //case SHOW_ALL_CUSTOMERS -> showAllProfiles();
                case SELECT_CUSTOMER -> selectCustomer();
                case EDIT_CUSTOMER -> editCustomer();
                case QUIT -> running = false;
            }
        }
        mySqlConnection.closeConnection();
    }

    private MenuChoice showMainMenu() {
        Scanner in = new Scanner(System.in);

        System.out.println("\nMAIN MENU\n" +
                "1. Create customer\n" +
                "2. Show all customers\n" +
                "3. Select customer\n" +
                "4. Edit customer\n" +
                "Q. Quit\n");

        char choice = in.nextLine().toLowerCase().charAt(0);
        MenuChoice menuChoice = null;
        switch (choice) {
            case '1' -> menuChoice = MenuChoice.CREATE_CUSTOMER;
            case '2' -> menuChoice = MenuChoice.SHOW_ALL_CUSTOMERS;
            case '3' -> menuChoice = MenuChoice.SELECT_CUSTOMER;
            case '4' -> menuChoice = MenuChoice.EDIT_CUSTOMER;
            case 'q' -> menuChoice = MenuChoice.QUIT;
        }
        return menuChoice;
    }

    private void createCustomer() {
        Customer customer = userTypesCustomer(); // profile kører userTypesProfile() som returnerer en profile ?
        mySqlConnection.addCustomer(customer);
    }

    private Customer userTypesCustomer() {
        Scanner in = new Scanner(System.in);
        System.out.println("\nCREATE PROFILE");
        System.out.print("First name: ");
        String firstName = in.nextLine();
        System.out.print("Last name: ");
        String lastName = in.nextLine();
        String fullname = firstName + " " + lastName;
        System.out.print("Address: ");
        String address = in.nextLine();
        System.out.print("Zipcode: ");
        int zipCode = in.nextInt();
        in.nextLine();
        System.out.print("City: ");
        String city = in.nextLine();
        System.out.print("Phone Number: ");
        int phoneNumber = in.nextInt();
        in.nextLine();
        System.out.print("Email: ");
        String email = in.nextLine();
        System.out.print("License Number: ");
        int licenseNumber = in.nextInt();
        in.nextLine();
        System.out.print("License issue date (dd-mm-yyyy): ");
        String dateString = in.nextLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate licenseIssueDate = LocalDate.parse(dateString, formatter);
        Customer customer = new Customer(fullname, address, zipCode, city, phoneNumber, email, licenseNumber, licenseIssueDate);
        return customer;
    }

    private Customer selectCustomer() {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter profile id: ");
        int customerId = in.nextInt();
        Customer customer = mySqlConnection.getCustomer(customerId);
        System.out.println(customer);
        return customer;
    }

    private void editCustomer() {
        Scanner in = new Scanner(System.in);
        Customer customer = selectCustomer();
        System.out.println("What do you want to change?\n" +
                "1. Name\n" +
                "2. Adress\n" +
                "3. Zipcode\n" +
                "4. City\n" +
                "5. Phone number\n" +
                "6. E-mail\n" +
                "7. License number\n" +
                "8. License issue date");
        char userIn = in.nextLine().toLowerCase().charAt(0);
        switch (userIn) {
            case '1' -> {
                System.out.println("Enter new Full name");
                customer.setName(in.nextLine());
            }
            case '2' -> {
                System.out.println("Enter new address");
                customer.setAddress(in.nextLine());
            }
            case '3' -> {
                System.out.println("Enter new zipcode");
                customer.setZipCode(in.nextInt());
            }
            case '4' -> {
                System.out.println("Enter new city");
                customer.setCity(in.nextLine());
            }
            case '5' -> {
                System.out.println("Enter new phonenumber");
                customer.setPhoneNumber(in.nextInt());
            }
            case '6' -> {
                System.out.println("Enter new E-mail");
                customer.setEmail(in.nextLine());
            }
            case '7' -> {
                System.out.println("Enter new license number");
                customer.setLicenseNumber(in.nextInt());
            }
            case '8' -> {
                System.out.println("Enter new license issue date (use format date-month-year)");
                String newDate = in.nextLine();
                LocalDate licenseIssueDate = LocalDate.parse(newDate);
                customer.setLicenseIssueDate(licenseIssueDate);
            }
        }
    }
}
