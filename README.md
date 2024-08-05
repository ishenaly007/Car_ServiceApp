
Console Management System
A console-based application for managing users, cars, and orders. This system provides distinct functionalities based on user roles (Admin, Manager, Client), including user registration, car management, order processing, and audit logging.

Features
User Management
Registration and Authentication: Secure user registration and login.
Role-based Access:
Admin: Full control over all users and data.
Manager: Manage orders and cars.
Client: Create and manage their own orders.
CRUD Operations:
Create, Read, Update, Delete users.
Car Management
CRUD Operations: Admins and Managers can manage car information.
Search and Filter: By brand, model, year, price, and condition.
Order Management
Order Creation: Clients can request car repairs.
Order Status Management: Managers update the status of orders.
Order Cancellation: Clients can cancel their orders.
Order Search and Filter: By date, client, status, and car.
Audit Logging
Action Tracking: Logs all significant actions performed by users.
Export Logs: Admins can export the audit logs.
Project Structure
ConsoleApp: The main entry point of the application.
UserManagement: Handles user-related operations, including registration and authentication.
UserService and UserServiceImpl: Business logic for user management.
CarService and CarServiceImpl: Business logic for car management.
OrderService and OrderServiceImpl: Business logic for order management.
AuditService: Manages and logs user activities.
Repositories:
UserRepository: Data access for users.
CarRepository: Data access for cars.
OrderRepository: Data access for orders.
Menu Classes:
AdminMenu: Interface for admin-specific actions.
ManagerMenu: Interface for manager-specific actions.
ClientMenu: Interface for client-specific actions.
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
Future Enhancements
Enhanced user interface.
Email notifications.
Payment processing.
Contributing
Contributions are welcome!:) Feel free to fork the repository and submit a pull request.