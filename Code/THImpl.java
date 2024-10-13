import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.lang.String;
import java.util.ArrayList;
import java.util.Vector;
import java.util.ListIterator;
import java.util.Scanner;
/*
 *  Η κλάση που δίνει την υλοποίηση του remote object του RMI registry που σηκώνει ο server
 */
public class THImpl extends UnicastRemoteObject implements THInterface {
    
    private AvailableSeats zonePA, zonePB, zonePC, zoneKE, zonePTH;
    private ArrayList<Reservations> reservsList;
    private ArrayList zonePACallbackClients;
    private ArrayList zonePBCallbackClients;
    private ArrayList zonePCCallbackClients;
    private ArrayList zoneKECallbackClients;
    private ArrayList zonePTHCallbackClients;
    private String clientResponse;
    
    public THImpl() 
        throws RemoteException { 
        super();
        clientResponse = "";
        reservsList = new ArrayList<>();
        zonePACallbackClients = new ArrayList<>();
        zonePBCallbackClients = new ArrayList<>();
        zonePCCallbackClients = new ArrayList<>();
        zoneKECallbackClients = new ArrayList<>();
        zonePTHCallbackClients = new ArrayList<>();
        this.zonePA = new AvailableSeats(100, "ΠΑ", "Πλατεία - Ζώνη Α", 45);
        this.zonePB = new AvailableSeats(200, "ΠΒ", "Πλατεία - Ζώνη Β", 35);
        this.zonePC = new AvailableSeats(400, "ΠΓ", "Πλατεία - Ζώνη Γ", 25);
        this.zoneKE = new AvailableSeats(225, "ΚΕ", "Κεντρικός Εξώστης", 30);
        this.zonePTH = new AvailableSeats(75, "ΠΘ", "Πλαϊνά Θεωρεία", 20);       
    }

    public ArrayList<AvailableSeats> list(String hostname) 
        throws RemoteException {
        ArrayList<AvailableSeats> seatsList;
        
        seatsList = new ArrayList<>();
        seatsList.add(zonePA);
        seatsList.add(zonePB);
        seatsList.add(zonePC);
        seatsList.add(zoneKE);
        seatsList.add(zonePTH);

        return seatsList;
    }

    public int book(String hostname, String type, int number, String name, THClientInterface clientObj, final int PRINTMSGS) 
        throws RemoteException {
        int cost;
        int availableSeats;

        cost = 0;
        switch (type) {
            case "ΠΑ" :
                availableSeats = returnAvailableSeats(zonePA, number, clientObj, PRINTMSGS);
                cost = countCost(zonePA, hostname, type, availableSeats, name);
                return cost;
            case "ΠΒ" :
                availableSeats = returnAvailableSeats(zonePB, number, clientObj, PRINTMSGS);
                cost = countCost(zonePB, hostname, type, availableSeats, name);
                return cost;
            case "ΠΓ" :
                availableSeats = returnAvailableSeats(zonePC, number, clientObj, PRINTMSGS);
                cost = countCost(zonePC, hostname, type, availableSeats, name);
                return cost;
            case "ΚΕ" :
                availableSeats = returnAvailableSeats(zoneKE, number, clientObj, PRINTMSGS);
                cost = countCost(zoneKE, hostname, type, availableSeats, name);
                return cost;
            case "ΠΘ" :
                availableSeats = returnAvailableSeats(zonePTH, number, clientObj, PRINTMSGS);
                cost = countCost(zonePTH, hostname, type, availableSeats, name);
                return cost;
        }
        return cost;
    }
    
    private int returnAvailableSeats(AvailableSeats zone, int number, THClientInterface clientObj, final int PRINTMSGS)
        throws RemoteException {
        int availableSeats = 0;
        
        
        if (zone.getNumber() != 0) {
            if (zone.getNumber() >= number) {
                availableSeats = number;
                clientObj.updateClient("Reservation Successful");
                return availableSeats;
            } else {
                if (PRINTMSGS == 1) { // Λόγω ότι καλείται 2 φορές η book, για να μην τυπώνεται στην οθόνη του client ξανά η ερώτηση
                    clientObj.updateClient("Available seats are only " + zone.getNumber() + ".\nDo you want to continue the reservation? (type yes for continue or no for stop)");
                }
                if (this.clientResponse.equals("yes")) {
                    availableSeats = zone.getNumber();
                    clientObj.updateClient("\nReservation Successful");
                    this.clientResponse = ""; // Επαναφορά της ανταπόκρισης του client στο κενό σύμβολο, ώστε να μην εκτελεστεί λανθασμένα
                    return zone.getNumber();  // κώδικας λόγω ότι το clientResponse περιέχει την απάντηση από προηγούμενη ερώτηση (Η book καλείται 2 φορές)
                } 
                if (this.clientResponse.equals("no")) {
                    clientObj.updateClient("\nReservation Unsuccessful");
                    availableSeats = 0;
                    this.clientResponse = "";
                    return availableSeats;
                }
            }
        } else {
            if (PRINTMSGS == 1) { 
                clientObj.updateClient("No available seats!");
                clientObj.updateClient("Do you want to add you in immediate notification list, when at least 1 of your requested seats are available? (type yes for continue or no for stop)");
            }
            if (this.clientResponse.equals("yes")) {
                clientObj.updateClient("You have been registered for notification...");
                this.registerForCallback(clientObj, zone.getType());
            }
            if (this.clientResponse.equals("no")) {
                clientObj.updateClient("\nReservation Unsuccessful");
            }
            this.clientResponse = "";
            availableSeats = 0;
            return availableSeats;
        }
        this.clientResponse = "";
        return availableSeats;
    }

    private int countCost(AvailableSeats zone, String hostname, String type, int number, String name) 
        throws RemoteException {
        int cost;

        cost = 0;
        zone.reserveSeats(number);
        cost = number * zone.getValuePerSeat();
        storeReservation(type, number, name, cost);
    
        return cost;
    }

    private void storeReservation(String type, int number, String name, int cost) {
        Reservations reserv = new Reservations(type, number, name, cost);
        reservsList.add(reserv);
    }

    public synchronized void registerForCallback(THClientInterface callbackClient, String type)
        throws RemoteException {
            switch (type) {
                case "ΠΑ" :
                    if (!zonePACallbackClients.contains(callbackClient)) {
                        zonePACallbackClients.add(callbackClient);
                    }
                    System.out.println("Registered new client of " + type);
                    break;
                case "ΠΒ" :
                    if (!zonePBCallbackClients.contains(callbackClient)) {
                        zonePACallbackClients.add(callbackClient);
                    }
                    System.out.println("Registered new client of " + type);
                    break;
                case "ΠΓ" :
                    if (!zonePCCallbackClients.contains(callbackClient)) {
                        zonePCCallbackClients.add(callbackClient);
                    }
                    System.out.println("Registered new client of " + type);
                    break;
                case "ΚΕ" :
                    if (!zoneKECallbackClients.contains(callbackClient)) {
                        zoneKECallbackClients.add(callbackClient);
                    }
                    System.out.println("Registered new client of " + type);
                    break;
                case "ΠΘ" :
                    if (!zonePTHCallbackClients.contains(callbackClient)) {
                        zonePTHCallbackClients.add(callbackClient);
                    }
                    System.out.println("Registered new client of " + type);
                    break;
            }
            doCallback(callbackClient, type);
    }

    private synchronized void doCallback(THClientInterface callbackClient, String type) 
        throws RemoteException {
            boolean callbackSend = false;

            for (;;) {
                switch (type) {
                    case "ΠΑ" :
                        if (zonePA.getNumber() > 0) {
                            callbackClient.notifyCallbackClient(zonePA.getNumber() + " seats of type " + type + " are available!!");     
                            unregisterForCallback(callbackClient, type);
                            callbackSend = true;
                        }
                        break;
                    case "ΠΒ" :
                        if (zonePB.getNumber() > 0) {
                            callbackClient.notifyCallbackClient(zonePB.getNumber() + " seats of type " + type + " are available!!");
                            unregisterForCallback(callbackClient, type);
                            callbackSend = true;
                        }
                        break;
                    case "ΠΓ" :
                        if (zonePC.getNumber() > 0) {
                            callbackClient.notifyCallbackClient(zonePC.getNumber() + " seats of type " + type + " are available!!");    
                            unregisterForCallback(callbackClient, type);
                            callbackSend = true;
                        }
                        break;
                    case "ΚΕ" :
                        if (zoneKE.getNumber() > 0) {
                            callbackClient.notifyCallbackClient(zoneKE.getNumber() + " seats of type " + type + " are available!!");    
                            unregisterForCallback(callbackClient, type);
                            callbackSend = true;
                        }
                        break;
                    case "ΠΘ" :
                        if (zonePTH.getNumber() > 0) {
                            callbackClient.notifyCallbackClient(zonePTH.getNumber() + " seats of type " + type + " are available!!");  
                            unregisterForCallback(callbackClient, type);
                            callbackSend = true;
                        }
                        break;
                }
                if (callbackSend) {
                    break;
                }
            }    
    }

    public synchronized void unregisterForCallback(THClientInterface callbackClient, String type)
        throws RemoteException {
            switch (type) {
                case "ΠΑ" :
                    if (zonePACallbackClients.contains(callbackClient)) {
                        zonePACallbackClients.remove(callbackClient);
                    }
                    System.out.println("Unregistered client of " + type);
                    break;
                case "ΠΒ" :
                    if (zonePBCallbackClients.contains(callbackClient)) {
                        zonePBCallbackClients.remove(callbackClient);
                    }
                    System.out.println("Unregistered client of " + type);
                    break;
                case "ΠΓ" :
                    if (zonePCCallbackClients.contains(callbackClient)) {
                        zonePCCallbackClients.remove(callbackClient);
                    }
                    System.out.println("Unregistered client of " + type);
                    break;
                case "ΚΕ" :
                    if (zoneKECallbackClients.contains(callbackClient)) {
                        zoneKECallbackClients.remove(callbackClient);
                    }
                    System.out.println("Unregistered client of " + type);
                    break;
                case "ΠΘ" :
                    if (zonePTHCallbackClients.contains(callbackClient)) {
                        zonePTHCallbackClients.remove(callbackClient);
                    }
                    System.out.println("Unregistered client of " + type);
                    break;
            }
    }

    public ArrayList<Reservations> guests(String hostname) 
        throws RemoteException {
        return reservsList;
    }

    public ArrayList<Reservations> cancel(String hostname, String type, int number, String name, THClientInterface clientObj) 
        throws RemoteException {
        boolean reservExists;
        boolean count = false;
        ArrayList<Reservations> clientReservs = new ArrayList<>();
        ListIterator<Reservations> iterator = reservsList.listIterator();

        while (iterator.hasNext()) {
            Reservations nextReserv = iterator.next();
            reservExists = (nextReserv.getType().equals(type) && (nextReserv.getNumber() == number) && (nextReserv.getName().equals(name)));
            if (reservExists) {
                iterator.remove();
                clientObj.updateClient("Cancellation successful!");
                restoreSeats(type, number);
                count = true;
            }
        }

        if (!count) {
            ArrayList<Reservations> reservNotFoundList = new ArrayList<>();
            clientObj.updateClient("Reservation Not Found");
            return reservNotFoundList;
        }
       
        if (!reservsList.isEmpty()) {
            for (Reservations reserv : reservsList) {
                if (reserv.getName().equals(name)) {
                    clientReservs.add(reserv);
                }
            }
        }

        return clientReservs;
    }
    
    private void restoreSeats(String type, int number)
        throws RemoteException {
        switch (type) {
            case "ΠΑ" :
                zonePA.unreserveSeats(number);
                break;
            case "ΠΒ" :
                zonePB.unreserveSeats(number);
                break;
            case "ΠΓ" :
                zonePC.unreserveSeats(number);
                break;
            case "ΚΕ" :
                zoneKE.unreserveSeats(number);
                break;
            case "ΠΘ" :
                zonePTH.unreserveSeats(number);
                break;
        }
    }

    public String updateServer(String upd)
        throws RemoteException {
        this.clientResponse = upd;
        return upd;
    }

    public String getClientResponse()
        throws RemoteException {
        return clientResponse;
    }
}

   
  
    

 
    
    

    

    
