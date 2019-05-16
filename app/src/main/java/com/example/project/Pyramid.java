package com.example.project;

public class Pyramid {
    private int id, volume;
    private String pharaoh, name, dynasty, locaiton, image;

    public Pyramid(int id, int volume, String pharaoh, String name, String dynasty, String location, String image) {
        this.id = id;
        this.volume = volume;
        this.pharaoh = pharaoh;
        this.name = name;
        this.dynasty = dynasty;
        this.locaiton = location;
        this.image = image;
    }

    public Pyramid(int id) {
        this.id = id;
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

    public String getLocaiton() {
        return locaiton;
    }

    public String getImage() {
        return image;
    }
}
