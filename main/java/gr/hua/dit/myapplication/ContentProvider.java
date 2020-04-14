package gr.hua.dit.myapplication;

import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class ContentProvider extends android.content.ContentProvider {
    private UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
    private static String AUTHORITY = "gr.hua.dit.myapplication";

    DBCon dbHelper ;
    @Override
    public boolean onCreate() {
        matcher.addURI(AUTHORITY,"locations",1);
        return false;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] strings, @Nullable String s, @Nullable String[] strings1, @Nullable String s1) {

        return null;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        dbHelper = new DBCon(getContext());
        SQLiteDatabase db = dbHelper.getWritableDatabase();

               long id = db.insert(DBCon.TABLE,null,contentValues);


                //way to push my data to firebase but it pushes previous data again,so i have the same data multiple times
                //ArrayList<String> users = new ArrayList<>();
                // dbHelper.sendDatatoFireBase(users);

        Uri.parse(uri.toString()+"/"+id);
        return uri;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }
}
