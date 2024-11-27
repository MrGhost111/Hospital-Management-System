import java.util.*;

public class Administrators extends Users {
    private DatabaseManager db;

    public Administrators(String hospitalID, String password, String name) {
        super(hospitalID, password, name);
        this.db = DatabaseManager.getInstance();
    }

    public void generateReport(String reportType) {
        System.out.println("\nGenerating " + reportType + " Report");
        System.out.println("================================");

        switch (reportType.toLowerCase()) {
            case "billing":
                double totalRevenue = 0;
                int totalBills = 0;
                int paidBills = 0;
                for (Bill bill : db.getPatientBills("")) {  // Gets all bills
                    totalBills++;
                    if (bill.isPaid()) {
                        paidBills++;
                        totalRevenue += bill.getAmount();
                    }
                }
                System.out.println("Total Bills: " + totalBills);
                System.out.println("Paid Bills: " + paidBills);
                System.out.println("Total Revenue: $" + totalRevenue);
                break;

            case "patient":
                // Count total appointments and prescriptions
                int totalAppointments = 0;
                int totalPrescriptions = 0;
                for (Users user : db.getAllUsers()) {
                    if (user instanceof Patient) {
                        totalPrescriptions += db.getPatientPrescriptions(user.getHospitalID()).size();
                        // Count appointments
                        Patient patient = (Patient) user;
                        totalAppointments += patient.getAppointments().size();
                    }
                }
                System.out.println("Total Appointments: " + totalAppointments);
                System.out.println("Total Prescriptions: " + totalPrescriptions);
                break;
        }
    }

    public void addNurse(String nurseID, String name, String department) {
        Nurses nurse = new Nurses(nurseID, name, department);
        db.addNurse(nurse);
        System.out.println("Nurse added successfully.");
    }

    public void viewNurses() {
        ArrayList<Nurses> nurses = db.getNurses();
        if (nurses.isEmpty()) {
            System.out.println("No nurses registered.");
        } else {
            for (int i = 0; i < nurses.size(); i++) {
                System.out.println(i + ": " + nurses.get(i).getName());
            }
        }
    }

    public void removeNurse(int index) {
        db.removeNurse(index);
        System.out.println("Nurse removed successfully.");
    }

    public void assignDoctorToPatient(String patientID, String doctorID) {
        db.assignDoctorToPatient(patientID, doctorID);
        System.out.println("Doctor assigned to patient successfully.");
    }

    public void generateBill(String patientID, double amount, String description) {
        String billID = "BILL" + System.currentTimeMillis();
        Bill bill = new Bill(billID, patientID, amount, description, java.time.LocalDate.now().toString());
        db.addBill(bill);
        System.out.println("Bill generated successfully.");
    }

    public void updateUserDetails(String hospitalID, String newName, String newContactInfo) {
        Users user = db.getUser(hospitalID);
        if (user != null) {
            user.updateProfile(newName, newContactInfo);
            db.updateUser(user);
        } else {
            System.out.println("User not found!");
        }
    }

    @Override
    public void displayMenu() {
        System.out.println("\nAdministrator Menu:");
        System.out.println("1. View Staff List");
        System.out.println("2. Add Staff Member");
        System.out.println("3. Remove Staff Member");
        System.out.println("4. Manage Nurses");
        System.out.println("5. Generate Reports");
        System.out.println("6. Assign Doctor to Patient");
        System.out.println("7. Generate Bill");
        System.out.println("8. Update User Details");
        System.out.println("9. Logout");
    }

    @Override
    public void handleUserChoice(int choice, Scanner scanner) {
        switch (choice) {
            case 1:
                System.out.println("\nDoctors:");
                for (Users user : db.getAllUsers()) {
                    if (user instanceof Doctor) {
                        System.out.println(user.getHospitalID() + ": Dr. " + user.getName());
                    }
                }
                break;

            case 2:
                System.out.println("1. Add Doctor");
                System.out.println("2. Add Patient");
                int type = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                System.out.print("Enter hospital ID: ");
                String hospitalID = scanner.nextLine();
                System.out.print("Enter name: ");
                String name = scanner.nextLine();
                System.out.print("Enter password: ");
                String password = scanner.nextLine();

                if (type == 1) {
                    Doctor doctor = new Doctor(hospitalID, password, name);
                    db.addUser(doctor);
                    System.out.println("Doctor added successfully.");
                } else if (type == 2) {
                    Patient patient = new Patient(hospitalID, password, name, "New patient");
                    db.addUser(patient);
                    System.out.println("Patient added successfully.");
                }
                break;

            case 3:
                System.out.print("Enter hospital ID to remove: ");
                String idToRemove = scanner.nextLine();
                db.removeUser(idToRemove);
                System.out.println("User removed successfully.");
                break;

            case 4:
                System.out.println("1. View Nurses");
                System.out.println("2. Add Nurse");
                System.out.println("3. Remove Nurse");
                int nurseChoice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (nurseChoice) {
                    case 1:
                        viewNurses();
                        break;
                    case 2:
                        System.out.print("Enter nurse ID: ");
                        String nurseID = scanner.nextLine();
                        System.out.print("Enter name: ");
                        String nurseName = scanner.nextLine();
                        System.out.print("Enter department: ");
                        String department = scanner.nextLine();
                        addNurse(nurseID, nurseName, department);
                        break;
                    case 3:
                        viewNurses();
                        System.out.print("Enter nurse index to remove: ");
                        int index = scanner.nextInt();
                        removeNurse(index);
                        break;
                }
                break;

            case 5:
                System.out.println("1. Billing Report");
                System.out.println("2. Patient Report");
                int reportChoice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                if (reportChoice == 1) {
                    generateReport("billing");
                } else if (reportChoice == 2) {
                    generateReport("patient");
                }
                break;

            case 6:
                System.out.print("Enter patient ID: ");
                String patientID = scanner.nextLine();
                System.out.print("Enter doctor ID: ");
                String doctorID = scanner.nextLine();
                assignDoctorToPatient(patientID, doctorID);
                break;

            case 7:
                System.out.print("Enter patient ID: ");
                String billPatientID = scanner.nextLine();
                System.out.print("Enter amount: ");
                double amount = scanner.nextDouble();
                scanner.nextLine(); // Consume newline
                System.out.print("Enter description: ");
                String description = scanner.nextLine();
                generateBill(billPatientID, amount, description);
                break;

            case 8:
                System.out.print("Enter hospital ID of user to update: ");
                String updateID = scanner.nextLine();
                System.out.print("Enter new name: ");
                String newName = scanner.nextLine();
                System.out.print("Enter new contact info: ");
                String newContact = scanner.nextLine();
                updateUserDetails(updateID, newName, newContact);
                break;

            case 9:
                System.out.println("Logging out...");
                break;

            default:
                System.out.println("Invalid choice.");
        }
    }
}
