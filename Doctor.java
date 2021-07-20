/**
 * The {@code Doctor} class is the blueprint of a doctor instance in the Medical Center of APU.
 * @author Mozhar TP058272
 * @author Samir  TP050965
 */

final public class Doctor extends EndUser {
    private String medicalSpecialty;
    private int numberOfAppointments;
    private boolean isDeleted;
    
    public Doctor(String firstAndLastName, String gender, 
                  String dateOfBirth, String medicalSpecialty,
                  String telephoneNumber, String residenceAddress,
                  String emailAddress, String signInPassword) {
        this.setFirstAndLastName(firstAndLastName);
        this.setGender(gender);
        this.setDateOfBirth(dateOfBirth);
        this.setMedicalSpecialty(medicalSpecialty);
        this.setTelephoneNumber(telephoneNumber);
        this.setResidenceAddress(residenceAddress);
        this.setEmailAddress(emailAddress);
        this.setSignInPassword(signInPassword);
        numberOfAppointments = 0;
        isDeleted = false;
    }

    final public void setMedicalSpecialty(String specialty) {
        this.medicalSpecialty = specialty;
    }

    final public String getMedicalSpecialty() {
        return medicalSpecialty;
    }

    final public void setNumberOfAppointments(int numberOfAppointments) {
        this.numberOfAppointments = numberOfAppointments;
    }

    final public int getNumberOfAppointments() {
        return numberOfAppointments;
    }
    
    final public void setIsDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    final public boolean isDeleted() {
        return isDeleted;
    }

    final public void incrementAppointmentsNumber() {
        numberOfAppointments++;
        assert numberOfAppointments <= Integer.MAX_VALUE;
    }

    final public void decrementAppointmentsNumber() {
        numberOfAppointments--;
        assert numberOfAppointments >= 0;
    }
    
    final public static Doctor getDoctor(String emailAddress) {
        Doctor doctorReturn = null;
        for(Doctor doctor : APUMedicalCenter.doctorsArrayList) {
            if(doctor.getEmailAddress().equals(emailAddress)) {
                doctorReturn = doctor;
            }
        } 
        return doctorReturn;
    }
    
    final public static boolean isDoctorEmailAddressUsed(String doctorEmailAddress) {
        boolean doctorEmailFoundInAdminsArrayList  = false;
        boolean doctorEmailFoundInDoctorsArrayList = false;
        for(Admin admin : APUMedicalCenter.adminsArrayList) {
            if(admin.getEmailAddress().equals(doctorEmailAddress)) {
                doctorEmailFoundInAdminsArrayList = true;
                break;
            }
        } for(Doctor doctor : APUMedicalCenter.doctorsArrayList) {
            if(doctor.getEmailAddress().equals(doctorEmailAddress)) {
                doctorEmailFoundInDoctorsArrayList = true;
                break;
            }
        }
        if(doctorEmailFoundInAdminsArrayList || doctorEmailFoundInDoctorsArrayList) {
            return true;
        } else {
            return false;
        }
    }

    final public static boolean doesDoctorExist(String doctorEmailAddress) {
        for(Doctor doctor: APUMedicalCenter.doctorsArrayList) {
            if(doctor.getEmailAddress().equals(doctorEmailAddress)) {
                return true;
            }
        } 
        return false;
    }

    @Override
    final public void signOut() {
        APUMedicalCenter.signedInDoctor = null;
    }
}