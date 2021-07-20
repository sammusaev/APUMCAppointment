final public class Appointment {
    private String    ID;
    private String    doctorEmail;
    private String    doctorComment;
    private String    patientEmail;
    private int       cost;
    private String    date;
    private String    time;
    private boolean   isComplete;
    
    public Appointment(String doctorEmail, String patientEmail, String date, String time, int cost) {
        this.ID             = "AP" + IO.GetAppointmentNumber();
        this.doctorEmail    = doctorEmail;
        this.patientEmail   = patientEmail;
        this.doctorComment  = "None";
        this.date           = date;
        this.time           = time;
        this.cost           = cost;
        isComplete          = false;
        IO.NextRandomAppointmentNumber();
    }
    //GETTERS
    final public String getID() {
        return ID;
    }
    final public String getPatientEmail(){
        return patientEmail;
    }
    final public String getDoctorEmail(){
        return doctorEmail;
    }
    final public String getTime(){
        return time;
    }
    final public String getDate(){
        return date;
    }

    final public int getCost() {
        return cost;
    }

    final public boolean getIsComplete() {
        return isComplete;
    }

    final public String getDoctorComment() {
        return doctorComment;
    }

    final public String getDoctorName(){
        return doctorEmail;
    }
    //SETTERS
    final public void setID(String id){
        this.ID = id;
    }

    final public void setDoctorComment(String doctorComment) {
        this.doctorComment = doctorComment;
    }

    final public void setIsComplete(boolean isComplete) {
        this.isComplete = isComplete;
    }

    final public void setCost(int cost) {
        this.cost = cost;
    }

    final public Doctor getDoctor(){
        Doctor doctorObject = null;
        for (Doctor doc: APUMedicalCenter.doctorsArrayList){
            if (doc.getEmailAddress().equals(doctorEmail)){
                doctorObject = doc;
            }
        } return doctorObject;
    }

    final public Patient getPatient(){
        Patient patientObject = null;
        for (Patient pat: APUMedicalCenter.patientsArrayList){
            if (pat.getEmailAddress().equals(patientEmail)){
                patientObject = pat;
            }
        } return patientObject;
    }

    final public String getPatientName(){
        String patientName = null;
        for (Patient pat: APUMedicalCenter.patientsArrayList){
            if (pat.getEmailAddress().equals(patientEmail)){
                patientName = pat.getFirstAndLastName();
            }
        } return patientName;
    }

    final public Appointment getAppointment(String ID) {
        Appointment appointmentReturn = null;
        for(Appointment appointment : APUMedicalCenter.appointmentsArrayList) {
            if(appointment.getID().equals(ID)) {
                appointmentReturn = appointment;
            }
        }
        return appointmentReturn;
    }
}