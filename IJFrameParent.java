import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;
/**
 * The {@code IJFrameParent} interface defines constants to be used by a JFrame that implements it
 * and default methods that set components to default values.
 * @author Mozhar TP058272 
 * @author Samir TP050965
 */

public interface IJFrameParent {
    final int       DEFAULT_JFRAME_WIDTH      = 430;
    final int       DEFAULT_JFRAME_HEIGHT     = 400;
    final int       DEFAULT_JBUTTON_WIDTH     = 100;
    final int       DEFAULT_JBUTTON_HEIGHT    = 40;
    final Font      DEFAULT_FONT              = new Font("Times New Roman", Font.PLAIN, 17);
    final Border    DEFAULT_BORDER            = BorderFactory.createLineBorder(Color.BLACK, 1);
    final ImageIcon APULOGOICON               = new ImageIcon("apuLogo.png");
    final ImageIcon apuLogoImageBuffer        = new ImageIcon("logo.png");
    final Image     apuLogoImageBuffer2       = apuLogoImageBuffer.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
    final ImageIcon APULOGOIMAGE              = new ImageIcon(apuLogoImageBuffer2);
    final String    EMPTY_STRING              = "";
    
    public default void setJLabelDefaults(JLabel jLabel) {
        jLabel.setFont(DEFAULT_FONT);
        jLabel.setFocusable(false);
    }
    default void setJButtonDefaults(JButton jButton) {
        jButton.setFont(DEFAULT_FONT);
        jButton.setFocusable(false);
    }
    default void setJTextFieldDefaults(JTextField jTextField) {
        jTextField.setFont(DEFAULT_FONT);
        jTextField.setBorder(DEFAULT_BORDER);
    }
}