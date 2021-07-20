import javax.swing.*;
import java.awt.event.*;

/**
 * The {@code AdminSignInJFrame} class creates a login form for admins.
 * @author Mozhar TP058272 
 * @author Samir TP050965
 */
final public class AdminSignInJFrame extends SignInTemplate implements ActionListener{
    AdminMainPageJFrame adminMainPageWindow; 
    
    public AdminSignInJFrame() {
        super();
        super.banner1JLabel.setText("Greetings admin, please enter your credentials.");

        super.signInJButton.addActionListener(this);
        super.clearAllFieldsJButton.addActionListener(this);
    }

    public void actionPerformed(ActionEvent actionEvent) {
        JButton clickedJButton   = (JButton) actionEvent.getSource();
        String  emailAddress     = super.emailJTextField.getText();
        String  password         = new String(super.passwordJPasswordField.getPassword());
        boolean credentialsFound = false;
        boolean duplicateSignIn  = false;
        if(clickedJButton == super.signInJButton) {
            if(!emailAddress.isEmpty() && !password.isEmpty()) {  
                if(APUMedicalCenter.signedInAdmin == null) {
                    for(Admin admin : APUMedicalCenter.adminsArrayList) {
                        if(admin.getEmailAddress().equals(emailAddress) && admin.getSignInPassword().equals(password)) {
                            APUMedicalCenter.signedInAdmin = admin;
                            credentialsFound = true;
                            break;
                        } else {
                            continue;
                        }
                    }
                } else {
                    duplicateSignIn  = true;
                }
                if(credentialsFound && !duplicateSignIn) {  
                    ClearAllFields();
                    this.setVisible(false);
                    WelcomeJFrame.adminWelcomeWindow.setVisible(false);
                    adminMainPageWindow = new AdminMainPageJFrame();
                    adminMainPageWindow.setVisible(true);
                } else if(!credentialsFound && duplicateSignIn) {
                    Notifier.duplicateSignIn("Admin");
                } else if(!credentialsFound) {
                    Notifier.invalidCredentials();
                } 
            } else {
                Notifier.emptyInput();
            }
        } else if (clickedJButton.equals(super.clearAllFieldsJButton)) {
            super.ClearAllFields();
        }
    }
}