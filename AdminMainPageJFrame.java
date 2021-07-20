import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
/**
 * The {@code AdminMainPageJFrame} class creates a GUI that allows an admin to
 * navigate between managing patients, doctors, and appointments.
 * @author Mozhar TP058272
 */

final public class AdminMainPageJFrame extends JFrame implements ActionListener, IJFrameParent {
    private static final long serialVersionUID = 1L;
    final private JLabel welcomeJLabel;
    private JButton managePatientsJButton, manageDoctorsJButton,manageAppointmentsJButton,
                    generateReportsJButton, signOutJButton;
    private static AdminPatientsManageJFrame managePatients  = new AdminPatientsManageJFrame();
    private static AdminDoctorsManageJFrame manageDoctors = new AdminDoctorsManageJFrame();
    private static AdminAppointmentsManageJFrame manageAppointments = new AdminAppointmentsManageJFrame();
    private static AdminReportsManageJFrame manageReports = new AdminReportsManageJFrame();
    private final int JFRAME_HEIGHT = 350;
    
    public AdminMainPageJFrame() {
        Container contentPane = getContentPane();
        SpringLayout layOut = new SpringLayout();
        contentPane.setLayout(layOut);
        contentPane.setBackground(new Color(0xFFFFFFF));
        setTitle("APU Medical Center");
        setIconImage(APULOGOICON.getImage());
        setSize(DEFAULT_JFRAME_WIDTH, JFRAME_HEIGHT);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                signOut();
            }
        });
        //#region GUI
        welcomeJLabel = new JLabel();        
        String fullName = APUMedicalCenter.signedInAdmin.getFirstAndLastName();
        welcomeJLabel.setText("Welcome " + fullName + ", you are signed in as an admin.");
        setJLabelDefaults(welcomeJLabel);
        layOut.putConstraint(SpringLayout.HORIZONTAL_CENTER, welcomeJLabel, 0, SpringLayout.HORIZONTAL_CENTER, contentPane);
        layOut.putConstraint(SpringLayout.NORTH, welcomeJLabel, 10, SpringLayout.NORTH, contentPane);
        contentPane.add(welcomeJLabel);

        managePatientsJButton = new JButton("      Manage Patients    ");
        setJButtonDefaults(managePatientsJButton);
        layOut.putConstraint(SpringLayout.WEST, managePatientsJButton, 10, SpringLayout.WEST, contentPane);
        layOut.putConstraint(SpringLayout.NORTH, managePatientsJButton, 50, SpringLayout.NORTH, welcomeJLabel);
        managePatientsJButton.addActionListener(this);
        contentPane.add(managePatientsJButton);

        manageDoctorsJButton = new JButton("    Manage Doctors    ");
        setJButtonDefaults(manageDoctorsJButton);
        layOut.putConstraint(SpringLayout.WEST, manageDoctorsJButton, 35, SpringLayout.EAST, managePatientsJButton);
        layOut.putConstraint(SpringLayout.NORTH, manageDoctorsJButton, 50, SpringLayout.NORTH, welcomeJLabel);
        manageDoctorsJButton.addActionListener(this);
        contentPane.add(manageDoctorsJButton);

        manageAppointmentsJButton = new JButton("Manage Appointments");
        setJButtonDefaults(manageAppointmentsJButton);
        layOut.putConstraint(SpringLayout.WEST, manageAppointmentsJButton, 10, SpringLayout.WEST, contentPane);
        layOut.putConstraint(SpringLayout.NORTH, manageAppointmentsJButton, 100, SpringLayout.NORTH, managePatientsJButton);
        manageAppointmentsJButton.addActionListener(this);
        contentPane.add(manageAppointmentsJButton);

        generateReportsJButton = new JButton("    Manage Reports    ");
        setJButtonDefaults(generateReportsJButton);
        layOut.putConstraint(SpringLayout.WEST, generateReportsJButton, 36, SpringLayout.EAST, manageAppointmentsJButton);
        layOut.putConstraint(SpringLayout.NORTH, generateReportsJButton, 100, SpringLayout.NORTH, manageDoctorsJButton);
        generateReportsJButton.addActionListener(this);
        contentPane.add(generateReportsJButton);

        signOutJButton = new JButton("Sign out");
        setJButtonDefaults(signOutJButton);
        layOut.putConstraint(SpringLayout.HORIZONTAL_CENTER, signOutJButton, 0, SpringLayout.HORIZONTAL_CENTER,
                contentPane);
        layOut.putConstraint(SpringLayout.NORTH, signOutJButton, 250, SpringLayout.NORTH, contentPane);
        signOutJButton.addActionListener(this);
        contentPane.add(signOutJButton);
        // #endregion
    }

    final public void actionPerformed(ActionEvent actionEvent) {
        JButton clickedJButton = (JButton) actionEvent.getSource();
        if(clickedJButton.equals(managePatientsJButton)) {
            managePatients.setVisible(true);
        } else if(clickedJButton.equals(manageDoctorsJButton)) {
            manageDoctors.setVisible(true);
        } else if(clickedJButton.equals(manageAppointmentsJButton)) {
            manageAppointments.setVisible(true);
        } else if(clickedJButton.equals(generateReportsJButton)) {
            manageReports.setVisible(true);
        }else if(clickedJButton.equals(signOutJButton)) {
            signOut();
        }
    }
    public boolean signOut() {
        boolean signOut = false;
        int choice = JOptionPane.showConfirmDialog(null, "Are you sure you want to sign out?",
                                                         "Sign Out", JOptionPane.YES_NO_OPTION);
        if(choice == JOptionPane.YES_OPTION) {
            APUMedicalCenter.signedInAdmin.signOut();
            signOut = true;
            closeAllWindows(signOut);
            this.dispose();
        } else if(choice == JOptionPane.NO_OPTION || choice == JOptionPane.CLOSED_OPTION) { }
        return signOut;
    }
    public void closeAllWindows(boolean isSignOut) {
        if(isSignOut) {
            managePatients.setVisible(false);
            manageDoctors.setVisible(false);
            manageAppointments.setVisible(false);
            manageReports.setVisible(false);
        }
    }
}