package dd.joshuamann.health_to_you_application;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class register_activity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);

        final EditText etUsername= (EditText) findViewById(R.id.etUsername);
        final EditText etEmail= (EditText) findViewById(R.id.etEmail);
        final EditText etPassword= (EditText) findViewById(R.id.etPassword);
        final EditText etPhone= (EditText) findViewById(R.id.etPhone);
        final EditText etHistory= (EditText) findViewById(R.id.etHistory);
        final EditText etHeight= (EditText) findViewById(R.id.etHeight);
        final EditText etWeight= (EditText) findViewById(R.id.etWeight);
        final Button bRegister = (Button) findViewById(R.id.bRegister);
/*
        bRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loginIntent = new Intent(register_activity.this, sos_activity.class);
                register_activity.this.startActivity(loginIntent);
            }
        });
        */
    }
}