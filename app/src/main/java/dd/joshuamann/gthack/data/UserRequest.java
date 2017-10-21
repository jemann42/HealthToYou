package dd.joshuamann.gthack.data;


import android.provider.ContactsContract;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DataSnapshot;

import dd.joshuamann.gthack.model.BasicUser;


/**
 * Created by Joshua Mann on 10/14/2017.
 */

public class UserRequest {
    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;
    public UserRequest() {
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();


    }
    public FirebaseAuth getAuth(){
        return mAuth;
    }
    public DatabaseReference getDatabaseReference(){
        return mDatabase;
    }
    public void createNewBasicUser(String userid, String firstname, String lastname, String email, String pic, String dob, String phone, String weight, String height, String history) {
        BasicUser bUser = new BasicUser(firstname, lastname, email, pic, dob, phone, weight, height, history);
        mDatabase.child("users").child(userid).setValue(bUser);
    }

    public void updateProfile (String userid){
        mDatabase.child("users").child(userid).setValue("some value");
    }

    public String usernameFromEmail(String email){
        if (email.contains("@")){
            return email.split("@")[0];
        }else{
            return email;
        }
    }

    public FirebaseUser checkCurrentUser()
    {
        return mAuth.getCurrentUser();
    }

}
