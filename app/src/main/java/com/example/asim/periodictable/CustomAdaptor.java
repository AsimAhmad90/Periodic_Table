package com.example.asim.periodictable;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by asim on 4/18/2017.
 */

public class CustomAdaptor extends ArrayAdapter {
    Context context;
    String[] array;
    int[] imgcolor = {R.color.LightGreen,R.color.orange,R.color.Yellow,R.color.Blue,R.color.DarkGreenish,R.color.WaterishBlue,
                    R.color.LightMaroon,R.color.Grey,R.color.PinkishOrange,R.color.Purple};
    public CustomAdaptor(Context context, String[] array) {
        super(context, R.layout.customlayout,array);
        this.array = array;
        this.context = context;
    }


    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.customlayout,null);
        TextView textView = (TextView) view.findViewById(R.id.tvcustom);
        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.linLayout);
         linearLayout.setBackgroundResource(imgcolor[position]);

        textView.setText(array[position]);

        return view;

    }
}
