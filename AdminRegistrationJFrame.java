import java.text.DecimalFormat;
import javax.swing.*;
import java.awt.event.*;
/**
 * The {@code AdminRegistrationJFrame} class creates a registration form for 
 * admin registration.
 * @author Mozhar TP058272 
 * @author Samir TP050965
 */

final public class AdminRegistrationJFrame extends RegistrationTemplate implements ActionListener {
    public AdminRegistrationJFrame() {
        //#region GUICreation
        super(); // Call the parent constructor to get the shared GUI components created
        super.banner1JLabel.setText("Admin Registration Panel");
        super.layOut.putConstraint(SpringLayout.HORIZONTAL_CENTER, super.banner1JLabel, 0, SpringLayout.HORIZONTAL_CENTER, super.contentPane);
        super.contentPane.add(super.banner1JLabel);

        super.layOut.putConstraint(SpringLayout.WEST, super.telephoneNumberJLabel, 8, SpringLayout.WEST, super.contentPane);
        super.layOut.putConstraint(SpringLayout.NORTH, super.telephoneNumberJLabel, 180, SpringLayout.NORTH, super.contentPane);
        super.contentPane.add(super.telephoneNumberJLabel);

        super.layOut.putConstraint(SpringLayout.WEST, super.telephoneNumberJTextField, 14, SpringLayout.EAST, super.telephoneNumberJLabel);
        super.layOut.putConstraint(SpringLayout.NORTH, super.telephoneNumberJTextField, 180, SpringLayout.NORTH, super.contentPane);
        super.contentPane.add(super.telephoneNumberJTextField);

        super.layOut.putConstraint(SpringLayout.WEST, super.residenceAddressJLabel, 8, SpringLayout.WEST, super.contentPane);
        super.layOut.putConstraint(SpringLayout.NORTH, super.residenceAddressJLabel, 220, SpringLayout.NORTH, super.contentPane);
        super.contentPane.add(super.residenceAddressJLabel);

        super.layOut.putConstraint(SpringLayout.WEST, super.residenceAddressJTextField, 17, SpringLayout.EAST, super.residenceAddressJLabel);
        super.layOut.putConstraint(SpringLayout.NORTH, super.residenceAddressJTextField, 220, SpringLayout.NORTH, super.contentPane);
        super.contentPane.add(super.residenceAddressJTextField);

        super.layOut.putConstraint(SpringLayout.WEST, super.emailAddressJLabel, 8, SpringLayout.WEST, super.contentPane);
        super.layOut.putConstraint(SpringLayout.NORTH, super.emailAddressJLabel, 260, SpringLayout.NORTH, super.contentPane);
        super.contentPane.add(super.emailAddressJLabel);

        super.layOut.putConstraint(SpringLayout.WEST, super.emailAddressJTextField, 44, SpringLayout.EAST, super.emailAddressJLabel);
        super.layOut.putConstraint(SpringLayout.NORTH, super.emailAddressJTextField, 260, SpringLayout.NORTH, super.contentPane);
        super.contentPane.add(super.emailAddressJTextField);

        super.layOut.putConstraint(SpringLayout.WEST, super.loginPasswordJLabel, 7, SpringLayout.WEST, super.contentPane);
        super.layOut.putConstraint(SpringLayout.NORTH, super.loginPasswordJLabel, 300, SpringLayout.NORTH, super.contentPane);
        super.contentPane.add(super.loginPasswordJLabel);

        super.layOut.putConstraint(SpringLayout.WEST, super.loginPasswordJPasswordField, 25, SpringLayout.EAST, super.loginPasswordJLabel);
        super.layOut.putConstraint(SpringLayout.NORTH, super.loginPasswordJPasswordField, 300, SpringLayout.NORTH, super.contentPane);
        super.contentPane.add(super.loginPasswordJPasswordField);

        super.layOut.putConstraint(SpringLayout.HORIZONTAL_CENTER, super.registerJButton, -80, SpringLayout.HORIZONTAL_CENTER, super.contentPane);
        super.layOut.putConstraint(SpringLayout.NORTH, super.registerJButton, 340, SpringLayout.NORTH, super.contentPane);
        super.registerJButton.addActionListener(this);
        super.contentPane.add(super.registerJButton);

        super.layOut.putConstraint(SpringLayout.HORIZONTAL_CENTER, super.clearJButton, 80,SpringLayout.HORIZONTAL_CENTER, super.contentPane);
        super.layOut.putConstraint(SpringLayout.NORTH, super.clearJButton, 340, SpringLayout.NORTH, super.contentPane);
        super.clearJButton.addActionListener(this);
        super.contentPane.add(super.clearJButton);
        //#endregion
    }
    public void actionPerformed(ActionEvent actionEvent) {
        JButton clickedJButton = (JButton) actionEvent.getSource();
        if(clickedJButton.equals(super.registerJButton)) {
            String role                            = "admin";
            String firstAndLastName                = super.firstAndLastNameJTextField.getText();
            String telephoneNumber                 = super.telephoneNumberJTextField.getText();
            String residenceAddress                = super.residenceAddressJTextField.getText();
            String emailAddress                    = super.emailAddressJTextField.getText(); 
            String signInPassword                  = new String(super.loginPasswordJPasswordField.getPassword());
            boolean emptyInput                     = false;
            boolean duplicateAdminEmailAddress     = Admin.isAdminEmailAddressUsed(emailAddress);
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
            String[] adminData = { firstAndLastName, gender, telephoneNumber, residenceAddress, 
                                   emailAddress, signInPassword };            
            for(String datum : adminData) {
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
                                    if(InputValidator.isStaffEmailAddressValid(emailAddress)) {
                                        if(InputValidator.isSignInPasswordValid(signInPassword)) {
                                            Admin newAdmin = new Admin(firstAndLastName, gender, dateOfBirth, 
                                                                       telephoneNumber, residenceAddress, 
                                                                       emailAddress, signInPassword);
                                            boolean success = APUMedicalCenter.adminsArrayList.add(newAdmin);
                                            if(success) {
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
        } else if(clickedJButton == clearJButton) {
            super.ClearAllFields();
        }
    }
}