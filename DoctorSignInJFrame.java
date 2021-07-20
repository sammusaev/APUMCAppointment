import javax.swing.*;
import java.awt.event.*;
/**
 * The {@code DoctorLoginJFrame} class creates a login form for 
 * doctors.
 * @author Mozhar TP058272 
 * @author Samir TP050965
 */
final public class DoctorSignInJFrame extends SignInTemplate implements ActionListener {
    public static DoctorMainPageJFrame doctorMainPageWindow;
    
    public DoctorSignInJFrame() {
        super();
        super.banner1JLabel.setText("Greetings doctor, please enter your credentials.");
        super.signInJButton.addActionListener(this);
        super.clearAllFieldsJButton.addActionListener(this);
    }

    public void actionPerformed(ActionEvent actionEvent) {
        JButton clickedJButton   = (JButton) actionEvent.getSource();
        String  emailAddress     = super.emailJTextField.getText();
        String  password         = new String(passwordJPasswordField.getPassword());
        boolean credentialsFound = false;
        boolean duplicateSignIn  = false;
        if(clickedJButton.equals(super.signInJButton)) {
            if(!emailAddress.isEmpty() && !password.isEmpty()) {  
                if(APUMedicalCenter.signedInDoctor == null) {
                    for(Doctor doctor : APUMedicalCenter.doctorsArrayList) {
                        if(doctor.getEmailAddress().equals(emailAddress) && doctor.getSignInPassword().equals(password)) {
                            APUMedicalCenter.signedInDoctor = doctor;
                            credentialsFound = true;
                            break;
                        } else {
                            continue;
                        }
                    }
                } else { duplicateSignIn = true; }
                
                if(credentialsFound && !duplicateSignIn) {  
                    ClearAllFields();
                    this.setVisible(false);
                    WelcomeJFrame.doctorWelcomeWindow.setVisible(false);
                    doctorMainPageWindow = new DoctorMainPageJFrame();
                    doctorMainPageWindow.setVisible(true);
                } else if(!credentialsFound && duplicateSignIn) {
                    Notifier.duplicateSignIn("Doctor");
                } else if(!credentialsFound) {
                    Notifier.invalidCredentials();
                } 
            } else {
                Notifier.emptyInput();
            }
        } else if(clickedJButton == clearAllFieldsJButton) {
            super.ClearAllFields();
        }
    }
} 