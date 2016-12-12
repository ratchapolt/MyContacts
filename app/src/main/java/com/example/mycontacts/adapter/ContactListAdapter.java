package com.example.mycontacts.adapter;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mycontacts.R;
import com.example.mycontacts.model.Contact;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by User on 6/11/2559.
 */

public class ContactListAdapter extends ArrayAdapter<Contact> {
    private Context mContext;
    private int mLayoutResId;
    private ArrayList<Contact> mContactList;

    public ContactListAdapter(Context context, int resource, ArrayList<Contact> contactList) {
        super(context, resource, contactList);

        this.mContext = context;
        this.mLayoutResId = resource;
        this.mContactList = contactList;

    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View itemLayout = convertView;

        if (itemLayout == null){
            itemLayout = View.inflate(mContext, mLayoutResId, null);
        }

        ImageView imageView = (ImageView) itemLayout.findViewById(R.id.image);
        TextView nameTextView = (TextView) itemLayout.findViewById(R.id.text);
        TextView phoneTextView = (TextView) itemLayout.findViewById(R.id.phone);

        Contact contact = mContactList.get(position );

        String contactName = contact.getName();
        nameTextView.setText(contact.getName());

        String contactPhone = contact.getPhone();
        phoneTextView.setText(contact.getPhone());

        String contactImage = contact.getImage();

        AssetManager am = mContext.getAssets();
        try {
            InputStream stream = am.open(contactImage);
            Drawable imageDrawable = Drawable.createFromStream(stream, null);
            imageView.setImageDrawable(imageDrawable);

        } catch (IOException e) {
            Log.e("ContactListAdapter"," Error open image file: "+ contactImage);
            e.printStackTrace();
        }



       /* if ("android".equals(contactName)) {
            imageView.setImageResource(R.mipmap.ic_launcher);
        } else if ("ios".equals(contactName)) {
            imageView.setImageResource(R.drawable.ic_ios);
        } else if ("windows".equals(contactName)) {
            imageView.setImageResource(R.drawable.ic_windows);
        } else if ("linux".equals(contactName)) {
            imageView.setImageResource(R.drawable.ic_linux);
        }*/




        return itemLayout;
    }

    @Override
    public int getCount() {
        return 30;
    }
}
