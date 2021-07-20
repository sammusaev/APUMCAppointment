import javax.swing.JOptionPane;
/**
 * The {@code Notifier} final class contains only static methods that use {@code JOptionPane.showMessageDialog} 
 * to display messages to the output, including the validity or invalidity of input, in addition
 * commonly used general-purpose messages.
 * <p> The {@code Notifier} does not allow instances initializations. </p>
 * @author Mozhar TP058272
 * @author Samir  TP050965
 */
final public class Notifier {
    
    /**
     * Don't let anyone instantiate this class.
     */
    private Notifier() {}

    final public static void emptyInput() {
        JOptionPane.showMessageDialog(null, "Empty fields are not allowed!\n" +
                                            "Please fill out all of the fields.",
                                            "Empty Fields", 
                                            JOptionPane.WARNING_MESSAGE);
    }

    final public static void duplicateEmailAddress(String emailAddress) {
        JOptionPane.showMessageDialog(null, "The email address \"" + emailAddress + 
                                            "\" you have entered is already used.\n" +
                                            "Please use a unique email address.",
                                            "Duplicate Email Address",
                                            JOptionPane.WARNING_MESSAGE);
    }

    final public static void invalidName(String name) {
        JOptionPane.showMessageDialog(null, "The name \"" + name + 
                                            "\" you have entered is invalid.\n" +
                                            "Please enter your real name.\n" +
                                            "Examples of valid names:\nMuhammad,\nMuhammad Jamal," +
                                            " or\nMuhammad Ahmad Jamal.",
                                            "Invalid Name", 
                                            JOptionPane.WARNING_MESSAGE);
    }

    final public static void invalidDateOfBirth(String dateOfBirth) {
        JOptionPane.showMessageDialog(null, "The date of birth \"" + dateOfBirth + 
                                            "\" you have provided is invalid.\n" +
                                            "Please provide a valid and genuine date of birth.", 
                                            "Invalid Date of Birth",
                                            JOptionPane.WARNING_MESSAGE);
    }

    final public static void invalidDate(String date) {
        JOptionPane.showMessageDialog(null, "The date \"" + date + 
                                            "\" you have provided is invalid.\n" +
                                            "Please provide a valid date.", 
                                            "Invalid Date",
                                            JOptionPane.WARNING_MESSAGE);
    }

    final public static void invalidTelephoneNumber(String telephoneNumber) {
        JOptionPane.showMessageDialog(null, "The telephone number \"" + telephoneNumber + 
                                            "\" you have entered is invalid.\n" + 
                                            "Please use one of the following formats:\n" +
                                            "0XX-XXXX-XXX (10 digits),\n0XX-XXXX-XXXX (11 digits),\n0XXXXXXXXX (10 digits)," +
                                            " or\n0XXXXXXXXXX (11 digits), where X is a digit.",
                                            "Invalid Telephone Number", 
                                            JOptionPane.WARNING_MESSAGE);
    }

    final public static void invalidResidenceAddress(String residenceAddress) {
        JOptionPane.showMessageDialog(null, "The residence address \"" + residenceAddress +
                                            "\" you have entered is invalid.\n" +
                                            "Please enter a valid residence address.",
                                            "Invalid Residence Address", 
                                            JOptionPane.WARNING_MESSAGE);
    }

    final public static void invalidSignInPassword(String signInPassword) {
        JOptionPane.showMessageDialog(null, "The sign in password \"" + signInPassword + 
                                            "\" you have entered is weak.\n Please follow this guideline:\n" +
                                            "The password must be between 6 and 25 characters and" + 
                                            " must contain at least one of the followings:\nA digit (0 to 9)\n"+
                                            "A lower case letter\nAn upper case letter, and\n" +
                                            "A special character from this list: @ # $ % ^ & + =\n" +
                                            "(No white space characters are allowed).",
                                            "Weak Sign In Password", 
                                            JOptionPane.WARNING_MESSAGE);
    }

    final public static void invalidStaffEmailAddress(String staffEmailAddress) {
        JOptionPane.showMessageDialog(null, "The email address \"" + staffEmailAddress +
                                            "\" you have entered is invalid.\n" +
                                            "The email address must be a valid APU staff email address.",
                                            "Invalid APU Staff Email Address", 
                                            JOptionPane.WARNING_MESSAGE);
    }
    final public static void staffEmailAddressNotFound(String staffEmailAddress) {
        JOptionPane.showMessageDialog(null, "The email address \"" + staffEmailAddress +
                                            "\" you have entered does not exist.\n" +
                                            "The email address must be a valid APU staff email address.",
                                            "Invalid APU Staff Email Address", 
                                            JOptionPane.WARNING_MESSAGE);
    }
    final public static void invalidPatientEmailAddress(String patientEmailAddress) {
        JOptionPane.showMessageDialog(null, "The email address \"" + patientEmailAddress +
                                            "\" you have entered is invalid.\n" +
                                            "The email address must be a valid APU student email address.", 
                                            "Invalid APU Student Email Address", 
                                            JOptionPane.WARNING_MESSAGE);
    }
    final public static void invalidAppointmentDate(String appointmentDate) {
        JOptionPane.showMessageDialog(null, "The date \"" + appointmentDate + 
                                            "\" you have provided for the appointment is invalid.\n" +
                                            "Please provide a valid and genuine date.", 
                                            "Invalid Date",
                                            JOptionPane.WARNING_MESSAGE);
    }
    final public static void duplicateAppointmentDate(String doctorName, String appointmentDate, String appointmentTime) {
        JOptionPane.showMessageDialog(null, "Dr." + doctorName + " already has an appointment at \n"+
                                            appointmentTime + " on " + appointmentDate,
                                            appointmentTime, JOptionPane.WARNING_MESSAGE);
    }
    
    public static void doctorDeleteSuccessful() {
        JOptionPane.showMessageDialog(null, "The doctor has been has been successfully deleted.",
                                            "Doctor Deleted", JOptionPane.INFORMATION_MESSAGE);
	}
    final public static void doctorDeleteSuccessful(String name, String emailAddress) {
        JOptionPane.showMessageDialog(null, "The doctor \"" + name +
                                            "\" with email address of \"" + emailAddress +
                                            "\" has been successfully deleted.",
                                            "Doctor Deleted", 
                                            JOptionPane.INFORMATION_MESSAGE);
    }
    final public static void patientDeleteSuccessful() {
        JOptionPane.showMessageDialog(null, "The patient has been has been successfully deleted.",
                                            "Patient Deleted", 
                                            JOptionPane.INFORMATION_MESSAGE);
    }
    final public static void patientDeleteSuccessful(String name, String emailAddress) {
        JOptionPane.showMessageDialog(null, "The patient \"" + name +
                                            "\" with email address of \"" + emailAddress +
                                            "\" has been successfully deleted.",
                                            "Patient Deleted", 
                                            JOptionPane.INFORMATION_MESSAGE);
    }
    final public static void staffRegisterSuccessful(String role, String name, String emailAddress, String signInPassword){
        JOptionPane.showMessageDialog(null, "The " + role + " has been successfully registered.\n" +
                                            "Name: " + name +"\nEmail address: " +
                                            emailAddress + "\nSign in password: " + signInPassword, 
                                            "Registration is Successful", 
                                            JOptionPane.INFORMATION_MESSAGE);
    }
    final public static void patientRegisterSuccessful() {
        JOptionPane.showMessageDialog(null, "The patient has been successfully registered.\n",
                                            "Registration is Successful", 
                                            JOptionPane.INFORMATION_MESSAGE);
    }

    final public static void patientRegisterSuccessful(String name, String emailAddress) {
        JOptionPane.showMessageDialog(null, "The patient has been successfully registered.\n" +
                                            "Name: " + name +"\nEmail Address: " + emailAddress,
                                            "Registration is Successful", 
                                            JOptionPane.INFORMATION_MESSAGE);
    }

    final public static void doctorEmailAddressNotFound(String emailAddress) {
        JOptionPane.showMessageDialog(null, "The doctor email address \"" + emailAddress +
                                            "\" does not exist.", "Doctor Email Address not Found",
                                            JOptionPane.WARNING_MESSAGE);
    }
    
    final public static void patientEmailAddressNotFound(String emailAddress) {
        JOptionPane.showMessageDialog(null, "The patient email address \"" + emailAddress +
                                            "\" does not exist.", "Patient Email Address not Found",
                                            JOptionPane.WARNING_MESSAGE);
    }
    
    final public static void staffRegisterUnSuccessful() {
        JOptionPane.showMessageDialog(null, "An error occurred while trying to add the admin.",
                                            "Admin can not be Addedd", 
                                            JOptionPane.ERROR_MESSAGE);
	}
    final public static void duplicateSignIn(String identity) {
        JOptionPane.showMessageDialog(null, "An existing " + identity.toLowerCase() + 
                                            " has already signed in using this device.\n" +
                                            "Please use another device to sign in.",
                                            identity + " Already Signed In",
                                            JOptionPane.WARNING_MESSAGE);
    }
    final public static void invalidCredentials() {
        JOptionPane.showMessageDialog(null, "The credentials you have entered are invalid!",
                                            "Invalid Credentials", 
                                            JOptionPane.WARNING_MESSAGE);
    }
    final public static void noManualRegistrationForDoctor() {
        JOptionPane.showMessageDialog(null, "If you are a new medical doctor and want to register your details in the Medical Center," +
                                            " please know that you can't do it yourself.\nContact the admin staff and" +
                                            " for registration enquires.", "No Manual Medical Doctor Registration",
                                            JOptionPane.INFORMATION_MESSAGE);
    }
	final public static void noPatientDirectEdit() {
        JOptionPane.showMessageDialog(null, "Please note that patients records can not be edited directly.\n" +
                                            "To edit a patient, first delete their record and create a new one with" +
                                            " the updated details.\nThis is done to prevent details to be edited without being validated and avoid corruption of the data.",
                                            "No Direct Editing", 
                                            JOptionPane.WARNING_MESSAGE);
	}
    final public static void noDoctorDirectEdit() {
        JOptionPane.showMessageDialog(null, "Please note that you can not edit doctors details.\n" +
                                            "Only the doctors via their profiles can edit their details.",
                                            "No Direct Editing", 
                                            JOptionPane.WARNING_MESSAGE);
	}
	
	public static void noAppointmentDirectEdit() {
        JOptionPane.showMessageDialog(null, "Please note that you can not edit an appointment details.\n" +
                                            "To update an appointment, first delete it and then create a new one" +
                                            " with the updated details.\nThis is done to prevent details to be edited without being validated and avoid corruption of the data.",
                                            "No Direct Editing", 
                                            JOptionPane.WARNING_MESSAGE);
	}
	public static void appointmentDeleteSuccessful() {
        JOptionPane.showMessageDialog(null, "The appointment has been has been successfully deleted.",
                                            "Appointment Deleted", JOptionPane.INFORMATION_MESSAGE);
	}
	public static void appointmentIDNotFound(String appointmentToDeleteID) {
        JOptionPane.showMessageDialog(null, "The appointment ID \"" + appointmentToDeleteID + "\" does not exist.",
                                            "Appointment ID Not Found", JOptionPane.INFORMATION_MESSAGE);
	}

    public static void doctorChangeInfoSuccessful(String parameter){
        JOptionPane.showMessageDialog(null, "Successfully changed " + parameter,
                                            "Data Updated", JOptionPane.INFORMATION_MESSAGE);
    }
    public static void invalidCheckout(){
        JOptionPane.showMessageDialog(null, "Invalid cost/comment. Try again",
                                            "Checkout failed", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void checkoutSuccessful(){
        JOptionPane.showMessageDialog(null, "Appointment updated",
                                            "Checkout successful", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void checkoutUnchanged(){
        JOptionPane.showMessageDialog(null, "Appointment unchanged",
                                            "Checkout unsuccessful", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void checkoutChargeOutOfBounds(){
        JOptionPane.showMessageDialog(null, "Patient charge must be between 50-20000" ,
                                            "Invalid charge", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void invalidInt(){
        JOptionPane.showMessageDialog(null, "Invalid input. Please enter numeric digits only" ,
                                            "Invalid data type", JOptionPane.INFORMATION_MESSAGE);
    }
}