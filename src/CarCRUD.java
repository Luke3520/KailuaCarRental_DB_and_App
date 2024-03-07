import java.util.Scanner;
public class CarCRUD {

    public static Car createCar() {
            Scanner in = new Scanner(System.in);
            System.out.print("Brand: ");
            String brand = in.nextLine();
            System.out.print("Model: ");
            String model = in.nextLine();
            System.out.println("Type of Fuel: ");
            String typeOfFuel = in.nextLine();
            System.out.println("License plate: ");
            String zipCode = in.nextLine();
            System.out.println("Registration year and month (YYYY-MM): ");
            int registration = in.nextInt();
            in.nextLine();
            System.out.println("Mileage: ");
            int mileage = in.nextInt();
            Car car = new Car(brand, model, typeOfFuel, zipCode, registration, mileage);
            return car;
        }
    }
