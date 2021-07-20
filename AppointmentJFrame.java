import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.text.DateFormatSymbols;
import java.text.DecimalFormat;
import java.time.YearMonth;

public class AppointmentJFrame extends JFrame implements ActionListener, IJFrameParent{
    private static final long serialVersionUID = 1L;
    final private JLabel welcomeLabel, patientEmailLabel, appointmentDateLabel, appointmentTimeLabel;
    final private JLabel appointmentSpecialtyLabel,appointmentDoctorLabel, appointmentCostLabel;
    final private JTextField patientEmailTextField, appointmentCostTextField;
    final private JComboBox<Integer> appointmentDayComboBox, appointmentYearComboBox;
    final private JComboBox<String> appointmentMonthComboBox, appointmentDoctorComboBox, appointmentSpecialtyComboBox, appointmentTimeComboBox;
    final private Integer[] daysInNumber;
    final private String[] monthsInLetters;
    JList<String> appointmentData = new JList<String>();
    final private JButton createAppointmentButton, cancelButton;

    public AppointmentJFrame() {
        Container contentPane = getContentPane();
        SpringLayout layOut = new SpringLayout();
        contentPane.setLayout(layOut);
        this.setDefaultCloseOperation(HIDE_ON_CLOSE);
        contentPane.setBackground(new Color(0xFFFFFFF));
        setTitle("APU Medical Center");
        setIconImage(APULOGOICON.getImage());
        setSize(500, 400);
        setLocationRelativeTo(null);
        setResizable(false);

        //GUI COMPONENTS
        //LABELS
        welcomeLabel = new JLabel           ("Create New Appointment");
        setJLabelDefaults(welcomeLabel);
        layOut.putConstraint(SpringLayout.HORIZONTAL_CENTER, welcomeLabel, 8, SpringLayout.HORIZONTAL_CENTER, contentPane);
        layOut.putConstraint(SpringLayout.NORTH, welcomeLabel, 15, SpringLayout.NORTH, contentPane);
        
        patientEmailLabel = new JLabel      ("Patient Email"); //To be possibly made redundant?
        setJLabelDefaults(patientEmailLabel);
        layOut.putConstraint(SpringLayout.WEST,  patientEmailLabel, 8, SpringLayout.WEST, contentPane);
        layOut.putConstraint(SpringLayout.NORTH, patientEmailLabel, 60, SpringLayout.NORTH, contentPane);

        appointmentDateLabel = new JLabel   ("Appointment Date");
        setJLabelDefaults(appointmentDateLabel);
        layOut.putConstraint(SpringLayout.WEST,  appointmentDateLabel, 8, SpringLayout.WEST, contentPane);
        layOut.putConstraint(SpringLayout.NORTH, appointmentDateLabel, 100, SpringLayout.NORTH, contentPane);
        
        appointmentTimeLabel = new JLabel   ("Appointment Time");
        setJLabelDefaults(appointmentTimeLabel);
        layOut.putConstraint(SpringLayout.WEST,  appointmentTimeLabel, 8, SpringLayout.WEST, contentPane);
        layOut.putConstraint(SpringLayout.NORTH, appointmentTimeLabel, 140, SpringLayout.NORTH, contentPane);
    
        appointmentSpecialtyLabel=new JLabel("Doctor Specialty");
        setJLabelDefaults(appointmentSpecialtyLabel);
        layOut.putConstraint(SpringLayout.WEST,  appointmentSpecialtyLabel, 8, SpringLayout.WEST, contentPane);
        layOut.putConstraint(SpringLayout.NORTH, appointmentSpecialtyLabel, 180, SpringLayout.NORTH, contentPane);

        appointmentDoctorLabel = new JLabel ("Preferred Doctor");
        setJLabelDefaults(appointmentDoctorLabel);
        layOut.putConstraint(SpringLayout.WEST,  appointmentDoctorLabel, 8, SpringLayout.WEST, contentPane);
        layOut.putConstraint(SpringLayout.NORTH, appointmentDoctorLabel, 220, SpringLayout.NORTH, contentPane);

        appointmentCostLabel = new JLabel   ("Consultation Cost");
        setJLabelDefaults(appointmentCostLabel);
        layOut.putConstraint(SpringLayout.WEST,  appointmentCostLabel, 8, SpringLayout.WEST, contentPane);
        layOut.putConstraint(SpringLayout.NORTH, appointmentCostLabel, 260, SpringLayout.NORTH, contentPane);

        //TEXTFIELDS
        patientEmailTextField = new JTextField(25);
        setJTextFieldDefaults(patientEmailTextField);
        layOut.putConstraint(SpringLayout.WEST,  patientEmailTextField, 25, SpringLayout.EAST, patientEmailLabel);
        layOut.putConstraint(SpringLayout.NORTH, patientEmailTextField, 60, SpringLayout.NORTH, contentPane);

        appointmentCostTextField = new JTextField(10);
        appointmentCostTextField.setText("50");
        setJTextFieldDefaults(appointmentCostTextField);
        layOut.putConstraint(SpringLayout.WEST,  appointmentCostTextField, 11, SpringLayout.EAST, appointmentCostLabel);
        layOut.putConstraint(SpringLayout.NORTH, appointmentCostTextField, 260, SpringLayout.NORTH, contentPane);

        //COMBOBOXES
        daysInNumber = new Integer[31];
        for(int i = 0; i < 31; i++) { 
            daysInNumber[i] = i + 1; 
        }
        appointmentDayComboBox = new JComboBox<Integer>(daysInNumber);
        appointmentDayComboBox.setFocusable(true);
        appointmentDayComboBox.setFont(DEFAULT_FONT);
        appointmentDayComboBox.setBorder(DEFAULT_BORDER);
        layOut.putConstraint(SpringLayout.WEST,  appointmentDayComboBox, 11, SpringLayout.EAST, appointmentDateLabel);
        layOut.putConstraint(SpringLayout.NORTH, appointmentDayComboBox, 100, SpringLayout.NORTH, contentPane);

        monthsInLetters = new DateFormatSymbols().getMonths();
        appointmentMonthComboBox = new JComboBox<String>();
        appointmentMonthComboBox.setFocusable(true);
        appointmentMonthComboBox.setFont(DEFAULT_FONT);
        appointmentMonthComboBox.setBorder(DEFAULT_BORDER);
        for (int i = 0; i <= 11; i++) { appointmentMonthComboBox.addItem(monthsInLetters[i]); }
        layOut.putConstraint(SpringLayout.WEST,  appointmentMonthComboBox, 25, SpringLayout.EAST, appointmentDayComboBox);
        layOut.putConstraint(SpringLayout.NORTH, appointmentMonthComboBox, 100, SpringLayout.NORTH, contentPane);

        final int currentYear = YearMonth.now().getYear();
        final Integer years[] = {currentYear, currentYear+1};
        appointmentYearComboBox = new JComboBox<Integer>(years);
        appointmentYearComboBox.setFocusable(true);
        appointmentYearComboBox.setFont(DEFAULT_FONT);
        appointmentYearComboBox.setBorder(DEFAULT_BORDER);
        layOut.putConstraint(SpringLayout.WEST,  appointmentYearComboBox, 25, SpringLayout.EAST, appointmentMonthComboBox);
        layOut.putConstraint(SpringLayout.NORTH, appointmentYearComboBox, 100, SpringLayout.NORTH, contentPane);

        appointmentTimeComboBox = new JComboBox<String>();
        appointmentTimeComboBox.setFocusable(true);
        appointmentTimeComboBox.setFont(DEFAULT_FONT);
        appointmentTimeComboBox.setBorder(DEFAULT_BORDER);

        final String appointmentHours[] = {"09:00", "10:00","11:00","12:00","14:00","15:00","16:00","17:00"}; //OFFICE HOURS
        for (String hr: appointmentHours){
            appointmentTimeComboBox.addItem(hr);
        }
        layOut.putConstraint(SpringLayout.WEST,  appointmentTimeComboBox, 11, SpringLayout.EAST, appointmentTimeLabel);
        layOut.putConstraint(SpringLayout.NORTH, appointmentTimeComboBox, 140, SpringLayout.NORTH, contentPane);

        final String specialties[] = {"--Pick a Specialty--","General practice", "Psychiatry", "Surgery", "Internal medicine", "Pediatrics", "Neurology", "Dermatology" };

        appointmentSpecialtyComboBox = new JComboBox<String>(specialties); //Get specialties
        appointmentSpecialtyComboBox.setFocusable(true);
        appointmentSpecialtyComboBox.setFont(DEFAULT_FONT);
        appointmentSpecialtyComboBox.setBorder(DEFAULT_BORDER);
        layOut.putConstraint(SpringLayout.WEST,  appointmentSpecialtyComboBox, 15, SpringLayout.EAST, appointmentSpecialtyLabel);
        layOut.putConstraint(SpringLayout.NORTH, appointmentSpecialtyComboBox, 180, SpringLayout.NORTH, contentPane);

        appointmentDoctorComboBox = new JComboBox<>(); //Filter through available docs based on date & time
        appointmentDoctorComboBox.setFocusable(true);
        appointmentDoctorComboBox.setFont(DEFAULT_FONT);
        appointmentDoctorComboBox.setBorder(DEFAULT_BORDER);
        layOut.putConstraint(SpringLayout.WEST,  appointmentDoctorComboBox, 15, SpringLayout.EAST, appointmentDoctorLabel);
        layOut.putConstraint(SpringLayout.NORTH, appointmentDoctorComboBox, 220, SpringLayout.NORTH, contentPane);

        //DISPLAYS DOCTORS FOR CORRESPONDING SPECIALTY
        appointmentSpecialtyComboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                String selected = appointmentSpecialtyComboBox.getSelectedItem().toString();
                appointmentDoctorComboBox.removeAllItems();
                for (Doctor doc: APUMedicalCenter.doctorsArrayList){
                    if (selected.equals(doc.getMedicalSpecialty())){ appointmentDoctorComboBox.addItem(doc.getFirstAndLastName()); }
                }
            }
        });

        //BUTTONS
        createAppointmentButton = new JButton("Create Appointment");
        setJButtonDefaults(createAppointmentButton);
        createAppointmentButton.setSize(DEFAULT_JBUTTON_WIDTH, DEFAULT_JBUTTON_HEIGHT);
        layOut.putConstraint(SpringLayout.WEST, createAppointmentButton, 8, SpringLayout.WEST, contentPane);
        layOut.putConstraint(SpringLayout.NORTH, createAppointmentButton, 300, SpringLayout.NORTH, contentPane);
        createAppointmentButton.addActionListener(this);

        cancelButton = new JButton("Cancel");
        setJButtonDefaults(cancelButton);
        cancelButton.setSize(DEFAULT_JBUTTON_WIDTH, DEFAULT_JBUTTON_HEIGHT);
        layOut.putConstraint(SpringLayout.WEST,  cancelButton, 20, SpringLayout.EAST, createAppointmentButton);
        layOut.putConstraint(SpringLayout.NORTH, cancelButton, 300, SpringLayout.NORTH, contentPane);
        cancelButton.addActionListener(this);

        //ADDING ALL COMPONENTS
        contentPane.add(welcomeLabel);
        contentPane.add(patientEmailLabel);
        contentPane.add(appointmentDateLabel);
        contentPane.add(appointmentTimeLabel);
        contentPane.add(appointmentSpecialtyLabel);
        contentPane.add(appointmentDoctorLabel);
        contentPane.add(appointmentCostLabel);
        contentPane.add(patientEmailTextField);
        contentPane.add(appointmentTimeComboBox);
        contentPane.add(appointmentCostTextField);
        contentPane.add(appointmentDayComboBox);
        contentPane.add(appointmentMonthComboBox);
        contentPane.add(appointmentYearComboBox);
        contentPane.add(appointmentDoctorComboBox);
        contentPane.add(appointmentSpecialtyComboBox);
        contentPane.add(createAppointmentButton);
        contentPane.add(cancelButton);
        //END OF GUI 
    }

    //ACTION LISTENERS 
    public void actionPerformed(ActionEvent actionEvent) {
        JButton clickedButton = (JButton) actionEvent.getSource();
        if (clickedButton == cancelButton){
            this.setVisible(false);
        }

        //MAIN VALIDATION & VERIFICATION 
        else if (clickedButton == createAppointmentButton) {
            //CHECK IF PATIENT EXISTS 
            String patientEmail         = patientEmailTextField.getText();
            if (Patient.doesPatientExist(patientEmail)){
                if (appointmentDoctorComboBox.getItemCount() == 0 || appointmentCostTextField.getText().equals("")){
                    Notifier.emptyInput();
                }
                else if (InputValidator.isStringParsableToInt(appointmentCostTextField.getText()) == false) {
                    Notifier.invalidInt();
                }
                else{
                    String appointmentDoc   = appointmentDoctorComboBox.getSelectedItem().toString();
                    Doctor doctorObject     = validateDoc(appointmentDoc);
                    Patient patient         = Patient.getPatient(patientEmail);
                    String appointmentTime  = appointmentTimeComboBox.getSelectedItem().toString();
                    int appointmentCost     = Integer.parseInt(appointmentCostTextField.getText());

                    if (appointmentCost < 1 || appointmentCost > 20000){
                        Notifier.checkoutChargeOutOfBounds();
                    }
                    else{
                        DecimalFormat df        = new DecimalFormat("00");
                        String appointmentDay   = df.format((int) appointmentDayComboBox.getSelectedItem());
                        String appointmentMonth = df.format((int) appointmentMonthComboBox.getSelectedIndex() + 1);
                        String appointmentYear  = appointmentYearComboBox.getSelectedItem().toString();
                        String appointmentDate  = appointmentYear + "-" + appointmentMonth + "-" + appointmentDay;
                        
                        if(InputValidator.isDateValid(appointmentDate)){
                            if(validateDoctorAvailability(doctorObject, appointmentTime, appointmentDate)){
                                int opt = JOptionPane.showConfirmDialog(this, "Confirm appointment", "Validation passed", JOptionPane.YES_NO_OPTION);
    
                                if (opt==JOptionPane.YES_OPTION){
                                    //APPOINTMENT IS CREATED HERE
                                    Appointment app = new Appointment(doctorObject.getEmailAddress(), patientEmail, appointmentDate, appointmentTime, appointmentCost);
                                    APUMedicalCenter.appointmentsArrayList.add(app);
                                    patient.incrementAppointmentsNumber();
                                    JOptionPane.showMessageDialog(this, "Done");
                                    this.setVisible(false);
                                }
                            } else { Notifier.duplicateAppointmentDate(doctorObject.getFirstAndLastName(), appointmentDate, appointmentTime); }
                        } else { Notifier.invalidDate(appointmentDate); }   
                    }
                }   
            } else { Notifier.patientEmailAddressNotFound(patientEmail); } 
        } //end of create appointment event
    } //end of actionPerformed

    public boolean validatePatient(String patEmail){
        for (Patient pat: APUMedicalCenter.patientsArrayList){
            if (patEmail.equals(pat.getEmailAddress())){
                return true;
            }
        } return false;
    }

    public Doctor validateDoc(String docName){ 
        for (Doctor doc: APUMedicalCenter.doctorsArrayList){
            if (docName.equals(doc.getFirstAndLastName())){
                return doc;
            }
        } return null;
    }

    //CHECKS IF DOCTORS IS AVAILABLE AT DATE+TIME 
    public Boolean validateDoctorAvailability(Doctor doc, String appTime, String appDate){
        //assert debugging 
        for (Appointment app: APUMedicalCenter.appointmentsArrayList){ //Deal with ^^^
            if (doc.equals(app.getDoctor()) && appTime.equals(app.getTime()) && appDate.equals(app.getDate()) && !app.getIsComplete()){
                return false;
            }
        } return true;
    }

    //Rescheduling an appointment from AdminAppointmentsManageJFrame
    public void rescheduleAppointment(String appID){
        try{
            for (Appointment appointment: APUMedicalCenter.appointmentsArrayList){
                if (appointment.getID().equals(appID)){
                    patientEmailTextField.setText(appointment.getPatientEmail());
                    appointmentCostTextField.setText(String.valueOf(appointment.getCost()));
                    appointmentDoctorComboBox.setSelectedItem(appointment.getDoctor());
                    appointmentSpecialtyComboBox.setSelectedItem(appointment.getDoctor().getMedicalSpecialty());
                    
                    patientEmailTextField.setEnabled(false);
                    appointmentCostTextField.setEnabled(false);
                    appointmentDoctorComboBox.setEnabled(false);
                    appointmentSpecialtyComboBox.setEnabled(false);
                    cancelButton.setEnabled(false);
    
                    APUMedicalCenter.appointmentsArrayList.remove(appointment);
                    this.setDefaultCloseOperation(0);
                    break;
                }
            }
        }
        catch (Exception ex){
            System.out.println("Error: " + ex.getMessage());
        }
    }
}