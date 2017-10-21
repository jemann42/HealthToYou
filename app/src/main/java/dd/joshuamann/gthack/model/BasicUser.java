package dd.joshuamann.gthack.model;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by Joshua Mann on 10/14/2017.
 */
@IgnoreExtraProperties
public class BasicUser {
    public String firstname;
    public String lastname;
    public String email;

    public String pic;
    public String dob;
    public String phone;
    public String weight;
    public String height;
    public String history;

    public BasicUser() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public BasicUser(String firstname) {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
        this.firstname = firstname;
    }

    public BasicUser(String firstname, String lastname, String email, String pic, String dob, String phone, String weight, String height, String history) {
        this.firstname = firstname;
        this.email = email;
        this.lastname = lastname;
        this.pic = pic;
        this.dob = dob;
        this.phone = phone;
        this.weight = weight;
        this.height = height;
        this.history = history;
    }

    public String getFirstname() {
        return firstname;
    }
}

/*
public String status;
public String category;
public String category_details;
public Location location; // Pass Location Object
*/




