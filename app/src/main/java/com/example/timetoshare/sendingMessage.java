package com.example.timetoshare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

public class sendingMessage extends AppCompatActivity {

    SharedPreferences db;
    private static final String PERMS = Manifest.permission.READ_EXTERNAL_STORAGE;
    private static final int RC_IMAGE_PERMS = 100;
    private static final int RC_CHOOSE_PHOTO = 200;
    private Uri uriImageSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sending_message);

        RecyclerView recyclerView = findViewById(R.id.recyclerViewSM);

        List<ItemSendMessage> pictures = new ArrayList<ItemSendMessage>();
        pictures.add(new ItemSendMessage(R.drawable.dog));
        pictures.add(new ItemSendMessage(R.drawable.dragon));
        pictures.add(new ItemSendMessage(R.drawable.chicken));
        pictures.add(new ItemSendMessage(R.drawable.dog));
        pictures.add(new ItemSendMessage(R.drawable.dragon));
        pictures.add(new ItemSendMessage(R.drawable.chicken));
        pictures.add(new ItemSendMessage(R.drawable.dog));
        pictures.add(new ItemSendMessage(R.drawable.dragon));
        pictures.add(new ItemSendMessage(R.drawable.chicken));

        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(new sendMessageAdapter(getApplicationContext(), pictures));

        db = getSharedPreferences(getIntent().getExtras().getString("groupName"), Context.MODE_PRIVATE);

        final TextView nMembersSM = (TextView) findViewById(R.id.nMembersSM);
        nMembersSM.setText(getMembersSM() + " membres");

        final TextView titleSM = (TextView) findViewById(R.id.titleSM);
        titleSM.setText(getTitleSM());

        final ImageView imgSM = (ImageView) findViewById(R.id.iconeSM);
        imgSM.setImageResource(getImageSM());

        final TextView textSM = (TextView) findViewById(R.id.textSM);
        textSM.setText(getTextSM());

        final ImageButton addButton = (ImageButton) findViewById(R.id.addSM);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accessPicturesSM();
            }
        });

        final ImageButton sendButton = (ImageButton) findViewById(R.id.sendSM);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requestSendSM();
            }
        });

        final ImageButton leaveButton = (ImageButton) findViewById(R.id.leaveManager);
        leaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    public int getMembersSM() {
        return db.getInt("numberMembers", -1);
    }
    public String getTitleSM() {
        return getIntent().getExtras().getString("groupName");
    }

    public int getImageSM() {
        return db.getInt("image", 0);
    }

    public String getTextSM() {
        return db.getString("message", "Error");
    }

    public void setRandomImagesSM() {

    }

    public void requestSendSM() {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        this.handleResponse(requestCode, resultCode, data);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @AfterPermissionGranted(RC_IMAGE_PERMS)
    public void accessPicturesSM() {
        if (!EasyPermissions.hasPermissions(this, PERMS)) {
            EasyPermissions.requestPermissions(this, getString(R.string.popup_title_permission_files_access), RC_IMAGE_PERMS, PERMS);
            return;
        }
        Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(i, RC_CHOOSE_PHOTO);
    }

    // Handle activity response (after user has chosen or not a picture)
    private void handleResponse(int requestCode, int resultCode, Intent data){
        ImageView imageView = (ImageView) findViewById(R.id.iconeSM);
        if (requestCode == RC_CHOOSE_PHOTO) {
            if (resultCode == RESULT_OK) { //SUCCESS
                this.uriImageSelected = data.getData();
                Glide.with(this) //SHOWING PREVIEW OF IMAGE
                        .load(this.uriImageSelected)
                        .apply(RequestOptions.circleCropTransform())
                        .into(imageView);
            } else {
                Toast.makeText(this, getString(R.string.toast_title_no_image_chosen), Toast.LENGTH_SHORT).show();
            }
        }
    }

}