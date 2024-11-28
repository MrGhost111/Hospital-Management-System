public class Prescription {
    private String prescriptionID;
    private String patientID;
    private String doctorID;
    private String medication;
    private String dosage;
    private String instructions;
    private String date;

    public Prescription(String prescriptionID, String patientID, String doctorID,
                        String medication, String dosage, String instructions, String date) {
        this.prescriptionID = prescriptionID;
        this.patientID = patientID;
        this.doctorID = doctorID;
        this.medication = medication;
        this.dosage = dosage;
        this.instructions = instructions;
        this.date = date;
    }

    public String getPatientID() {
        return patientID;
    }

    @Override
    public String toString() {
        return String.format("Prescription ID: %s\nMedication: %s\nDosage: %s\nInstructions: %s\nDate: %s",
                prescriptionID, medication, dosage, instructions, date);
    }
}