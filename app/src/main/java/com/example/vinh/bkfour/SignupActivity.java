package com.example.vinh.bkfour;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

/**
 * Created by HoDucDan_51200482 on 3/26/2016.
 */
public class SignupActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        final EditText name=(EditText)findViewById(R.id.signup_name);
        final EditText pass=(EditText)findViewById(R.id.signup_pass);

        Button btn=(Button)findViewById(R.id.signup_btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ParseUser user = new ParseUser();
                user.setUsername(name.getText().toString());
                user.setPassword(pass.getText().toString());

                user.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {
                        Toast.makeText(getApplicationContext(), "done", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
