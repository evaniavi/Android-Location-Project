package gr.hua.dit.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import androidx.annotation.Nullable;

public class DBCon extends SQLiteOpenHelper{
    private static final String DB_NAME = "users.db";
    private static final int DB_VERSION = 1;
    public static final String TABLE = "USERS";
    public static final String KEY_ID = "ID";
    public static final String LAT = "LAT";
    public static final String LON = "LON";
    public static final String UNIX_TIMESTAMP = "UNIX_TIMESTAMP";

    public DBCon(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+ TABLE + " ( "+
                KEY_ID +" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                LAT + " VARCHAR(20) NOT NULL, " +
                LON + " VARCHAR(20) NOT NULL, " +
                UNIX_TIMESTAMP + " VARCHAR(20) NOT NULL "+
                ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public void sendDatatoFireBase( ArrayList<String> users){
        SQLiteDatabase db = this.getReadableDatabase();
        //String[] tableColumns = new String[]{"ID","NAME","LAST_NAME","AGE"};
        Cursor cursor = db.rawQuery("select * from Users",null);
        while(cursor.moveToNext()){
            users.add( cursor.getString(0));
            users.add( cursor.getString(1));
            users.add( cursor.getString(2));
            users.add( cursor.getString(3));
        }
        if(users.size() > 0 ){
            DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("USERS");
            for(String u : users){
                ref.push().setValue(u);
            }
        }
    }
}
