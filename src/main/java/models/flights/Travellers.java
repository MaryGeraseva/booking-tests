package models.flights;

import java.util.Objects;

public class Travellers {

    private String category;
    private int quantity;

    public Travellers(String category, int quantity) {
        this.category = category;
        this.quantity = quantity;
    }

    public String getCategory() {
        return category;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Travellers that = (Travellers) o;
        return quantity == that.quantity &&
                Objects.equals(category, that.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(category, quantity);
    }

    @Override
    public String toString() {
        return "Travellers{" +
                "category='" + category + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
