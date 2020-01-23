package models.flights;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Objects;

public class Flight {

    private String departureDestination;
    private String arrivalDestination;
    private LocalDate departureDate;
    private LocalDate arrivalDate;
    private String cabinClass;
    private Travellers[] travellers;

    public Flight(String departureDestination, String arrivalDestination, LocalDate departureDate, LocalDate arrivalDate) {
        this.departureDestination = departureDestination;
        this.arrivalDestination = arrivalDestination;
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
    }

    public Flight(String departureDestination, String arrivalDestination, LocalDate departureDate, LocalDate arrivalDate,
                  String cabinClass, Travellers[] travellers) {
        this.departureDestination = departureDestination;
        this.arrivalDestination = arrivalDestination;
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
        this.cabinClass = cabinClass;
        this.travellers = travellers;
    }

    public String getDepartureDestination() {
        return departureDestination;
    }

    public String getArrivalDestination() {
        return arrivalDestination;
    }

    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public LocalDate getArrivalDate() {
        return arrivalDate;
    }

    public String getCabinClass() {
        return cabinClass;
    }

    public Travellers[] getTravellers() {
        return travellers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Flight flight = (Flight) o;
        return Objects.equals(departureDestination, flight.departureDestination) &&
                Objects.equals(arrivalDestination, flight.arrivalDestination) &&
                Objects.equals(departureDate, flight.departureDate) &&
                Objects.equals(arrivalDate, flight.arrivalDate) &&
                Objects.equals(cabinClass, flight.cabinClass) &&
                Arrays.equals(travellers, flight.travellers);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(departureDestination, arrivalDestination, departureDate, arrivalDate, cabinClass);
        result = 31 * result + Arrays.hashCode(travellers);
        return result;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "departureDestination='" + departureDestination + '\'' +
                ", arrivalDestination='" + arrivalDestination + '\'' +
                ", departureDate=" + departureDate +
                ", arrivalDate=" + arrivalDate +
                ", cabinClass='" + cabinClass + '\'' +
                ", travellers=" + Arrays.toString(travellers) +
                '}';
    }
}
