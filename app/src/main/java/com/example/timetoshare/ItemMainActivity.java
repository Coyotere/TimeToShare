package com.example.timetoshare;

public class ItemMainActivity {

    String name;
    int numMember;
    int image;
    Boolean needMessage;
    int active;

    public ItemMainActivity(String name, int numMember, int image, Boolean needMessage, int active) {
        this.name = name;
        this.numMember = numMember;
        this.image = image;
        this.needMessage = needMessage;
        this.active = active;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumMember() {
        return numMember;
    }

    public void setNumMember(int numMember) {
        this.numMember = numMember;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public Boolean getNeedMessage() {
        return needMessage;
    }

    public void setNeedMessage(Boolean needMessage) {
        this.needMessage = needMessage;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }
}
