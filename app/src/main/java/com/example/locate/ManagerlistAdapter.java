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

public class ManagerlistAdapter extends ArrayAdapter<String> {

    private final Activity context;
    private final ArrayList<String> maintitle;
    private final ArrayList<String> subtitle;
    //int lyout;

    public ManagerlistAdapter(Activity context, ArrayList<String> maintitle, ArrayList<String> subtitle,int layout) {
        super(context, layout, maintitle);
        // TODO Auto-generated constructor stub
        //this.lyout=layout;
        this.context=context;
        this.maintitle=maintitle;
        this.subtitle=subtitle;


    }

    public View getView(final int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.manager_list, null,true);

        TextView titleText = (TextView) rowView.findViewById(R.id.manager_name);

        final TextView subtitleText = (TextView) rowView.findViewById(R.id.manager_phone);
        if(maintitle.get(position).startsWith("?")){
            titleText.setText(maintitle.get(position)+"?");

        }
        else{
            titleText.setText(maintitle.get(position));

        }

        subtitleText.setText(subtitle.get(position));

        Button button=(Button)rowView.findViewById(R.id.allow);
        button.setFocusable(false);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(maintitle.get(position).startsWith("?")){
                    DatabaseHelper databaseHelper=new DatabaseHelper(context);
                    databaseHelper.updateContact_Manager(new Contact(maintitle.get(position).substring(1),subtitle.get(position)));
                    //

                    SmsManager smgr = SmsManager.getDefault();
                    smgr.sendTextMessage(subtitle.get(position),null,":"+maintitle.get(position),null,null);
                    Toast.makeText(context,subtitle.get(position),Toast.LENGTH_SHORT).show();

                }

//                maintitle.remove(position);
//                subtitle.remove(position);
//                notifyDataSetChanged();
            }
        });

        return rowView;

    };
}
