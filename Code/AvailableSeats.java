import java.io.Serializable;
import java.lang.*;
import java.util.Scanner;
/*
 *  Η κλάση με τις ζώνες των θέσεων του θεάτρου
 */
public class AvailableSeats implements Serializable {
    
    private int number;
    private String type;
    private String description;
    private int valuePerSeat;

    public AvailableSeats() {
        this.number = 0;
        this.type = "";
        this.description = "";
        this.valuePerSeat = 0;
    }

    public AvailableSeats(int number, String type, String description, int valuePerSeat) {
        this.number = number;
        this.type = type;
        this.description = description;
        this.valuePerSeat = valuePerSeat;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getNumber() {
        return this.number;
    }

    public void setType(String type) {
        this.type = type;
    }
    
    public String getType() {
        return this.type;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getDescription() {
        return this.description;
    }

    public void setName(int valuePerSeat) {
        this.valuePerSeat = valuePerSeat;
    }

    public int getValuePerSeat() {
        return this.valuePerSeat;
    }
    
    public void reserveSeats(int number) {
        this.number -= number;
    }

    public void unreserveSeats(int number) {
        this.number += number;
    }

    @Override
    public String toString() {
        return this.number + " θέσεις " + this.description +
            " (κωδικός: " + this.type + ") - τιμή: " + this.valuePerSeat;
    }

}
