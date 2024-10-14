![Alt text](https://upload.wikimedia.org/wikipedia/commons/thumb/a/a5/Flag_of_the_United_Kingdom_%281-2%29.svg/255px-Flag_of_the_United_Kingdom_%281-2%29.svg.png)

# Theater Reservation System with Java RMI

For the requested Assignment, click the link:  
[Assignment](Assignment/)

For the Source Code, click the link:  
[Code](Code/)

For the detailed Documentation, click the link:  
[Documentation](Documentation/)

## Overview

This project implements a Java RMI (Remote Method Invocation) application that allows multiple clients to reserve seats for a theater performance at Theater X. The application consists of a server (THServer), a client (THClient), a remote interface (THInterface), and the implementation of this interface (THImpl). The server manages seat reservations and notifies clients of availability.

## Course Information

- **Course**: [Distributed Systems](https://ice.uniwa.gr/education/undergraduate/courses/distributed-systems/)
- **Semester**: 6
- **Program of Study**: [UNIWA](https://www.uniwa.gr/)
- **Department**: [Informatics and Computer Engineering](https://ice.uniwa.gr/)
- **Lab Instructor**: [Doka Aikaterini](http://www.cslab.ece.ntua.gr/~doka/)
- **Academic Season**: 2022-2023

## Student Information

- **Name**: Athanasiou Vasileios Evangelos
- **Student ID**: 19390005
- **Status**: Undergraduate

## Key Features

- **Concurrent Server**: The server can handle multiple client requests simultaneously.
- **Available Seats**: The theater offers:
  - **100 PA type seats** (Square - Zone A) at **45 Euros each**
  - **200 PB type seats** (Square - Zone B) at **35 Euros each**
  - **400 PC type seats** (Square - Zone C) at **25 Euros each**
  - **225 KE type seats** (Central Exit) at **30 Euros each**
  - **75 PTH type seats** (Side Theories) at **20 Euros each**
- **Client Commands**:
  - **List Available Seats**: Clients can list all available seats and their prices.
  - **Reserve Seats**: Clients can reserve a specified number of seats.
  - **List Reservations**: Clients can view all reservations made.
  - **Cancel Reservations**: Clients can cancel their reservations.
- **Subscription for Notifications**: Clients can subscribe for immediate notifications when seats become available again after a cancellation.

## Program Workflow

1. **Client Initialization**: Clients run the TheaterClient application, specifying commands and parameters.
2. **Seat Management**:
   - Clients can list available seats.
   - Clients can reserve seats by providing the type, number, and their name.
   - Clients can view their reservations.
   - Clients can cancel their reservations, with the option to subscribe for notifications if they receive a failure message during booking.
3. **Server Processing**: The TheaterServer manages the reservation process, updates available seats, and communicates with the RMI registry.
4. **Notifications**: When a seat is canceled, all subscribed clients receive immediate notifications.

## Requirements

- **Java Development Kit (JDK)**: Ensure you have JDK 8 or higher installed.
- **RMI Registry**: The RMI registry must be running on the specified host.
- **Java Libraries**: Ensure that your Java environment supports RMI.

## Installation & Usage

### 1. Clone the Repository
Download the repository to your local machine:
```
git clone https://github.com/Distributed-Systems-aka-Uniwa/RMI.git
```
### 2. Compile the Classes
Compile the Java classes using the following command:
```
javac *.java
```
### 3. Start the RMI Registry
Start the RMI registry in a terminal:
```
rmiregistry
```
### 4. Run the Theater Server
In a new terminal, start the Theater Server:
```
java THServer
```
### 5. Run the Theater Client
Open multiple terminals to run the client for testing multiple users:
```
java THClient
```
### 6. Usage Instructions
To list available seats:
```
java THClient list <hostname>
```
To book seats:
```
java THClient book <hostname> <type> <number> <name>
```
To view all reservations:
```
java THClient guests <hostname>
```
To cancel reservations:
```
java THClient cancel <hostname> <type> <number> <name>
```

![Alt text](https://upload.wikimedia.org/wikipedia/commons/thumb/5/5c/Flag_of_Greece.svg/255px-Flag_of_Greece.svg.png)

# Σύστημα Κρατήσεων Θεάτρου με Java RMI

Για την ζητούμενη εργασία, κάντε κλικ στον σύνδεσμο:  
[Assignment](Assignment/)

Για τον Πηγαίο Κώδικα, κάντε κλικ στον σύνδεσμο:  
[Code](Code/)

Για την αναλυτική Τεκμηρίωση, κάντε κλικ στον σύνδεσμο:  
[Documentation](Documentation/)

## Επισκόπηση

Αυτό το έργο υλοποιεί μια εφαρμογή Java RMI (Remote Method Invocation) που επιτρέπει σε πολλούς πελάτες να κάνουν κρατήσεις θέσεων για μια παράσταση στο Θέατρο X. Η εφαρμογή αποτελείται από έναν διακομιστή (THServer), έναν πελάτη (THClient), μια απομακρυσμένη διεπαφή (THInterface) και την υλοποίηση αυτής της διεπαφής (THImpl). Ο διακομιστής διαχειρίζεται τις κρατήσεις θέσεων και ειδοποιεί τους πελάτες για την διαθεσιμότητα.

## Πληροφορίες Μαθήματος

- **Μάθημα**: [Κατανεμημένα Συστήματα](https://ice.uniwa.gr/education/undergraduate/courses/distributed-systems/)
- **Εξάμηνο**: 6
- **Πρόγραμμα Σπουδών**: [ΠΑΔΑ](https://www.uniwa.gr/)
- **Τμήμα**: [Μηχανικών Πληροφορικής και Υπολογιστών](https://ice.uniwa.gr/)
- **Εκπαιδευτής Εργαστηρίου**: [Δόκα Αικατερίνη](http://www.cslab.ece.ntua.gr/~doka/)
- **Ακαδημαϊκή Χρονιά**: 2022-2023

## Πληροφορίες Φοιτητή

- **Όνομα**: Αθανασίου Βασίλειος Ευάγγελος
- **Αριθμός Φοιτητή**: 19390005
- **Κατάσταση**: Προπτυχιακός Φοιτητής

## Κύρια Χαρακτηριστικά

- **Ταυτόχρονος Διακομιστής**: Ο διακομιστής μπορεί να χειριστεί πολλές αιτήσεις πελατών ταυτόχρονα.
- **Διαθέσιμες Θέσεις**: Το θέατρο προσφέρει:
  - **100 ΠΑ τύπου θέσεις** (Πλατεία - Ζώνη Α) στα **45 Ευρώ η καθεμία**
  - **200 ΠΒ τύπου θέσεις** (Πλατεία - Ζώνη Β) στα **35 Ευρώ η καθεμία**
  - **400 ΠΓ τύπου θέσεις** (Πλατεία - Ζώνη Γ) στα **25 Ευρώ η καθεμία**
  - **225 ΚΕ τύπου θέσεις** (Κεντρικός Εξώστης) στα **30 Ευρώ η καθεμία**
  - **75 ΠΘ τύπου θέσεις** (Πλαϊνά Θεωρεία) στα **20 Ευρώ η καθεμία**
- **Εντολές Πελάτη**:
  - **Λίστα Διαθέσιμων Θέσεων**: Οι πελάτες μπορούν να δουν όλες τις διαθέσιμες θέσεις και τις τιμές τους.
  - **Κράτηση Θέσεων**: Οι πελάτες μπορούν να κάνουν κράτηση για έναν καθορισμένο αριθμό θέσεων.
  - **Λίστα Κρατήσεων**: Οι πελάτες μπορούν να δουν όλες τις κρατήσεις που έχουν κάνει.
  - **Ακύρωση Κρατήσεων**: Οι πελάτες μπορούν να ακυρώσουν τις κρατήσεις τους.
- **Συνδρομή για Ειδοποιήσεις**: Οι πελάτες μπορούν να εγγραφούν για άμεσες ειδοποιήσεις όταν οι θέσεις γίνουν διαθέσιμες ξανά μετά από μια ακύρωση.

## Ροή Προγράμματος

1. **Αρχικοποίηση Πελάτη**: Οι πελάτες εκκινούν την εφαρμογή TheaterClient, καθορίζοντας εντολές και παραμέτρους.
2. **Διαχείριση Θέσεων**:
   - Οι πελάτες μπορούν να δουν τις διαθέσιμες θέσεις.
   - Οι πελάτες μπορούν να κάνουν κράτηση θέσεων παρέχοντας τον τύπο, τον αριθμό και το όνομά τους.
   - Οι πελάτες μπορούν να δουν τις κρατήσεις τους.
   - Οι πελάτες μπορούν να ακυρώσουν τις κρατήσεις τους, με την επιλογή να εγγραφούν για ειδοποιήσεις εάν λάβουν μήνυμα αποτυχίας κατά την κράτηση.
3. **Επεξεργασία Διακομιστή**: Ο TheaterServer διαχειρίζεται τη διαδικασία κρατήσεων, ενημερώνει τις διαθέσιμες θέσεις και επικοινωνεί με το RMI registry.
4. **Ειδοποιήσεις**: Όταν μια θέση ακυρωθεί, όλοι οι εγγεγραμμένοι πελάτες λαμβάνουν άμεσες ειδοποιήσεις.

## Απαιτήσεις

- **Java Development Kit (JDK)**: Βεβαιωθείτε ότι έχετε εγκαταστήσει το JDK 8 ή νεότερο.
- **RMI Registry**: Ο RMI registry πρέπει να είναι σε λειτουργία στον καθορισμένο υπολογιστή.
- **Java Libraries**: Βεβαιωθείτε ότι το περιβάλλον Java υποστηρίζει το RMI.

## Εγκατάσταση & Χρήση

### 1. Κλωνοποίηση του Αποθετηρίου
Κατεβάστε το αποθετήριο στον τοπικό σας υπολογιστή:
```
git clone https://github.com/Distributed-Systems-aka-Uniwa/RMI.git
```
### 2. Μεταγλώττιση των Κλάσεων
Μεταγλωττίστε τις κλάσεις Java χρησιμοποιώντας την παρακάτω εντολή:
```
javac *.java
```
### 3. Εκκίνηση του RMI Registry
Ξεκινήστε το RMI registry σε ένα τερματικό:
```
rmiregistry
```
### 4. Εκκίνηση του Θεατρικού Διακομιστή
Σε ένα νέο τερματικό, ξεκινήστε τον THServer:
```
java THServer
```
### 5. Εκκίνηση του Θεατρικού Πελάτη
Ανοίξτε πολλαπλά τερματικά για να τρέξετε τον πελάτη για τη δοκιμή πολλών χρηστών:
```
java THClient
```
### 6. Οδηγίες Χρήσης
Για να δείτε τις διαθέσιμες θέσεις:
```
java THClient list <hostname>
```
Για να κάνετε κράτηση θέσεων:
```
java THClient book <hostname> <type> <number> <name>
```
Για να δείτε όλες τις κρατήσεις:
```
java THClient guests <hostname>
```
Για να ακυρώσετε κρατήσεις:
```
java THClient cancel <hostname> <type> <number> <name>
```
