package com.example.ioana.licenta;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
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

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Get the selected item text from ListView

                Retete selectedItem = (Retete) parent.getItemAtPosition(position);
                String s = selectedItem.toString();
                Intent i = new Intent(DisplayListView.this,RetetaDetailsActivity.class);
                i.putExtra("extras", s);
                startActivity(i);

                // Display the selected item text on TextView
                //tv.setText("Your favorite : " + selectedItem);
            }
        });




        json_string=getIntent().getExtras().getString("json_data");

        try {
            json_string= json_string.replaceAll("\n", "\\n");
            jsonObject=new JSONObject(json_string);

//jsonArray=jsonObject.getJSONArray("response");
            jsonArray  = new JSONObject(json_string).getJSONArray("server_response");
       int count=0;

            String numereteta;
          String nr,elemente,instructiuni;

       while(count<jsonArray.length())
       {
JSONObject JO=jsonArray.getJSONObject(count);

        numereteta=JO.getString("numereteta");
        nr=JO.getString("nr");
       elemente=JO.getString("elemente");
       instructiuni=JO.getString("instructiuni");

       Retete retete=new Retete(numereteta,nr,elemente,instructiuni);
           //Retete retete=new Retete(numereteta);

      retetaAdapter.add(retete);

        count++;

       }


        } catch (JSONException e) {
            e.printStackTrace();
        }

    }



}
