package gr.hua.dit.myapplication;

import android.Manifest;
import android.app.Service;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;

import android.widget.Toast;


import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.sql.Timestamp;

import androidx.core.app.ActivityCompat;


public class LocationService extends Service{
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;
    LocationManager locationManager;
    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        startLocationService();
        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {


    }

    public LocationService() {

    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public void startLocationService() {
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            //i cant call request permissionResult
            //ActivityCompat.requestPermissions(gr.hua.dit.myapplication.MainActivity,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},7);
        }

            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 10, new LocationListener() {

                @Override
                public void onLocationChanged(Location location) {
                    firebaseDatabase = FirebaseDatabase.getInstance();
                    databaseReference = firebaseDatabase.getReference();

                    double latitude = location.getLatitude();
                    double longtitude = location.getLongitude();
                    Timestamp unix_timestamp = new Timestamp(System.currentTimeMillis());
                    ContentResolver resolver = getContentResolver();
                    ContentValues contentValues = new ContentValues();
                    Uri uri = Uri.parse("content://gr.hua.dit.myapplication/locations");

                    contentValues.put("LON",longtitude);
                    contentValues.put("LAT",latitude);
                    contentValues.put("UNIX_TIMESTAMP",unix_timestamp.toString());

                    resolver.insert(uri,contentValues);

                    Toast.makeText(LocationService.this, "YOUR CURRENT LATITUDE IS:" + latitude + "AND YOUR CURRENT LONGTITUDE IS:" + longtitude, Toast.LENGTH_LONG).show();
                   //code to insert data to firebase to see if everything works

                    User user = new User();
                    user.setLongtitude(longtitude);
                    user.setLatitude(latitude);
                    user.setUnix_imestamp(unix_timestamp.toString());

                    databaseReference.push().setValue(user);


                }

                @Override
                public void onStatusChanged(String provider, int status, Bundle extras) {
                }

                @Override
                public void onProviderEnabled(String provider) {
                    Toast.makeText(LocationService.this, "GPS is enabled", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onProviderDisabled(String provider) {
                    Toast.makeText(LocationService.this, "GPS is disabled ", Toast.LENGTH_SHORT).show();
                }
            });
        }



}
