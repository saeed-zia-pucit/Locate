package com.example.locate;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddNewEmployee extends AppCompatActivity {
    Button request;
    EditText Name,Phone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_employee);

    request=(Button)findViewById(R.id.button);
    Name=(EditText)findViewById(R.id.name);
    Phone=(EditText)findViewById(R.id.phone);
    request.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String phone=Phone.getText().toString();
            phone=phone.substring(1);
            phone="+92"+phone;
            try{
                SmsManager smgr = SmsManager.getDefault();
                smgr.sendTextMessage(phone,null,"?"+Name.getText(),null,null);
                Toast.makeText(AddNewEmployee.this, "Request Sent Successfully", Toast.LENGTH_SHORT).show();

                DatabaseHelper databaseHelper=new DatabaseHelper(getApplicationContext());
                String temp_name="?"+Name.getText().toString();
                Contact contact=new Contact(temp_name,phone);
                databaseHelper.addContact(contact,"employe");

                //
                Phone.setText("");
                Name.setText("");
            }
            catch (Exception e){
                Toast.makeText(AddNewEmployee.this, "SMS Failed to Send, Please try again", Toast.LENGTH_SHORT).show();
            }


            //int a=databaseHelper.getContactsCount();
        }
    });
    }
}




