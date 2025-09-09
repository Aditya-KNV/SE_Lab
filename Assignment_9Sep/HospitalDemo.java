import java.util.ArrayList;
import java.util.List;

class Hospital {
    private String name;
    private String address;
    private List<Doctor> doctors;
    private List<Patient> patients;
    private List<Appointment> appointments;

    public Hospital(String name, String address) {
        this.name = name;
        this.address = address;
        this.doctors = new ArrayList<>();
        this.patients = new ArrayList<>();
        this.appointments = new ArrayList<>();
    }

    public void addDoctor(Doctor doc) {
        doctors.add(doc);
        System.out.println("Doctor added: " + doc.getName());
    }

    public void addPatient(Patient pat) {
        patients.add(pat);
        System.out.println("Patient added: " + pat.getName());
    }

    public void scheduleAppt(Appointment appt) {
        appointments.add(appt);
        System.out.println("Appointment scheduled: #" + appt.getId());
    }

    public void listDoctors() {
        System.out.println("Doctors at " + name + ":");
        for (Doctor d : doctors) {
            System.out.println(" - " + d.getName() + ", " + d.getSpecialization());
        }
    }

    public void listPatients() {
        System.out.println("Patients at " + name + ":");
        for (Patient p : patients) {
            System.out.println(" - " + p.getName() + ", age " + p.getAge());
        }
    }
}

class Doctor {
    private int docId;
    private String name;
    private String specialization;

    public Doctor(int docId, String name, String specialization) {
        this.docId = docId;
        this.name = name;
        this.specialization = specialization;
    }

    public void checkPatient(Patient p) {
        System.out.println("Dr. " + name + " is checking patient " + p.getName());
    }

    public int getDocId() { return docId; }
    public String getName() { return name; }
    public String getSpecialization() { return specialization; }
}

class Patient {
    private int patientId;
    private String name;
    private int age;

    public Patient(int patientId, String name, int age) {
        this.patientId = patientId;
        this.name = name;
        this.age = age;
    }

    public void getDetails() {
        System.out.println("Patient Details - ID: " + patientId + ", Name: " + name + ", Age: " + age);
    }

    public int getPatientId() { return patientId; }
    public String getName() { return name; }
    public int getAge() { return age; }
}

class Appointment {
    private int id;
    private String date;
    private Doctor doctor;
    private Patient patient;
    private String illness;
    private Bill bill;

    public Appointment(int id, String date, Doctor doctor, Patient patient, String illness) {
        this.id = id;
        this.date = date;
        this.doctor = doctor;
        this.patient = patient;
        this.illness = illness;
        this.bill = new Bill(id + 1000, 0.0, "Unpaid"); 
    }

    public void confirm() {
        System.out.println("Appointment #" + id + " on " + date + " confirmed for patient " 
                           + patient.getName() + " with Dr. " + doctor.getName() + " suffering from " + illness);
    }

    public int getId() { return id; }
    public Doctor getDoctor() { return doctor; }
    public Patient getPatient() { return patient; }
    public Bill getBill() { return bill; }
}

class Bill {
    private int id;
    private double amount;
    private String status;

    public Bill(int id, double amount, String status) {
        this.id = id;
        this.amount = amount;
        this.status = status;
    }

    public void generate(double amt) {
        this.amount = amt;
        this.status = "Unpaid";
        System.out.println("Bill generated: ID #" + id + ", Amount = " + amount);
    }

    public void pay() {
        if ("Unpaid".equals(status)) {
            status = "Paid";
            System.out.println("Bill #" + id + " has been paid.");
        } else {
            System.out.println("Bill #" + id + " is already paid.");
        }
    }

    public int getId() { return id; }
    public double getAmount() { return amount; }
    public String getStatus() { return status; }
}

public class HospitalDemo {
    public static void main(String[] args) {
        
        Hospital hospital = new Hospital("Kauvery Hospital", "Electronic City");

        Doctor doc1 = new Doctor(1, "Allu Bhaai", "Cardiology");
        Doctor doc2 = new Doctor(2, "Mahesh Bob", "Pediatrics");

        Patient pat1 = new Patient(101, "Aditya", 30);
        Patient pat2 = new Patient(102, "KNV", 25);

        hospital.addDoctor(doc1);
        hospital.addDoctor(doc2);
        hospital.addPatient(pat1);
        hospital.addPatient(pat2);

        hospital.listDoctors();
        hospital.listPatients();

        Appointment appt1 = new Appointment(201, "2025-09-12", doc1, pat1, "Chest Pain");
        hospital.scheduleAppt(appt1);
        appt1.confirm();

        Bill bill1 = appt1.getBill();
        bill1.generate(250.00);
        bill1.pay();
    }
}
