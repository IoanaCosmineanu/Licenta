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
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class AnotherLoginActivity extends AppCompatActivity implements View.OnClickListener{

   private Button buttonSignIn;
   private EditText editTextEmail;
   private EditText editTextPassword;
   private TextView textViewSignup;

   private String _email = "";
   private FirebaseAuth firebaseAuth;
   private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_another_login);

        RelativeLayout relativeLayout =findViewById(R.id.layout);
        AnimationDrawable animationDrawable=(AnimationDrawable) relativeLayout.getBackground();
        animationDrawable.setEnterFadeDuration(2000);
        animationDrawable.setExitFadeDuration(4000);
        animationDrawable.start();


        firebaseAuth=FirebaseAuth.getInstance();

//if(firebaseAuth.getCurrentUser() !=null){
 //   finish();
 //   startActivity(new Intent(getApplicationContext(),ProfileActivity.class));
    // profile activity here
    
//}

        editTextEmail=(EditText)findViewById(R.id.editTextEmail);
        editTextPassword=(EditText)findViewById(R.id.editTextPassword);
        buttonSignIn=(Button)findViewById(R.id.buttonSignin);
        textViewSignup=(TextView)findViewById(R.id.textViewSignUp);

        progressDialog=new ProgressDialog(this);

        buttonSignIn.setOnClickListener(this);


        textViewSignup.setOnClickListener(this);

    }
    private void userLogin(){
        String email=editTextEmail.getText().toString().trim();
        String password=editTextPassword.getText().toString().trim();

        if (TextUtils.isEmpty(email)) {
            //email is empty
            Toast.makeText(this, "Introduceti adresa email", Toast.LENGTH_SHORT).show();
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
        //we will first show a progressbar
        progressDialog.setMessage("Autentificare utilizator...");
        progressDialog.show();
        _email = email;
        firebaseAuth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
            progressDialog.dismiss();
            if(task.isSuccessful())
            {

                //start the profile activity
                finish();
                Intent intent = new Intent(getApplicationContext(), MeniuActivity.class);
                intent.putExtra("UtilizatorCurent", _email);
                startActivity(intent);
                //startActivity(new Intent(getApplicationContext(),MeniuActivity.class));
            }
                    }
                });
    }

    @Override
    public void onClick(View view) {
        if(view==buttonSignIn){
            userLogin();
        }
        if(view==textViewSignup){
            finish();
           startActivity(new Intent(this,LoginActivity.class));
        }
    }
}
