package com.example.ioana.licenta;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class FeedbackDetails extends AppCompatActivity {
    TextView TVF;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback_details);
        TVF=(TextView)findViewById(R.id.tvf);


        Bundle bundle = getIntent().getExtras();
        TVF.setText(bundle.getString("extrasfeed"));
    }
}
