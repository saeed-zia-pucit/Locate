package com.example.locate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ShareLocation extends AppCompatActivity {
Button share;
EditText phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_location);

        Intent intent=getIntent();
        final String location=intent.getStringExtra("location");
    share=(Button)findViewById(R.id.share);
    share.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            phone=findViewById(R.id.phone);
             String p=phone.getText().toString();

           try {
               SmsManager smgr = SmsManager.getDefault();
               smgr.sendTextMessage(p, null, location, null, null);
               Toast.makeText(getApplicationContext(), "Location Shared", Toast.LENGTH_LONG).show();
               phone.setText("");
           }
           catch (Exception e){
               Toast.makeText(getApplicationContext(), "SMS Failed to Send, Please try again", Toast.LENGTH_SHORT).show();
           }
        }
    });
    }
}
