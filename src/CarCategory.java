import java.util.Scanner;

public class CarCategory {
    private int carCategoryID;

    public CarCategory(int carCategoryID) {
        this.carCategoryID = carCategoryID;
    }

    public int getCarCategoryID() {
        return carCategoryID;
    }

    public static CarCategory setCarCategoryID() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nCREATE Car");
        System.out.println("Enter Car Category: 1. Luxury - 2. Family - 3. Sport:");
        int userInput = scanner.nextInt();
        CarCategory carCategoryID = new CarCategory(userInput);
        return carCategoryID;
    }
}
