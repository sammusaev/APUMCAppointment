public class Patient extends EndUser {
    private int numberOfAppointments;
    private boolean isDeleted;

    public Patient(String firstAndLastName, String gender,
                   String dateOfBirth, String telephoneNumber,
                   String residenceAddress, String emailAddress) { 
        this.setFirstAndLastName(firstAndLastName);
        this.setGender(gender);
        this.setDateOfBirth(dateOfBirth);
        this.setTelephoneNumber(telephoneNumber);
        this.setResidenceAddress(residenceAddress);
        this.setEmailAddress(emailAddress);
        numberOfAppointments = 0;
        isDeleted = false;
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
    public static Patient getPatient(String patientEmailAddress) {
        Patient patientReturn = null;
        for(Patient patient : APUMedicalCenter.patientsArrayList) {
            if(patient.getEmailAddress().equals(patientEmailAddress)) {
                patientReturn = patient;
            }
        }
        return patientReturn;
    }

    public static boolean doesPatientExist(String patientEmailAddress) {
        for(Patient patient : APUMedicalCenter.patientsArrayList) {
            if(patient.getEmailAddress().equals(patientEmailAddress)) {
                return true;
            }
        } return false;
    } 
    
    public static boolean isPatientEmailAddressUsed(String patientEmailAddress) {
        for(Patient patient : APUMedicalCenter.patientsArrayList) {
            if(patientEmailAddress.equals(patient.getEmailAddress())) {
                return true;
            }
        } return false;
    }
    
    @Override
    final public void signOut() {
        throw new UnsupportedOperationException();
    }
}