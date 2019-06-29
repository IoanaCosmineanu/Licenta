package com.example.ioana.licenta;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class GetFeedbackActivity extends AppCompatActivity {

    String json_stringfeed;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_feedback);

    }

    class BackgroundTask extends AsyncTask<Void, Void, String>

    {
        String JSON_STRINGFEED;
        String json_urlfeed;

        @Override
        protected void onPreExecute() {
            json_urlfeed = "https://licentaioana1.000webhostapp.com/new.php";
        }

        @Override
        protected String doInBackground(Void... voids) {
            try {

                URL url = new URL(json_urlfeed);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();


                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder stringBuilder = new StringBuilder();


                while ((JSON_STRINGFEED = bufferedReader.readLine()) != null) {
                    stringBuilder.append(JSON_STRINGFEED + "\n");

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
            TextView textView = (TextView) findViewById(R.id.textviewfeed);
            textView.setText(result);
            json_stringfeed = result;
        }
    }
    public void getJSONfeed(View view) {

        new GetFeedbackActivity.BackgroundTask().execute();
    }
}
