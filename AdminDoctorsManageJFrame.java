import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.event.*;
import java.util.ArrayList;
/**
 * The {@code AdminDoctorsManageJFrame} class creates a GUI with a JTable that allows an admin to
 * view, add, and delete doctors.
 * @author Mozhar TP058272
 */
final public class AdminDoctorsManageJFrame extends JFrame implements IJFrameParent, ActionListener {
    private JButton addDoctorJButton, deleteDoctorJButton, editDoctorJButton, exitDoctorJButton;
    private static DoctorRegistrationJFrame registerDoctor = new DoctorRegistrationJFrame();
    private JTable doctorsJTable;
    private String doctorsJTableColumns[];
    private String doctorsJTableRows[];
    private DefaultTableModel model;
    private static DoctorRegistrationJFrame doctorRegisterWindow = new DoctorRegistrationJFrame();
    
    public AdminDoctorsManageJFrame() {
        Container contentPane = getContentPane();
        SpringLayout layOut = new SpringLayout();
        contentPane.setLayout(layOut);
        contentPane.setBackground(new Color(0xFFFFFFF));
        setTitle("APU Medical Center");
        setIconImage(APULOGOICON.getImage());
        setSize(1000, 360);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        doctorsJTableColumns = new String[] { "Full Name", "Gender", "Date of Birth", "Medical Specialty",
                                              "Telephone Number", "Residence Address", "Email Address" };
        doctorsJTable = new JTable();
        model = new DefaultTableModel();
        model.setColumnIdentifiers(doctorsJTableColumns);
        doctorsJTable.setModel(model);
        doctorsJTable.setFont(DEFAULT_FONT);
        doctorsJTable.setEnabled(false); // Dont allow admin to edit the JTable
        doctorsJTable.getTableHeader().setReorderingAllowed(false); // Dont allow the admin to reorder the cells
        doctorsJTable.setRowHeight(30);
        doctorsJTable.setPreferredScrollableViewportSize((new Dimension(943, 250)));
        doctorsJTable.setFillsViewportHeight(true);
        doctorsJTable.setBorder(DEFAULT_BORDER);
        TableColumnModel doctorsJTableColumnModel = doctorsJTable.getColumnModel();
        doctorsJTableColumnModel.getColumn(0).setPreferredWidth(150);
        doctorsJTableColumnModel.getColumn(1).setPreferredWidth(40);
        doctorsJTableColumnModel.getColumn(1).setResizable(false);
        doctorsJTableColumnModel.getColumn(2).setPreferredWidth(75);
        doctorsJTableColumnModel.getColumn(2).setResizable(false);
        doctorsJTableColumnModel.getColumn(3).setPreferredWidth(100);
        doctorsJTableColumnModel.getColumn(4).setPreferredWidth(100);
        doctorsJTableColumnModel.getColumn(5).setPreferredWidth(130);
        doctorsJTableColumnModel.getColumn(6).setPreferredWidth(180);
        JScrollPane jScrollPane = new JScrollPane(doctorsJTable);
        updateJTableEntries();

        layOut.putConstraint(SpringLayout.WEST, jScrollPane, 20, SpringLayout.WEST, contentPane);
        layOut.putConstraint(SpringLayout.NORTH, jScrollPane, 5, SpringLayout.NORTH, contentPane);
        contentPane.add(jScrollPane);

        addDoctorJButton = new JButton("           Add a doctor          ");
        setJButtonDefaults(addDoctorJButton);
        layOut.putConstraint(SpringLayout.WEST, addDoctorJButton, 20, SpringLayout.WEST, contentPane);
        layOut.putConstraint(SpringLayout.NORTH, addDoctorJButton, 280, SpringLayout.NORTH, contentPane);
        addDoctorJButton.addActionListener(this);
        contentPane.add(addDoctorJButton);

        deleteDoctorJButton = new JButton("                Delete a doctor                ");
        setJButtonDefaults(deleteDoctorJButton);
        layOut.putConstraint(SpringLayout.WEST, deleteDoctorJButton, 20, SpringLayout.EAST, addDoctorJButton);
        layOut.putConstraint(SpringLayout.NORTH, deleteDoctorJButton, 280, SpringLayout.NORTH, contentPane);
        contentPane.add(deleteDoctorJButton);

        editDoctorJButton = new JButton("                Edit a doctor                ");
        setJButtonDefaults(editDoctorJButton);
        layOut.putConstraint(SpringLayout.WEST, editDoctorJButton, 10, SpringLayout.EAST, deleteDoctorJButton);
        layOut.putConstraint(SpringLayout.NORTH, editDoctorJButton, 280, SpringLayout.NORTH, contentPane);
        contentPane.add(editDoctorJButton);

        exitDoctorJButton = new JButton("            Exit            ");
        setJButtonDefaults(exitDoctorJButton);
        layOut.putConstraint(SpringLayout.WEST, exitDoctorJButton, 20, SpringLayout.EAST, editDoctorJButton);
        layOut.putConstraint(SpringLayout.NORTH, exitDoctorJButton, 280, SpringLayout.NORTH, contentPane);
        contentPane.add(exitDoctorJButton);
    }
    @Override
    public void setJButtonDefaults(JButton jButton) {
        jButton.setFont(DEFAULT_FONT);
        jButton.setFocusable(false);
        jButton.addActionListener(this);
    }

    final public void actionPerformed(ActionEvent actionEvent) {
        JButton clickedJButton = (JButton) actionEvent.getSource();
        if (clickedJButton.equals(addDoctorJButton)) {
            doctorRegisterWindow.setVisible(true);
        } else if (clickedJButton.equals(deleteDoctorJButton)) {
            deleteDoctor();
        } else if(clickedJButton.equals(editDoctorJButton)) {
            Notifier.noDoctorDirectEdit();
        } else if(clickedJButton.equals(exitDoctorJButton)) {
            this.setVisible(false);
        }
    }
    
    final public void updateJTableEntries() {
        doctorsJTableColumns = new String[7]; // Initialize the array since new records are added here only
        ActionListener timeListener = new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                model.setRowCount(0);
                for(Doctor doctor : APUMedicalCenter.doctorsArrayList) {
                    doctorsJTableColumns[0] = doctor.getFirstAndLastName();
                    doctorsJTableColumns[1] = doctor.getGender();
                    doctorsJTableColumns[2] = doctor.getDateOfBirth();
                    doctorsJTableColumns[3] = doctor.getMedicalSpecialty();
                    doctorsJTableColumns[4] = doctor.getTelephoneNumber();
                    doctorsJTableColumns[5] = doctor.getResidenceAddress();
                    doctorsJTableColumns[6] = doctor.getEmailAddress();
                    model.addRow(doctorsJTableColumns);
                }
            };
        };
        new Timer(0, timeListener).start(); // Adds records into the JTable all the time
    }
    final public void deleteDoctor() {
        String doctorToDeleteEmailAddress = JOptionPane.showInputDialog(null, "Enter the email address of the doctor to be delete: ");
        if(doctorToDeleteEmailAddress != null) {
            if(!doctorToDeleteEmailAddress.isEmpty()) {
                Doctor doctorToDelete = Doctor.getDoctor(doctorToDeleteEmailAddress); 
                if(doctorToDelete != null) {
                    APUMedicalCenter.signedInAdmin.deleteDoctor(doctorToDelete);
                    Notifier.doctorDeleteSuccessful();
                } else {
                    Notifier.patientEmailAddressNotFound(doctorToDeleteEmailAddress);
                }
            } else {
                Notifier.emptyInput();
            }
        } else { 
            
        }     
    }
}