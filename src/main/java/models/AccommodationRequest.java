package models;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class AccommodationRequest {

    private String destination;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private int adults;
    private List<Child> children;
    private int rooms;
    private boolean travelForWork;

    public AccommodationRequest(String destination, LocalDate checkInDate, LocalDate checkOutDate, int adults, List<Child> children, int rooms, boolean travelForWork) {
        this.destination = destination;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.adults = adults;
        this.children = children;
        this.rooms = rooms;
        this.travelForWork = travelForWork;
    }

    public String getDestination() {
        return destination;
    }

    public LocalDate getCheckInDate() {
        return checkInDate;
    }

    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }

    public int getAdults() {
        return adults;
    }

    public List<Child> getChildren() {
        return children;
    }

    public int getRooms() {
        return rooms;
    }

    public boolean isTravelForWork() {
        return travelForWork;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccommodationRequest that = (AccommodationRequest) o;
        return adults == that.adults &&
                rooms == that.rooms &&
                travelForWork == that.travelForWork &&
                Objects.equals(destination, that.destination) &&
                Objects.equals(checkInDate, that.checkInDate) &&
                Objects.equals(checkOutDate, that.checkOutDate) &&
                Objects.equals(children, that.children);
    }

    @Override
    public int hashCode() {
        return Objects.hash(destination, checkInDate, checkOutDate, adults, children, rooms, travelForWork);
    }

    @Override
    public String toString() {
        return "RoomRequest{" +
                "destination='" + destination + '\'' +
                ", checkInDate=" + checkInDate +
                ", checkOutDate=" + checkOutDate +
                ", adults=" + adults +
                ", children=" + children +
                ", rooms=" + rooms +
                ", travelForWork=" + travelForWork +
                '}';
    }
}
