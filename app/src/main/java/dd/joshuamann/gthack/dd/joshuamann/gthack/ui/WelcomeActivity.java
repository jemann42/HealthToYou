package dd.joshuamann.gthack.dd.joshuamann.gthack.ui;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import dd.joshuamann.gthack.R;
import dd.joshuamann.gthack.data.UserRequest;
import dd.joshuamann.gthack.model.BasicUser;

public class WelcomeActivity extends AppCompatActivity{

    private Button bsignout;
    private FirebaseAuth.AuthStateListener authListener;
    private FirebaseAuth auth;
    private TextView welcome;
    private DatabaseReference ref;
    private  String uId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        welcome = (TextView) findViewById(R.id.etWelcome);



        //get firebase auth instance
        auth = FirebaseAuth.getInstance();

        //get current user
         FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
       uId = user.getUid();
        ref = FirebaseDatabase.getInstance().getReference();



        authListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user == null) {
                    // user auth state is changed - user is null
                    // launch login activity
                    startActivity(new Intent(WelcomeActivity.this, SignIn.class));
                    finish();
                }
            }
        };

        bsignout = (Button) findViewById(R.id.bsignout);

        bsignout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signOut();
            }
        });

       ValueEventListener postList = new ValueEventListener() {
            private DataSnapshot dataSnapshot;

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for(DataSnapshot postSnapshot: dataSnapshot.getChildren()){
                    BasicUser bUser = new BasicUser( );
                    bUser = postSnapshot.child(uId).getValue(BasicUser.class);
                    String name = bUser.getFirstname();
                    //BasicUser((postSnapshot.child(uId).child("firstname").getValue().toString());
                   // System.out.println(postSnapshot.child(uId).getValue(BasicUser.class).firstname.toString());
                    welcome.setText(bUser.firstname);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };}

  /*  @Override
    public void onStart()
    {
        ValueEventListener userListemer = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                BasicUser bUser = dataSnapshot.getValue(BasicUser.class);
                welcome.setText(bUser.firstname);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
    }*/
    //sign out method
    public void signOut() {
        auth.signOut();
        startActivity(new Intent(WelcomeActivity.this, SignIn.class));
    }
    @Override
    public void onStart() {
        super.onStart();
        auth.addAuthStateListener(authListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (authListener != null) {
            auth.removeAuthStateListener(authListener);
        }
    }
}
