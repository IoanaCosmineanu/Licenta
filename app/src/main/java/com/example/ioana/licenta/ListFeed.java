package com.example.ioana.licenta;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ListFeed extends AppCompatActivity {

    String json_stringfeed;
    JSONObject jsonObjectf;
    JSONArray jsonArrayf;

    FeedbackAdaptor feedbackAdaptor;

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_feed);

        listView=(ListView) findViewById(R.id.listview);


        feedbackAdaptor=new FeedbackAdaptor(this,R.layout.row_layout);



        listView.setAdapter(feedbackAdaptor);


listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Feedbackuri selectedItem=(Feedbackuri)parent.getItemAtPosition(position);
        String st=selectedItem.toString();
        Intent in=new Intent(ListFeed.this,FeedbackDetails.class);
        in.putExtra("extrasfeed",st);
        startActivity(in);

    }
});



        json_stringfeed=getIntent().getExtras().getString("json_data");

        try {
            json_stringfeed= json_stringfeed.replaceAll("\n", "\\n");
            jsonObjectf=new JSONObject(json_stringfeed);


            jsonArrayf  = new JSONObject(json_stringfeed).getJSONArray("server_response");
            int count=0;

            String titlu;
            String categorie,text,rating,utilizator;

            while(count<jsonArrayf.length())
            {
                JSONObject JOF=jsonArrayf.getJSONObject(count);

                titlu=JOF.getString("titlu");
                categorie=JOF.getString("categorie");
                text=JOF.getString("text");
                rating=JOF.getString("rating");
               utilizator=JOF.getString("utilizator");

               Feedbackuri feedbackuri=new Feedbackuri(titlu,categorie,text,rating,utilizator);

               feedbackAdaptor.add(feedbackuri);

                count++;

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
