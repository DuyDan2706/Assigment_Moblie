package com.example.petshop.model;

public class Cycle {
    private int id;
    private String name;
    private int imageId;

    public Cycle() {
    }

    public Cycle(int id, String name, int imageId) {
        this.id = id;
        this.name = name;
        this.imageId = imageId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", imageId=" + imageId +
                '}';
    }
}
