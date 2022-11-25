package com.example.timetoshare;

import android.net.Uri;
import android.widget.Button;

public class ItemSendMessage {

    Uri image;
    Button button;

    public ItemSendMessage(Uri image) {
        this.image = image;
    }

    public Uri getImage() {
        return image;
    }

    public void setImage(Uri image) {
        this.image = image;
    }

    public Button getButton() {
        return button;
    }

    public void setButton(Button button) {
        this.button = button;
    }
}
