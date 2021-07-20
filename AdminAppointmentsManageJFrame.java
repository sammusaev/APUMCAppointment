import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.event.*;

final public class AdminAppointmentsManageJFrame extends JFrame implements IJFrameParent, ActionListener {
    private static final long serialVersionUID = 1L;
    private JButton addAppointmentJButton, deleteAppointmentJButton, editAppointmentJButton, exitAppointmentJButton;
    private static JTable appointmentsJTable;
    private String appointmentsColumns[];

    private DefaultTableModel model;
    private static AppointmentJFrame registerAppointment;
    
    public AdminAppointmentsManageJFrame() {
        Container contentPane = getContentPane();
        SpringLayout layOut = new SpringLayout();
        contentPane.setLayout(layOut);
        contentPane.setBackground(new Color(0xFFFFFFF));
        setTitle("APU Medical Center");
        setIconImage(APULOGOICON.getImage());
        setSize(1000, 360);
        this.setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        appointmentsColumns = new String[] { "ID", "Doctor Email", "Doctor Comment", "Patient Email",
                                             "Cost", "Date", "Time", "Completed" };
        appointmentsJTable = new JTable();
        model = new DefaultTableModel();
        model.setColumnIdentifiers(appointmentsColumns);
        appointmentsJTable.setModel(model);
        appointmentsJTable.setFont(DEFAULT_FONT);

        appointmentsJTable.setCellSelectionEnabled(false); //No single cell selection
        appointmentsJTable.setRowSelectionAllowed(true); //Only rows
        appointmentsJTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); //Single row
        appointmentsJTable.setDefaultEditor(Object.class, null); //No editing

        appointmentsJTable.getTableHeader().setReorderingAllowed(false); // Dont allow the admin to reorder the cells
        appointmentsJTable.setRowHeight(30);
        appointmentsJTable.setPreferredScrollableViewportSize((new Dimension(943, 250)));
        appointmentsJTable.setFillsViewportHeight(true);
        appointmentsJTable.setBorder(DEFAULT_BORDER);
        TableColumnModel appointmentsJTableColumnModel = appointmentsJTable.getColumnModel();
        appointmentsJTableColumnModel.getColumn(0).setPreferredWidth(50);
        appointmentsJTableColumnModel.getColumn(1).setPreferredWidth(200);
        appointmentsJTableColumnModel.getColumn(2).setPreferredWidth(200);
        appointmentsJTableColumnModel.getColumn(3).setPreferredWidth(200);
        appointmentsJTableColumnModel.getColumn(4).setPreferredWidth(70);
        appointmentsJTableColumnModel.getColumn(5).setPreferredWidth(90);
        appointmentsJTableColumnModel.getColumn(6).setPreferredWidth(80);
        appointmentsJTableColumnModel.getColumn(7).setPreferredWidth(100);
        JScrollPane jScrollPane = new JScrollPane(appointmentsJTable);
        layOut.putConstraint(SpringLayout.WEST, jScrollPane, 20, SpringLayout.WEST, contentPane);
        layOut.putConstraint(SpringLayout.NORTH, jScrollPane, 5, SpringLayout.NORTH, contentPane);
        contentPane.add(jScrollPane);
        

        addAppointmentJButton = new JButton("       Add an appointment      ");
        addAppointmentJButton.addActionListener(this);
        layOut.putConstraint(SpringLayout.WEST, addAppointmentJButton, 20, SpringLayout.WEST, contentPane);
        layOut.putConstraint(SpringLayout.NORTH, addAppointmentJButton, 280, SpringLayout.NORTH, contentPane);
        setJButtonDefaults(addAppointmentJButton);
        contentPane.add(addAppointmentJButton);

        deleteAppointmentJButton = new JButton("         Delete an appointment         ");
        setJButtonDefaults(deleteAppointmentJButton);
        deleteAppointmentJButton.addActionListener(this);
        layOut.putConstraint(SpringLayout.WEST, deleteAppointmentJButton, 20, SpringLayout.EAST, addAppointmentJButton);
        layOut.putConstraint(SpringLayout.NORTH, deleteAppointmentJButton, 280, SpringLayout.NORTH, contentPane);
        contentPane.add(deleteAppointmentJButton);

        editAppointmentJButton = new JButton("       Reschedule an appointment       ");
        setJButtonDefaults(editAppointmentJButton);
        editAppointmentJButton.addActionListener(this);
        layOut.putConstraint(SpringLayout.WEST, editAppointmentJButton, 10, SpringLayout.EAST, deleteAppointmentJButton);
        layOut.putConstraint(SpringLayout.NORTH, editAppointmentJButton, 280, SpringLayout.NORTH, contentPane);
        contentPane.add(editAppointmentJButton);

        exitAppointmentJButton = new JButton("        Exit        ");
        setJButtonDefaults(exitAppointmentJButton);
        exitAppointmentJButton.addActionListener(this);
        layOut.putConstraint(SpringLayout.WEST, exitAppointmentJButton, 27, SpringLayout.EAST, editAppointmentJButton);
        layOut.putConstraint(SpringLayout.NORTH, exitAppointmentJButton, 280, SpringLayout.NORTH, contentPane);
        contentPane.add(exitAppointmentJButton);

        populateTable();

        //USED TO UPDATE TABLE
        appointmentsJTable.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                populateTable();
            }
			@Override
			public void focusLost(FocusEvent e) {
			}
        });
    } 
    
    //POPULATES JTABLE WITH DATA
    public void populateTable(){
        model.setRowCount(0);
        for (Appointment appointment: APUMedicalCenter.appointmentsArrayList){
                    String appointmentID           = appointment.getID();
                    String appointmentDoctorEmail  = appointment.getDoctorEmail();
                    String doctorComment           = appointment.getDoctorComment();
                    String appointmentPatientEmail = appointment.getPatientEmail();
                    String appointmentCost         = Integer.toString(appointment.getCost());
                    String appointmentDate         = appointment.getDate();
                    String appointmentTime         = appointment.getTime();
                    String appointmentIsComplete   = Boolean.toString(appointment.getIsComplete());
                    model.insertRow(0, new Object[] {appointmentID, appointmentDoctorEmail,
                                                     doctorComment,appointmentPatientEmail, appointmentCost,
                                                     appointmentDate, appointmentTime, appointmentIsComplete});
        }
    }
    
    //HANDLES BUTTON EVENTS
    public void actionPerformed(ActionEvent actionEvent) {
        JButton clickedJButton = (JButton) actionEvent.getSource();
        if(clickedJButton.equals(addAppointmentJButton)) {
            registerAppointment = new AppointmentJFrame();
            registerAppointment.setVisible(true);
        } else if(clickedJButton.equals(deleteAppointmentJButton) && !appointmentsJTable.getSelectionModel().isSelectionEmpty()) {
            int choice = JOptionPane.showConfirmDialog(null, "Are you sure?", "Deleting Appointment", JOptionPane.YES_NO_OPTION);
            if (choice == JOptionPane.NO_OPTION){}
            else if(choice == JOptionPane.YES_OPTION) {
                deleteAppointment();
            }
            else{}
        } else if(clickedJButton.equals(editAppointmentJButton) && 
                 !appointmentsJTable.getSelectionModel().isSelectionEmpty() && 
                 model.getValueAt(appointmentsJTable.getSelectedRow(), 7).toString().equals("false")) {
            int choice = JOptionPane.showConfirmDialog(null, "Rescheduling will recreate the appointment. Continue?", "Rescheduling Appointment", JOptionPane.YES_NO_OPTION);
            if (choice== JOptionPane.NO_OPTION){}
            else if(choice == JOptionPane.YES_OPTION) {
                AppointmentJFrame rescheduleAppointment = new AppointmentJFrame();
                rescheduleAppointment.setVisible(true);
                String appID = model.getValueAt(appointmentsJTable.getSelectedRow(), 0).toString();
                rescheduleAppointment.rescheduleAppointment(appID);
            }
            else{}
        } else if(clickedJButton.equals(exitAppointmentJButton)) {
            this.setVisible(false);
        }
    }

    //DELETES APPOINTMENT FROM ARRAYLIST
    final public void deleteAppointment() {
        Appointment app = null;
        for (Appointment appointment: APUMedicalCenter.appointmentsArrayList){
            if (appointment.getID().equals(model.getValueAt(appointmentsJTable.getSelectedRow(), 0))){
                app = appointment;
            }
        } 
        APUMedicalCenter.appointmentsArrayList.remove(app);
    }
}