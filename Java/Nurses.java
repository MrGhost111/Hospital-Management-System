public class Nurses {
    private String nurseID;
    private String name;
    private String department;

    public Nurses(String nurseID, String name, String department) {
        this.nurseID = nurseID;
        this.name = name;
        this.department = department;
    }

    // Getter method for name
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return String.format("Nurse ID: %s, Name: %s, Department: %s", nurseID, name, department);
    }
}
