import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;

final public class DoctorMainPageJFrame extends JFrame implements ActionListener, IJFrameParent {
    private static final long serialVersionUID = 1L;
    
    final private JLabel welcomeLabel;

    //DTM inside JTABLE
    final private JTabbedPane mainTabbedPane;
    final private JScrollPane completedScrollPane, upcomingScrollPane;
    JTable completedAppsTable = new JTable();
    JTable upcomingAppsTable = new JTable();
    
    DefaultTableModel completedAppsModel = (DefaultTableModel)completedAppsTable.getModel();
    DefaultTableModel upcomingAppsModel = (DefaultTableModel)upcomingAppsTable.getModel();

    final private JButton signOutButton, checkOutButton, updatePersonalInfoButton;

    //CONSTRUCTOR
    public DoctorMainPageJFrame() {
        Container contentPane = getContentPane();
        SpringLayout layOut = new SpringLayout();
        contentPane.setLayout(layOut);
        contentPane.setBackground(new Color(0xFFFFFFF));
        setTitle("APU Medical Center");
        setIconImage(APULOGOICON.getImage());
        setSize(750, 360);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                signOut();
            }
        });

        welcomeLabel = new JLabel();
        String fullName = APUMedicalCenter.signedInDoctor.getFirstAndLastName();
        welcomeLabel.setText("Welcome, Dr. " + fullName);
        setJLabelDefaults(welcomeLabel);  
        layOut.putConstraint(SpringLayout.HORIZONTAL_CENTER, welcomeLabel, 0, SpringLayout.HORIZONTAL_CENTER, contentPane);
        layOut.putConstraint(SpringLayout.NORTH, welcomeLabel, 10, SpringLayout.NORTH, contentPane);
        contentPane.add(welcomeLabel);

        mainTabbedPane = new JTabbedPane();
        
        //POPULATE
        upcomingAppsModel.addColumn("Appointment ID");
        upcomingAppsModel.addColumn("Date");
        upcomingAppsModel.addColumn("Time");
        upcomingAppsModel.addColumn("Patient");

        completedAppsModel.addColumn("Appointment ID");
        completedAppsModel.addColumn("Date");
        completedAppsModel.addColumn("Time");
        completedAppsModel.addColumn("Patient");
        completedAppsModel.addColumn("Charge");

        upcomingAppsTable.setCellSelectionEnabled(false); //No selecting cells
        upcomingAppsTable.setRowSelectionAllowed(true); //Only rows
        upcomingAppsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); //1 row at a time
        upcomingAppsTable.setDefaultEditor(Object.class, null); //No editing

        completedAppsTable.setCellSelectionEnabled(false);
        completedAppsTable.setRowSelectionAllowed(true); 
        completedAppsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); 
        completedAppsTable.setDefaultEditor(Object.class, null); 

        //HOLD JTABLE IN SCROLLPANE
        completedScrollPane = new JScrollPane(completedAppsTable);
        upcomingScrollPane = new JScrollPane(upcomingAppsTable);
        upcomingScrollPane.setPreferredSize(new Dimension(500, 245));
        completedScrollPane.setPreferredSize(new Dimension(500, 245));
        completedScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        upcomingScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

        //ADD TABBBED PANES
        mainTabbedPane.add("Upcoming Appointments", upcomingScrollPane);
        mainTabbedPane.add("Completed Appointments", completedScrollPane);
        layOut.putConstraint(SpringLayout.WEST, mainTabbedPane, 5, SpringLayout.WEST, contentPane);
        layOut.putConstraint(SpringLayout.NORTH, mainTabbedPane, 40, SpringLayout.NORTH, contentPane);
        contentPane.add(mainTabbedPane);

        //CHECKOUT BUTTON
        checkOutButton = new JButton("Checkout");
        checkOutButton.addActionListener(this);
        setJButtonDefaults(checkOutButton);
        layOut.putConstraint(SpringLayout.WEST, checkOutButton, 515, SpringLayout.WEST, contentPane);
        layOut.putConstraint(SpringLayout.NORTH, checkOutButton, 60, SpringLayout.NORTH, contentPane);
        contentPane.add(checkOutButton);
        
        //APPEARS ONLY WHEN UPCOMING APPOINTMENT TAB IS SELECTED
        mainTabbedPane.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent evt) {
              if (mainTabbedPane.getSelectedIndex()==0){
                  checkOutButton.setVisible(true);
              }
              else if (mainTabbedPane.getSelectedIndex()==1){
                checkOutButton.setVisible(false);
              }
            }
        }); 

        //UPDATE PERSONAL INFORMATION BUTTON
        updatePersonalInfoButton = new JButton("Update Profile");
        updatePersonalInfoButton.addActionListener(this);
        setJButtonDefaults(updatePersonalInfoButton);
        layOut.putConstraint(SpringLayout.WEST, updatePersonalInfoButton, 515, SpringLayout.WEST, contentPane);
        layOut.putConstraint(SpringLayout.NORTH, updatePersonalInfoButton, 245, SpringLayout.NORTH, contentPane);
        contentPane.add(updatePersonalInfoButton);

        //SIGN OUT BUTTON
        signOutButton = new JButton("Sign out");
        signOutButton.addActionListener(this);
        setJButtonDefaults(signOutButton);
        layOut.putConstraint(SpringLayout.WEST, signOutButton, 515, SpringLayout.WEST, contentPane);
        layOut.putConstraint(SpringLayout.NORTH, signOutButton, 280, SpringLayout.NORTH, contentPane);
        contentPane.add(signOutButton);

        populateTables();
    }

    //EVENT LISTENER
    public void actionPerformed(ActionEvent actionEvent) {
        JButton clickedButton = (JButton) actionEvent.getSource();
        if (clickedButton == signOutButton){ 
            signOut(); 
        }
        else if (clickedButton == checkOutButton && !upcomingAppsTable.getSelectionModel().isSelectionEmpty()){
            for (Appointment app: APUMedicalCenter.appointmentsArrayList){
                if (app.getID().equals(upcomingAppsModel.getValueAt(upcomingAppsTable.getSelectedRow(),0))){
                    String costStr = JOptionPane.showInputDialog(this, "Patient Charge (RM)");
                    String comment = JOptionPane.showInputDialog(this, "Comment", app.getDoctorComment());
                    if (costStr!=null && comment!=null){
                        try{
                            int cost = Integer.valueOf(costStr);
                            if (cost > 0 && cost < 20000){
                                app.setCost(cost);
                                app.setDoctorComment(comment);
                                app.setIsComplete(true);
                                Notifier.checkoutSuccessful();
                            } else{
                                Notifier.checkoutChargeOutOfBounds();
                            }
                        } catch (Exception ex){
                            Notifier.invalidCheckout();
                        }
                    } else{
                        Notifier.checkoutUnchanged();
                    }  
                }
            }
            populateTables();
        }
        else if (clickedButton == updatePersonalInfoButton){
            String[] changeOptions = {"Phone Number", "Address"};
            try{
                String input = (String) JOptionPane.showInputDialog(null, "What information would you like to update?",
                                "Updating Personal Information", JOptionPane.QUESTION_MESSAGE, null, changeOptions,
                                changeOptions[0]);
                if (input!=null){
                    changePersonalInfo(input);
                }  
            } 
            catch (Exception ex) {
                System.out.println("Error:" + ex.getMessage());
            }
        }
    }

    public void populateTables(){
        upcomingAppsModel.setRowCount(0);
        completedAppsModel.setRowCount(0); //clears rows

        for (Appointment app: APUMedicalCenter.appointmentsArrayList){
            String docEmail = APUMedicalCenter.signedInDoctor.getEmailAddress();
            if (app.getDoctorEmail().equals(docEmail)){
                String ID   = app.getID();
                String date = app.getDate();
                String time = app.getTime();
                String patientName = app.getPatientName();
                String cost = String.valueOf(app.getCost());
                if (app.getIsComplete()){ 
                    completedAppsModel.insertRow(0,new Object[] {ID, date, time, patientName, "RM"+cost});
                } 
                else if (!app.getIsComplete()){ 
                    upcomingAppsModel.insertRow(0,new Object[] {ID, date, time, patientName}); 
                }
           }
        }
    }

    //Change doctor's personal information
    public void changePersonalInfo(String parameter){
        String newInfo;
        //Changing Phone Number
        if (parameter.equals("Phone Number")){
            newInfo = JOptionPane.showInputDialog("Updating "+ parameter, APUMedicalCenter.signedInDoctor.getTelephoneNumber());
            if (newInfo!=null){
                if (InputValidator.isTelephoneNumberValid(newInfo)){
                    try { 
                        APUMedicalCenter.signedInDoctor.setTelephoneNumber(newInfo);
                        Notifier.doctorChangeInfoSuccessful(parameter);
                    } 
                    catch (Exception ex) {
                        System.out.println("Error:" + ex.getMessage());
                    }
                } else{
                    Notifier.invalidTelephoneNumber(newInfo);
                }
            }
        } 
        //Changing Residence Address
        else if (parameter.equals("Address")){
            newInfo = JOptionPane.showInputDialog("Changing "+ parameter, APUMedicalCenter.signedInDoctor.getResidenceAddress());
            if (newInfo!=null){
                if (InputValidator.isResidenceAddressValid(newInfo)){
                    try { 
                        APUMedicalCenter.signedInDoctor.setResidenceAddress(newInfo);
                        Notifier.doctorChangeInfoSuccessful(parameter);
                    } 
                    catch (Exception ex) {
                        System.out.println("Error:" + ex.getMessage());
                    }
                } else{
                    Notifier.invalidResidenceAddress(newInfo);
                }
            }
        }
    }

    //Sign out Confirmation (Triggered via Button or Window Close)
    public void signOut() {
        try {
            int choice = JOptionPane.showConfirmDialog(null, "Are you sure you want to sign out?", "Sign Out", JOptionPane.YES_NO_OPTION);
            if(choice == JOptionPane.YES_OPTION) {
                APUMedicalCenter.signedInDoctor.signOut();
                this.dispose();
            } else if(choice == JOptionPane.NO_OPTION || choice == JOptionPane.CLOSED_OPTION) {}
        } catch(HeadlessException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Your Computer is Incompatible",
                                                JOptionPane.ERROR_MESSAGE);
        }
    }
}