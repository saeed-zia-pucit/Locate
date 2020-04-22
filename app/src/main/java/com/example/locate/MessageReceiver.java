package com.example.locate;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.telephony.SmsManager;
import android.widget.Toast;

public class MessageReceiver extends Service {
    /**
     * Command to the service to display a message
     */
    static final int MSG_SAY_HELLO = 1;

    /**
     * Handler of incoming messages from clients.
     */
    static class IncomingHandler extends Handler {
        private Context applicationContext;

        IncomingHandler(Context context) {
            applicationContext = context.getApplicationContext();
        }

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_SAY_HELLO:
                    try{
                        SmsManager smgr = SmsManager.getDefault();
                        smgr.sendTextMessage("03024855393",null,"Request ",null,null);
                        ///Toast.makeText(AddNewEmployee.this, "SMS Sent Successfully", Toast.LENGTH_SHORT).show();
                    }
                    catch (Exception e){
                        ///Toast.makeText(AddNewEmployee.this, "SMS Failed to Send, Please try again", Toast.LENGTH_SHORT).show();
                    }

                    Toast.makeText(applicationContext, "hello", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    //super.handleMessage(msg);
                    Toast.makeText(applicationContext, "hello default!", Toast.LENGTH_SHORT).show();

            }
        }
    }

    /**
     * Target we publish for clients to send messages to IncomingHandler.
     */
    Messenger mMessenger;

    /**
     * When binding to the service, we return an interface to our messenger
     * for sending messages to the service.
     */
    @Override
    public IBinder onBind(Intent intent) {
        Toast.makeText(getApplicationContext(), "binding", Toast.LENGTH_SHORT).show();
        mMessenger = new Messenger(new IncomingHandler(this));
        return mMessenger.getBinder();
    }
}
