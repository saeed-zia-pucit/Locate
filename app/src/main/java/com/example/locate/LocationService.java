package com.example.locate;


import android.app.Service;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.telephony.SmsManager;
import android.util.Log;
import android.widget.Toast;

import androidx.core.content.ContextCompat;

import java.util.Timer;

public class LocationService extends Service implements LocationListener {

    boolean isGPSEnable = false;
    boolean isNetworkEnable = false;
    double latitude,longitude;
    LocationManager locationManager;
    Location location;
    private Handler mHandler = new Handler();
    private Timer mTimer = null;
    long notify_interval = 1000;
    public static String str_receiver = "servicetutorial.service.receiver";
    String address;
    public LocationService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {


        // Let it continue running until it is stopped.
        Toast.makeText(this, "Trying to get your location", Toast.LENGTH_LONG).show();
       // SmsManager smgr = SmsManager.getDefault();
        //smgr.sendTextMessage("03024855393",null,"started",null,null);
        if(intent.getExtras().containsKey("address")) {
            address = intent.getStringExtra("address");
        }
        fn_getlocation();
        return START_STICKY;
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
       // Toast.makeText(this, "Service Destroyed", Toast.LENGTH_LONG).show();
    }
    private void fn_getlocation(){

        locationManager = (LocationManager)getApplicationContext().getSystemService(LOCATION_SERVICE);
        isGPSEnable = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        isNetworkEnable = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);


        if (!isGPSEnable && !isNetworkEnable){

        }else {

            if ((ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)) {

                if (isNetworkEnable) {
                    location = null;
                    locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 0, this);
                    if (locationManager != null) {
                        location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                        if (location != null) {

                            Log.e("latitude", location.getLatitude() + "");
                            Log.e("longitude", location.getLongitude() + "");

                            latitude = location.getLatitude();
                            longitude = location.getLongitude();
                            //Toast.makeText(getApplicationContext(), latitude+"", Toast.LENGTH_SHORT).show();
//                            try{Intent intent=Intent.getIntent("location");}
//                            catch (Exception e){
//
//                            }
                            fn_update(location);
                        }
                    }

                }

            }
            if (isGPSEnable){

                location = null;
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,1000,0,this);
                if (locationManager!=null){
                    location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                    if (location!=null){
                        Log.e("latitude",location.getLatitude()+"");
                        Log.e("longitude",location.getLongitude()+"");
                        latitude = location.getLatitude();
                        longitude = location.getLongitude();


                        fn_update(location);
                    }
                }
            }
            else{
                Toast.makeText(getApplicationContext(), "please Enable your GPS", Toast.LENGTH_SHORT).show();

            }


        }

    }
    private void fn_update(Location location){

        if(location.getLongitude()!=0) {
            SmsManager smgr = SmsManager.getDefault();
            smgr.sendTextMessage(address, null, "'" + location.getLatitude() + "," + location.getLongitude(), null, null);
            Toast.makeText(getApplicationContext(), ("'" + location.getLatitude() + "," + location.getLongitude()).substring(1), Toast.LENGTH_SHORT).show();

        }
        stopSelf();
    }

    public  String getLocation(){
        return "";
    }

    @Override
    public void onLocationChanged(Location location) {
       // Toast.makeText(getApplicationContext(), latitude+"location change", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
