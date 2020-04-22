package com.example.locate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.widget.Toast;

public class ReceiveSms extends BroadcastReceiver {

    public static final String SMS_BUNDLE = "pdus";
    String address;
    public void onReceive(Context context, Intent intent) {
        Bundle intentExtras = intent.getExtras();
        if (intentExtras != null) {
            Object[] sms = (Object[]) intentExtras.get(SMS_BUNDLE);
            String smsMessageStr = "";
            for (int i = 0; i < sms.length; ++i) {
                SmsMessage smsMessage = SmsMessage.createFromPdu((byte[]) sms[i]);

                String smsBody = smsMessage.getMessageBody().toString();
                address = smsMessage.getOriginatingAddress();

               // smsMessageStr += "SMS From: " + address + "\n";
                smsMessageStr = smsBody ;
            }
            if(smsMessageStr.startsWith(":")){ //request accepted for access
//
                DatabaseHelper databaseHelper=new DatabaseHelper(context);
                databaseHelper.updateContact(new Contact(smsMessageStr.substring(2),address));



            }
            else if(smsMessageStr.startsWith("?")){ //request for access
                String name=smsMessageStr;//add with ? in untill this contacat is accepted as manager
                DatabaseHelper databaseHelper=new DatabaseHelper(context);
                databaseHelper.addContact(new Contact(name,address),"manager");


            }
            else if(smsMessageStr.startsWith(".")){//request for location
//verifiy the its your manager list
                DatabaseHelper databaseHelper=new DatabaseHelper(context);
                if(databaseHelper.searchContact(address)){
                    Intent intent_service=new Intent(context, LocationService.class);
                    intent_service.putExtra("address",address);
                    context.startService(intent_service);

                }

                //

               // Toast.makeText(context, address, Toast.LENGTH_SHORT).show();

            }
            else if(smsMessageStr.startsWith("'")){//location received
              Intent intent1=new Intent(context,MapsActivity.class);
              intent1.putExtra("location",smsMessageStr.substring(1));//removing first character '
                intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent1);
            }
//            SmsManager smgr = SmsManager.getDefault();
//            smgr.sendTextMessage("03074841821",null,"hello",null,null);

            //this will update the UI with message
            //SmsActivity inst = SmsActivity.instance();
            //inst.updateList(smsMessageStr);
        }
    }

}
