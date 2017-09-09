package com.ncopado.petagram.pojo;

/**
 * Created by ncopado on 02/09/17.
 */

public class Pet {

    private  String Name;
    private  int Reiting;
    private  int Photo;


    public Pet(String name, int reiting, int photo) {
        Name = name;
        Reiting = reiting;
        Photo = photo;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getReiting() {
        return Reiting;
    }

    public void setReiting(int reiting) {
        Reiting = reiting;
    }

    public int getPhoto() {
        return Photo;
    }

    public void setPhoto(int photo) {
        Photo = photo;
    }
}
