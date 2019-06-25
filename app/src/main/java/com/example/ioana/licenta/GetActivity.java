package com.example.ioana.licenta;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Checkable;
import android.widget.EditText;
import android.widget.RadioButton;
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

    EditText ET_Ingredient1, ET_Ingredient2;
    String ing1, ing2;


    String json_string;
   // Button desert, fel, supe;
    int ok = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_get);

        ET_Ingredient1 = (EditText) findViewById(R.id.et_ing1);
        ET_Ingredient2 = (EditText) findViewById(R.id.et_ing2);


      //  desert = (Button) findViewById(R.id.button2);
      //  fel = (Button) findViewById(R.id.button3);
      //  supe = (Button) findViewById(R.id.button4);

    }


    public void getJSON(View view) {
        if (ok == 0)
            Toast.makeText(GetActivity.this, "Selecteaza categoria", Toast.LENGTH_SHORT).show();
        else {

            new BackgroundTask().execute();

        }
    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch (view.getId()) {
            case R.id.desertradio:
                if (checked) {
                    ok = 1;
                    Toast.makeText(GetActivity.this, "S-a selectat categoria Desert", Toast.LENGTH_SHORT).show();
                    break;
                }

            case R.id.felprincipalradio:
                if (checked) {
                    ok = 2;
                    Toast.makeText(GetActivity.this, "S-a selectat categoria Fel Principal", Toast.LENGTH_SHORT).show();
                    break;
                }

            case R.id.superadio:
                if (checked) {
                    ok = 3;
                    Toast.makeText(GetActivity.this, "S-a selectat categoria Supe", Toast.LENGTH_SHORT).show();
                    break;

                }
        }

    }


    class BackgroundTask extends AsyncTask<Void, Void, String>

    {
        String JSON_STRING;
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
                System.out.println(ing1);
                System.out.println(ing2);
                json_url += "?ing1=" + ing1 + "&ing2=" + ing2;
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

            } catch (MalformedURLException e) {
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
            TextView textView = (TextView) findViewById(R.id.txtview);
            textView.setText(result);
            json_string = result;

        }


    }

    public void parseJSON(View view) {
        if (json_string == null) {
            Toast.makeText(getApplicationContext(), "First get JSON", Toast.LENGTH_LONG).show();
        } else {
            Intent intent = new Intent(this, DisplayListView.class);
            intent.putExtra("json_data", json_string);
            startActivity(intent);

        }
    }

    public void salveaza(View view) {

        if (ok == 0)
            Toast.makeText(GetActivity.this, "Selecteaza categoria", Toast.LENGTH_SHORT).show();

        else {

            ing1 = ET_Ingredient1.getText().toString();
            ing2 = ET_Ingredient2.getText().toString();
        }


    }

    public void onCheckboxClicked(View view) {

        // Is the view now checked?
        boolean checked = ((Checkable) view).isChecked();

        // Check which checkbox was clicked
        switch (view.getId()) {
            case R.id.checkbox:
                if (checked) {
                    ing1 = ET_Ingredient1.getText().toString();
                    ing2 = ET_Ingredient2.getText().toString();

                } else {
                    Toast.makeText(GetActivity.this, "Selecteaza categoria", Toast.LENGTH_SHORT).show();

                }
            break;
        }

    }
}