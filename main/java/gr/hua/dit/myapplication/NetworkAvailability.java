package gr.hua.dit.myapplication;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;


public class NetworkAvailability extends BroadcastReceiver {
    Intent service;


    @Override
    public void onReceive(Context context, Intent intent) {

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        //if connected to internet start service else stop it
        if (netInfo != null) {


            service = new Intent(context, LocationService.class);
            Toast.makeText(context, "Location Service Started", Toast.LENGTH_SHORT).show();
            context.startService(service);
        } else {
            context.stopService(service);
            Toast.makeText(context, "Location service  stopped,you disconnected from the internet", Toast.LENGTH_SHORT).show();
        }
    }
}

