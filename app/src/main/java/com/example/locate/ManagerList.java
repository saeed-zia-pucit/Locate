package com.example.locate;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ManagerList extends AppCompatActivity {

    ListView list;
    List<Contact> contacts;
    ArrayList<String> name_list,phone_list;
    Button allow;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_list);
//
        final DatabaseHelper databaseHelper=new DatabaseHelper(getApplicationContext());
        contacts= databaseHelper.getAllContacts("manager");
        name_list=new ArrayList<>();
        phone_list=new ArrayList<>();
        for (Contact cn : contacts)

        {
            name_list.add(cn.getName());
            phone_list.add(cn.getPhoneNumber());
            // Writing Contacts to log
            Log.d("Name: ", cn.getName());
        }
        //
        //String s="locate";
        final ManagerlistAdapter adapter;

        adapter = new ManagerlistAdapter(this, name_list, phone_list, R.layout.manager_list);


        list=(ListView)findViewById(R.id.manager_list);
        list.setAdapter(adapter);


        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // TODO Auto-generated method stub



                    //code specific to first list item
                    final TextView tv_id = (TextView) view.findViewById(R.id.manager_name);
                    String txt = tv_id.getText().toString();
                    Toast.makeText(getApplicationContext(),txt+"jyhggjg",Toast.LENGTH_SHORT).show();


            }
        });
           // allow=(Button)findViewById(R.id.allow);
//            allow.setOnClickListener(new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//            TextView ph = (TextView) ((View) v.getParent()).findViewById(R.id.manager_name);
//            TextView name = (TextView) ((View) v.getParent()).findViewById(R.id.manager_phone);
//            //String s=tv_id.getText().toString();
//            //databaseHelper.deleteContact(new Contact(name.getText().toString(),ph.getText().toString()));
//            //adapter.notifyDataSetChanged();
//        }
//    });


    }
    public  void test(){

    }
}

