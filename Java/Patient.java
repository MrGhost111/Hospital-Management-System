import java.util.*;

public class Patient extends User {
    private String medicalRecord;
    private ArrayList<Appointment> appointments;

    public Patient(String hospitalID, String password, String name, String medicalRecord) {
        super(hospitalID, password, name);
        this.medicalRecord = medicalRecord;
        this.appointments = new ArrayList<>();
    }

    public void updateMedicalRecord(String newRecord) {
        this.medicalRecord = newRecord;
    }

    public ArrayList<Appointment> getAppointments() {
        return appointments;
    }

    public void viewAppointments() {
        if (appointments.isEmpty()) {
            System.out.println("No scheduled appointments.");
        } else {
            for (Appointment appointment : appointments) {
                System.out.println(appointment);
            }
        }
    }

    public void scheduleAppointment(Doctor doctor, String timeSlot) {
        Appointment appointment = new Appointment(this, doctor, timeSlot);
        appointments.add(appointment);
        doctor.addAppointment(appointment);  // Adding appointment to the doctor's list
        System.out.println("Appointment scheduled with Dr. " + doctor.getName() + " at " + timeSlot);
    }

    public void viewPrescriptions() {
        ArrayList<Prescription> prescriptions = DatabaseManager.getInstance().getPatientPrescriptions(hospitalID);
        if (prescriptions.isEmpty()) {
            System.out.println("No prescriptions found.");
        } else {
            for (Prescription prescription : prescriptions) {
                System.out.println(prescription);
                System.out.println("---------------");
            }
        }
    }

    public void viewBills() {
        ArrayList<Bill> bills = DatabaseManager.getInstance().getPatientBills(hospitalID);
        if (bills.isEmpty()) {
            System.out.println("No bills found.");
        } else {
            for (Bill bill : bills) {
                System.out.println(bill);
                System.out.println("---------------");
            }
        }
    }

    public void payBill(Scanner scanner) {
        ArrayList<Bill> bills = DatabaseManager.getInstance().getPatientBills(hospitalID);
        if (bills.isEmpty()) {
            System.out.println("No unpaid bills found.");
            return;
        }

        System.out.println("Unpaid Bills:");
        int index = 1;
        ArrayList<Bill> unpaidBills = new ArrayList<>();
        for (Bill bill : bills) {
            if (!bill.isPaid()) {
                System.out.printf("%d. Amount: $%.2f - %s%n", index++, bill.getAmount(), bill);
                unpaidBills.add(bill);
            }
        }

        if (unpaidBills.isEmpty()) {
            System.out.println("No unpaid bills found.");
            return;
        }

        System.out.print("Enter bill number to pay (1-" + unpaidBills.size() + "): ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        if (choice > 0 && choice <= unpaidBills.size()) {
            Bill selectedBill = unpaidBills.get(choice - 1);
            selectedBill.payBill();
            System.out.println("Bill paid successfully.");
        } else {
            System.out.println("Invalid bill number.");
        }
    }

    @Override
    public void displayMenu() {
        System.out.println("\nPatient Menu:");
        System.out.println("1. View Medical Record");
        System.out.println("2. Update Contact Information");
        System.out.println("3. View Scheduled Appointments");
        System.out.println("4. Schedule an Appointment");
        System.out.println("5. View Prescriptions");
        System.out.println("6. View Bills");
        System.out.println("7. Pay Bill");
        System.out.println("8. Change Password");
        System.out.println("9. Logout");
    }

    @Override
    public void handleUserChoice(int choice, Scanner scanner) {
        switch (choice) {
            case 1:
                System.out.println("Medical Record: " + medicalRecord);
                break;
            case 2:
                System.out.print("Enter new name: ");
                String newName = scanner.nextLine();
                System.out.print("Enter new contact info: ");
                String newContact = scanner.nextLine();
                updateProfile(newName, newContact);
                break;
            case 3:
                viewAppointments();
                break;
            case 4:
                System.out.print("Enter doctor ID: ");
                String docID = scanner.nextLine();
                System.out.print("Enter date (YYYY-MM-DD): ");
                String date = scanner.nextLine();
                System.out.print("Enter time slot: ");
                String timeSlot = scanner.nextLine();
                Doctor doctor = (Doctor) DatabaseManager.getInstance().getUser(docID);
                if (doctor != null) {
                    scheduleAppointment(doctor, date + " " + timeSlot);
                }
                break;
            case 5:
                viewPrescriptions();
                break;
            case 6:
                viewBills();
                break;
            case 7:
                payBill(scanner);
                break;
            case 8:
                System.out.print("Enter new password: ");
                String newPassword = scanner.nextLine();
                changePassword(newPassword);
                break;
            case 9:
                System.out.println("Logging out...");
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }
}
