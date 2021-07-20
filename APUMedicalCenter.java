import java.util.ArrayList;
final public class APUMedicalCenter {
    
    /**
     * Don't let anyone instantiate this class.
     */
    private APUMedicalCenter() {}

    private static WelcomeJFrame          welcomeWindow            = new WelcomeJFrame();
    public  static Admin                  signedInAdmin            = null;
    public  static Doctor                 signedInDoctor           = null;
    public  static ArrayList<Admin>       adminsArrayList          = new ArrayList<Admin>();
    public  static ArrayList<Doctor>      doctorsArrayList         = new ArrayList<Doctor>();
    public  static ArrayList<Doctor>      deletedDoctorsArrayList  = new ArrayList<Doctor>();
    public  static ArrayList<Patient>     patientsArrayList        = new ArrayList<Patient>();
    public  static ArrayList<Patient>     deletedPatientsArrayList = new ArrayList<Patient>();
    public  static ArrayList<Appointment> appointmentsArrayList    = new ArrayList<Appointment>();
    final public static void main(String[] args) {
        IO.ReadAdminsData();
        IO.ReadDoctorsData();
        IO.ReadDeletedDoctorsData();
        IO.ReadPatientsData();
        IO.ReadDeletedPatientsData();
        IO.ReadAppointmentsData();
        IO.ReadAppointmentNumber();
    }
}