                        Hospital Management System (HMS)
Project Description
The Hospital Management System (HMS) is a Java-based command-line interface (CLI) application designed to automate and streamline various operations within a hospital. It offers role-based access for users, such as patients, doctors, and administrators, allowing users to interact with the system according to their specific roles and privileges.

The system focuses on managing hospital operations like patient records, appointment scheduling, staff management, and many other things. The HMS adheres to core Object-Oriented Programming (OOP) principles like encapsulation, inheritance, polymorphism, and abstraction, ensuring a modular, scalable, and maintainable design.

Key Features
Role-Based Access: Supports three main user roles with specific capabilities:

Administrators: Manage hospital staff (add/update/remove doctors, nurses, and patients), oversee appointments, generate reports (billing, patient history), manage hospital operations, and assign doctors and nurses to patients.
Doctors: Manage patient records, view and update appointments, prescribe medications, record treatment outcomes, and manage personal profile and availability schedule.
Patients: View medical records, update personal information, schedule, reschedule, or cancel appointments, view prescriptions, and pay bills.
Login System: Users log in with a hospital ID and password, and are presented with role-specific menus and functionalities. (the credential for each role are shared in this document.)

Setup Instructions
To set up and run the Hospital Management System on your local machine, follow these steps:

Install Java Development Kit (JDK):

(17 is preferred)
Clone the Repository:

Clone the project repository to your local machine using the following command:
Compile the Project:

Navigate to the project directory and compile the Java files.
Run the Application:

Run the Main class to start the application:
Login Credentials:

Use the following default login credentials to access the system:
Administrator:
Hospital ID: A001
Password: password
Doctor:
Hospital ID: D001
Password: password
Patient:
Hospital ID: P001
Password: password
How to Use
Login Process
Start the application and enter your hospital ID and password.
Depending on the credentials, you will be logged in as a patient, doctor, or administrator.
Role-Specific Menus
Patient Menu: Options to view medical records, schedule appointments, view prescriptions, and pay bills.
Doctor Menu: Options to view and update patient records, manage appointments, and prescribe medications.
Administrator Menu: Options to manage hospital staff, generate reports, and oversee appointments.
Performing Actions
Choose an option from your role-specific menu to perform the corresponding action.
Follow the prompts to enter the necessary details for each action.
Logging Out
Select the "Logout" option from your menu to log out and return to the login prompt (it will be the last option).
Object-Oriented Concepts Used
Encapsulation: Data like hospital ID, password, and medical records are encapsulated in the User, Patient, and Doctor classes.
Inheritance: The base class User provides common attributes and methods for the subclasses Patient, Doctor, and Administrator.
Tools & Technologies
Programming Language: Java (using JDK 17)
Development Environment: IntelliJ IDEA for code development
Design Approach: Object-oriented programming principles
User Interface: Command-Line Interface (CLI)
Possible Enhancements That We Coudn't Do
Graphical User Interface (GUI): Enhance the system with a GUI using JavaFX or Swing for a more user-friendly experience.
Database Integration: Connect to a database (e.g., MySQL) for persistent data storage.
More Roles and Features: Add roles like Nurses, Pharmacist or Technicians and features like billing and insurance management.
Security Enhancements: Implement hashed passwords and stronger authentication mechanisms.
