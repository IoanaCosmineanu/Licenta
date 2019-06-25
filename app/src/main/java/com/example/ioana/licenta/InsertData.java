package com.example.ioana.licenta;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class InsertData extends AppCompatActivity {

  EditText ET_Titlu,ET_NrIngr,ET_Ingred,ET_Preparare,ET_Ingred1,ET_Ingred2;
  String numereteta,nr,elemente,instructiuni,ingredient1,ingredient2;

  Button desert, fel, supe;
  int ok=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_insert_data);
        ET_Titlu = (EditText) findViewById(R.id.et_numereteta);
        ET_NrIngr = (EditText) findViewById(R.id.et_nring);
        ET_Ingred = (EditText) findViewById(R.id.et_elem);
        ET_Preparare = (EditText) findViewById(R.id.et_instruct);
        ET_Ingred1=(EditText) findViewById(R.id.et_ingred1);
        ET_Ingred2=(EditText) findViewById(R.id.et_ingred2);

        desert = (Button) findViewById(R.id.button2);
        fel = (Button) findViewById(R.id.button3);
        supe = (Button) findViewById(R.id.button4);

        desert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              ok=1;
                Toast.makeText(InsertData.this, "S-a selectat categoria Desert", Toast.LENGTH_SHORT).show();
            }
       });

        fel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ok=2;
                Toast.makeText(InsertData.this, "S-a selectat categoria Fel Principal", Toast.LENGTH_SHORT).show();
            }
       });

       supe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               ok=3;
                Toast.makeText(InsertData.this, "S-a selectat categoria Supe", Toast.LENGTH_SHORT).show();
            }
        });


}
/*
    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch (view.getId()) {
            case R.id.desertradio:
                if (checked) {
                    ok = 1;
                    Toast.makeText(InsertData.this, "S-a selectat categoria Desert", Toast.LENGTH_SHORT).show();
                    break;
                }

            case R.id.felprincipalradio:
                if (checked) {
                    ok = 2;
                    Toast.makeText(InsertData.this, "S-a selectat categoria Fel Principal", Toast.LENGTH_SHORT).show();
                    break;
                }

            case R.id.superadio:
                if (checked) {
                    ok = 3;
                    Toast.makeText(InsertData.this, "S-a selectat categoria Supe", Toast.LENGTH_SHORT).show();
                    break;

                }
        }
    }
*/
    public class Backgroundtask extends AsyncTask<String,Void,String> {

        Context ctx;
        Backgroundtask(Context ctx){

            this.ctx=ctx;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {
            String insert_url="";
            if(ok==1)
            insert_url="https://licentaioana1.000webhostapp.com/insert_retete_desert.php";
            if(ok==2)
                insert_url="https://licentaioana1.000webhostapp.com/insert_retete_fel.php";
            if(ok==3)
                insert_url="https://licentaioana1.000webhostapp.com/insert_retete_supe.php";
            String method=params[0];
            if(method.equals("insert"))
            {
                String numereteta=params[1];
                String nr=params[2];
                String elemente=params[3];
                String instructiuni=params[4];
                String ingredient1=params[5];
                String ingredient2=params[6];

                try {
                    URL url=new URL(insert_url);
                    HttpURLConnection httpURLConnection=(HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    OutputStream OS = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(OS,"UTF-8"));

                    String data=URLEncoder.encode("numereteta","UTF-8")+"="+URLEncoder.encode(numereteta,"UTF-8")+"&"+
                            URLEncoder.encode("nr","UTF-8")+"="+URLEncoder.encode(nr,"UTF-8")+"&"+
                            URLEncoder.encode("elemente","UTF-8")+"="+URLEncoder.encode(elemente,"UTF-8")+"&"+
                            URLEncoder.encode("instructiuni","UTF-8")+"="+URLEncoder.encode(instructiuni,"UTF-8")+"&"+
                            URLEncoder.encode("ingredient1","UTF-8")+"="+URLEncoder.encode(ingredient1,"UTF-8")+"&"+
                            URLEncoder.encode("ingredient2","UTF-8")+"="+URLEncoder.encode(ingredient2,"UTF-8");


                    bufferedWriter.write(data);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    OS.close();
                    InputStream IS=httpURLConnection.getInputStream();
                    IS.close();
                    return "Inserarea s-a efectuat cu succes!";

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String result) {
            Toast.makeText(ctx,result,Toast.LENGTH_LONG).show();
        }

        //public void execute(String method, String numereteta, String editText2, String editText3, String editText4) {
        // }
    }


    public void insertdata(View view) {


        if(ok==0)
            Toast.makeText(InsertData.this, "Selecteaza categoria", Toast.LENGTH_SHORT).show();

        else {

            numereteta = ET_Titlu.getText().toString();
            nr = ET_NrIngr.getText().toString();
            elemente = ET_Ingred.getText().toString();
            instructiuni = ET_Preparare.getText().toString();
            ingredient1=ET_Ingred1.getText().toString();
            ingredient2=ET_Ingred2.getText().toString();
            String method = "insert";
            Backgroundtask backgroundtask = new Backgroundtask(this);
            backgroundtask.execute(method, numereteta, nr, elemente, instructiuni,ingredient1,ingredient2);
            finish();
        }
    }
}
