package com.example.ioana.licenta;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.AnimationDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class FeedbackActivity extends AppCompatActivity {

    EditText Et_titlu, Et_text;
    RatingBar ratingBar;
    String titlu;
    String text;
    String rating;
    String categorie;

    String ok = "0";
String utilizator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        Et_titlu = (EditText) findViewById(R.id.idtitlu);
        Et_text = (EditText) findViewById(R.id.textfeed);
        ratingBar = (RatingBar) findViewById(R.id.rating_bar);

        //Bundle bundle = getIntent().getExtras();
       // utilizator = bundle.getString("UtilizatorCurent");


    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch (view.getId()) {
            case R.id.desertradio:
                if (checked) {
                    ok = "1";
                    categorie = ok;
                    Toast.makeText(FeedbackActivity.this, "S-a selectat categoria Desert", Toast.LENGTH_SHORT).show();

                    break;
                }

            case R.id.felprincipalradio:
                if (checked) {
                    ok = "2";
                    categorie = ok;
                    Toast.makeText(FeedbackActivity.this, "S-a selectat categoria Fel Principal", Toast.LENGTH_SHORT).show();
                    break;
                }

            case R.id.superadio:
                if (checked) {
                    ok = "3";
                    categorie = ok;
                    Toast.makeText(FeedbackActivity.this, "S-a selectat categoria Supe", Toast.LENGTH_SHORT).show();
                    break;

                }
        }
    }
/*
    public void getfeed(View view) {

    }
*/
    public void feedback(View view) {
        startActivity(new Intent(this,GetFeedbackActivity.class));
    }

    /*
        public void parseJSONfeed(View view) {
            if (json_stringfeed == null) {
                Toast.makeText(getApplicationContext(), "First get JSON", Toast.LENGTH_LONG).show();
            } else {
                Intent intent = new Intent(this, ListFeed.class);
                intent.putExtra("json_data", json_stringfeed);
                startActivity(intent);

            }
        }
    */
    public class Backgroundtask extends AsyncTask<String, Void, String> {

        Context ctx;

        Backgroundtask(Context ctx) {

            this.ctx = ctx;
        }


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {
            String insertfeed_url;

            insertfeed_url = "https://licentaioana1.000webhostapp.com/feedback.php";

            String method = params[0];
            if (method.equals("insertfeed")) {

                String titlu = params[1];
                String categorie = params[2];
                String text = params[3];
                String rating = params[4];
                String utilizator = params[5];

                try {
                    URL url = new URL(insertfeed_url);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    OutputStream OS = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS, "UTF-8"));

                    String data = URLEncoder.encode("titlu", "UTF-8") + "=" + URLEncoder.encode(titlu, "UTF-8") + "&" +
                            URLEncoder.encode("categorie", "UTF-8") + "=" + URLEncoder.encode(categorie, "UTF-8") + "&" +
                            URLEncoder.encode("text", "UTF-8") + "=" + URLEncoder.encode(text, "UTF-8") + "&" +
                            URLEncoder.encode("rating", "UTF-8") + "=" + URLEncoder.encode(rating, "UTF-8") + "&" +
                            URLEncoder.encode("utilizator", "UTF-8") + "=" + URLEncoder.encode(utilizator, "UTF-8");


                    bufferedWriter.write(data);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    OS.close();
                    InputStream IS = httpURLConnection.getInputStream();
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
        protected void onPostExecute(String result) {

            Toast.makeText(ctx, result, Toast.LENGTH_LONG).show();


        }
    }
    /*
    public void btnStartCamera(View view) {

        Toast.makeText(this,utilizator,Toast.LENGTH_LONG).show();
    }

*/
    /*
    public void btnOpenGalery(View view) {
        startActivity(new Intent(this,GetFeedbackActivity.class));

    }
    */

    public void insertfeed(View view) {


        if (ok == "0")
            Toast.makeText(FeedbackActivity.this, "Selecteaza categoria", Toast.LENGTH_SHORT).show();

        else {

            titlu = Et_titlu.getText().toString();
            categorie=ok;
            text = Et_text.getText().toString();
            rating = String.valueOf(ratingBar.getRating());
            Bundle bundle = getIntent().getExtras();
            utilizator = bundle.getString("UtilizatorCurent");

            String method = "insertfeed";
          Backgroundtask backgroundtask = new Backgroundtask(this);
           backgroundtask.execute(method, titlu, categorie, text, rating, utilizator);
            finish();
        }
    }


}

