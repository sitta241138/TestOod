package com.example.khowoatt.test;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.khowoatt.test.R;
import com.squareup.picasso.Picasso;

/**
 * Created by khowoatt on 19/7/2560.
 */

public class AdapterCake extends BaseAdapter{
    private Context objContext;
    private String[] foodStrings,sourceStrings,priceString;

    public AdapterCake(Context objContext, String[] foodStrings, String[] sourceStrings, String[] priceString) {
        this.objContext = objContext;
        this.foodStrings = foodStrings;
        this.sourceStrings = sourceStrings;
        this.priceString = priceString;
    }

    @Override
    public int getCount() {
        return foodStrings.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater objLayoutInflater = (LayoutInflater) objContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = objLayoutInflater.inflate(R.layout.listview_pastry,parent,false);

        TextView nameTextView = (TextView) view.findViewById(R.id.texttext);
        TextView priceTextView = (TextView) view.findViewById(R.id.texd);
        ImageView foodImageView = (ImageView) view.findViewById(R.id.imvlivPastry);

        Picasso.with(objContext).load(sourceStrings[position]).into(foodImageView);
        nameTextView.setText(foodStrings[position]);
        priceTextView.setText(priceString[position]);
        return view;

    }
}
