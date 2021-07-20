import java.awt.Color;
import java.awt.Container;
import javax.swing.*;
/**
 * The {@code SignInTemplate} class is the blueprint for any login form, it declares and initializes components that
 * a login form requires. The {@code LoginTemplate} however does specify the SpringLayout constraints and thus any children class
 * requiring different constraints must override them explicitly.
 * @author Mozhar TP058272
 * @author Samir TP
 */
public class SignInTemplate extends JFrame implements IJFrameParent {
    public final JLabel banner1JLabel;
    public final JLabel emailJLabel, passwordJLabel;
    public final JTextField emailJTextField;
    public final JPasswordField passwordJPasswordField;
    public final JButton signInJButton, clearAllFieldsJButton;
 
    public SignInTemplate() {
        Container contentPane = getContentPane();
        SpringLayout layOut = new SpringLayout();
        contentPane.setLayout(layOut);
        contentPane.setBackground(new Color(0xFFFFFFF));
        setTitle("APU Medical Center");
        setIconImage(APULOGOICON.getImage());
        setSize(450, 180);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        banner1JLabel = new JLabel();
        setJLabelDefaults(banner1JLabel);
        layOut.putConstraint(SpringLayout.HORIZONTAL_CENTER, banner1JLabel, 0, SpringLayout.HORIZONTAL_CENTER, contentPane);
        contentPane.add(banner1JLabel);

        emailJLabel = new JLabel("Email Address:");
        setJLabelDefaults(emailJLabel);
        layOut.putConstraint(SpringLayout.WEST, emailJLabel, 8, SpringLayout.WEST, contentPane);
        layOut.putConstraint(SpringLayout.NORTH, emailJLabel, 30, SpringLayout.NORTH, contentPane);
        contentPane.add(emailJLabel);
        emailJTextField = new JTextField(24);
        setJTextFieldDefaults(emailJTextField);
        layOut.putConstraint(SpringLayout.WEST, emailJTextField, 5, SpringLayout.EAST, emailJLabel);
        layOut.putConstraint(SpringLayout.NORTH, emailJTextField, 30, SpringLayout.NORTH, contentPane);      
        contentPane.add(emailJTextField);

        passwordJLabel = new JLabel("Password:");
        setJLabelDefaults(passwordJLabel);
        layOut.putConstraint(SpringLayout.WEST, passwordJLabel, 8, SpringLayout.WEST, contentPane);
        layOut.putConstraint(SpringLayout.NORTH, passwordJLabel, 65, SpringLayout.NORTH, contentPane);
        contentPane.add(passwordJLabel);
        passwordJPasswordField = new JPasswordField(24);
        passwordJPasswordField.setFont(DEFAULT_FONT);
        passwordJPasswordField.setBorder(DEFAULT_BORDER);
        passwordJPasswordField.setEchoChar('*');
        layOut.putConstraint(SpringLayout.WEST, passwordJPasswordField, 38, SpringLayout.EAST, passwordJLabel);
        layOut.putConstraint(SpringLayout.NORTH, passwordJPasswordField, 65, SpringLayout.NORTH, contentPane);
        contentPane.add(passwordJPasswordField);

        signInJButton = new JButton("Sign in");
        setJButtonDefaults(signInJButton);
        layOut.putConstraint(SpringLayout.HORIZONTAL_CENTER, signInJButton, -60, SpringLayout.HORIZONTAL_CENTER,  contentPane);
        layOut.putConstraint(SpringLayout.NORTH, signInJButton, 100, SpringLayout.NORTH, contentPane);
        contentPane.add(signInJButton);

        clearAllFieldsJButton = new JButton("Clear all fields");
        setJButtonDefaults(clearAllFieldsJButton);
        layOut.putConstraint(SpringLayout.HORIZONTAL_CENTER, clearAllFieldsJButton, 80, SpringLayout.HORIZONTAL_CENTER, contentPane);
        layOut.putConstraint(SpringLayout.NORTH, clearAllFieldsJButton, 100, SpringLayout.NORTH, contentPane);
        contentPane.add(clearAllFieldsJButton);
    }
    
    public void ClearAllFields() {
        emailJTextField.setText(EMPTY_STRING);
        passwordJPasswordField.setText(EMPTY_STRING);
    }
}