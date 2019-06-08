package com.example.ioana.licenta;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RetrieveActivity extends AppCompatActivity {
            //declarare variabile

    EditText ET_Titlu,ET_NrIngr,ET_Ingred,ET_Preparare;
    String numereteta,nr,elemente,instructiuni;
    Button desert, fel, supe;
    int ok=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrieve);


       // ET_Titlu = (EditText) findViewById(R.id.editText1);
       // ET_NrIngr = (EditText) findViewById(R.id.editText2);
        // ET_Ingred = (EditText) findViewById(R.id.editText3);
       // ET_Preparare = (EditText) findViewById(R.id.editText4);
        desert = (Button) findViewById(R.id.button2);
        fel = (Button) findViewById(R.id.button3);
        supe = (Button) findViewById(R.id.button4);

        desert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ok=1;
                Toast.makeText(RetrieveActivity.this, "S-a selectat categoria Desert", Toast.LENGTH_SHORT).show();
            }
        });

        fel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ok=2;
                Toast.makeText(RetrieveActivity.this, "S-a selectat categoria Fel Principal", Toast.LENGTH_SHORT).show();
            }
        });

        supe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ok=3;
                Toast.makeText(RetrieveActivity.this, "S-a selectat categoria Supe", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void retrievedata(View view) {

        numereteta=ET_Titlu.getText().toString();
        nr=ET_NrIngr.getText().toString();
        elemente=ET_Ingred.getText().toString();
        instructiuni=ET_Preparare.getText().toString();
        String method="retrieve";



    }
}
