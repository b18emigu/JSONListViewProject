package com.example.project;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.URL;

public class Pyramid {
    private int id, volume;
    private String pharaoh, name, dynasty, location, image;

    public Pyramid(int id, int volume, String pharaoh, String name, String dynasty, String location, String image) {
        this.id = id;
        this.volume = volume;
        this.pharaoh = pharaoh;
        this.name = name;
        this.dynasty = dynasty;
        this.location = location;
        this.image = image;
    }

    public Pyramid(int id) {
        this.id = id;
    }

    public String getInformation() {
        return "Pyramiden är från den " + dynasty + " dynastyin, den finns i " + location + " och har en storlek av " + volume + " kubikmeter!";
    }

    public String getImage() {
        return image;
    }

    public int getId() {
        return id;
    }

    public int getVolume() {
        return volume;
    }

    public String getPharaoh() {
        return pharaoh;
    }

    public String getName() {
        return name;
    }
    public String getDynasty() {
        return dynasty;
    }

    public String getLocation() {
        return location;
    }
}
