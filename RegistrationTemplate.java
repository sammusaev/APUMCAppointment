import javax.swing.*;
import javax.swing.table.*;
import java.text.DateFormatSymbols;
import java.time.LocalDate;
import java.time.YearMonth;
import java.awt.*;
/**
 * The {@code Registration} abstract class is the blueprint for any registration form
 * @author Samir TP050965
 */

public abstract class RegistrationTemplate extends JFrame implements IJFrameParent {
    private static final long serialVersionUID = 1L;
    final private int JFRAME_WIDTH = 500;
    final private int JFRAME_HEIGHT = 420;
    public JButton registerJButton, editJButton, deleteJButton, clearJButton;
    public JLabel banner1JLabel, banner2JLabel, firstAndLastNameJLabel, dateOfBirthJLabel,
                  genderJLabel, telephoneNumberJLabel, residenceAddressJLabel,
                  emailAddressJLabel, loginPasswordJLabel, doctorSpecialtyJLabel;  
    final public JTextField firstAndLastNameJTextField, telephoneNumberJTextField, 
                            residenceAddressJTextField, emailAddressJTextField;
    final public JPasswordField loginPasswordJPasswordField; 
    final public ButtonGroup genderButtonGroup;
    final public JCheckBox maleJCheckBox, femaleJCheckBox;
    final public JComboBox<Integer> dayOfBirthJComboBox, yearOfBirthJComboBox;
    final public JComboBox<String> monthOfBirthJComboBox;
    final public Integer[] daysInNumber;
    final public String[]  monthsInLetter;
    final public Integer[] yearsInNumber;
    public String[] specialties;
    private LocalDate dateOfBirthLocalDate;
    public JComboBox<String> doctorSpecialtyJComboBox = new JComboBox<String>();
    Container contentPane;
    SpringLayout layOut;
    public DefaultTableModel defaultTableModel;
    public JTable jTable;
    
    public RegistrationTemplate() {
        contentPane = getContentPane();
        layOut = new SpringLayout();
        contentPane.setLayout(layOut);
        contentPane.setBackground(new Color(0xFFFFFFF));
        setTitle("APU Medical Center");
        setIconImage(APULOGOICON.getImage());
        setSize(JFRAME_WIDTH, JFRAME_HEIGHT);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        
        //#region GUI
        banner1JLabel = new JLabel();
        setJLabelDefaults(banner1JLabel);

        banner2JLabel = new JLabel("Please enter the details below");
        setJLabelDefaults(banner2JLabel);
        layOut.putConstraint(SpringLayout.HORIZONTAL_CENTER, banner2JLabel, 0, SpringLayout.HORIZONTAL_CENTER, contentPane);
        layOut.putConstraint(SpringLayout.NORTH, banner2JLabel, 30, SpringLayout.NORTH, contentPane);
        contentPane.add(banner2JLabel);

        firstAndLastNameJLabel = new JLabel("First and last name:");                                                           
        setJLabelDefaults(firstAndLastNameJLabel);
        layOut.putConstraint(SpringLayout.WEST,  firstAndLastNameJLabel, 10, SpringLayout.WEST, contentPane);
        layOut.putConstraint(SpringLayout.NORTH, firstAndLastNameJLabel, 60, SpringLayout.NORTH, contentPane);
        contentPane.add(firstAndLastNameJLabel);
        firstAndLastNameJTextField = new JTextField(25);
        setJTextFieldDefaults(firstAndLastNameJTextField);
        layOut.putConstraint(SpringLayout.WEST,  firstAndLastNameJTextField, 11, SpringLayout.EAST, firstAndLastNameJLabel);
        layOut.putConstraint(SpringLayout.NORTH, firstAndLastNameJTextField, 60, SpringLayout.NORTH, contentPane);
        contentPane.add(firstAndLastNameJTextField);
        
        genderJLabel = new JLabel("Gender:");
        setJLabelDefaults(genderJLabel);
        layOut.putConstraint(SpringLayout.WEST,  genderJLabel, 8, SpringLayout.WEST, contentPane);
        layOut.putConstraint(SpringLayout.NORTH, genderJLabel, 100, SpringLayout.NORTH, contentPane);
        contentPane.add(genderJLabel);
        
        genderButtonGroup = new ButtonGroup();
        maleJCheckBox = new JCheckBox("Male");
        maleJCheckBox.setFont(DEFAULT_FONT);
        maleJCheckBox.setFocusable(true);
        maleJCheckBox.setBackground(Color.white);
        layOut.putConstraint(SpringLayout.WEST,  maleJCheckBox, 90, SpringLayout.EAST, genderJLabel);
        layOut.putConstraint(SpringLayout.NORTH, maleJCheckBox, 100, SpringLayout.NORTH, contentPane);
        genderButtonGroup.add(maleJCheckBox);
        contentPane.add(maleJCheckBox);
        femaleJCheckBox = new JCheckBox("Female");
        femaleJCheckBox.setFont(DEFAULT_FONT);
        femaleJCheckBox.setFocusable(true);
        femaleJCheckBox.setBackground(Color.WHITE);
        genderButtonGroup.add(femaleJCheckBox);
        layOut.putConstraint(SpringLayout.WEST,  femaleJCheckBox, 100, SpringLayout.EAST, maleJCheckBox);
        layOut.putConstraint(SpringLayout.NORTH, femaleJCheckBox, 100, SpringLayout.NORTH, contentPane);
        contentPane.add(femaleJCheckBox);

        dateOfBirthJLabel = new JLabel("Date of birth:");
        setJLabelDefaults(dateOfBirthJLabel);
        layOut.putConstraint(SpringLayout.WEST,  dateOfBirthJLabel, 8, SpringLayout.WEST, contentPane);
        layOut.putConstraint(SpringLayout.NORTH, dateOfBirthJLabel, 140, SpringLayout.NORTH, contentPane);
        contentPane.add(dateOfBirthJLabel);
        
        daysInNumber = new Integer[31];
        yearsInNumber = new Integer[100];
        final int currentYear = YearMonth.now().getYear();
        for(int i = 0; i < 31; i++) { 
            daysInNumber[i] = i + 1; 
        }
        for(int i = 0; i < yearsInNumber.length; i++) {  
            yearsInNumber[i] = currentYear - i; 
        }
        monthsInLetter = new DateFormatSymbols().getMonths(); 
        dayOfBirthJComboBox  = new JComboBox<Integer>(daysInNumber);
        dayOfBirthJComboBox.setFocusable(true);
        dayOfBirthJComboBox.setFont(DEFAULT_FONT);
        dayOfBirthJComboBox.setBorder(DEFAULT_BORDER);
        layOut.putConstraint(SpringLayout.WEST,  dayOfBirthJComboBox, 55, SpringLayout.EAST, dateOfBirthJLabel);
        layOut.putConstraint(SpringLayout.NORTH, dayOfBirthJComboBox, 140, SpringLayout.NORTH, contentPane);
        contentPane.add(dayOfBirthJComboBox);
        
        monthOfBirthJComboBox = new JComboBox<String>();
        monthOfBirthJComboBox.setFocusable(true);
        monthOfBirthJComboBox.setFont(DEFAULT_FONT);
        monthOfBirthJComboBox.setBorder(DEFAULT_BORDER);
        for (int i = 0; i <= 11; i++) { monthOfBirthJComboBox.addItem(monthsInLetter[i]); }
        layOut.putConstraint(SpringLayout.WEST, monthOfBirthJComboBox, 25, SpringLayout.EAST, dayOfBirthJComboBox);
        layOut.putConstraint(SpringLayout.NORTH, monthOfBirthJComboBox, 140, SpringLayout.NORTH, contentPane);
        contentPane.add(monthOfBirthJComboBox);

        yearOfBirthJComboBox = new JComboBox<Integer>(yearsInNumber);
        yearOfBirthJComboBox.setFocusable(true);
        yearOfBirthJComboBox.setFont(DEFAULT_FONT);
        yearOfBirthJComboBox.setBorder(DEFAULT_BORDER);
        yearOfBirthJComboBox.setSelectedItem(yearsInNumber[31]);
        layOut.putConstraint(SpringLayout.WEST, yearOfBirthJComboBox, 25, SpringLayout.EAST, monthOfBirthJComboBox);
        layOut.putConstraint(SpringLayout.NORTH, yearOfBirthJComboBox, 140, SpringLayout.NORTH, contentPane);
        contentPane.add(yearOfBirthJComboBox);
        
        telephoneNumberJLabel = new JLabel("Telephone number:");
        telephoneNumberJLabel.setFont(DEFAULT_FONT);
        telephoneNumberJLabel.setFocusable(true);
        telephoneNumberJTextField = new JTextField(25);
        setJTextFieldDefaults(telephoneNumberJTextField);

        residenceAddressJLabel = new JLabel("Residence address:");
        setJLabelDefaults(residenceAddressJLabel);
        residenceAddressJTextField = new JTextField(25);
        setJTextFieldDefaults(residenceAddressJTextField);

        emailAddressJLabel = new JLabel("Email address:");
        setJLabelDefaults(emailAddressJLabel);
        emailAddressJTextField = new JTextField(25);
        setJTextFieldDefaults(emailAddressJTextField);
         
        loginPasswordJLabel = new JLabel("Sign in password:");
        setJLabelDefaults(loginPasswordJLabel);
        loginPasswordJPasswordField = new JPasswordField(25);
        loginPasswordJPasswordField.setFont(DEFAULT_FONT);
        loginPasswordJPasswordField.setBorder(DEFAULT_BORDER);
        loginPasswordJPasswordField.setEchoChar('*');

        registerJButton = new JButton("Register");
        setJButtonDefaults(registerJButton);
        
        editJButton = new JButton();
        setJButtonDefaults(editJButton);

        deleteJButton = new JButton();
        setJButtonDefaults(deleteJButton);

        clearJButton = new JButton("Clear all fields");
        setJButtonDefaults(clearJButton);
        //#endregion
    }
    final public void ClearAllFields() {
        JTextField[] JTextFields = { 
            firstAndLastNameJTextField, telephoneNumberJTextField, 
            residenceAddressJTextField, emailAddressJTextField
        };
        for(JTextField input : JTextFields) {
            input.setText(EMPTY_STRING);
        }
        dayOfBirthJComboBox. setSelectedItem(daysInNumber[0]);
        monthOfBirthJComboBox.setSelectedItem(monthsInLetter[0]);
        yearOfBirthJComboBox.setSelectedItem(yearsInNumber[31]);
        genderButtonGroup.clearSelection();
        loginPasswordJPasswordField.setText(EMPTY_STRING);
    }
}