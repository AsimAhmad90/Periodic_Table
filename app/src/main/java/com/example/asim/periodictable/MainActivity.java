package com.example.asim.periodictable;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import static android.R.attr.id;

public class MainActivity extends AppCompatActivity {

    ListView lv;
    MySqliteHelper mySqliteHelper;
    String[] cname;

//    String[] ename,symbol,atomicn,atomicw;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        mySqliteHelper = new MySqliteHelper(this);
        File dbFile = getApplicationContext().getDatabasePath(MySqliteHelper.DB_NAME);
        if (false == dbFile.exists()) {
            mySqliteHelper.getReadableDatabase();

            if (copyDataBase(this)) {
                Toast.makeText(getBaseContext(), "database succefully copied", Toast.LENGTH_SHORT).show();

            }
            else
            {
                Toast.makeText(getBaseContext(), "database  copied error", Toast.LENGTH_SHORT).show();
            }
        }

        ///////////////////////////////////////////////////////////////////////////////////////////

        lv = (ListView) findViewById(R.id.list);

        final ArrayList<String[]> list = mySqliteHelper.getClasses();

         cname = list.get(0);
        String[] ccolor = list.get(1);
        CustomAdaptor adaptor = new CustomAdaptor(MainActivity.this,cname);
       // final ArrayAdapter<String> adptr = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, array);
        lv.setAdapter(adaptor);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent i = new Intent(MainActivity.this,Main2Activity.class);
                i.putExtra("A",cname[position]);
                startActivity(i);
                mySqliteHelper.getID(cname[position]);
            }
        });
    }
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public boolean copyDataBase(Context context)
    {
        try
        {
            InputStream inputStream = context.getAssets().open(mySqliteHelper.DB_NAME);
            String outFileName = MySqliteHelper.DB_LOCATION + MySqliteHelper.DB_NAME;
            OutputStream outputStream = new FileOutputStream(outFileName);
            byte[] buff = new byte[1024];
            int length = 0;
            while((length = inputStream.read(buff)) > 0)
            {
                outputStream.write(buff,0,length);
            }
            outputStream.flush();
            outputStream.close();
            Log.d("copying database", "successfully copied");
            return  true;

        }
        catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }

}

