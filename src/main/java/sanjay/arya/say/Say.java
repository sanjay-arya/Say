package sanjay.arya.say;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by sanjay on 11/23/15.
 */
public class Say extends SQLiteOpenHelper {

    static String DB_PATH;
    static String DB_NAME;

    static SQLiteDatabase say;

    static Context mContext;

    public static void initialize(Context context, String Database_Name, int Version) {
        new Say(context, Database_Name, 1);
    }

    Say(Context context, String Database_Name, int Version) {
        super(context, Database_Name, null, Version);
        mContext = context;

        DB_PATH = mContext.getDatabasePath(Database_Name).toString();
        DB_NAME = Database_Name;

        File file = new File(DB_PATH);

        if (!file.exists()) {
            try {

                InputStream inputStream = mContext.getAssets().open(DB_NAME);

                Log.i(" DB inputStream : ", DB_NAME);
                Log.i(" DB outfilename : ", DB_PATH);

                OutputStream outputStream = new FileOutputStream(DB_PATH);

                Log.i(" DB outputStream : ", DB_PATH);

                // transfer bytes from the inputfile to the outputfile
                byte[] buffer = new byte[1024];
                int length;

                while ((length = inputStream.read(buffer)) > 0) {
                    outputStream.write(buffer, 0, length);
                }

                Log.i(" DB copy success : ", DB_PATH);

                outputStream.flush();
                outputStream.close();
                inputStream.close();

                say = SQLiteDatabase.openDatabase(DB_PATH, null, SQLiteDatabase.OPEN_READWRITE);
                Log.i("DataBase", "Opening DataBase success");

            } catch (Exception e) {
                Log.e("Say Error", e.getMessage());
            }
        } else {
            say = SQLiteDatabase.openDatabase(DB_PATH, null, SQLiteDatabase.OPEN_READWRITE);
            Log.i("DataBase", "Opening DataBase success");
        }


    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
