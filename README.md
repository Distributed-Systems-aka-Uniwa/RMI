<p align="center">
  <img src="https://www.especial.gr/wp-content/uploads/2019/03/panepisthmio-dut-attikhs.png" alt="UNIWA" width="150"/>
</p>

<p align="center">
  <strong>UNIVERSITY OF WEST ATTICA</strong><br>
  SCHOOL OF ENGINEERING<br>
  DEPARTMENT OF COMPUTER ENGINEERING AND INFORMATICS
</p>

---

<p align="center">
  <strong>Distributed Systems</strong>
</p>

<h1 align="center">
  Theater Reservation System with Java RMI
</h1>

<p align="center">
  <strong>Vasileios Evangelos Athanasiou</strong><br>
  Student ID: 19390005
</p>

<p align="center">
  <a href="https://github.com/Ath21" target="_blank">GitHub</a> ·
  <a href="https://www.linkedin.com/in/vasilis-athanasiou-7036b53a4/" target="_blank">LinkedIn</a>
</p>

<p align="center">
  Supervisor: Doka Katerina, Senior Researcher<br>
</p>

<p align="center">
  <a href="http://www.cslab.ece.ntua.gr/~doka/" target="_blank">UNIWA Profile</a> ·
  <a href="https://www.linkedin.com/in/kdoka/" target="_blank">LinkedIn</a>
</p>

<p align="center">
  Athens, June 2023
</p>

---

# Project Overview

This project implements a Java RMI (Remote Method Invocation) application that allows multiple clients to reserve seats for a theater performance at Theater X. The application consists of a server (THServer), a client (THClient), a remote interface (THInterface), and the implementation of this interface (THImpl). The server manages seat reservations and notifies clients of availability.

---

## Table of Contents


| Section | Folder / File | Description |
|------:|---------------|-------------|
| 1 | `assign/` | Assignment material |
| 1.1 | `assign/DS-LAB-ASK-2-2022-23.pdf` | Laboratory assignment description (English) |
| 1.2 | `assign/ΚΣ-ΕΡΓΑΣΤΗΡΙΟ-ΑΣΚ-2-2022-23.pdf` | Περιγραφή εργαστηριακής άσκησης (Greek) |
| 2 | `docs/` | Theoretical documentation |
| 2.1 | `docs/Java-Remote-Method-Invocation.pdf` | Java RMI theory (English) |
| 2.2 | `docs/Απομακρυσμένη-Κλήση-Μεθόδου-Java.pdf` | Απομακρυσμένη Κλήση Μεθόδου – Java (Greek) |
| 3 | `src/` | Source code implementation |
| 3.1 | `src/THServer.java` | Remote server main program |
| 3.2 | `src/THImpl.java` | Remote object implementation |
| 3.3 | `src/THInterface.java` | Remote interface definition |
| 3.4 | `src/THClient.java` | Client main program |
| 3.5 | `src/THClientImpl.java` | Client-side object implementation |
| 3.6 | `src/THClientInterface.java` | Client-side interface |
| 3.7 | `src/AvailableSeats.java` | Supporting class: seat management |
| 3.8 | `src/Reservations.java` | Supporting class: reservation management |
| 4 | `README.md` | Repository overview and usage instructions |

---

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

---

## Open the Documentation
1. Navigate to the `docs/` directory
2. Open the report corresponding to your preferred language:
    - English: `Java-Remote-Method-Invocation.pdf`
    - Greek: `Απομακρυσμένη-Κλήση-Μεθόδου-Java.pdf`