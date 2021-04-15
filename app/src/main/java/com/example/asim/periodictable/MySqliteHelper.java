package com.example.asim.periodictable;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static com.example.asim.periodictable.R.id.LL;
import static com.example.asim.periodictable.R.id.tv1;
import static com.example.asim.periodictable.R.id.tv2;
import static com.example.asim.periodictable.R.id.tv3;
import static com.example.asim.periodictable.R.id.tv4;

/**
 * Created by asim on 4/23/2017.
 */

public class MySqliteHelper    extends SQLiteOpenHelper {

    public static final String DB_NAME = "mydb.sqlite";
    public static final String DB_LOCATION = "/data/data/com.example.asim.periodictable/databases/";



    private Context mContext;
    private SQLiteDatabase mDatabase;

    public MySqliteHelper(Context context) {
        super(context, DB_NAME, null, 1);
        mContext = context;
    }

    //////////////////////OPEN DATABASE/////////////////////////////////////////////
    public void openDataBase() {
        String dbPath = mContext.getDatabasePath(DB_NAME).getPath();
        if (mDatabase != null && mDatabase.isOpen()) {

            return;

        }
        mDatabase = SQLiteDatabase.openDatabase(dbPath, null, SQLiteDatabase.OPEN_READWRITE);
    }

    ///////////////////////////CLOSE DATABASE//////////////////////////////////////////////////////
    public void closeDataBase() {
        if (mDatabase != null) {
            mDatabase.close();
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    public ArrayList<String[]> getClasses() {

        SQLiteDatabase db = this.getReadableDatabase();
        openDataBase();
        Cursor cursor = db.rawQuery(" SELECT *FROM ClassTable ", null);
        String[] cnames = new String[cursor.getCount()];
        String[] ccolors = new String[cursor.getCount()];
        int i = 0;
        while (cursor.moveToNext()) {

            cnames[i] = cursor.getString(1);
            ccolors[i] = cursor.getString(2);
            i++;

        }

        ArrayList<String[]> list = new ArrayList<>();
        list.add(cnames);
        list.add(ccolors);
        return list;
    }

    public ArrayList getID(String name) {

        SQLiteDatabase db = this.getReadableDatabase();
        openDataBase();
        Cursor cursor = db.rawQuery(" Select c_id from ClassTable Where classname  =? ", new String[]{name});

        ArrayList<String[]> list = new ArrayList<>();
        int id = 0;
        if (cursor.moveToNext()) {
            id = cursor.getInt(0);
            Cursor cursor1 = db.rawQuery(" Select  *from ClassElement Where c_id  =? ", new String[]{String.valueOf(id)});
            String[] ename = new String[cursor1.getCount()];
            String[] symbol = new String[cursor1.getCount()];
            String[] atomicn = new String[cursor1.getCount()];
            String[] atomicw = new String[cursor1.getCount()];
            int i = 0;

            while (cursor1.moveToNext()) {

                ename[i] = cursor1.getString(2);
                symbol[i] = cursor1.getString(3);
                atomicn[i] = cursor1.getString(4);
                atomicw[i] = cursor1.getString(5);

//                Toast.makeText(mContext, cursor1.getString(2) + "  " + cursor1.getString(3) + "  " + cursor1.getString(4) + "  "
                //                      + cursor1.getString(5) + "  ", Toast.LENGTH_SHORT).show();

                i++;

            }

            list.add(ename);
            list.add(symbol);
            list.add(atomicn);
            list.add(atomicw);


        }
        return list;

    }
}



