package com.example.ioana.licenta;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class GetActivity extends AppCompatActivity {

    String JSON_STRING;
    Button desert, fel, supe;
    int ok=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_get);


        desert = (Button) findViewById(R.id.button2);
        fel = (Button) findViewById(R.id.button3);
        supe = (Button) findViewById(R.id.button4);


        desert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ok=1;
                Toast.makeText(GetActivity.this, "S-a selectat categoria Desert", Toast.LENGTH_SHORT).show();
            }
        });

        fel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ok=2;
                Toast.makeText(GetActivity.this, "S-a selectat categoria Fel Principal", Toast.LENGTH_SHORT).show();
            }
        });

        supe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ok=3;
                Toast.makeText(GetActivity.this, "S-a selectat categoria Supe", Toast.LENGTH_SHORT).show();
            }
        });

    }




    public void getJSON(View view) {
        if(ok==0)
            Toast.makeText(GetActivity.this, "Selecteaza categoria", Toast.LENGTH_SHORT).show();
        else
    new BackgroundTask().execute();

    }
    public void parseJSON(View view) {

    }


    class BackgroundTask extends AsyncTask<Void, Void, String>

    {
        String json_url;

        @Override
        protected void onPreExecute() {

            if (ok == 1)
                json_url = "https://licentaioana1.000webhostapp.com/json_get_data_Desert.php";
            if (ok == 2)
                json_url = "https://licentaioana1.000webhostapp.com/json_get_data_Fel_Principal.php";
            if (ok == 3)
                json_url = "https://licentaioana1.000webhostapp.com/json_get_data_supe.php";


        }


        @Override
        protected String doInBackground(Void... voids) {


            try {
                URL url = new URL(json_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder stringBuilder = new StringBuilder();


                while ((JSON_STRING = bufferedReader.readLine()) != null) {
                    stringBuilder.append(JSON_STRING + "\n");

                }

                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return stringBuilder.toString().trim();

            }
            catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }


        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String result) {
            TextView textView=(TextView)findViewById(R.id.txtview);
            textView.setText(result);
        }


    }

}