package gr.hua.dit.myapplication;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    BroadcastReceiver br = null;
    IntentFilter filter;
    Context myContext;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BroadcastReceiver br = new NetworkAvailability();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(br, intentFilter);

    }

    @Override
    public void onPause()    {
        try {
            if (br == null) {
                Log.d("Receiver", "Can't unregister a receiver which was never registered");
            } else {
                getApplicationContext().unregisterReceiver(br);
                br = null;
            }
        } catch(Exception err)  {
            Log.e(err.getClass().getName(), err.getMessage(), err);
            Log.e("Receiver not registered", "Couldn't get context");
        }
        super.onPause();
    }


    @Override
    public void onResume()  {
        if(br != null)  {
            Log.d("Receiver", "Can't register receiver which already has been registered");
        }   else    {
            try {
                br = new NetworkAvailability();
                filter = new IntentFilter();
                filter.addAction(CONNECTIVITY_SERVICE);
                myContext = getApplicationContext();
                myContext.registerReceiver(br, filter);
            } catch(Exception err)   {
                Log.e(err.getClass().getName(), err.getMessage(), err);
            }
        }
        super.onResume();
    }



}
