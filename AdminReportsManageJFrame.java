import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

final public class AdminReportsManageJFrame extends JFrame implements IJFrameParent, ActionListener {
    private JButton patientReportsJButton, doctorReportsJButton, appointmentReportsJButton;
    private  JTextArea reportsJTextArea;
     
    public AdminReportsManageJFrame() {
        Container contentPane = getContentPane();
        contentPane.setLayout(new FlowLayout());
        setTitle("APU Medical Center");
        setIconImage(APULOGOICON.getImage());
        setSize(580, 360);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        reportsJTextArea = new JTextArea(11, 40);
        reportsJTextArea.setFont(DEFAULT_FONT);
        reportsJTextArea.setBorder(DEFAULT_BORDER);
        reportsJTextArea.setLineWrap(true);
        reportsJTextArea.setWrapStyleWord(true);
        reportsJTextArea.setBounds(50, 50, 300, 300);
        reportsJTextArea.setEditable(false);
        reportsJTextArea.setText(EMPTY_STRING);
        reportsJTextArea.setText("Please choose from below the report type you would like to generate.");
        JScrollPane jScrollPane = new JScrollPane(reportsJTextArea);
        jScrollPane.setPreferredSize(new Dimension(550, 250));
        jScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);        
        this.add(jScrollPane);

        patientReportsJButton = new JButton("Patients Report");
        setJButtonDefaults(patientReportsJButton);
        patientReportsJButton.addActionListener(this);
        this.add(patientReportsJButton);

        doctorReportsJButton = new JButton("Doctors Report");
        setJButtonDefaults(doctorReportsJButton);
        doctorReportsJButton.addActionListener(this);
        this.add(doctorReportsJButton);

        appointmentReportsJButton = new JButton("Appointments Report");
        setJButtonDefaults(appointmentReportsJButton);
        appointmentReportsJButton.addActionListener(this);
        this.add(appointmentReportsJButton);
    }

    public void actionPerformed(ActionEvent actionEvent){
        JButton clickedJButton = (JButton) actionEvent.getSource();
        if(clickedJButton.equals(patientReportsJButton)) {
            generatePatientsReport();
        } else if(clickedJButton.equals(doctorReportsJButton)) {
            generateDoctorsReport();
        } else if(clickedJButton.equals(appointmentReportsJButton)) {
            generateAppointmentsReport();
        }
    }
    
    private void generatePatientsReport() {
        // The used variables must be local so that they are not updated each time an event is fired
        int numberOfMales             = 0;
        int numberOfFemales           = 0;
        int totalNumberOfObjects      = 0;
        int totalAgeMales             = 0; 
        int totalAgeFemales           = 0;
        int averageAgeMales           = 0;
        int averageAgeFemales         = 0;
        int olderThan30Males          = 0;
        int olderThan30Females        = 0;
        int youngerThan30Males        = 0;
        int youngerThan30Females      = 0;
        int telephoneNumber10Digits   = 0;
        int telephoneNumber11Digits   = 0;
        int liveInKL                  = 0;
        int liveOutsideKL             = 0;
        double malePercentage         = 0;
        double femalePercentage       = 0; 
        int totalNumberOfAppointments = 0;
        int deletedPatients           = 0;   
        reportsJTextArea.setText(EMPTY_STRING);
        for(Patient patient : APUMedicalCenter.patientsArrayList) {
            if(patient.getGender().equals("Male")) {
                numberOfMales++;
                totalAgeMales += patient.getAgeInYears();
                if(patient.getAgeInYears() > 30) { olderThan30Males++; } else { youngerThan30Males++; }
            } else if(patient.getGender().equals("Female")) {
                numberOfFemales++;
                totalAgeFemales += patient.getAgeInYears();
                if(patient.getAgeInYears() > 30) { olderThan30Females++; } else { youngerThan30Females++; }
            }
            if(Integer.parseInt(patient.getTelephoneNumber()) == 10) { telephoneNumber10Digits++; } else { telephoneNumber11Digits++; }
            if(patient.getResidenceAddress().contains("KL")) { liveInKL++; } else { liveOutsideKL++; }
            totalNumberOfAppointments += patient.getNumberOfAppointments();
        }
        for(Patient patient : APUMedicalCenter.deletedPatientsArrayList) {
            deletedPatients++;
        }
        totalNumberOfObjects    = numberOfMales + numberOfFemales;
        malePercentage          = ((double) numberOfMales / totalNumberOfObjects) * 100;
        femalePercentage        = ((double) numberOfFemales / totalNumberOfObjects) * 100;
        reportsJTextArea.setText("The number of male patients: " + numberOfMales +
                                 "\nThe number of female patients: " + numberOfFemales +
                                 "\nThe total number of patients: " + totalNumberOfObjects +
                                 "\nThe percentage of male patients: " + malePercentage + "%" +
                                 "\nThe percentage of female patients: " + femalePercentage + "%" +
                                 "\nThe total number of male patients older than 30: " + olderThan30Males +
                                 "\nThe total number of male patients younger than 30: " + youngerThan30Males +
                                 "\nThe total number of female patients older than 30: " + olderThan30Females +
                                 "\nThe total number of female patients younger than 30: " + youngerThan30Females);
        if(numberOfMales != 0 && numberOfFemales != 0) {
            averageAgeMales          = (totalAgeMales / numberOfMales);
            averageAgeFemales        = (totalAgeFemales / numberOfFemales);
            reportsJTextArea.append("\nThe ratio of male to female patients: " + (double) numberOfMales / numberOfFemales +
                                    "\nThe ratio of female to male patients: " + (double) numberOfFemales / numberOfMales +
                                    "\nThe average age of the male patients: " + averageAgeMales +
                                    "\nThe average age of the female patients: " + averageAgeFemales);
        }
        reportsJTextArea.append("\nThe number of patients with  a 10 digits telephone number: "          + telephoneNumber10Digits   +
                                 "\nThe number of patients with a 11 digits telephone number: "          + telephoneNumber11Digits   +
                                 "\nThe total number of appointments belonging to all patients: "        + totalNumberOfAppointments +
                                 "\nThe total number of patients residing in KL: "                      + liveInKL                  +
                                 "\nThe total number of patients residing out of KL: "                  + liveOutsideKL             +
                                 "\nThe total number of deleted patients is: " + deletedPatients);
    }

    private void generateDoctorsReport() {
        int numberOfMales             = 0;
        int numberOfFemales           = 0;
        int totalNumberOfObjects      = 0;
        int totalAgeMales             = 0; 
        int totalAgeFemales           = 0;
        int averageAgeMales           = 0;
        int averageAgeFemales         = 0;
        int olderThan30Males          = 0;
        int olderThan30Females        = 0;
        int youngerThan30Males        = 0;
        int youngerThan30Females      = 0;
        int telephoneNumber10Digits   = 0;
        int telephoneNumber11Digits   = 0;
        int liveInKL                  = 0;
        int liveOutsideKL             = 0;
        double malePercentage         = 0;
        double femalePercentage       = 0; 
        int totalNumberOfAppointments = 0;
        int deletedDoctors            = 0;      
        reportsJTextArea.setText(EMPTY_STRING);
        for(Doctor doctor : APUMedicalCenter.doctorsArrayList) {
            if(doctor.getGender().equals("Male")) {
                numberOfMales++;
                totalAgeMales += doctor.getAgeInYears();
                if(doctor.getAgeInYears() > 30) { olderThan30Males++; } else { youngerThan30Males++; }
            } else if(doctor.getGender().equals("Female")) {
                numberOfFemales++;
                totalAgeFemales += doctor.getAgeInYears();
                if(doctor.getAgeInYears() > 30) { olderThan30Females++; } else { youngerThan30Females++; }
            }
            if(Integer.parseInt(doctor.getTelephoneNumber()) == 10) { telephoneNumber10Digits++; } else { telephoneNumber11Digits++; }
            if(doctor.getResidenceAddress().contains("KL")) { liveInKL++; } else { liveOutsideKL++; }
            totalNumberOfAppointments += doctor.getNumberOfAppointments();
        }
        for(Doctor doctor : APUMedicalCenter.deletedDoctorsArrayList) {
            deletedDoctors++;
        }
        totalNumberOfObjects     = numberOfMales + numberOfFemales;
        malePercentage           = ((double) numberOfMales   / totalNumberOfObjects) * 100;
        femalePercentage         = ((double) numberOfFemales / totalNumberOfObjects) * 100;
        reportsJTextArea.setText("The number of male doctors: " + numberOfMales +
                                 "\nThe number of female doctors: " + numberOfFemales +
                                 "\nThe total number of doctors: " + totalNumberOfObjects +
                                 "\nThe percentage of male doctors: " + malePercentage + "%" +
                                 "\nThe percentage of female doctors: " + femalePercentage + "%" +
                                 "\nThe total number of male doctors older than 30: " + olderThan30Males +
                                 "\nThe total number of male doctors younger than 30: " + youngerThan30Males +
                                 "\nThe total number of female doctors older than 30: " + olderThan30Females +
                                 "\nThe total number of female doctors younger than 30: " + youngerThan30Females);
        if(numberOfMales != 0 && numberOfFemales != 0) {
            averageAgeMales   = (totalAgeMales / numberOfMales);
            averageAgeFemales = (totalAgeFemales / numberOfFemales);
            reportsJTextArea.append("\nThe ratio of male to female doctors: "    + (double) (numberOfMales / numberOfFemales) +
                                    "\nThe ratio of female to male doctors: "    + (double) (numberOfFemales / numberOfMales) +
                                    "\nThe average age of the male doctors: "    + averageAgeMales +
                                    "\nThe average age of the female doctors: "  + averageAgeFemales);
        }
        reportsJTextArea.append("\nThe number of doctors with a 10 digit telephone number: "         + telephoneNumber10Digits   +
                                "\nThe number of doctors with a 11 digit telephone number: "         + telephoneNumber11Digits   +
                                "\nThe total number of appointments associated to all the doctors: " + totalNumberOfAppointments +
                                "\nThe total number of doctors residing in KL: "                     + liveInKL                  +
                                "\nThe total number of doctors residing out of KL: "                 + liveOutsideKL             +
                                "\nThe total number of deleted doctors is: "                         + deletedDoctors);
    }

    private void generateAppointmentsReport() {
        reportsJTextArea.setText(EMPTY_STRING);
        int totalNumberOfAppointments = 0;
        int isComplete                = 0;
        int notComplete               = 0;
        int totalCostOfAppointments   = 0;
        int costLessThan100           = 0;
        int costMoreThan100           = 0;
        int doctorCommented           = 0;
        int doctorNotCommented        = 0;

        for(Appointment appointment : APUMedicalCenter.appointmentsArrayList) {
            totalNumberOfAppointments++;
            totalCostOfAppointments += appointment.getCost();
            if(appointment.getIsComplete()) { isComplete++; } else { notComplete++; }
            if(appointment.getCost() > 100) { costMoreThan100++; } else{ costLessThan100++; }
            if(!appointment.getDoctorComment().isEmpty() && !appointment.getDoctorComment().equals("None")) {
                 doctorCommented++;
            } else {
                doctorNotCommented++;
            } 
        }
        reportsJTextArea.setText("The total number of appointments : " + totalNumberOfAppointments +
                                 "\nThe total number of completed appointments: " + isComplete +
                                 "\nThe total number of uncompleted appointments: " + notComplete +
                                 "\nThe total cost of all the appointments: " + totalCostOfAppointments + " RM" +
                                 "\nThe total number of appointments with cost less than 100 RM: " + costLessThan100 +
                                 "\nThe total number of appointments with cost more than 100 RM: " + costMoreThan100 +
                                 "\nThe total number appointments that doctors commented on: " + doctorCommented +
                                 "\nThe total number appointments that doctors did not comment on: " + doctorNotCommented);
    }    
}