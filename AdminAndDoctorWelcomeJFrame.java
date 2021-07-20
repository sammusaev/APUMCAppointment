import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
/**
 * The {@code AdminAndDoctorWelcomeJFrame} class creates a welcome window for either
 * admins or doctors. 
 * @author Mozhar TP058272 
 * @author Samir TP050965
 */

final public class AdminAndDoctorWelcomeJFrame extends JFrame implements ActionListener, IJFrameParent {
    private boolean isAdmin, isDoctor;
    private JButton signInJButton, registerJButton;
    private JLabel  welcomeJLabel, welcomeJLabel2;
    private final String managementPassword = "#a#123XYZONTIF";  
    private static AdminSignInJFrame adminSignInWindow;
    private static DoctorSignInJFrame doctorSignInWindow;
    private static AdminRegistrationJFrame adminRegisterWindow;
    
    public AdminAndDoctorWelcomeJFrame(String identity) {
        //#region GUI
        Container contentPane = getContentPane();
        contentPane.setLayout(new FlowLayout(FlowLayout.CENTER, 35, 15));
        contentPane.setBackground(new Color(0xFFFFFFF));
        setTitle("APU Medical Center");
        setIconImage(APULOGOICON.getImage());
        setSize(DEFAULT_JFRAME_WIDTH, DEFAULT_JFRAME_HEIGHT);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        welcomeJLabel = new JLabel();
        if(identity.equals("Admin")) {
            isAdmin = true;
            welcomeJLabel.setText("Welcome to the admin staff panel");
        } else if(identity.equals("Doctor")) {
            isDoctor = true;
            welcomeJLabel.setText("Welcome to the medical doctors panel");
        }
        welcomeJLabel.setFont(DEFAULT_FONT);
        welcomeJLabel.setForeground(Color.BLACK);
        welcomeJLabel.setSize(450, 460);
        welcomeJLabel.setIcon(APULOGOIMAGE);
        welcomeJLabel.setHorizontalTextPosition(JLabel.CENTER);
        welcomeJLabel.setVerticalTextPosition(JLabel.BOTTOM);
        welcomeJLabel.setIconTextGap(20);
        contentPane.add(welcomeJLabel);

        welcomeJLabel2 = new JLabel();
        welcomeJLabel2.setFont(DEFAULT_FONT);
        welcomeJLabel2.setForeground(Color.BLACK);
        welcomeJLabel2.setText("Please click one of the buttons to proceed");
        contentPane.add(welcomeJLabel2);

        signInJButton = new JButton("Sign in");
        signInJButton.setSize(DEFAULT_JBUTTON_WIDTH, DEFAULT_JBUTTON_HEIGHT);
        setJButtonDefaults(signInJButton);
        signInJButton.addActionListener(this);
        contentPane.add(signInJButton);

        registerJButton = new JButton("Register");
        registerJButton.setSize(DEFAULT_JBUTTON_WIDTH, DEFAULT_JBUTTON_HEIGHT);
        setJButtonDefaults(registerJButton);
        registerJButton.addActionListener(this);
        contentPane.add(registerJButton);
        //#endregion
    }

    final public void actionPerformed(ActionEvent actionEvent) {
        JButton clickedButton = (JButton) actionEvent.getSource();
        if(isAdmin && clickedButton.equals(signInJButton)) {
            adminSignInWindow = new AdminSignInJFrame();
            adminSignInWindow.setVisible(true);
        } else if(isDoctor && clickedButton.equals(signInJButton)) {
            doctorSignInWindow = new DoctorSignInJFrame();
            doctorSignInWindow.setVisible(true);
        } else if(isAdmin && clickedButton.equals(registerJButton)) {
            validateAdminStaff();
        } else if(isDoctor && clickedButton.equals(registerJButton)) {
            Notifier.noManualRegistrationForDoctor();
        }
    }
    
    public void validateAdminStaff() {
        while(true) {
            String passwordBuffer = JOptionPane.showInputDialog(null, "Enter the password given to you by" +
                                                                      " the management to proceed: ", 
                                                                      "Admin Validation",
                                                                      JOptionPane.DEFAULT_OPTION);
            if(passwordBuffer != null) {
                if(passwordBuffer.equals(managementPassword)) {
                    adminRegisterWindow = new AdminRegistrationJFrame();
                    adminRegisterWindow.setVisible(true);
                    break;
                } else if(!passwordBuffer.equals(managementPassword)) {
                    int input = JOptionPane.showConfirmDialog(null, "Incorrect password.\nDo you want to try again?", 
                                                                    "Admin Validation", 
                                                                    JOptionPane.YES_NO_OPTION);
                    if(input == JOptionPane.YES_OPTION) { 

                    } else if (input == JOptionPane.NO_OPTION || input == JOptionPane.CLOSED_OPTION) {
                        break; 
                    }
                }
            } else {
                break;
            }
        }
    }
}