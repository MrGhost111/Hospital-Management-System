import java.util.*;

public abstract class Users {
    protected String hospitalID;
    protected String password;
    protected boolean firstLogin = true;
    protected String name;
    protected String contactInfo;

    public Users(String hospitalID, String password, String name) {
        this.hospitalID = hospitalID;
        this.password = password;
        this.name = name;
        this.contactInfo = "Not Provided";
    }

    public String getHospitalID() {
        return hospitalID;
    }

    public boolean login(String id, String pass) {
        return this.hospitalID.equals(id) && this.password.equals(pass);
    }

    public void changePassword(String newPassword) {
        this.password = newPassword;
        System.out.println("Password changed successfully.");
        firstLogin = false;
    }

    public void updateProfile(String name, String contactInfo) {
        this.name = name;
        this.contactInfo = contactInfo;
        System.out.println("Profile updated successfully.");
    }

    public void promptPasswordChange(Scanner scanner) {
        if (firstLogin) {
            System.out.println("Please change your default password:");
            System.out.print("Enter new password: ");
            String newPassword = scanner.nextLine();
            changePassword(newPassword);
        }
    }

    // Add the getName method
    public String getName() {
        return name;
    }

    public abstract void displayMenu();
    public abstract void handleUserChoice(int choice, Scanner scanner);
}
