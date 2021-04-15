package com.example.asim.periodictable;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by asim on 4/24/2017.
 */

public class CustomAdaptor2 extends ArrayAdapter {
    Context context;
    String[] ename,symbol,atomicn,atomicw;
    TextView textView1,textView2,textView3,textView4;


    public CustomAdaptor2(Context context,String[] ename,String[] symbol,String[] atomicn,String[] atomicw) {

        super(context, R.layout.customlayout2,ename);

        this.context = context;
        this.ename = ename;
        this.symbol = symbol;
        this.atomicn = atomicn;
        this.atomicw = atomicw;

    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.customlayout2,null);
        TextView textView1 = (TextView) view.findViewById(R.id.tv1);
        TextView textView2 = (TextView) view.findViewById(R.id.tv2);
        TextView textView3 = (TextView) view.findViewById(R.id.tv3);
        TextView textView4 = (TextView) view.findViewById(R.id.tv4);

        textView1.setText(ename[position]);
        textView2.setText(symbol[position]);
        textView3.setText(atomicn[position]);
        textView4.setText(atomicw[position]);

        return view;
    }

}

