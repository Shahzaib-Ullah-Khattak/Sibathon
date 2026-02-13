import java.util.*;

/* =========================
   Doctor Model Class
========================= */
class Doctor {

    int id;
    String name;
    String specialization;
    String email;
    String phone;

    Doctor(int id, String name, String specialization, String email, String phone) {
        this.id = id;
        this.name = name;
        this.specialization = specialization;
        this.email = email;
        this.phone = phone;
    }

    void display() {
        System.out.println(id + " | " + name + " | " + specialization + " | Contact: " + phone);
    }
}


/* =========================
   Doctor Service Class
========================= */
class DoctorService {

    static ArrayList<Doctor> registeredDoctors = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    static void registerDoctor() {

        System.out.print("Enter ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Specialization: ");
        String specialization = sc.nextLine();

        System.out.print("Enter Email: ");
        String email = sc.nextLine();

        System.out.print("Enter Phone: ");
        String phone = sc.nextLine();

        registeredDoctors.add(new Doctor(id, name, specialization, email, phone));

        System.out.println("Doctor Registered Successfully!");
    }

    static void showDoctorsBySpecialization(String spec) {

        boolean found = false;

        for (Doctor d : registeredDoctors) {
            if (d.specialization.equalsIgnoreCase(spec)) {
                d.display();
                found = true;
            }
        }

        if (!found) {
            System.out.println("No Doctor Available for " + spec);
        }
    }
}


/* =========================
   Diagnosis Service Class
========================= */
class DiagnosisService {

    static Scanner sc = new Scanner(System.in);

    static void startDiagnosis() {

        System.out.println("\nEnter your symptoms separated by comma:");
        String input = sc.nextLine().toLowerCase();

        String[] symptoms = input.split(",");

        int severityScore = 0;
        String disease = "Unknown";
        String specialist = "General Physician";

        for (String s : symptoms) {

            s = s.trim();

            if (s.contains("fever") || s.contains("cough")) {
                disease = "Viral Infection";
                specialist = "General Physician";
                severityScore += 1;
            }

            if (s.contains("chest pain") || s.contains("heart")) {
                disease = "Heart Disease";
                specialist = "Cardiologist";
                severityScore += 3;
            }

            if (s.contains("breathing problem")) {
                disease = "Lung Infection";
                specialist = "Pulmonologist";
                severityScore += 2;
            }

            if (s.contains("skin rash")) {
                disease = "Skin Allergy";
                specialist = "Dermatologist";
                severityScore += 1;
            }
        }

        System.out.println("\nPossible Disease: " + disease);
        System.out.println("Recommended Specialist: " + specialist);

        // Severity Level
        if (severityScore <= 1) {
            System.out.println("Severity Level: 🟢 GREEN (Mild)");
        } 
        else if (severityScore == 2) {
            System.out.println("Severity Level: 🟡 YELLOW (Moderate)");
        } 
        else {
            System.out.println("Severity Level: 🔴 RED (Severe - Immediate Attention Required)");
        }

        // Show doctors
        DoctorService.showDoctorsBySpecialization(specialist);

        // Contact Option
        System.out.println("\nDo you want to contact a doctor? (yes/no)");
        String contact = sc.nextLine();

        if (contact.equalsIgnoreCase("yes")) {
            System.out.println("Connecting you to available doctor...");
            System.out.println("Appointment Request Sent Successfully!");
        }
    }
}


/* =========================
   Main Application Class
========================= */
public class docters {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        int choice;

        do {
            System.out.println("\n====== Online Health System ======");
            System.out.println("1. Register Doctor");
            System.out.println("2. Diagnosis");
            System.out.println("3. Exit");
            System.out.print("Enter Choice: ");

            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    DoctorService.registerDoctor();
                    break;

                case 2:
                    DiagnosisService.startDiagnosis();
                    break;

                case 3:
                    System.out.println("Thank You!");
                    break;

                default:
                    System.out.println("Invalid Choice!");
            }

        } while (choice != 3);
    }
}
