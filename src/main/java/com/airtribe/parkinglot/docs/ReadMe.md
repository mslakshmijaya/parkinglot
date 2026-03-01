


# 🚗 Parking Lot Management System

A Spring Boot-based backend application for managing a smart parking lot. It handles vehicle check-ins and check-outs, real-time parking spot availability, fee calculation, and transaction tracking.

---

## 📌 Features

- Vehicle check-in with automatic spot allocation based on vehicle size
- Vehicle check-out with fee calculation based on duration and size
- Real-time tracking of available parking spots
- Prevention of duplicate check-ins for the same vehicle
- RESTful APIs for integration with frontend or mobile apps
- Exception handling with structured JSON error responses

---

## 🛠️ Tech Stack

- **Java 25**
- **Spring Boot**
- **Spring Data JPA**
- **H2 / MySQL** (configurable)
- **Lombok**
- **Maven / Gradle**
- **JUnit 5** for testing

---

## 🚀 Getting Started

### Prerequisites

- Java 25
- Maven or Gradle
- IntelliJ IDEA (Ultimate recommended for class diagrams)

### Clone the Repository

```bash
git clone https://github.com/mslakshmijaya/parkinglot
cd parkinglot

````
📂 Project Structure
src/main/java/com/airtribe/parkinglot/
├── controller/
├── service/
├── entity/
├── dto/
├── repository/
├── exception/
├── enums/
└── util/



🧠 Future Enhancements
- WebSocket support for real-time UI updates
- Admin dashboard for monitoring
- Role-based access control
- Integration with payment gateways

📄
---

📬### API Endpoints
|    Method    |   End Point                |     Description      | 
---------------------------------------------------------------------------------------------------------
|   POST       | spots/init                     |     Initialization of the parking lot with the floors 
                                                    and spots with specified size count                | 
|   GET        | /spots/available?sizeCategory=?|  We can find the available slots by spotsize or all  |                                                          | 
|   POST       | parkingTransaction/checkin     |   vehicle checkin  with ticketid                     | 
|    GET       | parkingTransaction/checkout?ticketId=TICKET-1001 |  vechicle checkout with ticket id  | 






## use below Service URL to create parkinglot with floors adn spots
## initialization of parkinglot
http://localhost:1000/spots/init
Sample Payload: 
[
{ "floor": 1, "smallCount": 10, "mediumCount": 10, "largeCount": 5  },
{ "floor": 2, "smallCount": 3, "mediumCount": 2, "largeCount": 3  },
{ "floor": 3, "smallCount": 6, "mediumCount": 4, "largeCount": 2  }
]

## find the avaialable slots by sizecategory or all
http://localhost:1000/spots/available?sizeCategory=SMALL

## Checkin URL with payload: 
http://localhost:1000/parkingTransaction/checkin

{"vehicleSize":"MEDIUM","licensePlate":"TS15EF0820"}

## checkout URL:
http://localhost:1000/parkingTransaction/checkout?ticketId=TICKET-1001