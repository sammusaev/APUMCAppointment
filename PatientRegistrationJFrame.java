import javax.swing.*;
import java.awt.event.*;
import java.text.DecimalFormat;

final public class PatientRegistrationJFrame extends RegistrationTemplate implements ActionListener {
    public PatientRegistrationJFrame() {
        super();
        super.setSize(500, 380);

        super.banner1JLabel.setText("Patient Registration Panel");
        super.layOut.putConstraint(SpringLayout.HORIZONTAL_CENTER, super.banner1JLabel, 0, SpringLayout.HORIZONTAL_CENTER, super.contentPane);
        super.contentPane.add(super.banner1JLabel);

        super.layOut.putConstraint(SpringLayout.WEST,  super.telephoneNumberJLabel, 8,   SpringLayout.WEST,  super.contentPane);
        super.layOut.putConstraint(SpringLayout.NORTH, super.telephoneNumberJLabel, 180, SpringLayout.NORTH, super.contentPane);
        super.contentPane.add(super.telephoneNumberJLabel);

        super.layOut.putConstraint(SpringLayout.WEST,  super.telephoneNumberJTextField, 14,  SpringLayout.EAST,  super.telephoneNumberJLabel);
        super.layOut.putConstraint(SpringLayout.NORTH, super.telephoneNumberJTextField, 180, SpringLayout.NORTH, super.contentPane);
        super.contentPane.add(super.telephoneNumberJTextField);
        
        super.layOut.putConstraint(SpringLayout.WEST,  super.residenceAddressJLabel, 8,   SpringLayout.WEST,  super.contentPane);
        super.layOut.putConstraint(SpringLayout.NORTH, super.residenceAddressJLabel, 220, SpringLayout.NORTH, super.contentPane);
        super.contentPane.add(super.residenceAddressJLabel);

        super.layOut.putConstraint(SpringLayout.WEST,  super.residenceAddressJTextField, 17,  SpringLayout.EAST,  super.residenceAddressJLabel);
        super.layOut.putConstraint(SpringLayout.NORTH, super.residenceAddressJTextField, 220, SpringLayout.NORTH, super.contentPane);
        super.contentPane.add(super.residenceAddressJTextField);
        
        super.layOut.putConstraint(SpringLayout.WEST,  super.emailAddressJLabel, 8,   SpringLayout.WEST,  super.contentPane);
        super.layOut.putConstraint(SpringLayout.NORTH, super.emailAddressJLabel, 260, SpringLayout.NORTH, super.contentPane);
        super.contentPane.add(super.emailAddressJLabel);

        super.layOut.putConstraint(SpringLayout.WEST, super.emailAddressJTextField, 43, SpringLayout.EAST, super.emailAddressJLabel);
        super.layOut.putConstraint(SpringLayout.NORTH, super.emailAddressJTextField, 260, SpringLayout.NORTH, super.contentPane);
        super.contentPane.add(super.emailAddressJTextField);

        super.layOut.putConstraint(SpringLayout.HORIZONTAL_CENTER, super.registerJButton, -80, SpringLayout.HORIZONTAL_CENTER, super.contentPane);
        super.layOut.putConstraint(SpringLayout.NORTH, super.registerJButton, 300, SpringLayout.NORTH, super.contentPane);
        super.registerJButton.addActionListener(this);
        super.contentPane.add(super.registerJButton);

        super.layOut.putConstraint(SpringLayout.HORIZONTAL_CENTER, super.clearJButton, 80, SpringLayout.HORIZONTAL_CENTER, super.contentPane);
        super.layOut.putConstraint(SpringLayout.NORTH, super.clearJButton, 300, SpringLayout.NORTH, super.contentPane);
        super.clearJButton.addActionListener(this);
        super.contentPane.add(super.clearJButton);
    }

    final public void actionPerformed(ActionEvent actionEvent) {
        JButton clickedJButton = (JButton) actionEvent.getSource();
        if(clickedJButton.equals(super.registerJButton)) {
            String firstAndLastName                = super.firstAndLastNameJTextField.getText();
            String telephoneNumber                 = super.telephoneNumberJTextField.getText();
            String residenceAddress                = super.residenceAddressJTextField.getText();
            String emailAddress                    = super.emailAddressJTextField.getText(); 
            boolean emptyInput                     = false;
            boolean duplicateAdminEmailAddress     = Patient.isPatientEmailAddressUsed(emailAddress);
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
            String[] patientData = { firstAndLastName, gender, telephoneNumber, residenceAddress, emailAddress };            
            for(String datum : patientData) {
                if(datum.isEmpty()) {
                    emptyInput = true;
                    break; 
                }
            }
            if(!emptyInput) {
                if(!duplicateAdminEmailAddress) {
                    if(InputValidator.isNameValid(firstAndLastName)) {
                        if(InputValidator.isDateValid(dateOfBirth)) {
                            if(InputValidator.isTelephoneNumberValid(telephoneNumber)) {
                                if(InputValidator.isResidenceAddressValid(residenceAddress)) {
                                    if(InputValidator.isPatientEmailAddressValid(emailAddress)) {
                                        Patient newPatient = new Patient(firstAndLastName, gender, dateOfBirth, 
                                                                         telephoneNumber, residenceAddress, 
                                                                         emailAddress);
                                        if(APUMedicalCenter.signedInAdmin.registerPatient(newPatient)) {
                                            Notifier.patientRegisterSuccessful(firstAndLastName, emailAddress);
                                        } else { Notifier.staffRegisterUnSuccessful(); }
                                        ClearAllFields();                
                                    } else { Notifier.invalidPatientEmailAddress(emailAddress); } 
                                } else { Notifier.invalidResidenceAddress(residenceAddress); }
                            } else { Notifier.invalidTelephoneNumber(telephoneNumber); }
                        } else { Notifier.invalidDateOfBirth(dateOfBirthError); }     
                    } else { Notifier.invalidName(firstAndLastName); } 
                } else { Notifier.duplicateEmailAddress(emailAddress); }
            } else { Notifier.emptyInput(); }
        } else if(clickedJButton == clearJButton) {
            super.ClearAllFields();
        }
    }
}