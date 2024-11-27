import java.util.*;

public class DatabaseManager {
    private static DatabaseManager instance;
    private Map<String, User> users;
    private ArrayList<Nurse> nurses;
    private ArrayList<Prescription> prescriptions;
    private ArrayList<Bill> bills;
    private Map<String, ArrayList<String>> patientDoctorAssignments;

    private DatabaseManager() {
        users = new HashMap<>();
        nurses = new ArrayList<>();
        prescriptions = new ArrayList<>();
        bills = new ArrayList<>();
        patientDoctorAssignments = new HashMap<>();
    }

    public static DatabaseManager getInstance() {
        if (instance == null) {
            instance = new DatabaseManager();
        }
        return instance;
    }

    public void addUser(User user) {
        users.put(user.getHospitalID(), user);
    }

    public User getUser(String hospitalID) {
        return users.get(hospitalID);
    }

    public Collection<User> getAllUsers() {
        return users.values();
    }

    public void addNurse(Nurse nurse) {
        nurses.add(nurse);
    }

    public void removeNurse(int index) {
        if (index >= 0 && index < nurses.size()) {
            nurses.remove(index);
        }
    }

    public ArrayList<Nurse> getNurses() {
        return nurses;
    }

    public void addPrescription(Prescription prescription) {
        prescriptions.add(prescription);
    }

    public ArrayList<Prescription> getPatientPrescriptions(String patientID) {
        ArrayList<Prescription> patientPrescriptions = new ArrayList<>();
        for (Prescription prescription : prescriptions) {
            if (prescription.getPatientID().equals(patientID)) {
                patientPrescriptions.add(prescription);
            }
        }
        return patientPrescriptions;
    }

    public void addBill(Bill bill) {
        bills.add(bill);
    }

    public ArrayList<Bill> getPatientBills(String patientID) {
        ArrayList<Bill> patientBills = new ArrayList<>();
        for (Bill bill : bills) {
            if (bill.getPatientID().equals(patientID)) {
                patientBills.add(bill);
            }
        }
        return patientBills;
    }

    public void assignDoctorToPatient(String patientID, String doctorID) {
        patientDoctorAssignments.putIfAbsent(patientID, new ArrayList<>());
        patientDoctorAssignments.get(patientID).add(doctorID);
    }

    public ArrayList<String> getPatientDoctors(String patientID) {
        return patientDoctorAssignments.getOrDefault(patientID, new ArrayList<>());
    }

    // New methods to handle user updates and removal
    public void removeUser(String hospitalID) {
        users.remove(hospitalID);
    }

    public void updateUser(User user) {
        users.put(user.getHospitalID(), user);
    }
}
