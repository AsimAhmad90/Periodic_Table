package com.example.asim.periodictable;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {

    ListView lv2;
    MySqliteHelper mySqliteHelper;
    String[] ename,symbol,atomicn,atomicw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        mySqliteHelper = new MySqliteHelper(this);
        lv2 = (ListView) findViewById(R.id.lv2);

        Intent i = getIntent();
        String a= i.getStringExtra("A");

        ArrayList<String[]> list = mySqliteHelper.getID(a);

        ename = list.get(0);
        symbol = list.get(1);
        atomicn = list.get(2);
        atomicw = list.get(3);

        CustomAdaptor2 adaptor2 = new CustomAdaptor2(Main2Activity.this,ename,symbol,atomicn,atomicw);
        lv2.setAdapter(adaptor2);
    }
}