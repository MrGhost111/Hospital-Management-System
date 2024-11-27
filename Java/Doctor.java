import java.util.*;

public class Doctor extends User {
    private ArrayList<Appointment> appointments;
    private Map<String, ArrayList<String>> availability;

    public Doctor(String hospitalID, String password, String name) {
        super(hospitalID, password, name);
        this.appointments = new ArrayList<>();
        this.availability = new HashMap<>();
    }

    public void prescribeMedication(String patientID, String medication, String dosage, String instructions) {
        String prescriptionID = "PRE" + System.currentTimeMillis();
        Prescription prescription = new Prescription(prescriptionID, patientID, this.hospitalID,
                medication, dosage, instructions, java.time.LocalDate.now().toString());
        DatabaseManager.getInstance().addPrescription(prescription);
        System.out.println("Prescription created successfully.");
    }

    public void updateAvailability(String date, String timeSlot) {
        availability.putIfAbsent(date, new ArrayList<>());
        availability.get(date).add(timeSlot);
        System.out.println("Availability updated for " + date);
    }

    public void updatePatientRecord(Patient patient, String newRecord) {
        patient.updateMedicalRecord(newRecord);
        System.out.println("Patient medical record updated.");
    }

    public void addAppointment(Appointment appointment) {
        appointments.add(appointment);
    }

    @Override
    public void displayMenu() {
        System.out.println("\nDoctor Menu:");
        System.out.println("1. View Appointments");
        System.out.println("2. Update Patient Medical Records");
        System.out.println("3. Prescribe Medication");
        System.out.println("4. Update Availability");
        System.out.println("5. Update Profile");
        System.out.println("6. Logout");
    }

    @Override
    public void handleUserChoice(int choice, Scanner scanner) {
        switch (choice) {
            case 1:
                viewAppointments();
                break;
            case 2:
                System.out.print("Enter patient ID: ");
                String patientID = scanner.nextLine();
                System.out.print("Enter new medical record: ");
                String newRecord = scanner.nextLine();
                Patient patient = (Patient) DatabaseManager.getInstance().getUser(patientID);
                if (patient != null) {
                    updatePatientRecord(patient, newRecord);
                }
                break;
            case 3:
                System.out.print("Enter patient ID: ");
                String prescPatientID = scanner.nextLine();
                System.out.print("Enter medication: ");
                String medication = scanner.nextLine();
                System.out.print("Enter dosage: ");
                String dosage = scanner.nextLine();
                System.out.print("Enter instructions: ");
                String instructions = scanner.nextLine();
                prescribeMedication(prescPatientID, medication, dosage, instructions);
                break;
            case 4:
                System.out.print("Enter date (YYYY-MM-DD): ");
                String date = scanner.nextLine();
                System.out.print("Enter time slot: ");
                String timeSlot = scanner.nextLine();
                updateAvailability(date, timeSlot);
                break;
            case 5:
                System.out.print("Enter new name: ");
                String newName = scanner.nextLine();
                System.out.print("Enter new contact info: ");
                String newContact = scanner.nextLine();
                updateProfile(newName, newContact);
                break;
            case 6:
                System.out.println("Logging out...");
                break;
            default:
                System.out.println("Invalid choice.");
        }
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
}
