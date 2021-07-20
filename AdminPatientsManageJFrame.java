import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.event.*;
/**
 * The {@code AdminPatientsManageJFrame} class creates a GUI with a JTable that allows viewing, adding, and deleting of patients.
 * @author Mozhar TP058272
 */
final public class AdminPatientsManageJFrame extends JFrame implements ActionListener, IJFrameParent {
    private JButton addPatientJButton, deletePatientJButton, editPatientJButton, exitPatientJButton;
    private JTable patientsJTable;
    private String patientsJTableColumns[];
    private String[] patientsJTableRows = new String[6];
    private DefaultTableModel model;
    private static PatientRegistrationJFrame patientRegisterWindow = new PatientRegistrationJFrame();

    public AdminPatientsManageJFrame() {
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

        patientsJTableColumns = new String[] { "Full Name", "Gender", "Date of Birth", "Telephone Number",
                                               "Residence Address", "Email Address" };
        patientsJTable = new JTable();
        model = new DefaultTableModel();
        model.setColumnIdentifiers(patientsJTableColumns);
        patientsJTable.setModel(model);
        patientsJTable.setFont(DEFAULT_FONT);

        patientsJTable.getTableHeader().setReorderingAllowed(false); // Dont allow the admin to reorder the cells
        patientsJTable.getTableHeader().setResizingAllowed(false); // Dont allow the admin to resize the JTable cells
        patientsJTable.setRowHeight(30);
        patientsJTable.setPreferredScrollableViewportSize((new Dimension(943, 250)));
        patientsJTable.setFillsViewportHeight(true);
        patientsJTable.setBorder(DEFAULT_BORDER);
   
        TableColumnModel patientsJTableColumnModel = patientsJTable.getColumnModel();
        patientsJTableColumnModel.getColumn(0).setPreferredWidth(200);
        patientsJTableColumnModel.getColumn(0).setResizable(true);
        patientsJTableColumnModel.getColumn(1).setPreferredWidth(60);
        patientsJTableColumnModel.getColumn(2).setPreferredWidth(90);
        patientsJTableColumnModel.getColumn(3).setPreferredWidth(110);
        patientsJTableColumnModel.getColumn(4).setPreferredWidth(150);
        patientsJTableColumnModel.getColumn(5).setPreferredWidth(250);
        patientsJTableColumnModel.getColumn(5).setResizable(true);
        JScrollPane jScrollPane = new JScrollPane(patientsJTable);
        layOut.putConstraint(SpringLayout.WEST, jScrollPane, 20, SpringLayout.WEST, contentPane);
        layOut.putConstraint(SpringLayout.NORTH, jScrollPane, 5, SpringLayout.NORTH, contentPane);
        contentPane.add(jScrollPane);
        updateJTableEntries();

        addPatientJButton = new JButton("        Add a patient        ");
        setJButtonDefaults(addPatientJButton);
        layOut.putConstraint(SpringLayout.WEST, addPatientJButton, 20, SpringLayout.WEST, contentPane);
        layOut.putConstraint(SpringLayout.NORTH, addPatientJButton, 280, SpringLayout.NORTH, contentPane);
        addPatientJButton.addActionListener(this);
        contentPane.add(addPatientJButton);
        
        deletePatientJButton = new JButton("        Delete a patient        ");
        setJButtonDefaults(deletePatientJButton);
        layOut.putConstraint(SpringLayout.WEST, deletePatientJButton, 60, SpringLayout.EAST, addPatientJButton);
        layOut.putConstraint(SpringLayout.NORTH, deletePatientJButton, 280, SpringLayout.NORTH, contentPane);
        contentPane.add(deletePatientJButton);
        
        editPatientJButton = new JButton("        Edit a patient        ");
        setJButtonDefaults(editPatientJButton);
        layOut.putConstraint(SpringLayout.WEST, editPatientJButton, 60, SpringLayout.EAST, deletePatientJButton);
        layOut.putConstraint(SpringLayout.NORTH, editPatientJButton, 280, SpringLayout.NORTH, contentPane);
        contentPane.add(editPatientJButton);
        
        exitPatientJButton = new JButton("        Exit        ");
        setJButtonDefaults(exitPatientJButton);
        layOut.putConstraint(SpringLayout.WEST, exitPatientJButton, 70, SpringLayout.EAST, editPatientJButton);
        layOut.putConstraint(SpringLayout.NORTH, exitPatientJButton, 280, SpringLayout.NORTH, contentPane);
        contentPane.add(exitPatientJButton);
    }
    @Override
    public void setJButtonDefaults(JButton jButton) {
        jButton.setFont(DEFAULT_FONT);
        jButton.setFocusable(false);
        jButton.addActionListener(this);
    }
    
    final public void actionPerformed(ActionEvent actionEvent) {
        JButton clickedJButton = (JButton) actionEvent.getSource();
        if(clickedJButton.equals(addPatientJButton)) {
            patientRegisterWindow.setVisible(true);
        } else if(clickedJButton.equals(deletePatientJButton)) {
            deletePatient();
        } else if(clickedJButton.equals(editPatientJButton)) {
            Notifier.noPatientDirectEdit();
        } else if(clickedJButton.equals(exitPatientJButton)) {
            this.setVisible(false);
        }
    }
    
    final public void updateJTableEntries() {
        patientsJTableRows = new String[6];
        ActionListener timeListener = new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                model.setRowCount(0);
                for(Patient patient : APUMedicalCenter.patientsArrayList) {
                    patientsJTableRows[0] = patient.getFirstAndLastName();
                    patientsJTableRows[1] = patient.getGender();
                    patientsJTableRows[2] = patient.getDateOfBirth();
                    patientsJTableRows[3] = patient.getTelephoneNumber();
                    patientsJTableRows[4] = patient.getResidenceAddress();
                    patientsJTableRows[5] = patient.getEmailAddress();
                    model.addRow(patientsJTableRows);   
                }
            }
        };
        new Timer(0, timeListener).start();
    }

    final public void deletePatient() {
        String patientToDeleteEmailAddress = JOptionPane.showInputDialog(null, "Enter the email address of the patient to be delete: ");
        if(patientToDeleteEmailAddress != null) {
            if(!patientToDeleteEmailAddress.isEmpty()) {
                Patient patientToDelete = Patient.getPatient(patientToDeleteEmailAddress); 
                if(patientToDelete != null) {
                    APUMedicalCenter.signedInAdmin.deletePatient(patientToDelete);
                    Notifier.patientDeleteSuccessful();
                } else {
                    Notifier.patientEmailAddressNotFound(patientToDeleteEmailAddress);
                }
            } else {
                Notifier.emptyInput();
            }
        } else { } // The Cancel or "X" button is pushed so do nothing     
    }
}