import java.util.*;

public class Appointment {
    private Patient patient;
    private Doctor doctor;
    private String timeSlot;

    public Appointment(Patient patient, Doctor doctor, String timeSlot) {
        this.patient = patient;
        this.doctor = doctor;
        this.timeSlot = timeSlot;
    }

    public void setTimeSlot(String newTimeSlot) {
        this.timeSlot = newTimeSlot;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    @Override
    public String toString() {
        return "Appointment with Dr. " + doctor.getName() + " at " + timeSlot;
    }
}
