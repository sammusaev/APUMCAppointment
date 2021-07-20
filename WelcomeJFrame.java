import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

final public class WelcomeJFrame extends JFrame implements ActionListener, IJFrameParent {
    private JLabel welcomeJLabel, dateAndTimeJLabel;
    private JButton adminJButton, doctorJButton;
    public static AdminAndDoctorWelcomeJFrame adminWelcomeWindow;
    public static AdminAndDoctorWelcomeJFrame doctorWelcomeWindow;

    public WelcomeJFrame() {
        Container contentPane = getContentPane();
        contentPane.setLayout(new FlowLayout(FlowLayout.CENTER, 25, 15));
        contentPane.setBackground(Color.WHITE);
        setTitle("APU Medical Center");
        setIconImage(APULOGOICON.getImage());
        setSize(DEFAULT_JFRAME_WIDTH, DEFAULT_JFRAME_HEIGHT);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
        public void windowClosing(WindowEvent windowEvent) {
            int choice = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit the program?", 
                                                             "Exiting the Program", 
                                                             JOptionPane.YES_NO_OPTION);
            if(choice == JOptionPane.YES_OPTION) {
                IO.WriteAdminsData();
                IO.WriteDoctorsData();
                IO.WriteDeletedDoctorsData();
                IO.WritePatientsData();
                IO.WriteDeletedPatientsData();
                IO.WriteAppointmentsData();
                IO.WriteAppointmentNumber();
                System.exit(0);
            } else if(choice == JOptionPane.NO_OPTION || choice == JOptionPane.CLOSED_OPTION) {}
        }
        });

        welcomeJLabel = new JLabel();
        welcomeJLabel.setFont(DEFAULT_FONT);
        welcomeJLabel.setForeground(Color.BLACK);
        welcomeJLabel.setText("Welcome to the Medical Center of Asia Pacific University");
        welcomeJLabel.setSize(450, 460);
        welcomeJLabel.setIcon(APULOGOIMAGE);
        welcomeJLabel.setHorizontalTextPosition(JLabel.CENTER);
        welcomeJLabel.setVerticalTextPosition(JLabel.BOTTOM);
        welcomeJLabel.setIconTextGap(20);
        contentPane.add(welcomeJLabel);

        updatedateAndTimeJLabel();
        contentPane.add(dateAndTimeJLabel);

        adminJButton = new JButton("Admins");
        adminJButton.setSize(DEFAULT_JBUTTON_WIDTH, DEFAULT_JBUTTON_HEIGHT);
        adminJButton.setFont(DEFAULT_FONT);
        adminJButton.setFocusable(false);
        adminJButton.addActionListener(this);
        contentPane.add(adminJButton);

        doctorJButton = new JButton("Doctors");
        doctorJButton.setSize(DEFAULT_JBUTTON_WIDTH, DEFAULT_JBUTTON_HEIGHT);
        doctorJButton.setFont(DEFAULT_FONT);
        doctorJButton.setFocusable(false);
        doctorJButton.addActionListener(this);
        contentPane.add(doctorJButton);
    }

    public void actionPerformed(ActionEvent actionEvent) {
        JButton clickedJButton = (JButton) actionEvent.getSource();
        if(clickedJButton == adminJButton) {
            adminWelcomeWindow = new AdminAndDoctorWelcomeJFrame("Admin");
            adminWelcomeWindow.setVisible(true);
        } else if(clickedJButton == doctorJButton) {
            doctorWelcomeWindow = new AdminAndDoctorWelcomeJFrame("Doctor");
            doctorWelcomeWindow.setVisible(true);
        }
    }
    
    public void updatedateAndTimeJLabel() {
        dateAndTimeJLabel = new JLabel();
        dateAndTimeJLabel.setFont(DEFAULT_FONT);
        dateAndTimeJLabel.setForeground(Color.black);
        ActionListener timeListener = new ActionListener() {   
            public void actionPerformed(ActionEvent actionEvent) {
                LocalDateTime dateAndTime              = LocalDateTime.now();
                DateTimeFormatter formattedDateAndTime = DateTimeFormatter.ofPattern("E, MM/dd/yyyy HH:mm:ss");
                String formattedDateAndTimeString      = dateAndTime.format(formattedDateAndTime);
                dateAndTimeJLabel.setText("The local date and time are: " + formattedDateAndTimeString); 
            }
        };
        new Timer(0, timeListener).start();
    }
}