public class Bill {
    private String billID;
    private String patientID;
    private double amount;
    private String description;
    private boolean isPaid;
    private String date;

    public Bill(String billID, String patientID, double amount, String description, String date) {
        this.billID = billID;
        this.patientID = patientID;
        this.amount = amount;
        this.description = description;
        this.isPaid = false;
        this.date = date;
    }

    public String getPatientID() {
        return patientID;
    }

    public void payBill() {
        this.isPaid = true;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public double getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return String.format("Bill ID: %s\nAmount: $%.2f\nDescription: %s\nStatus: %s\nDate: %s",
                billID, amount, description, isPaid ? "Paid" : "Unpaid", date);
    }
}