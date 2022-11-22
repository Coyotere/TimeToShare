package com.example.timetoshare;

import android.widget.Button;

public class ItemSendMessage {

    int image;
    Button button;

    public ItemSendMessage(int image) {
        this.image = image;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public Button getButton() {
        return button;
    }

    public void setButton(Button button) {
        this.button = button;
    }
}
