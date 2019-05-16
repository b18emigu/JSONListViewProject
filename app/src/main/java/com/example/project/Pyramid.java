package com.example.project;

import android.graphics.drawable.Drawable;

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
        return "Pyramiden är från den " + dynasty + ", den finns i " + location + " och har en storlek av " + volume + " kubikmeter!";
    }

    public Drawable getImage() {
        try {
            InputStream is = (InputStream) new URL(image).getContent();
            Drawable d = Drawable.createFromStream(is, "src name");
            return d;
        } catch (Exception e) {
            return null;
        }
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
