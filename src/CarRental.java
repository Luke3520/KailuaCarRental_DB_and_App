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
                case CREATE_PROFILE -> createProfile();
                case SHOW_ALL_PROFILES -> showAllProfiles();
                case SELECT_PROFILE -> selectProfile();
                case EDIT_PROFILE -> editProfile();
                case QUIT -> running = false;
            }
        }
        mySqlConnection.closeConnection();
    }

    private MenuChoice showMainMenu() {
        Scanner in = new Scanner(System.in);

        System.out.println("\nMAIN MENU\n" +
                "1. Create profile\n" +
                "2. Show all profiles\n" +
                "3. Select profile\n" +
                "4. Edit profile\n" +
                "Q. Quit\n");

        char choice = in.nextLine().toLowerCase().charAt(0);
        MenuChoice menuChoice = null;
        switch (choice) {
            case '1' -> menuChoice = MenuChoice.CREATE_PROFILE;
            case '2' -> menuChoice = MenuChoice.SHOW_ALL_PROFILES;
            case '3' -> menuChoice = MenuChoice.SELECT_PROFILE;
            case '4' -> menuChoice = MenuChoice.EDIT_PROFILE;
            case 'q' -> menuChoice = MenuChoice.QUIT;
        }
        return menuChoice;
    }
}
