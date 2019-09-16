package com.example.myapplication;

public class RecyclerMainPageClassType {
    String image;
    String name;

    public RecyclerMainPageClassType(String name , String image){
        this.image = image;
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setName(String name) {
        this.name = name;
    }
}
