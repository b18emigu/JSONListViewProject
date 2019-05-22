package com.example.project;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
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

        ImageView image = (ImageView) item.findViewById(R.id.pyramidImage);
        new DownloadImageTask(image).execute(pyramid.getImage());

        TextView name = (TextView) item.findViewById(R.id.pyramidName);
        name.setText(pyramid.getName());

        TextView pharaoh = (TextView) item.findViewById(R.id.pyramidPharaoh);
        pharaoh.setText(pyramid.getPharaoh());

        TextView information = (TextView) item.findViewById(R.id.pyramidInformation);
        information.setText(pyramid.getInformation());

        return item;
    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }
}
