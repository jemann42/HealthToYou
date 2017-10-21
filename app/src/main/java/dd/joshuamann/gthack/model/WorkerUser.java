package dd.joshuamann.gthack.model;

/**
 * Created by Joshua Mann on 10/14/2017.
 */

public class WorkerUser extends BasicUser {
    public String specialty;
    public String backgroundCheck;
    public String company;

    public WorkerUser() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
        super();
    }

    public WorkerUser(String firstname, String lastname, String email, String password, String pic, String dob, String phone, String weight, String height, String history, String specialty, String backgroundCheck, String company) {
      //  super(firstname, lastname, email, password, pic, dob, phone, weight, height, history);
        super();
        this.specialty = specialty;
        this.backgroundCheck = backgroundCheck;
        this.company = company;
    }
}
