import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * The {@code InputValidator} class contains static patterns and methods to validate different types of input. 
 * @author Mozhar TP058272 
 * @author Samir TP050965
 */
final public class InputValidator {
    final public static Pattern VALID_NAME_REGEX                  = Pattern.compile("^[A-Za-z]{1,25}[ ][A-Za-z]{1,25}$|^[A-Za-z]{1,25}[ ][A-Za-z ]{1,25}$|^[A-Za-z]{1,25}$");
    final public static Pattern VALID_TELEPHONE_NUMBER_REGEX      = Pattern.compile("^0[1-9]{2}[\\d]{4}[\\d]{3,4}$|^0[1-9]{2}[-][\\d]{4}[-][\\d]{3,4}$");
    final public static Pattern VALID_RESIDENCE_ADDRESS_REGEX     = Pattern.compile("^[-A-Za-z,]{2,50}[ ][-A-Za-z\\d, ]{2,50}$");
    final public static Pattern VALID_STAFF_EMAIL_ADDRESS_REGEX   = Pattern.compile("^[A-Za-z]{1,30}[A-Za-z\\d\\.]{0,35}@staffemail.apu.edu.my$");
    final public static Pattern VALID_PATIENT_EMAIL_ADDRESS_REGEX = Pattern.compile("^tp[\\d]{6}@mail.apu.edu.my$");
    final public static Pattern VALID_SIGN_IN_PASSWORD            = Pattern.compile("(?=.*[\\d])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?!.*\\s).{6,25}");
    final public static Pattern VALID_NUMBER_REGEX                = Pattern.compile("^[0-9]*$");
    
    /**
     * Don't let anyone instantiate this class.
     */
    private InputValidator() {} 
    
    final public static boolean isNameValid(String name) {
        Matcher nameMatcher = VALID_NAME_REGEX.matcher(name);
        return(nameMatcher.find()); 
    }

    final public static boolean isTelephoneNumberValid(String telephoneNumber) {
        Matcher telephoneNumberMatcher = VALID_TELEPHONE_NUMBER_REGEX.matcher(telephoneNumber);
        return(telephoneNumberMatcher.find());
    }

    final public static boolean isResidenceAddressValid(String residenceAddress) {
        Matcher residenceAddressMatcher = VALID_RESIDENCE_ADDRESS_REGEX.matcher(residenceAddress);
        return(residenceAddressMatcher.find());
    }

    final public static boolean isStaffEmailAddressValid(String staffEmailAddress) {
        Matcher staffEmailAddressMatcher = VALID_STAFF_EMAIL_ADDRESS_REGEX.matcher(staffEmailAddress);
        return(staffEmailAddressMatcher.find());
    }

    final public static boolean isSignInPasswordValid(String signInPassword) {
        Matcher signInPasswordMatcher = VALID_SIGN_IN_PASSWORD.matcher(signInPassword);
        return(signInPasswordMatcher.find());
    }

    final public static boolean isPatientEmailAddressValid(String patientEmailAddress) {
        Matcher patientEmailAddressMatcher = VALID_PATIENT_EMAIL_ADDRESS_REGEX.matcher(patientEmailAddress);
        return(patientEmailAddressMatcher.find());
    }

    final public static boolean isDateValid(String date) {
        boolean validDateOfBirth = true; 
        LocalDate dateOfBirthLocalDate;
        try {
            dateOfBirthLocalDate = LocalDate.parse(date);
        } catch(DateTimeParseException exception) {
            validDateOfBirth = false;
        }
        return validDateOfBirth;
    }
    
    final public static boolean isStringParsableToInt(String line){
        Matcher stringAsIntMatcher = VALID_NUMBER_REGEX.matcher(line);
        return(stringAsIntMatcher.find());
    }
}