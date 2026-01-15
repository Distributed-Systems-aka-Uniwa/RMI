import java.io.Serializable;
import java.lang.*;
/*
 *  The class with reservation details
 */
public class Reservations implements Serializable {
    
    private String type;
    private int number;
    private String name;
    private int cost;

    public Reservations() {
        this.type = "";
        this.number = 0;
        this.name = "";
        this.cost = 0;
    }

    public Reservations(String type, int number, String name, int cost) {
        this.type = type;
        this.number = number;
        this.name = name;
        this.cost = cost;
    }

    public void setType(String type) {
        this.type = type;
    }
    
    public String getType() {
        return this.type;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getNumber() {
        return this.number;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getCost() {
        return this.cost;
    }

    @Override
    public String toString() {
        return "Customer Name  : " + this.name + "\n"
            + "Number of Seats : " + this.number + "\n"
            + "Type of Seats   : " + this.type + "\n" 
            + "Cost of Seats   : " + this.cost + "\n";
    }
}
