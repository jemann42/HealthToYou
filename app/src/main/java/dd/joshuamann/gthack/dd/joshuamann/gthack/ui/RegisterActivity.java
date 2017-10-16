package dd.joshuamann.gthack.dd.joshuamann.gthack.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
//import android.view.View;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

//import com.google.firebase.quickstart.database.models.User;
import dd.joshuamann.gthack.R;
import dd.joshuamann.gthack.data.UserRequest;
//import android.widget.ImageView;

public class RegisterActivity extends BaseActivity implements View.OnClickListener {
    private EditText etUsername;
    private EditText etEmail;
    private Button bRegister;
    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;
    private UserRequest uRequest = new UserRequest();
    private EditText etWeight;
    private EditText etHeight ;
    private EditText etFirstName;
    private EditText etHistory;
    private EditText etPhone;
    private EditText etLastName;
    private EditText etPassword;
    private static final String TAG = "RegisterActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);


        mDatabase = uRequest.getDatabaseReference();
        mAuth = uRequest.getAuth();

      //  etUsername= findViewById(R.id.etUsername);
        etEmail= findViewById(R.id.etEmail);
        
         etFirstName= findViewById(R.id.etFirstName);
         etLastName= findViewById(R.id.etLastName);
          etPhone= findViewById(R.id.etPhone);
          etHistory= findViewById(R.id.etHistory);
          etHeight= findViewById(R.id.etHeight);
          etWeight= findViewById(R.id.etWeight);
        bRegister = findViewById(R.id.bRegister);
        etPassword = findViewById(R.id.etPassword);
        bRegister.setOnClickListener(this);



        /*bRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent loginIntent = new Intent(register_activity.this, sos_activity.class);
                register_activity.this.startActivity(loginIntent);
            }
        });
*/

    }
    @Override
    public void onStart() {
        super.onStart();
        if (mAuth.getCurrentUser()!= null) {
            onAuthSuccess(mAuth.getCurrentUser());
        }
    }

    private void signUp() {
        Log.d(TAG, "signUp");
        if (!validateForm()) {
            return;
        }

        showProgressDialog();
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "createUser:onComplete:" + task.isSuccessful());
                        hideProgressDialog();

                        if (task.isSuccessful()) {
                            onAuthSuccess(task.getResult().getUser());
                        } else {
                            Toast.makeText(RegisterActivity.this, "Sign Up Failed",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
    private void onAuthSuccess(FirebaseUser currentUser) {
        String firstname,  lastname, pic, dob, phone, weight,  height, history;
        firstname = etFirstName.getText().toString();
        lastname = etLastName.getText().toString();
        pic= "";
        dob= "";
        phone = etPhone.getText().toString();
        weight= etWeight.getText().toString();
        height = etHeight.getText().toString();
        history = etHistory.getText().toString();

        String email = uRequest.usernameFromEmail(currentUser.getEmail());
        uRequest.createNewBasicUser(currentUser.getUid(),firstname, lastname, email, pic, dob, phone, weight, height,history);

    }

    private boolean validateForm() {
        boolean result = true;
        if (TextUtils.isEmpty(etEmail.getText().toString())) {
            etEmail.setError("Required");
            result = false;
        } else {
            etEmail.setError(null);
        }

        if (TextUtils.isEmpty(etPassword.getText().toString())) {
            etPassword.setError("Required");
            result = false;
        } else {
            etPassword.setError(null);
        }

        return result;
    }
    @Override
    public void onClick(View view) {
        //int i = view.getId();
        signUp();
        startActivity(new Intent(RegisterActivity.this, WelcomeActivity.class));
    }
}