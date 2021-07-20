import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.SecureRandom;
import java.util.Scanner;
/**
 * The {@code IO} class creates contains static attributes and 
 * methods that perform all IO operations.
 * @author Mozhar TP058272 
 * @author Samir TP050965
 */

final public class IO {
    /**
     * Don't let anyone instantiate this class.
     */
    private IO() {}

    private static SecureRandom secureRandom = new SecureRandom();
    private static int appointmentNumber;
    
    final public static void WriteAppointmentNumber() {
        try {
            PrintWriter printWriter = new PrintWriter("appointmentNumber.txt");
            printWriter.print(appointmentNumber);
            printWriter.close();
        } catch(IOException exception) {}
    }

    final public static void ReadAppointmentNumber() {
        try {
            Scanner fileScanner = new Scanner(new File("appointmentNumber.txt"));
            IO.appointmentNumber = Integer.parseInt(fileScanner.nextLine());
            fileScanner.close();
        } catch(IOException exception) {}
    }

    final public static int GetAppointmentNumber() {
        return appointmentNumber;
    }

    final public static void NextRandomAppointmentNumber() {
        appointmentNumber = secureRandom.nextInt(500);
    }

    final public static void WriteAdminsData() {
        try {
            PrintWriter printWriter = new PrintWriter("adminsData.txt");
            for(Admin admin : APUMedicalCenter.adminsArrayList) {
                printWriter.println(admin.getFirstAndLastName());
                printWriter.println(admin.getGender());
                printWriter.println(admin.getDateOfBirth());
                printWriter.println(admin.getTelephoneNumber());
                printWriter.println(admin.getResidenceAddress());
                printWriter.println(admin.getEmailAddress());
                printWriter.println(admin.getSignInPassword());
                printWriter.println(admin.getDoctorsRegisteredCount());
                printWriter.println(admin.getPatientsRegisteredCount());
                printWriter.println("========================================");
            }
            printWriter.close(); 
        } catch(IOException exception) {}
    }

    final public static void WriteDoctorsData() {
        try {
            PrintWriter printWriter = new PrintWriter("doctorsData.txt");
            for(Doctor doctor : APUMedicalCenter.doctorsArrayList) {
                printWriter.println(doctor.getFirstAndLastName());
                printWriter.println(doctor.getGender());
                printWriter.println(doctor.getDateOfBirth());
                printWriter.println(doctor.getMedicalSpecialty());
                printWriter.println(doctor.getTelephoneNumber());
                printWriter.println(doctor.getResidenceAddress());
                printWriter.println(doctor.getEmailAddress());
                printWriter.println(doctor.getSignInPassword());
                printWriter.println(doctor.getNumberOfAppointments());
                printWriter.println(doctor.isDeleted());
                printWriter.println("========================================");
            }
            printWriter.close();
        } catch(IOException exception) {}
    }

    final public static void WriteDeletedDoctorsData() {
        try {
            PrintWriter printWriter = new PrintWriter("deletedDoctorsData.txt");
            for(Doctor doctor : APUMedicalCenter.deletedDoctorsArrayList) {
                printWriter.println(doctor.getFirstAndLastName());
                printWriter.println(doctor.getGender());
                printWriter.println(doctor.getDateOfBirth());
                printWriter.println(doctor.getMedicalSpecialty());
                printWriter.println(doctor.getTelephoneNumber());
                printWriter.println(doctor.getResidenceAddress());
                printWriter.println(doctor.getEmailAddress());
                printWriter.println(doctor.getSignInPassword());
                printWriter.println(doctor.getNumberOfAppointments());
                printWriter.println(doctor.isDeleted());
                printWriter.println("========================================");
            }
            printWriter.close();
        } catch(IOException exception) {}
    }

    final public static void WritePatientsData() {
        try {
            PrintWriter printWriter = new PrintWriter("patientsData.txt");
            for(Patient patient : APUMedicalCenter.patientsArrayList) {
                printWriter.println(patient.getFirstAndLastName());
                printWriter.println(patient.getGender());
                printWriter.println(patient.getDateOfBirth());
                printWriter.println(patient.getTelephoneNumber());
                printWriter.println(patient.getResidenceAddress());
                printWriter.println(patient.getEmailAddress());
                printWriter.println(patient.getNumberOfAppointments());
                printWriter.println(patient.isDeleted());
                printWriter.println("========================================");
            }
            printWriter.close();
        } catch(IOException exception) {}          
    }

    final public static void WriteDeletedPatientsData() {
        try {
            PrintWriter printWriter = new PrintWriter("deletedPatientsData.txt");
            for(Patient patient : APUMedicalCenter.deletedPatientsArrayList) {
                printWriter.println(patient.getFirstAndLastName());
                printWriter.println(patient.getGender());
                printWriter.println(patient.getDateOfBirth());
                printWriter.println(patient.getTelephoneNumber());
                printWriter.println(patient.getResidenceAddress());
                printWriter.println(patient.getEmailAddress());
                printWriter.println(patient.getNumberOfAppointments());
                printWriter.println(patient.isDeleted());
                printWriter.println("========================================");
            }
            printWriter.close();
        } catch(IOException exception) {}          
    }

    final public static void WriteAppointmentsData() { // @Samir I edited this 
        try {
            PrintWriter printWriter = new PrintWriter("appointmentsData.txt");
            for(Appointment appointment : APUMedicalCenter.appointmentsArrayList) {
                printWriter.println(appointment.getID());
                printWriter.println(appointment.getDoctorEmail());
                printWriter.println(appointment.getDoctorComment());
                printWriter.println(appointment.getPatientEmail());
                printWriter.println(appointment.getCost());
                printWriter.println(appointment.getDate());
                printWriter.println(appointment.getTime());
                printWriter.println(appointment.getIsComplete());
                printWriter.println("========================================");
            }
            printWriter.close();
        } catch (IOException exception) {}
    }

    final public static void ReadAdminsData() {
        try {
            Scanner fileScanner = new Scanner(new File("adminsData.txt"));
            while(fileScanner.hasNext()) {
                String fullName             = fileScanner.nextLine();
                String gender               = fileScanner.nextLine();
                String dateOfBirth          = fileScanner.nextLine();
                String telephoneNumber      = fileScanner.nextLine();
                String residenceAddress     = fileScanner.nextLine();
                String emailAddress         = fileScanner.nextLine();
                String signInPassword       = fileScanner.nextLine();
                int doctorsRegisteredCount  = Integer.parseInt(fileScanner.nextLine());
                int patientsRegisteredCount = Integer.parseInt(fileScanner.nextLine());
                String $ignore              = fileScanner.nextLine();
                Admin  admin                = new Admin(fullName, gender, dateOfBirth,
                                                        telephoneNumber, residenceAddress,
                                                        emailAddress, signInPassword);
                admin.setDoctorsRegisteredCount(doctorsRegisteredCount);
                admin.setPatientsRegisteredCount(patientsRegisteredCount);
                APUMedicalCenter.adminsArrayList.add(admin);
            }
            fileScanner.close();
        } catch(IOException exception) {}
    }

    final public static void ReadDoctorsData() {
        try {
            Scanner fileScanner = new Scanner(new File("doctorsData.txt"));
            while(fileScanner.hasNext()) {
                String fullName          = fileScanner.nextLine();
                String gender            = fileScanner.nextLine();
                String dateOfBirth       = fileScanner.nextLine();
                String medicalSpecialty  = fileScanner.nextLine();
                String telephoneNumber   = fileScanner.nextLine();
                String residenceAddress  = fileScanner.nextLine();
                String emailAddress      = fileScanner.nextLine();
                String signInPassword    = fileScanner.nextLine();
                int numberOfAppointments = Integer.parseInt(fileScanner.nextLine());
                Boolean isDeleted        = Boolean.parseBoolean(fileScanner.nextLine());
                String $ignore           = fileScanner.nextLine();
                Doctor doctor            = new Doctor(fullName, gender, dateOfBirth,
                                                      medicalSpecialty, telephoneNumber,
                                                      residenceAddress, emailAddress, 
                                                      signInPassword);
                doctor.setNumberOfAppointments(numberOfAppointments);
                doctor.setIsDeleted(isDeleted);
                APUMedicalCenter.doctorsArrayList.add(doctor);
            }
            fileScanner.close();
        } catch(IOException exception) {}
    }

    final public static void ReadDeletedDoctorsData() {
        try {
            Scanner fileScanner = new Scanner(new File("deletedDoctorsData.txt"));
            while(fileScanner.hasNext()) {
                String fullName         = fileScanner.nextLine();
                String gender           = fileScanner.nextLine();
                String dateOfBirth      = fileScanner.nextLine();
                String medicalSpecialty = fileScanner.nextLine();
                String telephoneNumber  = fileScanner.nextLine();
                String residenceAddress = fileScanner.nextLine();
                String emailAddress     = fileScanner.nextLine();
                String signInPassword   = fileScanner.nextLine();
                int numberOfAppointments = Integer.parseInt(fileScanner.nextLine());
                Boolean isDeleted        = Boolean.parseBoolean(fileScanner.nextLine());
                String $ignore           = fileScanner.nextLine();
                Doctor doctor            = new Doctor(fullName, gender, dateOfBirth,
                                                      medicalSpecialty, telephoneNumber,
                                                      residenceAddress, emailAddress, 
                                                      signInPassword);
                doctor.setNumberOfAppointments(numberOfAppointments);
                doctor.setIsDeleted(isDeleted);
                APUMedicalCenter.deletedDoctorsArrayList.add(doctor);
            }
            fileScanner.close();
        } catch(IOException exception) {}
    }

    final public static void ReadPatientsData() {
        try {
            Scanner fileScanner = new Scanner(new File("patientsData.txt"));
            while(fileScanner.hasNext()) {
                String fullName          = fileScanner.nextLine();
                String gender            = fileScanner.nextLine();
                String dateOfBirth       = fileScanner.nextLine();
                String telephoneNumber   = fileScanner.nextLine();
                String residenceAddress  = fileScanner.nextLine();
                String emailAddress      = fileScanner.nextLine();
                int numberOfAppointments = Integer.parseInt(fileScanner.nextLine());
                boolean isDeleted        = Boolean.parseBoolean(fileScanner.nextLine());
                String $ignore           = fileScanner.nextLine();
                Patient patient          = new Patient(fullName, gender, dateOfBirth,
                                                       telephoneNumber, residenceAddress,
                                                       emailAddress);
                patient.setNumberOfAppointments(numberOfAppointments);
                patient.setIsDeleted(isDeleted);
                APUMedicalCenter.patientsArrayList.add(patient);
            }
            fileScanner.close();
        } catch(IOException exception) {}
    }

    final public static void ReadDeletedPatientsData() {
        try {
            Scanner fileScanner = new Scanner(new File("deletedPatientsData.txt"));
            while(fileScanner.hasNext()) {
                String fullName         = fileScanner.nextLine();
                String gender           = fileScanner.nextLine();
                String dateOfBirth      = fileScanner.nextLine();
                String telephoneNumber  = fileScanner.nextLine();
                String residenceAddress = fileScanner.nextLine();
                String emailAddress     = fileScanner.nextLine();
                int numberOfAppointments = Integer.parseInt(fileScanner.nextLine());
                boolean isDeleted        = Boolean.parseBoolean(fileScanner.nextLine());
                String $ignore           = fileScanner.nextLine();
                Patient patient          = new Patient(fullName, gender, dateOfBirth,
                                                       telephoneNumber, residenceAddress,
                                                       emailAddress);
                patient.setNumberOfAppointments(numberOfAppointments);
                patient.setIsDeleted(isDeleted);
                APUMedicalCenter.deletedPatientsArrayList.add(patient);
            }
            fileScanner.close();
        } catch(IOException exception) {}
    }

    final public static void ReadAppointmentsData() {
        try {
            Scanner fileScanner = new Scanner(new File("appointmentsData.txt"));
            while(fileScanner.hasNext()) {
                String  ID            = fileScanner.nextLine();
                String  doctorEmail   = fileScanner.nextLine();
                String  doctorComment = fileScanner.nextLine();
                String  patientEmail  = fileScanner.nextLine();
                int     cost          = Integer.parseInt(fileScanner.nextLine());
                String  date          = fileScanner.nextLine();
                String  time          = fileScanner.nextLine();
                Boolean isComplete    = Boolean.parseBoolean(fileScanner.nextLine());
                String $ignore        = fileScanner.nextLine();
                Appointment app       = new Appointment(doctorEmail, patientEmail, date, time, cost);
                app.setIsComplete(isComplete);
                app.setID(ID);
                app.setDoctorComment(doctorComment);
                APUMedicalCenter.appointmentsArrayList.add(app);
            }
            fileScanner.close();
        } catch (IOException exception) {}
    }
}