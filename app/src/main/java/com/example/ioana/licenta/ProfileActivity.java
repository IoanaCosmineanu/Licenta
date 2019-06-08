package com.example.ioana.licenta;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {


    private FirebaseAuth firebaseAuth;
    private TextView textViewUserEmail;
    private Button buttonLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        RelativeLayout relativeLayout =findViewById(R.id.layout);
        AnimationDrawable animationDrawable=(AnimationDrawable) relativeLayout.getBackground();
        animationDrawable.setEnterFadeDuration(2000);
        animationDrawable.setExitFadeDuration(4000);
        animationDrawable.start();


        firebaseAuth=FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser()==null){
            finish();
            startActivity(new Intent(this,AnotherLoginActivity.class));
        }

        FirebaseUser user=firebaseAuth.getCurrentUser();


        textViewUserEmail=(TextView)findViewById(R.id.textViewUserEmail);
        buttonLogout=(Button)findViewById(R.id.buttonLogout);

        textViewUserEmail.setText("Bine ati venit, "+" "+user.getEmail());

        buttonLogout.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
if(view==buttonLogout)
{
    firebaseAuth.signOut();
    finish();
    startActivity(new Intent(this,AnotherLoginActivity.class));
}
    }
}
