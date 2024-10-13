import java.io.Serializable;
import java.lang.*;
/*
 *  Η κλάση με τα στοιχεία των κρατήσεων
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
        return "Όνομα Πελάτη  : " + this.name + "\n"
            + "Πλήθος Θέσεων : " + this.number + "\n"
            + "Τύπος Θέσεων  : " + this.type + "\n" 
            + "Κόστος Θέσεων : " + this.cost + "\n";
    }
}
