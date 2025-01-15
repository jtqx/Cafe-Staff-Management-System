# Cafe Staff Management System

## Overview

The **Cafe Staff Management System** is a Java-based application designed to streamline staff scheduling and management for cafes. It integrates core Java, JavaFX for the user interface, and Hibernate for database interactions, ensuring efficient and scalable functionality.

## Features

- **User Management**
  - Create, read, update, and delete user profiles.
  - Suspend or reinstate user accounts.

- **Work Slot Management**
  - Add, update, and delete work slots for scheduling purposes.
  - Track available work slots by date and availability status.
  - Fetch assigned staff counts for various roles (Cashier, Chef, Waiter).

- **Bid Management**
  - Submit bids for specific work slots.
  - Approve or reject bids based on availability and role requirements.

## Technologies Used

- **Java**: Core language for business logic and application flow.
- **JavaFX**: For building a rich, interactive user interface.
- **Hibernate**: For Object-Relational Mapping (ORM) and seamless database interactions.
- **MySQL**: Database for storing user, work slot, and bid information.
- **Maven**: For project dependency management.

## Prerequisites

- **Java Development Kit (JDK)** 8 or higher.
- **MySQL Server**: Ensure the database is running and accessible.
- **Maven**: For managing project dependencies.

## Setup and Installation

1. **Clone the Repository**

   ```bash
   git clone https://github.com/yourusername/cafe-staff-management.git
   cd cafe-staff-management
   ```

2. **Set Up the Database**

   - Import the `schema.sql` file located in the `resources/` directory to set up the MySQL database.
   - Update the `hibernate.cfg.xml` file in the `resources/` directory with your database credentials.

3. **Build the Project**

   ```bash
   mvn clean install
   ```

4. **Run the Application**

   ```bash
   mvn javafx:run
   ```

## Directory Structure

```
.
├── src
│   ├── main
│   │   ├── java
│   │   │   ├── Entities
│   │   │   │   ├── UserAccount.java
│   │   │   │   ├── UserProfile.java
│   │   │   │   ├── WorkSlot.java
│   │   │   │   ├── Bid.java
│   │   ├── util
│   │   │   ├── HibernateUtil.java
│   ├── resources
│   │   ├── hibernate.cfg.xml
│   │   ├── schema.sql
├── pom.xml
├── README.md
```

## Configuration

- **`hibernate.cfg.xml`**: Contains database connection settings for Hibernate. Update the `hibernate.connection.url`, `hibernate.connection.username`, and `hibernate.connection.password` properties.

- **`schema.sql`**: Use this file to initialize the database schema.

## Usage

1. Launch the application.
2. Navigate through the JavaFX GUI to manage users, work slots, and bids.
3. View or update records, and interact with the system to manage staff scheduling efficiently.

## Contributing

Contributions are welcome! If you'd like to contribute:

1. Fork the repository.
2. Create a new branch (`git checkout -b feature-name`).
3. Commit your changes (`git commit -m 'Add some feature'`).
4. Push to the branch (`git push origin feature-name`).
5. Open a pull request.

## License

This project is licensed under the MIT License. See the `LICENSE` file for details.
