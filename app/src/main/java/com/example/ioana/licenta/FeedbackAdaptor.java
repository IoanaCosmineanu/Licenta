package com.example.ioana.licenta;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class FeedbackAdaptor extends ArrayAdapter {

    List list =new ArrayList();

    public FeedbackAdaptor(Context context, int resource) {
        super(context, resource);
    }


 //   @Override
    public void add( Feedbackuri object) {
        super.add(object);
        list.add(object);
    }

    @Override
    public int getCount() {

            return list.size();
    }


    @Override
    public Object getItem(int position) {
        return list.get(position);
    }



    @Override
    public View getView(int position, View convertView,  ViewGroup parent) {

        View row;
        row=convertView;

      //  RetetaAdapter.RetetaHolder retetaHolder;
        FeedbackAdaptor.FeedbackHolder feedbackHolder;
        if(row == null)
        {
            LayoutInflater layoutInflater=(LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            row=layoutInflater.inflate(R.layout.row_layout,parent,false);

          //  retetaHolder=new RetetaAdapter.RetetaHolder();
            feedbackHolder=new FeedbackAdaptor.FeedbackHolder();

          //  retetaHolder.tx_numereteta=(TextView) row.findViewById(R.id.tx_numereteta);

            feedbackHolder.tx_numereteta=(TextView)row.findViewById(R.id.tx_numereteta);

            //  retetaHolder.tx_nr=(TextView) row.findViewById(R.id.tx_nr);
            //  retetaHolder.tx_elemente=(TextView) row.findViewById(R.id.tx_elemente);
            //  retetaHolder.tx_instructiuni=(TextView) row.findViewById(R.id.tx_instructiuni);

           // row.setTag(retetaHolder);
            row.setTag(feedbackHolder);
    }
        else
        {
           // retetaHolder=(RetetaAdapter.RetetaHolder)row.getTag();
            feedbackHolder=(FeedbackAdaptor.FeedbackHolder)row.getTag();
        }

     //   Retete retete=(Retete) this.getItem(position);
        Feedbackuri feedbackuri=(Feedbackuri)this.getItem(position);

      //  retetaHolder.tx_numereteta.setText(retete.getNumereteta());

         feedbackHolder.tx_numereteta.setText(feedbackuri.getTitlu());

        //  retetaHolder.tx_nr.setText(retete.getNr());
        //   retetaHolder.tx_elemente.setText(retete.getElemente());
        //  retetaHolder.tx_instructiuni.setText(retete.getInstructiuni());

        return row;
    }
   // static  class RetetaHolder
    static class FeedbackHolder
    {
        TextView tx_numereteta;

        //  TextView tx_nr,tx_elemente,tx_instructiuni;
    }
}
