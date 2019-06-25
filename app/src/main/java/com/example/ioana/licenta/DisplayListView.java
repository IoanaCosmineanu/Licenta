package com.example.ioana.licenta;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DisplayListView extends AppCompatActivity {

    String json_string;
    JSONObject jsonObject;
    JSONArray jsonArray;
    RetetaAdapter retetaAdapter;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_listview_layout);

        listView=(ListView) findViewById(R.id.listview);

        retetaAdapter=new RetetaAdapter(this,R.layout.row_layout);


        listView.setAdapter(retetaAdapter);

        json_string=getIntent().getExtras().getString("json_data");

        try {
            jsonObject=new JSONObject(json_string);

jsonArray=jsonObject.getJSONArray("server_response");
       int count=0;

            String numereteta,nr,elemente,instructiuni;

       while(count<jsonArray.length())
       {
JSONObject JO=jsonArray.getJSONObject(count);

        numereteta=JO.getString("numereteta");
        nr=JO.getString("nr");
        elemente=JO.getString("elemente");
        instructiuni=JO.getString("instructiuni");

        Retete retete=new Retete(numereteta,nr,elemente,instructiuni);

      retetaAdapter.add(retete);

        count++;

       }


        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
