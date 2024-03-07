import java.time.LocalDate;

public class RentalContract {
    private int rentalContractId;
    private int customerId;
    private int carId;
    private LocalDate fromDateAndTime;
    private LocalDate toDateAndTime;
    private int maxKm;


    public RentalContract(int rentalContractId, int customerId, int carId, LocalDate fromDateAndTime, LocalDate toDateAndTime, int maxKm) {
        this.rentalContractId = rentalContractId;
        this.customerId = customerId;
        this.carId = carId;
        this.fromDateAndTime = fromDateAndTime;
        this.toDateAndTime = toDateAndTime;
        this.maxKm = maxKm;
    }

    public int getRentalContractId() {
        return rentalContractId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public int getCarId() {
        return carId;
    }

    public LocalDate getFromDateAndTime() {
        return fromDateAndTime;
    }

    public LocalDate getToDateAndTime() {
        return toDateAndTime;
    }

    public int getMaxKm() {
        return maxKm;
    }
}
