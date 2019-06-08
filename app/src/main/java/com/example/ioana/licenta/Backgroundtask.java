package com.example.ioana.licenta;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;

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
        String insert_url="https://licentaioana1.000webhostapp.com/insert_retete.php";
        String method=params[0];
        if(method.equals("insert"))
        {
            String numereteta=params[1];
            String nr=params[2];
            String elemente=params[3];
            String instructiuni=params[4];

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
                        URLEncoder.encode("instructiuni","UTF-8")+"="+URLEncoder.encode(instructiuni,"UTF-8");

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
