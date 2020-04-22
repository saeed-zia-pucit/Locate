package com.example.locate;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MyListAdapter extends ArrayAdapter<String> {

    private final Activity context;
    private final ArrayList<String> maintitle;
    private final ArrayList<String> subtitle;
    //int lyout;

    public MyListAdapter(Activity context, ArrayList<String> maintitle, ArrayList<String> subtitle,int layout) {
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

        titleText.setText(maintitle.get(position));

        subtitleText.setText(subtitle.get(position));


        Button deleteButton = (Button)view.findViewById(R.id.allow);

        deleteButton.setOnClickListener(
                new Button.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //confirmDelete();
                        maintitle.remove(position);
                        subtitle.remove(position);
                        notifyDataSetChanged();

           // TextView ph = (TextView) ((View) v.getParent()).findViewById(R.id.manager_name);
            ///TextView name = (TextView) ((View) v.getParent()).findViewById(R.id.manager_phone);
          //  String s=tv_id.getText().toString();
           //DatabaseHelper databaseHelper=new DatabaseHelper(context);
           //databaseHelper.deleteContact(new Contact(name.getText().toString(),ph.getText().toString()));
           //adapter.notifyDataSetChanged();
                    }
                }
        );

        return rowView;

    };
}
