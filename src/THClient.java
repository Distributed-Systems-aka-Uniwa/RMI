import java.rmi.Naming; 
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Scanner;
import java.net.MalformedURLException; 
import java.rmi.NotBoundException;
import java.lang.*;
/*
 *  The class that handles the client code
 */
public class THClient {
    public static void main(String[] args) { 
        try {
            int numArgs;
            String url;
            ArrayList<AvailableSeats> seatsList;
            ArrayList<Reservations> reservsList;
            ArrayList<Reservations> clientReservsList;
            String operation, hostname, type, name;
            String msg = "";
            int number, cost;

            numArgs = args.length;
            if (numArgs == 0) {
                printUsage();
            } else {
                url = new String("rmi://" + args[1] + ":1099/TheatreService");
                THInterface remoteRef = (THInterface) Naming.lookup(url);
                THClientInterface clientObj = new THClientImpl();
                operation = args[0];
                hostname = args[1]; 
                switch (operation) {
                    case "list" :        // JavaTHClient list <localhost>
                        seatsList = remoteRef.list(hostname);
                        for (AvailableSeats zone : seatsList) {
                            System.out.println(zone.toString());
                        }
                        System.exit(0);
                    case "book" :       // java THClient book <localhost> <type> <number> <name>
                        type = args[2];
                        number = Integer.parseInt(args[3]);
                        name = args[4];
                        cost = remoteRef.book(hostname, type, number, name, clientObj, 1);
                        Scanner input = new Scanner(System.in);
                        if (cost == 0) {
                            msg = input.next();
                            remoteRef.updateServer(msg);
                        }
                        if (msg.equals("yes")) {
                            cost = remoteRef.book(hostname, type, number, name, clientObj, 0);
                        }
                        input.close();
                        System.out.println("[Cost of reservation] : " + cost + "$");
                        System.exit(0);
                    case "guests" :     // java THClient guests <localhost>
                        reservsList = remoteRef.guests(hostname);
                        for (Reservations reserv : reservsList) {
                            System.out.println(reserv.toString());
                        }
                        System.exit(0);
                    case "cancel" :     // java THClient cancle <localhost> <type> <number> <name>
                        type = args[2];
                        number = Integer.parseInt(args[3]);
                        name = args[4];
                        clientReservsList = remoteRef.cancel(hostname, type, number, name, clientObj);
                        if (!clientReservsList.isEmpty()) {
                            for (Reservations clientReservs : clientReservsList) {
                                System.out.println(clientReservs.toString());
                            }
                        }
                        System.exit(0);
                }
            }
            
        } catch (MalformedURLException murle) { 
            System.out.println(); 
            System.out.println("MalformedURLException"); 
            System.out.println(murle.getMessage()); 
        } catch (RemoteException re) { 
            System.out.println(); 
            System.out.println("RemoteException"); 
            System.out.println(re.getMessage()); 
        } catch (NotBoundException nbe) { 
            System.out.println(); 
            System.out.println("NotBoundException"); 
            System.out.println(nbe.getMessage()); 
        } 
    }
    
    static void printUsage() {
        System.out.println("----------- Details about User Interface of the App -----------");
        System.out.println("Usage: java THClient");
        System.out.println("---------------- List of available seats ----------------------");
        System.out.println("Usage: java THClient list <hostname>");
        System.out.println("--------------------- Book seats -------------------------------");
        System.out.println("Usage: java TΗClient book <hostname> <type> <number> <name>");
        System.out.println("---------------- Details about guests -------------------------");
        System.out.println("Usage: java TΗClient guests <hostname>");
        System.out.println("--------------------- Cancel seats ----------------------------");
        System.out.println("Usage: java TΗClient cancel <hostname> <type> <number> <name>");
    }
}
    
    

