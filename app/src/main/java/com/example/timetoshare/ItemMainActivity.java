package com.example.timetoshare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

enum Active {
    BEFORE,PROGRESS,AFTER
}

public class ItemMainActivity {

    String name;
    int numMember;
    int image;
    Boolean needMessage;
    Active active;


    public ItemMainActivity(String name, int numMember, int image, Boolean needMessage, Active active) {
        this.name = name;
        this.numMember = numMember;
        this.image = image;
        this.needMessage = needMessage;
        this.active = active;
        Date currentTime = Calendar.getInstance().getTime();
        System.out.println(currentTime.toString());


    }

    public ItemMainActivity(String nameGroup, Context context) {

        this.name = nameGroup;

        SharedPreferences userData = context.getSharedPreferences(nameGroup, Context.MODE_PRIVATE);

        this.numMember = userData.getInt("numberMembers", 0);

        this.image = userData.getInt("image", 0);

        Date currentTime = Calendar.getInstance().getTime();

        //Date startingDate = DateFormat.parse();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");

        Date startingDate = null;
        try {
             startingDate = format.parse(userData.getString("startingDate", null));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println(startingDate);

        Date finalDate = null;
        try {
            finalDate = format.parse(userData.getString("finalDate", null));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Date lastRepetition = null;
        try {
            lastRepetition = format.parse(userData.getString("lastRepetition", null));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if(currentTime.after(startingDate) && currentTime.before(finalDate)){
            this.active = Active.PROGRESS;
        }
        else if(currentTime.before(startingDate)){
            this.active = Active.BEFORE;
        }
        else{
            this.active = Active.AFTER;
        }

        long diff = currentTime.getTime() - lastRepetition.getTime();
        int nbDay = (int) TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
        int repetition = userData.getInt("repetition", 0);


        System.out.println("nbDay = " + nbDay);

        this.needMessage = nbDay >= repetition && currentTime.before(finalDate);


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

    public Active getActive() {
        return active;
    }

    public void setActive(Active active) {
        this.active = active;
    }
}
