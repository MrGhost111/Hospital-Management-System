import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DatabaseManager db = DatabaseManager.getInstance();

        // Create and add sample users
        Patient patient = new Patient("P001", "password", "John Smith", "Initial record");
        Doctor doctor = new Doctor("D001", "password", "Dr. C.N Sharma");
        Administrator admin = new Administrator("A001", "password", "Admin User");

        // Add users to database
        db.addUser(patient);
        db.addUser(doctor);
        db.addUser(admin);

        // Add some sample data
        admin.addNurse("N001", "Sarah Johnson", "General");
        admin.generateBill("P001", 100.00, "Initial Consultation");
        doctor.prescribeMedication("P001", "Paracetamol", "500mg", "Take twice daily");

        User currentUser = null;

        while (true) {
            System.out.println("\n=== Hospital Management System ===");
            System.out.println("=================================");
            System.out.println("Please login to continue");

            System.out.print("Enter User ID: ");
            String userID = scanner.nextLine();
            System.out.print("Enter Password: ");
            String password = scanner.nextLine();

            // Get user from database and validate login
            User user = db.getUser(userID);
            if (user != null && user.login(userID, password)) {
                currentUser = user;
                System.out.println("\nWelcome, " + user.name + "!");
            } else {
                System.out.println("Invalid credentials. Please try again.");
                continue;
            }

            currentUser.promptPasswordChange(scanner); // Prompt for password change if needed

            // User session loop
            while (currentUser != null) {
                try {
                    currentUser.displayMenu();
                    System.out.print("Choose an option: ");
                    if (scanner.hasNextInt()) {
                        int choice = scanner.nextInt();
                        scanner.nextLine(); // consume the newline
                        currentUser.handleUserChoice(choice, scanner);

                        // Check for logout options
                        if ((currentUser instanceof Administrator && choice == 8) ||
                                (currentUser instanceof Doctor && choice == 6) ||
                                (currentUser instanceof Patient && choice == 9)) {
                            currentUser = null;
                        }
                    } else {
                        System.out.println("Please enter a number.");
                        scanner.nextLine(); // consume invalid input
                    }
                } catch (Exception e) {
                    System.out.println("An error occurred. Please try again.");
                    scanner.nextLine(); // consume any remaining invalid input
                }
            }
        }
    }
}
