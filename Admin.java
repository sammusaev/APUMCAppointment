/**
 * The {@code Admin} final class is the blueprint of an admin staff in the Medical Center of APU.
 * It provides an admin methods that monitor the entire system, such as
 * registering and deleting patients and doctors, in addition to managing appointments.
 * @author Mozhar TP058272
 * @author Samir  TP050965
 */

final public class Admin extends EndUser {
    
    /**
     * A counter for the number of doctors registered by an admin.
     * <p> Set to 0 initially via the constructor. </p>
     */
    private int doctorsRegisteredCount;
    
    /**
     * A counter for the number of patients registered by an admin.
     * <p> Set to 0 initially via the constructor. </p>
     */
    private int patientsRegisteredCount; 
    
    public Admin(String firstAndLastName, String gender, String dateOfBirth, 
                 String telephoneNumber, String residenceAddress, String emailAddress, 
                 String signInPassword) {
        this.setFirstAndLastName(firstAndLastName);
        this.setGender(gender);
        this.setDateOfBirth(dateOfBirth);
        this.setTelephoneNumber(telephoneNumber);
        this.setResidenceAddress(residenceAddress);
        this.setEmailAddress(emailAddress);
        this.setSignInPassword(signInPassword);
        this.doctorsRegisteredCount  = 0;
        this.patientsRegisteredCount = 0; 
    }
    final public void setDoctorsRegisteredCount(int doctorsRegisteredCount) {
        this.doctorsRegisteredCount = doctorsRegisteredCount;
    }
    final public int getDoctorsRegisteredCount() {
        return doctorsRegisteredCount;
    }
    final public void setPatientsRegisteredCount(int patientsRegisteredCount) {
        this.patientsRegisteredCount = patientsRegisteredCount;
    }
    final public int getPatientsRegisteredCount() {
        return patientsRegisteredCount;
    }

    /**
     * Registers a doctor by receiving a Doctor object and adding it to
     * {@code APUMedicalCenter.doctorsArrayList}. Returns true if adding is successful otherwise false. 
     * <p> Additionally it increments {@code doctorsRegisteredCount} by 1. </p>
     * @param newDoctor
     * The Doctor object to be registered.
     */
    final public boolean registerDoctor(Doctor newDoctor) {
        if(APUMedicalCenter.doctorsArrayList.add(newDoctor)) {
            doctorsRegisteredCount++;
            assert doctorsRegisteredCount <= Integer.MAX_VALUE;
            return true;
        }
        return false;
    }

    /**
     * Deletes a doctor by receiving a doctor's object and adding it to
     * {@code deletedDoctorArrayList} and removing it from {@code doctorsArrayList}.
     * @param doctorToDelete
     * The Doctor object to be deleted.
     */
    final public void deleteDoctor(Doctor doctorToDelete) {
        doctorToDelete.setIsDeleted(true);
        APUMedicalCenter.deletedDoctorsArrayList.add(doctorToDelete);
        APUMedicalCenter.doctorsArrayList.remove(doctorToDelete);
    }

    /**
     * Registers a patient by receiving a Patient object and adding it to
     * {@code APUMedicalCenter.patientsArrayList}. Returns true if adding is successful otherwise false.
     * <p> Additionally it increments {@code patientsRegisteredCount} by 1. </p>
     * @param newPatient
     * The Patient object to be registered.
     */
    final public boolean registerPatient(Patient newPatient) {
        if(APUMedicalCenter.patientsArrayList.add(newPatient)) {
            patientsRegisteredCount++;
            assert doctorsRegisteredCount <= Integer.MAX_VALUE;
            return true;
        }
        return false;
    }

    /**
     * Deletes a patient by receiving a patient's object and adding it to
     * {@code deletedPatientsArrayList} and removing it from {@code patientsArrayList}.
     * @param patientToDelete
     * The Patient object to be deleted.
     */
    final public void deletePatient(Patient patientToDelete) {
        patientToDelete.setIsDeleted(true);
        APUMedicalCenter.deletedPatientsArrayList.add(patientToDelete);
        APUMedicalCenter.patientsArrayList.remove(patientToDelete);
    }

    /**
     * Determines wether a given admin email address is used or not.
     * @param adminEmailAddress
     * @return true if adminEmailAddress is already used.
     * @return false if adminEmailAddress is not already used.
     */
    public static boolean isAdminEmailAddressUsed(String adminEmailAddress) {
        boolean adminEmailFoundInAdminsArrayList  = false;
        boolean adminEmailFoundInDoctorsArrayList = false;
        for(Admin admin : APUMedicalCenter.adminsArrayList) {
            if(admin.getEmailAddress().equals(adminEmailAddress)) {
                adminEmailFoundInAdminsArrayList = true;
                break;
            }
        } for(Doctor doctor : APUMedicalCenter.doctorsArrayList) {
            if(doctor.getEmailAddress().equals(adminEmailAddress)) {
                adminEmailFoundInDoctorsArrayList = true;
                break;
            }
        }
        if(adminEmailFoundInAdminsArrayList || adminEmailFoundInDoctorsArrayList) {
            return true;
        } else {
            return false;
        }
    }

    /** Signs an admin out of the program by setting {@code APUMedicalCenter.signedInAdmin} to null. */
    @Override
    final public void signOut() {
        APUMedicalCenter.signedInAdmin = null;
    }
}