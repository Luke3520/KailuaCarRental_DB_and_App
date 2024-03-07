import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.ArrayList;

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
            MenuChoice menuChoice = showMainMenu();
            switch (menuChoice) {
                case CREATE_CUSTOMER -> createCustomer();
                case SHOW_ALL_CUSTOMERS -> showAllProfiles();
                case SELECT_CUSTOMER -> selectCustomer();
                case EDIT_CUSTOMER -> editCustomer();
                case CREATE_CAR -> callCreateCar();
                case CREATE_RENTAL_CONTRACT -> System.out.println("temporary");
                case QUIT -> running = false;
            }
        }
        mySqlConnection.closeConnection();
    }

    private MenuChoice showMainMenu() {
        Scanner in = new Scanner(System.in);
        MenuChoice menuChoice = null;
        boolean validChoice = false;

        while (!validChoice) {
            System.out.print("\nMAIN MENU\n" +
                    "1. Create customer\n" +
                    "2. Show all customers\n" +
                    "3. Select customer\n" +
                    "4. Edit customer\n" +
                    "5. Create car\n" +
                    "6. Create rental contract\n" +
                    "Q. Quit\n");

            char choice = in.nextLine().toLowerCase().charAt(0);

            switch (choice) {
                case '1' -> {
                    menuChoice = MenuChoice.CREATE_CUSTOMER; validChoice = true;
                }
                case '2' -> {
                    menuChoice = MenuChoice.SHOW_ALL_CUSTOMERS; validChoice = true;
                }
                case '3' -> {
                    menuChoice = MenuChoice.SELECT_CUSTOMER; validChoice = true;
                }
                case '4' -> {
                    menuChoice = MenuChoice.EDIT_CUSTOMER; validChoice = true;
                }
                case '5' -> {
                    menuChoice= MenuChoice.CREATE_CAR; validChoice = true;
                }
                case '6' -> {
                    menuChoice= MenuChoice.CREATE_RENTAL_CONTRACT; validChoice = true;
                }
                case 'q' -> {
                    menuChoice = MenuChoice.QUIT; validChoice = true;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
        return menuChoice;
    }

    private void showAllProfiles() {
        ArrayList<Customer> customers = mySqlConnection.getAllCustomers();
        for (Customer customer : customers) {
            System.out.println(customer);
        }
    }

    private void createCustomer() {
        Customer customer = userTypesCustomer();
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
        Customer customer = new Customer(fullname, address, zipCode, phoneNumber, email, licenseNumber, licenseIssueDate);
        return customer;
    }

    private Customer selectCustomer() {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter profile ID: ");
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
                "4. Phone number\n" +
                "5. E-mail\n" +
                "6. License number\n" +
                "7. License issue date");
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
                System.out.println("Enter new phone number");
                customer.setPhoneNumber(in.nextInt());
            }
            case '5' -> {
                System.out.println("Enter new E-mail");
                customer.setEmail(in.nextLine());
            }
            case '6' -> {
                System.out.println("Enter new license number");
                customer.setLicenseNumber(in.nextInt());
            }
            case '7' -> {
                System.out.println("Enter new license issue date (use format date-month-year)");
                String newDate = in.nextLine();
                LocalDate licenseIssueDate = LocalDate.parse(newDate);
                customer.setLicenseIssueDate(licenseIssueDate);
            }
        }
        mySqlConnection.updateCustomer(customer);
    }
    public void callCreateCar() {
        CarCategory carCategoryID = CarCategory.setCarCategoryID();
        Car car = CarCRUD.createCar();
        mySqlConnection.addCar(carCategoryID, car);

    }
}