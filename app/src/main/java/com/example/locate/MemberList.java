package com.example.locate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MemberList extends AppCompatActivity {

    ListView list;
    List<Contact> contacts;
    ArrayList<String> name_list,phone_list;
    Button allow;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_list);
//
        final DatabaseHelper databaseHelper=new DatabaseHelper(getApplicationContext());
        contacts= databaseHelper.getAllContacts("employe");
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
        final MemberlistAdapter adapter;

        adapter = new MemberlistAdapter(this, name_list, phone_list, R.layout.employe_list);


        list=(ListView)findViewById(R.id.employe_listview);
        list.setAdapter(adapter);
//        list.setFocusable(false);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // TODO Auto-generated method stub


//
//                final TextView tv_id = (TextView) view.findViewById(R.id.employe_phone);
//                String phone = tv_id.getText().toString();
//               Toast.makeText(getApplicationContext(),phone,Toast.LENGTH_SHORT).show();
//               SmsManager smgr = SmsManager.getDefault();
//                smgr.sendTextMessage(phone,null,".request for location",null,null);
//
//


            }
        });
        FloatingActionButton fab = findViewById(R.id.fab1);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Here's a Snackbar", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();

                Intent intent=new Intent(getApplicationContext(),AddNewEmployee.class);
                startActivity(intent);
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

