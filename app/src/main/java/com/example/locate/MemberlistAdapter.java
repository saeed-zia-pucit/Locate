package com.example.locate;

import android.app.Activity;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MemberlistAdapter extends ArrayAdapter<String> {

    private final Activity context;
    private final ArrayList<String> maintitle;
    private final ArrayList<String> subtitle;
    //int lyout;

    public MemberlistAdapter(Activity context, ArrayList<String> maintitle, ArrayList<String> subtitle,int layout) {
        super(context, layout, maintitle);
        // TODO Auto-generated constructor stub
        //this.lyout=layout;
        this.context=context;
        this.maintitle=maintitle;
        this.subtitle=subtitle;


    }

    public View getView(final int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.employe_list, null,true);

        TextView titleText = (TextView) rowView.findViewById(R.id.employe_name);

        final TextView subtitleText = (TextView) rowView.findViewById(R.id.employe_phone);

        titleText.setText(maintitle.get(position));
        subtitleText.setText(subtitle.get(position));

       // Button button=(Button)rowView.findViewById(R.id.delete_employe);
        Button find=(Button)rowView.findViewById(R.id.find_loc);


        find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

        final TextView tv_id = (TextView) v.findViewById(R.id.employe_phone);
        String phone= subtitle.get(position).toString();

        SmsManager smgr = SmsManager.getDefault();
        smgr.sendTextMessage(phone,null,".request for location",null,null);


            }
        });


        return rowView;

    };
    public void Clicky(int position) {
        //DatabaseHelper databaseHelper = new DatabaseHelper(context);
//                    databaseHelper.deleteContact(new Contact(maintitle.get(position), subtitle.get(position)), "employe");
//
//                    maintitle.remove(position);
//                    subtitle.remove(position);
//                    notifyDataSetChanged();
        //Toast.makeText(context,v.getId()+"else",Toast.LENGTH_SHORT).show();
        //onClicky(position);


    }
}
