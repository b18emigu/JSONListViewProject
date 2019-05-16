package com.example.project;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class PyramidAdapter extends ArrayAdapter<Pyramid> {
    private Context context;
    private List<Pyramid> pyramids = new ArrayList<>();

    public PyramidAdapter(Context context, ArrayList<Pyramid> pyramids) {
        super(context, 0 , pyramids);
        this.context = context;
        this.pyramids = pyramids;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View item = convertView;
        if(item == null)
            item = LayoutInflater.from(context).inflate(R.layout.pyramid_item,parent,false);

        Pyramid pyramid = pyramids.get(position);

        // ImageView image = (ImageView) item.findViewById(R.id.pyramidImage);
        // image.setImageDrawable(pyramid.getImage());

        TextView name = (TextView) item.findViewById(R.id.pyramidName);
        name.setText(pyramid.getName());

        TextView pharaoh = (TextView) item.findViewById(R.id.pyramidPharaoh);
        pharaoh.setText(pyramid.getPharaoh());

        TextView information = (TextView) item.findViewById(R.id.pyramidInformation);
        information.setText(pyramid.getInformation());

        return item;
    }
}
