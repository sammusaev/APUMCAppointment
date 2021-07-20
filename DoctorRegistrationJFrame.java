import javax.swing.*;
import java.awt.event.*;
import java.text.DecimalFormat;
/**
 * The {@code DoctorRegistrationJFrame} class creates a registration form for 
 * doctor registration.
 * @author Mozhar TP058272 
 * @author Samir TP050965
 */

final public class DoctorRegistrationJFrame extends RegistrationTemplate implements ActionListener {
    public DoctorRegistrationJFrame() {
        super();
        super.setSize(500, 450);

        super.banner1JLabel.setText("Doctor Registration Panel");
        super.layOut.putConstraint(SpringLayout.HORIZONTAL_CENTER, banner1JLabel, 0, SpringLayout.HORIZONTAL_CENTER, contentPane);
        super.contentPane.add(banner1JLabel);

        super.doctorSpecialtyJLabel = new JLabel("Specialty:");
        super.setJLabelDefaults(doctorSpecialtyJLabel);
        super.layOut.putConstraint(SpringLayout.WEST, doctorSpecialtyJLabel, 8, SpringLayout.WEST, contentPane);
        super.layOut.putConstraint(SpringLayout.NORTH, doctorSpecialtyJLabel, 180, SpringLayout.NORTH, contentPane);
        super.contentPane.add(doctorSpecialtyJLabel);
        
        super.doctorSpecialtyJComboBox = new JComboBox<String>();
        doctorSpecialtyJComboBox.setFont(DEFAULT_FONT);
        doctorSpecialtyJComboBox.setBorder(DEFAULT_BORDER);
        super.specialties = new String[] {"General practice", "Psychiatry", "Surgery", "Internal medicine", "Pediatrics", "Neurology", "Dermatology" };
        for(String specialty : super.specialties) {
            super.doctorSpecialtyJComboBox.addItem(specialty);
        }
        super.doctorSpecialtyJComboBox.setSelectedItem(super.specialties[0]); // set the default to "General practice"
        super.layOut.putConstraint(SpringLayout.WEST,  super.doctorSpecialtyJComboBox, 78,  SpringLayout.EAST,  super.doctorSpecialtyJLabel);
        super.layOut.putConstraint(SpringLayout.NORTH, super.doctorSpecialtyJComboBox, 180, SpringLayout.NORTH, super.contentPane);
        super.contentPane.add(super.doctorSpecialtyJComboBox);

        super.layOut.putConstraint(SpringLayout.WEST,  super.telephoneNumberJLabel, 8, SpringLayout.WEST,    super.contentPane);
        super.layOut.putConstraint(SpringLayout.NORTH, super.telephoneNumberJLabel, 220, SpringLayout.NORTH, super.contentPane);
        super.contentPane.add(super.telephoneNumberJLabel);
        
        super.layOut.putConstraint(SpringLayout.WEST,  super.telephoneNumberJTextField, 14, SpringLayout.EAST,   super.telephoneNumberJLabel);
        super.layOut.putConstraint(SpringLayout.NORTH, super.telephoneNumberJTextField, 220, SpringLayout.NORTH, super.contentPane);
        super.contentPane.add(super.telephoneNumberJTextField);
        
        super.layOut.putConstraint(SpringLayout.WEST,  super.residenceAddressJLabel, 8,   SpringLayout.WEST,  super.contentPane);
        super.layOut.putConstraint(SpringLayout.NORTH, super.residenceAddressJLabel, 260, SpringLayout.NORTH, super.contentPane);
        super.contentPane.add(super.residenceAddressJLabel);

        super.layOut.putConstraint(SpringLayout.WEST,  super.residenceAddressJTextField, 17, SpringLayout.EAST,   super.residenceAddressJLabel);
        super.layOut.putConstraint(SpringLayout.NORTH, super.residenceAddressJTextField, 260, SpringLayout.NORTH, super.contentPane);
        super.contentPane.add(super.residenceAddressJTextField);

        super.layOut.putConstraint(SpringLayout.WEST,  super.emailAddressJLabel, 8, SpringLayout.WEST,    super.contentPane);
        super.layOut.putConstraint(SpringLayout.NORTH, super.emailAddressJLabel, 300, SpringLayout.NORTH, super.contentPane);
        super.contentPane.add(super.emailAddressJLabel);

        super.layOut.putConstraint(SpringLayout.WEST,  super.emailAddressJTextField, 44, SpringLayout.EAST,   super.emailAddressJLabel);
        super.layOut.putConstraint(SpringLayout.NORTH, super.emailAddressJTextField, 300, SpringLayout.NORTH, super.contentPane);
        super.contentPane.add(super.emailAddressJTextField);

        super.layOut.putConstraint(SpringLayout.WEST,  super.loginPasswordJLabel, 7,   SpringLayout.WEST,  super.contentPane);
        super.layOut.putConstraint(SpringLayout.NORTH, super.loginPasswordJLabel, 340, SpringLayout.NORTH, super.contentPane);
        super.contentPane.add(super.loginPasswordJLabel);
        
        super.layOut.putConstraint(SpringLayout.WEST,  super.loginPasswordJPasswordField, 25,  SpringLayout.EAST,  super.loginPasswordJLabel);
        super.layOut.putConstraint(SpringLayout.NORTH, super.loginPasswordJPasswordField, 340, SpringLayout.NORTH, super.contentPane);
        super.contentPane.add(super.loginPasswordJPasswordField);

        super.layOut.putConstraint(SpringLayout.WEST,  super.registerJButton, 140, SpringLayout.WEST,  super.contentPane);
        super.layOut.putConstraint(SpringLayout.NORTH, super.registerJButton, 375, SpringLayout.NORTH, super.contentPane);
        super.registerJButton.addActionListener(this);
        
        super.contentPane.add(super.registerJButton);

        super.layOut.putConstraint(SpringLayout.HORIZONTAL_CENTER, super.clearJButton, 70, SpringLayout.HORIZONTAL_CENTER, super.contentPane);
        super.layOut.putConstraint(SpringLayout.NORTH, super.clearJButton, 375, SpringLayout.NORTH, super.contentPane);
        super.clearJButton.addActionListener(this);
        super.contentPane.add(super.clearJButton);
    }
    final public void actionPerformed(ActionEvent actionEvent) {
        JButton clickedJButton = (JButton) actionEvent.getSource();
        if(clickedJButton.equals(super.registerJButton)) {
            String role                            = "doctor";
            String firstAndLastName                = super.firstAndLastNameJTextField.getText();
            String medicalSpecialty                = (String) doctorSpecialtyJComboBox.getSelectedItem();
            String telephoneNumber                 = super.telephoneNumberJTextField.getText();
            String residenceAddress                = super.residenceAddressJTextField.getText();
            String emailAddress                    = super.emailAddressJTextField.getText(); 
            String signInPassword                  = new String(super.loginPasswordJPasswordField.getPassword());
            boolean emptyInput                     = true;
            boolean duplicateAdminEmailAddress     = Doctor.isDoctorEmailAddressUsed(emailAddress);
            DecimalFormat decimalFormatter         = new DecimalFormat("00");
            String dateOfBirthDay                  = decimalFormatter.format((int) dayOfBirthJComboBox.getSelectedItem());
            String dateOfBirthMonth                = decimalFormatter.format((int) monthOfBirthJComboBox.getSelectedIndex() + 1);
            String dateOfBirthYear                 = yearOfBirthJComboBox.getSelectedItem().toString();
            String dateOfBirth                     = dateOfBirthYear + "-" + dateOfBirthMonth + "-" + dateOfBirthDay;
            String dateOfBirthError                = dateOfBirthDay  + "/" + dateOfBirthMonth + "/" + dateOfBirthYear;
            String gender                          = EMPTY_STRING;
            if(maleJCheckBox.isSelected()) {
                gender = "Male";
            } else if(femaleJCheckBox.isSelected()) {
                gender = "Female";
            }
            String[] doctorData = { firstAndLastName, gender, medicalSpecialty, telephoneNumber, residenceAddress, emailAddress, signInPassword };            
            for(String datum : doctorData) {
                if(datum.isEmpty()) {
                    emptyInput = false;
                    break; 
                }
            }
            if(emptyInput) {
                if(!duplicateAdminEmailAddress) {
                    if(InputValidator.isNameValid(firstAndLastName)) {
                        if(InputValidator.isDateValid(dateOfBirth)) {
                            if(InputValidator.isTelephoneNumberValid(telephoneNumber)) {
                                if(InputValidator.isResidenceAddressValid(residenceAddress)) {
                                    if(InputValidator.isStaffEmailAddressValid(emailAddress)) {
                                        if(InputValidator.isSignInPasswordValid(signInPassword)) {
                                            Doctor newDoctor = new Doctor(firstAndLastName, gender, dateOfBirth,
                                                                          medicalSpecialty, telephoneNumber, 
                                                                          residenceAddress, emailAddress,
                                                                          signInPassword);
                                            if(APUMedicalCenter.signedInAdmin.registerDoctor(newDoctor)) {
                                                Notifier.staffRegisterSuccessful(role, firstAndLastName, emailAddress, signInPassword);
                                            } else { Notifier.staffRegisterUnSuccessful(); }
                                            ClearAllFields();
                                        } else { Notifier.invalidSignInPassword(signInPassword); }                                
                                    } else { Notifier.invalidStaffEmailAddress(emailAddress); } 
                                } else { Notifier.invalidResidenceAddress(residenceAddress); }
                            } else { Notifier.invalidTelephoneNumber(telephoneNumber); }
                        } else { Notifier.invalidDateOfBirth(dateOfBirthError); }     
                    } else { Notifier.invalidName(firstAndLastName); } 
                } else { Notifier.duplicateEmailAddress(emailAddress); }
            } else { Notifier.emptyInput(); }
        } else if(clickedJButton.equals(super.clearJButton)) {
            super.ClearAllFields();
            doctorSpecialtyJComboBox.setSelectedItem(specialties[0]);
        }
    }
}