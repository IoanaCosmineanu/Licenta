package com.example.ioana.licenta;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private Button buttonSignup;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private TextView textViewSignin;

    private ProgressDialog progressDialog;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        RelativeLayout relativeLayout =findViewById(R.id.layout);
        AnimationDrawable animationDrawable=(AnimationDrawable) relativeLayout.getBackground();
        animationDrawable.setEnterFadeDuration(2000);
        animationDrawable.setExitFadeDuration(4000);
        animationDrawable.start();

        firebaseAuth = FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser() !=null){
            finish();
            startActivity(new Intent(getApplicationContext(),ProfileActivity.class));
            // profile activity here

        }

        progressDialog = new ProgressDialog(this);

        buttonSignup = (Button) findViewById(R.id.buttonSignup);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        textViewSignin = (TextView) findViewById(R.id.textViewSignin);


        buttonSignup.setOnClickListener(this);
        textViewSignin.setOnClickListener(this);

    }

    private void registerUser() {
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if (TextUtils.isEmpty(email)) {
            //email is empty
            Toast.makeText(this, "Introduceti adresa de mail", Toast.LENGTH_SHORT).show();
            //stopping the function execution further
            return;
        }

        if (TextUtils.isEmpty(password)) {
            //password is empty
            Toast.makeText(this, "Introduceti parola", Toast.LENGTH_SHORT).show();
            //stopping the function execution further
            return;
        }
        //if validations are ok
        //we will first show a progress dialog

        progressDialog.setMessage("Inregistrare utilizator...");
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<com.google.firebase.auth.AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<com.google.firebase.auth.AuthResult> task) {
if(task.isSuccessful()){
    //user is successfully registered and logged in
    //we will start the profile activity here
    //right now lets display a toast only
        finish();
        startActivity(new Intent(getApplicationContext(),ProfileActivity.class));
        // profile activity here
    }
   // Toast.makeText(LoginActivity.this,"Registered Successfully",Toast.LENGTH_SHORT).show();
else
{
    Toast.makeText(LoginActivity.this,"Nu s-a putut inregistra, incercati din nou",Toast.LENGTH_SHORT).show();
}
                    }
                });
    }
        @Override
        public void onClick (View view){
            if (view == buttonSignup) {
                registerUser();
            }
            if (view == textViewSignin) {
                //will open login activity here
                startActivity(new Intent(this,AnotherLoginActivity.class));
            }


        }
    }

