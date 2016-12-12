package com.example.mycontacts.model;

/**
 * Created by User on 23/10/2559.
 */

public class Contact {

    private String mName;
    private String mPhone;
    private String mImage;

    public Contact(String name, String phone, String image) {
        this.mName = name;
        this.mPhone = phone;
        this.mImage = image;
    }


    public String getName() {
        return mName;
    }
    public String getPhone() {
        return mPhone;
    }
    public String getImage() {
        return mImage;
    }
}

