import java.util.List;
import java.util.ArrayList;

public class PatientRecord {
    private String patientId;
    private String patientName;
    private List<String> treatments;
    private List<String> prescriptions;

    public PatientRecord(String patientId, String patientName) {
        this.patientId = patientId;
        this.patientName = patientName;
        this.treatments = new ArrayList<>();
        this.prescriptions = new ArrayList<>();
    }

    public String getPatientId() {
        return patientId;
    }

    public void addTreatment(String treatment) {
        treatments.add(treatment);
    }

    public void addPrescription(String prescription) {
        prescriptions.add(prescription);
    }

    @Override
    public String toString() {
        return "PatientRecord{" +
                "patientId='" + patientId + '\'' +
                ", patientName='" + patientName + '\'' +
                ", treatments=" + treatments +
                ", prescriptions=" + prescriptions +
                '}';
    }
}
