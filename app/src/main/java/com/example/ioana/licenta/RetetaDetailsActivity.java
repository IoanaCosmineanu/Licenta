package com.example.ioana.licenta;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class RetetaDetailsActivity extends AppCompatActivity {
    TextView TV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reteta_details);
        TV=(TextView)findViewById(R.id.tv);


        Bundle bundle = getIntent().getExtras();
    TV.setText(bundle.getString("extras"));

    }
}
