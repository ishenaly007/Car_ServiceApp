Project Overview

  This project is a console-based application for managing users, cars, and orders. The application allows different user roles (Admin, Manager, Client) to interact with the system through a simple command-line interface. The primary features include user registration and authentication, car management, order management, and audit logging.

Features
1. User Management
   Registration and Authentication: Users can register and log in with a username and password.
   User Roles: Different roles (Admin, Manager, Client) with distinct permissions and accessible actions.
   User CRUD Operations: Admins can create, read, update, and delete users.
2. Car Management
   CRUD Operations: Admins and Managers can add, update, delete, and view car details.
   Search and Filter: Users can search cars by brand, model, year, price, and condition.
3. Order Management
   Order Creation: Clients can create orders for car repairs.
   Order Status Management: Managers can update the status of orders.
   Order Cancellation: Clients can cancel their orders.
   Search and Filter Orders: Search orders by date, client, status, and car.
4. Audit Logging
   Action Logging: Records actions performed by users, including login, CRUD operations, and order management.
   Audit Export: Admins can export the audit log to a file.
   Project Structure
   ConsoleApp: Entry point of the application. Manages user interaction and navigates through different menus based on user roles.
   UserManagement: Handles user registration and authentication.
   UserService and UserServiceImpl: Business logic for user management.
   CarService and CarServiceImpl: Business logic for car management.
   OrderService and OrderServiceImpl: Business logic for order management.
   AuditService: Manages logging of user actions.
   UserRepository, CarRepository, OrderRepository: Data access layers for users, cars, and orders, respectively.
   Menu Classes (AdminMenu, ManagerMenu, ClientMenu): Command-line interfaces for different user roles.
   Getting Started
   Clone the Repository:

bash
Копировать код
git clone <repository-url>
cd <repository-directory>
Compile and Run the Application:

bash
Копировать код
javac -d out src/*.java
java -cp out ConsoleApp
Requirements
Java: Make sure you have Java Development Kit (JDK) installed on your machine.
Future Enhancements
Implementing a more sophisticated user interface.
Adding additional functionalities like email notifications and payment processing.
Contributing
Feel free to fork this project and make your improvements. Pull requests are welcome! : )
