import java.time.LocalDate;
import java.time.Period;
/**
 * The {@code EndUser} abstract class that is extended by end user objects; it defines common
 * data members and methods.
 * 
 * @author Mozhar TP058272 
 * @author Samir TP050965
 */

public abstract class EndUser {
    /** The attribute that holds the first and last name of the end user */
    private String firstAndLastName;

    /** The attribute that holds the gender of the end user */ 
    private String gender;
    
    /** The attribute that holds the date of birth of the end user*/
    private String dateOfBirth;
    
    /** The attribute that holds the telephone number of the end user */
    private String telephoneNumber;

    /** The attribute that will hold the residence address of the end user */
    private String residenceAddress;
    
    /** The attribute the holds the email of the end user */
    private String emailAddress;
    
    /** The attribute that will hold the login password of the end user */
    private String signInPassword;

    //#region Getters and Setters
    final public void setFirstAndLastName(String firstAndLastName) {
        this.firstAndLastName = firstAndLastName;
    }

    final public String getFirstAndLastName() {
        return firstAndLastName;
    }

    final public void setGender(String gender) {
        this.gender = gender;
    }

    final public String getGender() {
        return gender;
    }

    final public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    final public String getDateOfBirth() {
        return dateOfBirth;
    }

    final public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    final public String getTelephoneNumber() {
        return telephoneNumber;
    }

    final public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    final public String getEmailAddress() {
        return emailAddress;
    }

    final public void setResidenceAddress(String residenceAddress) {
        this.residenceAddress = residenceAddress;
    }

    final public String getResidenceAddress() {
        return residenceAddress;
    }

    final public void setSignInPassword(String signInPassword) {
        this.signInPassword = signInPassword;
    }

    final public String getSignInPassword() {
        return signInPassword;
    }
    //#endregion

    final public int getAgeInYears() {
        LocalDate currentDate = LocalDate.now();
        LocalDate newDateOfBirth = LocalDate.parse(dateOfBirth);
        return Period.between(newDateOfBirth, currentDate).getYears();
    }
    
    public abstract void signOut();
}